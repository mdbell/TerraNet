package me.mdbell.terranet.server.events;

import me.mdbell.bus.AbstractBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ConnectionState;

public class ServerConnectionEvent extends AbstractBusEvent<ConnectionCtx, ConnectionState> {

    public ServerConnectionEvent(IEventBus bus, ConnectionCtx source, ConnectionState value) {
        super(bus, source, value);
    }
}
