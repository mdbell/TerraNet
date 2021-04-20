package me.mdbell.terranet.examples.proxy;

import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.server.ConnectionState;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.client.ClientFactory;
import me.mdbell.terranet.client.events.ClientMessageEvent;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import me.mdbell.terranet.server.events.ServerConnectionEvent;
import me.mdbell.terranet.server.events.ServerMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProxyServer {

    private final Logger logger = LoggerFactory.getLogger(ProxyServer.class);

    private final String remoteHost;
    private final int remotePort;
    private final int localPort;

    private final Map<ConnectionCtx, ClientCtx> proxyMap = new HashMap<>();

    private ClientFactory<?> clientFactory;

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

        logger.info("Setting the ClientFactory");
        clientFactory = ClientFactory.createDefaultFactory();
        //clientFactory.setAttributesFactory(MyClientAttributes)

        logger.info("Starting server on port {}", localPort);
        ServerCtx<?> ctx = ServerFactory.createDefaultFactory().newInstance();
        //ctx.setAttributesFactory(MyAttributes)
        ctx.bind(localPort);
        logger.info("Listening for connections!");

        ctx.awaitClose();
    }

    @Subscribe
    public void onServerConnectionEvent(ServerConnectionEvent event) {
        if (event.message() == ConnectionState.DEREGISTER) {
            ConnectionCtx conn = event.source();
            ClientCtx ctx = proxyMap.get(conn);
            try {
                //TODO write disconnect message
                ctx.close();
            } catch (IOException e) {
                logger.error("Exception closing remote", e);
            }
            try {
                conn.close();
            } catch (IOException e) {
                logger.error("Exception closing local", e);
            }
            proxyMap.remove(conn);
            event.consume();
        }
    }

    @Subscribe
    public void onIncomingMessage(ServerMessageEvent event) {
        GameMessage message = event.message();
        ConnectionCtx conn = event.source();
        ClientCtx<?> ctx;
        if (message.getId() == Opcodes.OP_CONNECT) {
            if (proxyMap.containsKey(conn)) {
                logger.debug("Attempted to open a connection on an already open one?");
                return;
            }
            ctx = clientFactory.connect(remoteHost, remotePort);
            proxyMap.put(conn, ctx);
        } else {
            ctx = proxyMap.get(conn);
        }
        ctx.send(message);
        event.consume();
    }

    @Subscribe
    public void onOutgoingMessage(ClientMessageEvent event) {
        ClientCtx<?> ctx = event.source();
        GameMessage message = event.message();
        ConnectionCtx conn = proxyMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(ctx))
                .map(Map.Entry::getKey).findFirst().get();
        conn.send(message);
        event.consume();
    }

    public static void main(String[] args) throws Exception {
        new ProxyServer("127.0.0.1", 7777, 1337).run();
    }
}
