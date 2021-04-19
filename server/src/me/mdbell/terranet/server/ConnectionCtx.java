package me.mdbell.terranet.server;

import me.mdbell.bus.EventBusFactory;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.net.IReceivable;
import me.mdbell.terranet.common.net.ISendable;
import me.mdbell.terranet.server.events.ServerMessageEvent;

public abstract class ConnectionCtx implements ISendable, IReceivable {

    private static final IEventBus<ConnectionCtx> bus = EventBusFactory.getDefaultFactory().getOrCreate(ConnectionCtx.class);

    private final ServerCtx serverCtx;

    protected ConnectionCtx(ServerCtx serverCtx) {
        this.serverCtx = serverCtx;
    }

    public final ServerCtx getServer() {
        return serverCtx;
    }

    public static IEventBus<ConnectionCtx> bus() {
        return bus;
    }

    @Override
    public final void receive(GameMessage message) {
        ServerMessageEvent event = new ServerMessageEvent(bus, this, message);
        bus.post(event);
    }
}
