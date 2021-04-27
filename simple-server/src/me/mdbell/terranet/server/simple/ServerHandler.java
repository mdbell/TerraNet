package me.mdbell.terranet.server.simple;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.game.messages.WorldMetadataMessage;
import me.mdbell.terranet.common.game.messages.modules.OutgoingChatMessage;
import me.mdbell.terranet.common.net.ISendable;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ConnectionState;
import me.mdbell.terranet.server.events.ServerConnectionEvent;
import me.mdbell.terranet.server.simple.engine.Player;

import java.io.IOException;

@Slf4j
public class ServerHandler implements ISendable {

    private int connected = 0;
    private final ConnectionCtx<Player>[] connections;

    public ServerHandler(int max) {
        connections = new ConnectionCtx[max];
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
            if(ctx == null){
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

            sendServerMessage(p.getName() + " has left.");
        }
    }

    public void sendWorldInfo(ConnectionCtx<Player> ctx) {
        //TODO implement
        ctx.disconnect("World Info Unimplemented");
    }

    @Override
    public void send(GameMessage message) {
        if(message instanceof OutgoingChatMessage){
            OutgoingChatMessage ocm = (OutgoingChatMessage)message;
            String author = "SERVER";
            int id = ocm.getAuthor();
            if(id != AUTHOR_SERVER) {
                ConnectionCtx<Player> c = connections[id];
                if(c != null){
                    author = c.attrs().getName() + "[" + id + "]";
                }
            }
            log.info("[CHAT] [{}]: {}", author, ocm.getText());
        }
        for(int i = 0; i < connections.length; i++){
            if(connections[i] == null){
                continue;
            }
            connections[i].send(message);
        }
    }

    @Override
    public void close() {
        //ignored
    }
}
