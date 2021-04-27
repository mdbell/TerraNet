package me.mdbell.terranet.server.simple.engine.events;

import me.mdbell.bus.AbstractBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.server.simple.engine.GameLoop;
import me.mdbell.terranet.server.simple.ServerHandler;

public class TickEvent extends AbstractBusEvent<GameLoop, ServerHandler> {
    public TickEvent(IEventBus bus, GameLoop source, ServerHandler value) {
        super(bus, source, value);
    }
}
