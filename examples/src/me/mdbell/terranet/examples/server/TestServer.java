package me.mdbell.terranet.examples.server;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import me.mdbell.terranet.server.events.ServerConnectionEvent;
import me.mdbell.terranet.server.events.IncomingMessageEvent;

@Slf4j
public class TestServer {

    private final int port;

    public TestServer(int listenPort) {
        this.port = listenPort;
    }

    public void run() throws Exception {
        log.info("Starting server on port {}", port);
        ServerCtx<?> ctx = ServerFactory.createDefaultFactory().newInstance();
        //ctx.setAttributesFactory(MyAttributesFactory)
        ctx.bind(port);
        log.info("Listening for connections!");

        ConnectionCtx.bus().subscribe(this);
        ctx.awaitClose();
        ConnectionCtx.bus().unsubscribe(this);
    }

    @Subscribe
    public void onMessage(IncomingMessageEvent event) {
        log.info("New message from: {} Message: {}", event.source(), event.value());
    }

    @Subscribe
    public void onConnectionEvent(ServerConnectionEvent event) {
        log.info("Connection state for {} Changed to {}", event.source(), event.value());
    }

    public static void main(String[] args) throws Exception {
        new TestServer(1337).run();
    }
}
