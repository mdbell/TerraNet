package me.mdbell.terranet;

import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.client.ClientFactory;
import me.mdbell.terranet.client.events.ClientMessageEvent;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import me.mdbell.terranet.server.events.ServerMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class ProxyServer {

    private final Logger logger = LoggerFactory.getLogger(ProxyServer.class);

    private final String remoteHost;
    private final int remotePort;
    private final int localPort;

    private final Map<ConnectionCtx, ClientCtx> proxyMap = new HashMap<>();

    public ProxyServer(String remoteHost, int remotePort, int localPort) {
        this.remoteHost = remoteHost;
        this.remotePort = remotePort;
        this.localPort = localPort;
    }

    public void run() throws Exception {

        logger.info("Setting up server event handler");
        ConnectionCtx.bus().subscribe(this);
        logger.info("Setting up client event handler");
        ClientCtx.bus().subscribe(this);

        logger.info("Starting server on port {}", localPort);
        ServerCtx ctx = ServerFactory.getDefaultFactory().bind(localPort);
        logger.info("Listening for connections!");

        ctx.awaitClose();
    }

    @Subscribe
    public void onServerMessage(ServerMessageEvent event) {
        GameMessage message = event.message();
        ConnectionCtx conn = event.source();
        ClientCtx ctx;
        if (message.getId() == Opcodes.OP_CONNECT) {
            if (proxyMap.containsKey(conn)) {
                logger.debug("Attempted to open a connection on an already open one?");
                return;
            }
            ctx = ClientFactory.getDefaultFactory().connect(remoteHost, remotePort);
            proxyMap.put(conn, ctx);
        } else {
            ctx = proxyMap.get(conn);
        }
        ctx.send(message);
    }

    @Subscribe
    public void onClientMessage(ClientMessageEvent event) {
        ClientCtx ctx = event.source();
        GameMessage message = event.message();
        ConnectionCtx conn = proxyMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(ctx))
                .map(Map.Entry::getKey).findFirst().get();
        conn.send(message);
    }

    public static void main(String[] args) throws Exception {
        new ProxyServer("127.0.0.1", 7777, 1337).run();
    }
}
