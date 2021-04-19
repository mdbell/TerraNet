package me.mdbell.bus;

public interface IBusEvent<S, V> {

    IEventBus bus();

    S source();

    V message();
}
