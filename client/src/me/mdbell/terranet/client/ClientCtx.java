package me.mdbell.terranet.client;

import me.mdbell.bus.EventBusFactory;
import me.mdbell.bus.IBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.client.events.ClientMessageEvent;
import me.mdbell.terranet.common.game.messages.ConnectionMessage;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.net.IReceivable;
import me.mdbell.terranet.common.net.ISendable;

import java.io.Closeable;
import java.net.SocketAddress;

public abstract class ClientCtx implements ISendable, IReceivable, Closeable {

    private static final IEventBus<ClientCtx> bus = EventBusFactory.getDefaultFactory().getOrCreate(ClientCtx.class);

    public abstract SocketAddress getRemoteAddress();

    public void connect(){
        //TODO not use default version, perhaps pass it in from the factory?
        send(new ConnectionMessage().version(Opcodes.DEFAULT_VERSION));
    }

    @Override
    public void receive(GameMessage message) {
        IBusEvent<ClientCtx, GameMessage> event = new ClientMessageEvent(bus, this, message);
        bus.post(event);
    }

    public static IEventBus<ClientCtx> bus(){
        return bus;
    }
}
