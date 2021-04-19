package me.mdbell.terranet.client.events;

import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.common.game.messages.GameMessage;

public class ClientMessageEvent extends GameMessageEvent<ClientCtx> {
    public ClientMessageEvent(IEventBus bus, ClientCtx source, GameMessage value) {
        super(bus, source, value);
    }
}
