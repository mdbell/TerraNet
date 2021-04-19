package me.mdbell.terranet.examples.server;

import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import me.mdbell.terranet.server.events.ServerConnectionEvent;
import me.mdbell.terranet.server.events.ServerMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestServer {

    private static final Logger logger = LoggerFactory.getLogger(TestServer.class);

    private final int port;

    public TestServer(int listenPort) {
        this.port = listenPort;
    }

    public void run() throws Exception {
        logger.info("Starting server on port {}", port);
        ServerCtx ctx = ServerFactory.getDefaultFactory().bind(this.port);
        logger.info("Listening for connections!");

        ConnectionCtx.bus().subscribe(this);
        ctx.awaitClose();
        ConnectionCtx.bus().unsubscribe(this);
    }

    @Subscribe
    public void onMessage(ServerMessageEvent event) {
        logger.info("New message from: {} Message: {}", event.source(), event.message());
    }

    @Subscribe
    public void onConnectionEvent(ServerConnectionEvent event) {
        logger.info("Connection state for {} Changed to {}", event.source(), event.message());
    }

    public static void main(String[] args) throws Exception {
        new TestServer(1337).run();
    }
}
