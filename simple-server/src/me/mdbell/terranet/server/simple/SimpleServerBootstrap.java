package me.mdbell.terranet.server.simple;

import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import me.mdbell.terranet.server.simple.handlers.InitialHandshakeHandler;

public class SimpleServerBootstrap {

    private String version;
    private int port;

    public SimpleServerBootstrap(String version, int port) {
        this.version = version;
        this.port = port;
    }

    public void run() {
        ServerFactory<SimpleAttributes> factory = ServerFactory.createDefaultFactory();
        ServerCtx<SimpleAttributes> ctx = factory.newInstance();
        ctx.setAttributesFactory(new SimpleAttributesFactory());
        ConnectionHandler handler = new ConnectionHandler(16);
        ConnectionCtx.bus().subscribe(new InitialHandshakeHandler(handler, version));

        ctx.bind(port);
    }

    public void onMessageReceived(GameMessageEvent<SimpleAttributes> msg) {

    }

    public static void main(String[] args) {
        new SimpleServerBootstrap("Terraria237", 1337).run();
    }
}
