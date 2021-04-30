package me.mdbell.bus.internal;

import me.mdbell.bus.AbstractBusEvent;
import me.mdbell.bus.EventBusFactory;
import me.mdbell.bus.IEventBus;
import me.mdbell.bus.Subscribe;

public class Test {

    public void run(){
        IEventBus<Test> bus = EventBusFactory.getDefaultFactory().getOrCreate(Test.class);
        bus.subscribe(this);
        bus.post(new TestEvent(bus, this));
        bus.unsubscribe(this);
        bus.post(new TestEvent(bus, this));
    }

    @Subscribe(priority = 1)
    public void onEvent1(TestEvent event) {
        System.out.println(1);
    }
    @Subscribe(priority = 2)
    public void onEvent2(TestEvent event) {
        System.out.println(2);
        event.consume();
    }
    @Subscribe(priority = 3)
    public void onEvent3(TestEvent event) {
        System.out.println(3);
    }
    @Subscribe(priority = 4)
    public void onEvent4(TestEvent event) {
        System.out.println(4);
    }

    public static void main(String[] args){
        new Test().run();
    }

    public static class TestEvent extends AbstractBusEvent<Test, Object>{

        public TestEvent(IEventBus bus, Test source) {
            super(bus, source, null);
        }
    }
}
