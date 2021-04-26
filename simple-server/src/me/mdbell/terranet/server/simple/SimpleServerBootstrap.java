package me.mdbell.terranet.server.simple;

import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import me.mdbell.terranet.server.simple.handlers.InitialHandshakeHandler;

public class SimpleServerBootstrap {


    private final int port;

    public SimpleServerBootstrap(int port) {
        this.port = port;
    }

    public void run() {
        ServerFactory<SimpleAttributes> factory = ServerFactory.createDefaultFactory();
        ServerCtx<SimpleAttributes> ctx = factory.newInstance();
        ctx.setAttributesFactory(new SimpleAttributesFactory());
        ConnectionHandler handler = new ConnectionHandler(16);
        ConnectionCtx.bus().subscribe(new InitialHandshakeHandler(handler, Opcodes.DEFAULT_VERSION, "test"));

        ctx.bind(port);
    }

    public void onMessageReceived(GameMessageEvent<SimpleAttributes> msg) {

    }

    public static void main(String[] args) {
        new SimpleServerBootstrap(1337).run();
    }
}
