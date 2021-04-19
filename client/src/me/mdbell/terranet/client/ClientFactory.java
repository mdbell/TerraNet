package me.mdbell.terranet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ClientFactory {

    private static final Logger logger = LoggerFactory.getLogger(ClientFactory.class);

    private static final String[] DEFAULT_FACTORY_CLASS_NAMES = {
            "me.mdbell.terranet.client.netty.NettyClientFactory"
    };

    private static ClientFactory defaultFactory;

    static{
        createDefaultFactory();
    }

    private static void createDefaultFactory() {
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
            logger.debug("No default ClientFactory found!");
            return;
        }
        try {
            logger.debug("Setting default factory to {}", factory.getName());
            setDefaultFactory((ClientFactory) factory.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            logger.debug("Exception loading default factory", e);
        }
    }

    public static ClientFactory getDefaultFactory(){
        return defaultFactory;
    }

    public static void setDefaultFactory(ClientFactory factory){
        defaultFactory = factory;
    }

    public abstract ClientCtx connect(String host, int port);

    public abstract void shutdown();

}
