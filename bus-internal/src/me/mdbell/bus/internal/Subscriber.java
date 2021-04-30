package me.mdbell.bus.internal;

import me.mdbell.bus.IBusEvent;

abstract class Subscriber implements Comparable<Subscriber> {

    private final int priority;
    private final Object instance;

    public Subscriber(int priority, Object instance) {
        this.instance = instance;
        this.priority = priority;
    }

    Object getInstance(){
        return instance;
    }

    abstract void invoke(IBusEvent<?, ?> event);

    @Override
    public int compareTo(Subscriber o) {
        return o.priority - priority;
    }
}
