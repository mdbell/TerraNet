package me.mdbell.terranet.server.events;

import me.mdbell.bus.AbstractBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ConnectionState;
import me.mdbell.terranet.server.ServerCtx;

public class ServerConnectionEvent<T extends ConnectionAttributes> extends AbstractBusEvent<ConnectionCtx<T>, ConnectionState> {

    public ServerConnectionEvent(IEventBus<ServerCtx<T>> bus, ConnectionCtx<T> source, ConnectionState value) {
        super(bus, source, value);
    }
}
