package me.mdbell.bus.internal;

import me.mdbell.bus.IBusEvent;

import java.util.function.Consumer;

class LambdaSubscriber extends Subscriber {

    private final Consumer<IBusEvent<?, ?>> consumer;

    public LambdaSubscriber(int priority, Object instance, Consumer<IBusEvent<?, ?>> consumer) {
        super(priority, instance);
        this.consumer = consumer;
    }

    @Override
    void invoke(IBusEvent<?, ?> event) {
        consumer.accept(event);
    }
}
