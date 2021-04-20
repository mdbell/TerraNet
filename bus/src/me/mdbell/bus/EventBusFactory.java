package me.mdbell.bus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public abstract class EventBusFactory {

    private static final Logger logger = LoggerFactory.getLogger(EventBusFactory.class);

    private static final String[] DEFAULT_FACTORY_CLASS_NAMES = {
            "me.mdbell.bus.robot.RobotEventBusFactory",
            "me.mdbell.bus.internal.InternalEventBusFactory"
    };

    private static EventBusFactory defaultFactory = null;

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
            logger.debug("No default EventBusFactory found!");
            return;
        }
        try {
            logger.debug("Setting default factory to {}", factory.getName());
            setDefaultFactory((EventBusFactory) factory.getDeclaredConstructor().newInstance());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.debug("Exception loading default factory", e);
        }
    }

    public static EventBusFactory getDefaultFactory() {
        return defaultFactory;
    }

    public static void setDefaultFactory(EventBusFactory factory) {
        defaultFactory = factory;
    }

    public <T> IEventBus<T> newInstance(Class<T> clazz) {
        return newInstance(clazz.getName());
    }

    public abstract IEventBus getDefault();

    public abstract IEventBus newInstance(String name);

    public boolean exists(Class<?> clazz) {
        return exists(clazz.getName());
    }

    public abstract boolean exists(String name);

    public <T> IEventBus<T> get(Class<T> clazz) {
        return (IEventBus<T>) get(clazz.getName());
    }

    public abstract IEventBus<?> get(String name);

    public <T> IEventBus<T> getOrCreate(Class<T> clazz) {
        return (IEventBus<T>) getOrCreate(clazz.getName());
    }

    public IEventBus<?> getOrCreate(String name) {
        if (exists(name)) {
            return get(name);
        }
        return newInstance(name);
    }
}
