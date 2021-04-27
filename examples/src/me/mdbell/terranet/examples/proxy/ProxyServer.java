package me.mdbell.terranet.examples.proxy;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.client.ClientFactory;
import me.mdbell.terranet.client.events.ClientMessageEvent;
import me.mdbell.terranet.common.ext.StringExtensions;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ConnectionState;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import me.mdbell.terranet.server.events.ServerConnectionEvent;
import me.mdbell.terranet.server.events.ServerMessageEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ExtensionMethod({StringExtensions.class})
public class ProxyServer {

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

        log.info("Setting up server event handler");
        ConnectionCtx.bus().subscribe(this);
        log.info("Setting up client event handler");
        ClientCtx.bus().subscribe(this);

        log.info("Setting the ClientFactory");
        clientFactory = ClientFactory.createDefaultFactory();
        //clientFactory.setAttributesFactory(MyClientAttributes)

        log.info("Starting server on port {}", localPort);
        ServerCtx<?> ctx = ServerFactory.createDefaultFactory().newInstance();
        //ctx.setAttributesFactory(MyAttributes)
        ctx.bind(localPort);
        log.info("Listening for connections!");

        ctx.awaitClose();
    }

    @Subscribe
    public void onServerConnectionEvent(ServerConnectionEvent<?> event) {
        if (event.value() == ConnectionState.DEREGISTER) {
            ConnectionCtx<?> conn = event.source();
            ClientCtx ctx = proxyMap.get(conn);
            try {
                ctx.disconnect("Remote server closed connection.".toLiteral());
                ctx.close();
            } catch (IOException e) {
                log.error("Exception closing remote", e);
            }
            try {
                conn.close();
            } catch (IOException e) {
                log.error("Exception closing local", e);
            }
            proxyMap.remove(conn);
            event.consume();
        }
    }

    @Subscribe
    public void onIncomingMessage(ServerMessageEvent<?> event) {
        GameMessage message = event.value();
        ConnectionCtx<?> conn = event.source();
        ClientCtx<?> ctx;
        if (message.getOpcode() == Opcodes.OP_CONNECT) {
            if (proxyMap.containsKey(conn)) {
                log.debug("Attempted to open a connection on an already open one?");
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
    public void onOutgoingMessage(ClientMessageEvent<?> event) {
        ClientCtx<?> ctx = event.source();
        GameMessage message = event.value();
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
