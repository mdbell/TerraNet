package me.mdbell.terranet.server;

import me.mdbell.terranet.common.net.ConnectionAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServerFactory<T extends ConnectionAttributes> {

    private static final Logger logger = LoggerFactory.getLogger(ServerFactory.class);

    private static final String[] DEFAULT_FACTORY_CLASS_NAMES = {
            "me.mdbell.terranet.server.netty.NettyServerFactory"
    };

    private static Class<?> defaultFactoryClass;

    static {
        defaultFactoryClass = getDefaultFactoryClass();
    }

    private static Class<?> getDefaultFactoryClass() {
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
        if (factory == null) {
            logger.debug("No default ServerFactory found!");
        }
        return factory;
    }

    public static <T extends ConnectionAttributes> ServerFactory<T> createDefaultFactory() {
        try {
            return (ServerFactory<T>) defaultFactoryClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error("Unable to create defualt factory", e);
            throw new RuntimeException(e);
        }
    }

    public abstract ServerCtx<T> newInstance();

    public abstract void shutdown();
}
