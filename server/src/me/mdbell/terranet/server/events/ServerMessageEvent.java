package me.mdbell.terranet.server.events;

import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.server.ConnectionCtx;

public class ServerMessageEvent<T extends ConnectionAttributes> extends GameMessageEvent<ConnectionCtx<T>> {
    public ServerMessageEvent(IEventBus<ConnectionCtx<T>> bus, ConnectionCtx<T> source, GameMessage value) {
        super(bus, source, value);
    }
}
