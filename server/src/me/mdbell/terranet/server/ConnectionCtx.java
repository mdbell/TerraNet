package me.mdbell.terranet.server;

import me.mdbell.bus.EventBusFactory;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.common.net.IReceivable;
import me.mdbell.terranet.common.net.ISendable;
import me.mdbell.terranet.server.events.IncomingMessageEvent;

import java.io.Closeable;

public abstract class ConnectionCtx<T extends ConnectionAttributes> implements ISendable, IReceivable, Closeable {

    private static final IEventBus<ConnectionCtx> bus = EventBusFactory.getDefaultFactory().getOrCreate(ConnectionCtx.class);

    private final ServerCtx serverCtx;

    private T attrs;

    protected ConnectionCtx(ServerCtx serverCtx) {
        this.serverCtx = serverCtx;
    }

    protected void setAttributes(T attributes) {
        this.attrs = attributes;
    }

    public final T attrs() {
        return attrs;
    }

    public final ServerCtx getServer() {
        return serverCtx;
    }

    public static IEventBus<ConnectionCtx> bus() {
        return bus;
    }

    @Override
    public final void receive(GameMessage message) {
        IncomingMessageEvent event = new IncomingMessageEvent(bus, this, message);
        bus.post(event);
    }
}
