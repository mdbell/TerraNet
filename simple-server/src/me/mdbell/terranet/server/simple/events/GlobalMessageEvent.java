package me.mdbell.terranet.server.simple.events;

import me.mdbell.bus.AbstractBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.server.simple.ServerHandler;

public class GlobalMessageEvent extends AbstractBusEvent<ServerHandler, GameMessage> {
    public GlobalMessageEvent(IEventBus bus, ServerHandler source, GameMessage value) {
        super(bus, source, value);
    }
}
