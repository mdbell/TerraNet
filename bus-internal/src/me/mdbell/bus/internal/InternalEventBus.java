package me.mdbell.bus.internal;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.IBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.bus.Subscribe;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

@Slf4j
class InternalEventBus<S> implements IEventBus<S> {

    private static final List<Class<?>> processedClasses = new ArrayList<>();

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
        Class<?> source = receiver.getClass();
        if (!processedClasses.contains(source)) {
            iterateSubscribers(receiver);
            processedClasses.add(source);
        }
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void iterateSubscribers(Object instance) {
        Class<?> source = instance.getClass();
        for (Method m : source.getDeclaredMethods()) {
            Subscribe sub = m.getAnnotation(Subscribe.class);
            if (sub == null) {
                continue;
            }
            if (m.getParameterCount() > 1) {
                log.warn("{} has {} parameters, subscribers can only have 1!", m, m.getParameterCount());
                continue;
            }
            if (!Modifier.isPublic(m.getModifiers())) {
                log.warn("{} is not public, subscribers need to be public!", m);
                continue;
            }
            if (!m.getReturnType().equals(Void.TYPE)) {
                log.warn("{} has a return type, subscribers must be void", m);
                continue;
            }
            Class<?> eventType = m.getParameterTypes()[0];
            if (!IBusEvent.class.isAssignableFrom(eventType)) {
                log.warn("Method {} has an invalid parameter, it needs to be assignable from {}",
                        m, IBusEvent.class.getName());
                continue;
            }
            log.debug("{} is a subscriber", m);
            //addSubscriber(sub, m);
        }
    }

    @Override
    public void unsubscribe(Object receiver) {
        throw new UnsupportedOperationException("Unimplemented");
    }

}
