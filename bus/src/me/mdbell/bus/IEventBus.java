package me.mdbell.bus;

public interface IEventBus<S> {

    <T> void post(IBusEvent<S, T> event);

    <T> void consume(IBusEvent<S, T> event);

    void subscribe(Object receiver);

    void unsubscribe(Object receiver);
}
