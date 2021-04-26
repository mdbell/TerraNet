package me.mdbell.terranet.server.simple.handlers;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.common.game.messages.*;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.simple.ConnectionHandler;
import me.mdbell.terranet.server.simple.HandshakeState;
import me.mdbell.terranet.server.simple.SimpleAttributes;

@Slf4j
public class InitialHandshakeHandler implements Opcodes {

    private final ConnectionHandler handler;
    private final String version;

    public InitialHandshakeHandler(ConnectionHandler conn, String version) {
        this.handler = conn;
        this.version = version;
    }

    @Subscribe
    public void onMessage(GameMessageEvent<ConnectionCtx<SimpleAttributes>> event) {
        ConnectionCtx<SimpleAttributes> ctx = event.source();
        GameMessage message = event.message();
        switch (message.getOpcode()) {
            case OP_CONNECT:
                onConnect(ctx, (ConnectionMessage) message);
                break;
            case OP_PLAYER_INFO:
                onPlayerInfo(ctx, (PlayerInfoMessage) message);
                break;
            case OP_UUID:
                onUuid(ctx, (UUIDMessage) message);
                break;
            default:
                log.warn("Unhandled message {}", message);
        }
    }

    public void onConnect(ConnectionCtx<SimpleAttributes> ctx, ConnectionMessage msg) {
        SimpleAttributes attrs = ctx.attrs();
        if (attrs.getHandshakeState() == HandshakeState.NEW) {
            if (!version.equals(msg.getVersion())) {
                ctx.disconnect("Unsupported version:" + msg.getVersion());
            }
            int id = handler.addConnection(ctx);
            if (id == -1) {
                log.info("Disconnecting connection due to max players");
                ctx.disconnect("Server full!");
            } else {
                ctx.slot(id, false);
                attrs.setHandshakeState(HandshakeState.ASSIGNED_ID);
            }
        } else {
            ctx.disconnect("Bad connection");
        }
    }

    public void onPlayerInfo(ConnectionCtx<SimpleAttributes> ctx, PlayerInfoMessage msg){
        SimpleAttributes attrs = ctx.attrs();
        if(attrs.getHandshakeState() != HandshakeState.ASSIGNED_ID) {
            return;
        }
        if(attrs.getId() != msg.getId()) {
            log.warn("Player at slot {} tried to set info for {}", attrs.getId(), msg.getId());
            ctx.disconnect("Bad id");
            return;
        }
        //TODO set user info
        attrs.setHandshakeState(HandshakeState.INFO_SET);
    }

    private void onUuid(ConnectionCtx<SimpleAttributes> ctx, UUIDMessage message) {
        SimpleAttributes attrs = ctx.attrs();
        if(attrs.getHandshakeState() != HandshakeState.INFO_SET) {
            ctx.disconnect("");
        }
        attrs.setUuid(message.getUuid());
        attrs.setHandshakeState(HandshakeState.UUID_SET);
    }
}
