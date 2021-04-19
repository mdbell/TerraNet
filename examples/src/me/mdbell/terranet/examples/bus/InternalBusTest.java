package me.mdbell.terranet.examples.bus;

import me.mdbell.bus.*;
import me.mdbell.bus.internal.InternalEventBusFactory;

public class InternalBusTest {

    public void run(){
        EventBusFactory factory = new InternalEventBusFactory();
        IEventBus<InternalBusTest> bus = factory.getOrCreate(InternalBusTest.class);

        bus.subscribe(this);

        bus.post(new TestEvent(bus, this, "Hello!"));
    }

    @Subscribe
    public void testEvent(TestEvent event){
        System.out.println(event.message());
    }

    public static void main(String[] args){
        new InternalBusTest().run();
    }

    private static class TestEvent extends AbstractBusEvent<InternalBusTest, String> {

        public TestEvent(IEventBus bus, InternalBusTest source, String value) {
            super(bus, source, value);
        }
    }
}
