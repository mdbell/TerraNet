package me.mdbell.bus.internal;

import me.mdbell.bus.IEventBus;
import me.mdbell.bus.MappedEventBusFactory;

public class InternalEventBusFactory extends MappedEventBusFactory {

    private final IEventBus defaultBus = new InternalEventBus();

    @Override
    public IEventBus getDefault() {
        return defaultBus;
    }

    @Override
    protected IEventBus newInstance() {
        return new InternalEventBus();
    }
}
