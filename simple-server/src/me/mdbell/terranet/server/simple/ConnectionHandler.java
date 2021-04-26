package me.mdbell.terranet.server.simple;

import me.mdbell.terranet.server.ConnectionCtx;

public class ConnectionHandler {

    private int connected = 0;
    private final ConnectionCtx<SimpleAttributes>[] connections;

    public ConnectionHandler(int max) {
        connections = new ConnectionCtx[max];
    }

    public int getMaxConnections() {
        return connections.length;
    }

    public int addConnection(ConnectionCtx<SimpleAttributes> ctx) {
        if (connected >= connections.length) {
            return -1;
        }
        int id = connected;
        connections[connected++] = ctx;
        return id;
    }
}
