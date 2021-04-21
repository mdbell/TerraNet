package me.mdbell.bus.robot;

import me.mdbell.bus.IBusEvent;
import me.mdbell.bus.IEventBus;
import org.greenrobot.eventbus.EventBus;

final class RobotEventBus implements IEventBus {

    private final EventBus bus;

    RobotEventBus(EventBus bus) {
        this.bus = bus;
    }

    @Override
    public void post(IBusEvent event) {
        bus.post(event);
    }

    @Override
    public void consume(IBusEvent event) {
        bus.cancelEventDelivery(event);
    }

    @Override
    public void subscribe(Object receiver) {
        bus.register(receiver);
    }

    @Override
    public void unsubscribe(Object receiver) {
        bus.unregister(receiver);
    }
}
