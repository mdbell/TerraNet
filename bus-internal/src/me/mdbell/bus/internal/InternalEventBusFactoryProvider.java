package me.mdbell.bus.internal;

import me.mdbell.bus.EventBusFactory;
import me.mdbell.bus.EventBusFactoryProvider;

public class InternalEventBusFactoryProvider implements EventBusFactoryProvider {
    @Override
    public EventBusFactory getFactory() {
        return new InternalEventBusFactory();
    }
}
