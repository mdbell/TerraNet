package me.mdbell.bus;

import java.util.HashMap;
import java.util.Map;

public abstract class MappedEventBusFactory extends EventBusFactory {

    private final Map<String, IEventBus> busMap = new HashMap<>();

    protected final void put(String key, IEventBus bus) {
        busMap.put(key, bus);
    }

    protected abstract IEventBus newInstance();

    @Override
    public final IEventBus newInstance(String name) {
        if (exists(name)) {
            throw new IllegalStateException("Named event bus exists!");
        }
        IEventBus bus = newInstance();
        this.put(name, bus);
        return bus;
    }

    @Override
    public final boolean exists(String name) {
        return busMap.containsKey(name);
    }

    @Override
    public final IEventBus get(String name) {
        return busMap.get(name);
    }
}
