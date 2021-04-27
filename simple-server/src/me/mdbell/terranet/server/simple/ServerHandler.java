package me.mdbell.terranet.server.simple;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.EventBusFactory;
import me.mdbell.bus.IEventBus;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.common.ext.StringExtensions;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.net.ISendable;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ConnectionState;
import me.mdbell.terranet.server.events.ServerConnectionEvent;
import me.mdbell.terranet.server.simple.engine.Player;
import me.mdbell.terranet.server.simple.events.GlobalMessageEvent;
import me.mdbell.terranet.server.simple.util.WorldUtils;
import me.mdbell.terranet.world.tree.WorldNode;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ExtensionMethod({StringExtensions.class})
public class ServerHandler implements ISendable {

    private static final IEventBus<ServerHandler> bus = EventBusFactory.getDefaultFactory().getOrCreate(ServerHandler.class);

    private WorldNode world;

    private int connected = 0;
    private final ConnectionCtx<Player>[] connections;

    public ServerHandler(int max, WorldNode world) {
        connections = new ConnectionCtx[max];
        this.world = world;
    }

    public int getMaxConnections() {
        return connections.length;
    }

    public int addConnection(ConnectionCtx<Player> ctx) {
        if (connected >= connections.length) {
            return -1;
        }
        for (int id = 0; id < connections.length; id++) {
            if (connections[id] == null) {
                connections[id] = ctx;
                connected++;
                return id;
            }
        }
        ;
        return -1;
    }

    public ConnectionCtx<Player> getPlayerByUuid(String uuid) {
        for (int i = 0; i < connections.length; i++) {
            ConnectionCtx<Player> ctx = connections[i];
            if (ctx == null) {
                continue;
            }
            Player p = ctx.attrs();
            if (uuid.equals(p.getUuid())) {
                return ctx;
            }
        }
        return null;
    }

    private void removeConnection(ConnectionCtx<Player> source) {
        Player p = source.attrs();
        if (p.getId() != -1) {
            int id = p.getId();
            connections[id] = null;
            connected--;
            return;
        }
        for (int i = 0; i < connections.length; i++) {
            if (connections[i] == source) {
                connections[i] = null;
                connected--;
                break;
            }
        }
    }

    @Subscribe
    public void onServerConnectionEvent(ServerConnectionEvent<Player> event) {
        if (event.value() == ConnectionState.DEREGISTER) {
            Player p = event.source().attrs();
            log.info("Removing Player \"{}\" at {} (uuid: {})", p.getName(), p.getId(), p.getUuid());
            removeConnection(event.source());
            sendServerMessage("{0} {1}".toFormatted(p.getName(), "has left."));
        }
    }

    public void sendWorldInfo(ConnectionCtx<Player> ctx) {
        //TODO implement
        //disconnect("Goodbye {0}".toFormatted(ctx.attrs().getName()));
        ctx.send(WorldUtils.deriveMessageFrom(world));
    }

    public ConnectionCtx<Player> getPlayerById(int id) {
        for (ConnectionCtx<Player> connection : connections) {
            if (connection != null && connection.attrs().getId() == id) {
                return connection;
            }
        }
        return null;
    }


    @Override
    public void send(GameMessage message) {
        bus.post(new GlobalMessageEvent(bus, this, message));
        for (ConnectionCtx<Player> connection : connections) {
            if (connection == null) {
                continue;
            }
            connection.send(message);
        }
    }

    @Override
    public void close() {
        //ignored
    }

    public static IEventBus<ServerHandler> bus() {
        return bus;
    }
}
