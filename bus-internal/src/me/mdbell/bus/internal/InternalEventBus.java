package me.mdbell.bus.internal;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.IBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.bus.Subscribe;

import java.lang.invoke.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.function.Consumer;

@Slf4j
class InternalEventBus<S> implements IEventBus<S> {
    private final Map<Class<?>, Collection<Subscriber>> subscriberMap = new HashMap<>();

    private final List<IBusEvent<S, ?>> posting = new LinkedList<>();
    private final List<IBusEvent<S, ?>> consumed = new LinkedList<>();

    @Override
    public <T> void post(IBusEvent<S, T> event) {
        posting.add(event);
        try {
            Collection<Subscriber> subs = subscriberMap.get(event.getClass());
            if (subs != null) {
                for (Subscriber s : subs) {
                    if (consumed.contains(event)) {
                        break;
                    }
                    s.invoke(event);
                }
            }
        } finally {
            posting.remove(event);
            consumed.remove(event);
        }
    }

    @Override
    public <T> void consume(IBusEvent<S, T> event) {
        if (posting.contains(event)) {
            consumed.add(event);
        }
    }

    @Override
    public void subscribe(Object receiver) {
        iterateSubscribers(receiver);
    }

    @Override
    public void unsubscribe(Object receiver) {
        for (Collection<Subscriber> subs : subscriberMap.values()) {
            subs.removeIf(s -> s.getInstance() == receiver);
        }
    }

    private void iterateSubscribers(Object instance) {
        for (Class<?> source = instance.getClass(); source != null; source = source.getSuperclass()) {
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
                addSubscriber(sub.priority(), m, instance, eventType);
            }
        }
    }

    private void addSubscriber(int priority, Method m, Object instance, Class<?> eventType) {
        Subscriber s = null;
        try {
            s = createLambdaSubscriber(priority, m, instance, eventType);
        } catch (Throwable t) {
            log.warn("Unable to create lambda subscriber. Reason:", t);
            log.warn("Falling back to reflection");
            s = new MethodSubscriber(priority, instance, m);
        }
        for (Class<?> c = eventType; c != null && c != Object.class; c = c.getSuperclass()) {
            put(c, s);
        }
    }

    private LambdaSubscriber createLambdaSubscriber(int priority, Method method, Object instance, Class<?> eventType) throws Throwable {
        Class<?> inside = instance.getClass();
        log.debug("Creating lambda subscriber targeting {} inside {}, event type {}", method.getName(), inside.getName(), eventType.getName());
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType signature = MethodType.methodType(void.class, eventType);
        MethodHandle target = lookup.findVirtual(inside, method.getName(), signature);
        CallSite callsite = LambdaMetafactory.metafactory(lookup, "accept",
                MethodType.methodType(Consumer.class, inside),
                signature.changeParameterType(0, Object.class),
                target,
                signature);
        MethodHandle result = callsite.getTarget();
        Consumer<IBusEvent<?, ?>> consumer = (Consumer<IBusEvent<?, ?>>) result.bindTo(instance).invokeExact();
        return new LambdaSubscriber(priority, instance, consumer);
    }

    private void put(Class<?> eventType, Subscriber subscriber) {
        subscriberMap.computeIfAbsent(eventType, k -> new TreeSet<>()).add(subscriber);
    }
}
