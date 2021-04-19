package me.mdbell.terranet.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ServerFactory {

    private static final Logger logger = LoggerFactory.getLogger(ServerFactory.class);

    private static final String[] DEFAULT_FACTORY_CLASS_NAMES = {
            "me.mdbell.terranet.server.netty.NettyServerFactory"
    };

    private static ServerFactory defaultFactory;

    static {
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
            logger.debug("No default ServerFactory found!");
            return;
        }
        try {
            logger.debug("Setting default factory to {}", factory.getName());
            setDefaultFactory((ServerFactory) factory.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            logger.debug("Exception loading default factory", e);
        }
    }

    public static ServerFactory getDefaultFactory() {
        return defaultFactory;
    }

    public static void setDefaultFactory(ServerFactory factory) {
        defaultFactory = factory;
    }

    public abstract ServerCtx bind(int port);

    public abstract void shutdown();
}
