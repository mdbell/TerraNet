package me.mdbell.terranet.server.simple.handlers;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.common.game.messages.*;
import me.mdbell.terranet.common.ext.ArrayExtensions;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.simple.ConnectionHandler;
import me.mdbell.terranet.server.simple.HandshakeState;
import me.mdbell.terranet.server.simple.SimpleAttributes;

@Slf4j
@ExtensionMethod({ArrayExtensions.class})
public class InitialHandshakeHandler implements Opcodes {

    private final ConnectionHandler handler;
    private final String version;
    private final String password;

    public InitialHandshakeHandler(ConnectionHandler conn) {
        this(conn, Opcodes.DEFAULT_VERSION, null);
    }

    public InitialHandshakeHandler(ConnectionHandler conn, String version, String password) {
        this.handler = conn;
        this.version = version;
        this.password = password;
    }

    @Subscribe(priority = 10)
    public void onMessage(GameMessageEvent<ConnectionCtx<SimpleAttributes>> event) {
        ConnectionCtx<SimpleAttributes> ctx = event.source();
        GameMessage message = event.message();
        boolean consume = switch (message.getOpcode()) {
            case OP_CONNECT -> onConnect(ctx, (ConnectionMessage) message);
            case OP_PASSWORD_RESPONSE -> onPassword(ctx, (PasswordResponseMessage) message);
            case OP_PLAYER_INFO -> onPlayerInfo(ctx, (PlayerInfoMessage) message);
            case OP_UUID -> onUuid(ctx, (UUIDMessage) message);
            case OP_PLAYER_HP -> onHealth(ctx, (PlayerHealthMessage) message);
            case OP_PLAYER_MANA -> onMana(ctx, (PlayerManaMessage) message);
            case OP_UPDATE_BUFFS -> onBuffs(ctx, (UpdateBuffsMessage) message);
            default -> false;
        };
        if (consume) {
            event.consume();
        } else {
            log.info("{}", message);
        }
    }

    private boolean onBuffs(ConnectionCtx<SimpleAttributes> ctx, UpdateBuffsMessage message) {
        SimpleAttributes attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.MANA_SET) {
            return false;
        }
        message.getBuffs().copyTo(attrs.getBuffs());
        return true;
    }

    private boolean onHealth(ConnectionCtx<SimpleAttributes> ctx, PlayerHealthMessage message) {
        SimpleAttributes attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.UUID_SET) {
            return false;
        }
        attrs.setCurrentHp(message.getHp());
        attrs.setMaxHp(message.getMaxHp());
        attrs.setHandshakeState(HandshakeState.HEALTH_SET);
        return true;
    }

    private boolean onMana(ConnectionCtx<SimpleAttributes> ctx, PlayerManaMessage message) {
        SimpleAttributes attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.HEALTH_SET) {
            return false;
        }
        attrs.setCurrentMana(message.getMana());
        attrs.setMaxMana(message.getMaxMana());
        attrs.setHandshakeState(HandshakeState.MANA_SET);
        return true;
    }

    private boolean onPassword(ConnectionCtx<SimpleAttributes> ctx, PasswordResponseMessage message) {
        SimpleAttributes attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.PASSWORD_REQUESTED) {
            ctx.disconnect();
            return true;
        }
        if (!this.password.equals(message.getPassword())) {
            log.info("Recieved wrong password '{}'", message.getPassword());
            ctx.disconnect("Bad password");
            return true;
        }
        return registerConnection(ctx);
    }

    public boolean onConnect(ConnectionCtx<SimpleAttributes> ctx, ConnectionMessage msg) {
        SimpleAttributes attrs = ctx.attrs();
        if (attrs.getHandshakeState() == HandshakeState.NEW) {
            if (!version.equals(msg.getVersion())) {
                ctx.disconnect("Unsupported version:" + msg.getVersion());
            } else if (password != null) {
                ctx.requestPassword();
                attrs.setHandshakeState(HandshakeState.PASSWORD_REQUESTED);
            } else {
                return registerConnection(ctx);
            }
        } else {
            ctx.disconnect("Bad connection");
        }
        return true;
    }

    private boolean registerConnection(ConnectionCtx<SimpleAttributes> ctx) {
        SimpleAttributes attrs = ctx.attrs();
        int id = handler.addConnection(ctx);
        if (id == -1) {
            log.info("Disconnecting connection due to max players");
            ctx.disconnect("Server full!");
        } else {
            ctx.slot(id, false);
            attrs.setHandshakeState(HandshakeState.ASSIGNED_ID);
        }
        return true;
    }

    public boolean onPlayerInfo(ConnectionCtx<SimpleAttributes> ctx, PlayerInfoMessage msg) {
        SimpleAttributes attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.ASSIGNED_ID) {
            return false;
        }
        //TODO set user info
        attrs.setHandshakeState(HandshakeState.INFO_SET);
        return true;
    }

    private boolean onUuid(ConnectionCtx<SimpleAttributes> ctx, UUIDMessage message) {
        SimpleAttributes attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.INFO_SET) {
            ctx.disconnect("");
            return true;
        }
        attrs.setUuid(message.getUuid());
        attrs.setHandshakeState(HandshakeState.UUID_SET);
        return true;
    }
}
