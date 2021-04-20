package me.mdbell.terranet.client;

import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.common.net.ConnectionAttributesFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ClientFactory<T extends ConnectionAttributes> {

    private static final Logger logger = LoggerFactory.getLogger(ClientFactory.class);

    private static final Class<?> defaultFactory;

    private static final String[] DEFAULT_FACTORY_CLASS_NAMES = {
            "me.mdbell.terranet.client.netty.NettyClientFactory"
    };

    private ConnectionAttributesFactory<T> attributeFactory;

    static {
        defaultFactory = getDealtFactoryClass();
    }

    private static Class<?> getDealtFactoryClass() {
        Class<?> factory = null;
        for (int i = 0; i < DEFAULT_FACTORY_CLASS_NAMES.length; i++) {
            String name = DEFAULT_FACTORY_CLASS_NAMES[i];
            try {
                factory = Class.forName(name);
                break;
            } catch (ClassNotFoundException e) {
                logger.debug("Factory {} not found, skipping!", name);
            }
        }
        return factory;
    }

    public static <T extends ConnectionAttributes> ClientFactory<T> createDefaultFactory() {
        try {
            return (ClientFactory<T>) defaultFactory.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Exception creating default client factory", e);
            throw new RuntimeException(e);
        }
    }

    public final void setAttributeFactory(ConnectionAttributesFactory<T> factory){
        this.attributeFactory = factory;
    }

    public final ClientCtx<T> connect(String host, int port) {
        ClientCtx<T> ctx = connectImpl(host, port);
        if (attributeFactory != null) {
            ctx.setAttributes(attributeFactory.newInstance());
        }
        return ctx;
    }

    protected abstract ClientCtx<T> connectImpl(String host, int port);

    public abstract void shutdown();

}
