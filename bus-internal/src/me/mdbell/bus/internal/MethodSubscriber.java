package me.mdbell.bus.internal;

import lombok.SneakyThrows;
import me.mdbell.bus.IBusEvent;

import java.lang.reflect.Method;

class MethodSubscriber extends Subscriber {

    private final Method method;

    public MethodSubscriber(int priority, Object instance, Method method) {
        super(priority, instance);
        this.method = method;
    }

    @SneakyThrows
    @Override
    void invoke(IBusEvent<?, ?> event) {
        method.invoke(getInstance(), event);
    }
}
