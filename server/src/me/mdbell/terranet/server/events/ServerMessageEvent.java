package me.mdbell.terranet.server.events;

import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.server.ConnectionCtx;

public class ServerMessageEvent extends GameMessageEvent<ConnectionCtx> {
    public ServerMessageEvent(IEventBus<ConnectionCtx> bus, ConnectionCtx source, GameMessage value) {
        super(bus, source, value);
    }
}
