package me.mdbell.bus.robot;

import me.mdbell.bus.IEventBus;
import me.mdbell.bus.MappedEventBusFactory;
import org.greenrobot.eventbus.EventBus;

public class RobotEventBusFactory extends MappedEventBusFactory {
    private final IEventBus defaultBus = new RobotEventBus(new EventBus());

    @Override
    public IEventBus getDefault() {
        return defaultBus;
    }

    @Override
    protected IEventBus newInstance() {
        EventBus impl = EventBus.builder()
                .logNoSubscriberMessages(false)
                .addIndex(SubscriberAdapter.INSTANCE)
                .build();
        return new RobotEventBus(impl);
    }

}
