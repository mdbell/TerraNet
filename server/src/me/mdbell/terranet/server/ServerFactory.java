package me.mdbell.terranet.server;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.net.ConnectionAttributes;

import java.lang.reflect.InvocationTargetException;

@Slf4j
public abstract class ServerFactory<T extends ConnectionAttributes> {

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
                log.debug("Factory {} not found, skipping!", name);
            }
        }
        if (factory == null) {
            log.debug("No default ServerFactory found!");
        }
        return factory;
    }

    public static <T extends ConnectionAttributes> ServerFactory<T> createDefaultFactory() {
        try {
            return (ServerFactory<T>) defaultFactoryClass.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            log.error("Unable to create defualt factory", e);
            throw new RuntimeException(e);
        }
    }

    public abstract ServerCtx<T> newInstance();

    public abstract void shutdown();
}
