package me.mdbell.terranet.examples.client;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.client.ClientFactory;
import me.mdbell.terranet.client.events.ClientMessageEvent;

import java.io.IOException;

@Slf4j
public class TestClient {

    private final String host;
    private final int port;

    public TestClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {
        ClientFactory<?> factory = ClientFactory.createDefaultFactory();
        ClientCtx<?> ctx = factory.connect(host, port);

        ctx.connect();

        ClientCtx.bus().subscribe(this);

    }

    @Subscribe
    public void onClientMessage(ClientMessageEvent<?> event) throws IOException {
        log.info("Message Received from: {} Message:{}", event.source(), event.message());
        event.source().close();
    }

    public static void main(String[] args) {
        new TestClient("127.0.0.1", 7777).run();
    }
}
