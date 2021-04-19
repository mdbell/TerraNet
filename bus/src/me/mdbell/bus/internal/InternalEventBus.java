package me.mdbell.bus.internal;

import me.mdbell.bus.IBusEvent;
import me.mdbell.bus.IEventBus;

class InternalEventBus<S> implements IEventBus<S> {

    @Override
    public <T> void post(IBusEvent<S, T> event) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public <T> void consume(IBusEvent<S, T> event) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public void subscribe(Object receiver) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public void unsubscribe(Object receiver) {
        throw new UnsupportedOperationException("Unimplemented");
    }

}
