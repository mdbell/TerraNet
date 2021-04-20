package me.mdbell.terranet.server;

import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.net.ConnectionAttributesFactory;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.server.events.ServerConnectionEvent;

public abstract class ServerCtx<T extends ConnectionAttributes> {

    private ConnectionAttributesFactory<T> attributesFactory;

    public final void register(ConnectionCtx<T> ctx){
        if(attributesFactory != null){
            ctx.setAttributes(attributesFactory.newInstance());
        }
        IEventBus<ConnectionCtx> bus = ConnectionCtx.bus();
        bus.post(new ServerConnectionEvent(bus, ctx, ConnectionState.REGISTER));
    }

    public void setAttributesFactory(ConnectionAttributesFactory<T> factory){
        this.attributesFactory = factory;
    }

    public ConnectionAttributesFactory<T> getAttributesFactory(){
        return attributesFactory;
    }

    public final void deregister(ConnectionCtx<T> ctx){
        IEventBus<ConnectionCtx> bus = ConnectionCtx.bus();
        bus.post(new ServerConnectionEvent(bus, ctx, ConnectionState.DEREGISTER));
    }

    public abstract void bind(int port);

    public abstract void awaitClose() throws InterruptedException;

}
