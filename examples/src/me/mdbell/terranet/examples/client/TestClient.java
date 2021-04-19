package me.mdbell.terranet.examples.client;

import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.client.ClientFactory;
import me.mdbell.terranet.client.events.ClientMessageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TestClient {

    private static final Logger logger = LoggerFactory.getLogger(TestClient.class);

    private final String host;
    private final int port;

    public TestClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        ClientFactory factory = ClientFactory.getDefaultFactory();

        ClientCtx ctx = factory.connect(host, port);

        ctx.connect();

        ClientCtx.bus().subscribe(this);

    }

    @Subscribe
    public void onClientMessage(ClientMessageEvent event) throws IOException {
        logger.info("Message Received from: {} Message:{}", event.source(), event.message());
        event.source().close();
    }

    public static void main(String[] args) {
        new TestClient("127.0.0.1", 7777).run();
    }
}
