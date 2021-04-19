package me.mdbell.terranet.server.events;

import me.mdbell.bus.AbstractBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.ConnectionState;
import me.mdbell.terranet.server.ConnectionCtx;

public class ServerConnectionEvent extends AbstractBusEvent<ConnectionCtx, ConnectionState> {

    public ServerConnectionEvent(IEventBus bus, ConnectionCtx source, ConnectionState value) {
        super(bus, source, value);
    }
}
