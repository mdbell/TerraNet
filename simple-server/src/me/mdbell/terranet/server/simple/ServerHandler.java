package me.mdbell.terranet.server.simple;

import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.simple.engine.Player;
import me.mdbell.terranet.server.simple.engine.Tickable;

public class ServerHandler implements Tickable {

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
        int id = connected;
        connections[connected++] = ctx;
        return id;
    }

    @Override
    public void tick(long tick) {
        for(int i = 0; i < connected; i++){
            connections[i].attrs().tick(i);
        }
    }
}
