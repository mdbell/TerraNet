package me.mdbell.bus;

import java.util.Objects;

public abstract class AbstractBusEvent<S, V> implements IBusEvent<S, V> {

    private final IEventBus bus;
    private final S source;
    private final V value;

    public AbstractBusEvent(IEventBus bus, S source, V value) {
        Objects.requireNonNull(bus);
        this.bus = bus;
        this.source = source;
        this.value = value;
    }

    @Override
    public final IEventBus bus() {
        return bus;
    }

    @Override
    public final S source() {
        return source;
    }

    @Override
    public final V message() {
        return value;
    }

    public final void consume() {
        bus().consume(this);
    }
}
