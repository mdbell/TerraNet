package me.mdbell.terranet.client.events;

import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.net.ConnectionAttributes;

public class ClientMessageEvent<T extends ConnectionAttributes> extends GameMessageEvent<ClientCtx<T>> {
    public ClientMessageEvent(IEventBus<ClientCtx<T>> bus, ClientCtx<T> source, GameMessage value) {
        super(bus, source, value);
    }
}
