package me.mdbell.terranet.server;

import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.ConnectionState;
import me.mdbell.terranet.server.events.ServerConnectionEvent;

public abstract class ServerCtx {

    public final void register(ConnectionCtx ctx){
        IEventBus<ConnectionCtx> bus = ConnectionCtx.bus();
        bus.post(new ServerConnectionEvent(bus, ctx, ConnectionState.REGISTER));
    }

    public final void deregister(ConnectionCtx ctx){
        IEventBus<ConnectionCtx> bus = ConnectionCtx.bus();
        bus.post(new ServerConnectionEvent(bus, ctx, ConnectionState.DEREGISTER));
    }

    public abstract void awaitClose() throws InterruptedException;

}
