package me.mdbell.terranet.server.simple.handlers;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.events.GameMessageEvent;
import me.mdbell.terranet.common.game.messages.*;
import me.mdbell.terranet.common.ext.ArrayExtensions;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.simple.ServerHandler;
import me.mdbell.terranet.server.simple.data.HandshakeState;
import me.mdbell.terranet.server.simple.data.Item;
import me.mdbell.terranet.server.simple.engine.Player;

@Slf4j
@ExtensionMethod({ArrayExtensions.class})
public class InitialHandshakeHandler implements Opcodes {

    private final ServerHandler handler;
    private final String version;
    private final String password;

    public InitialHandshakeHandler(ServerHandler conn) {
        this(conn, Opcodes.DEFAULT_VERSION, null);
    }

    public InitialHandshakeHandler(ServerHandler conn, String version, String password) {
        this.handler = conn;
        this.version = version;
        this.password = password;
    }

    @Subscribe(priority = 10)
    public void onMessage(GameMessageEvent<ConnectionCtx<Player>> event) {
        ConnectionCtx<Player> ctx = event.source();
        GameMessage message = event.value();
        boolean consume = switch (message.getOpcode()) {
            case OP_CONNECT -> onConnect(ctx, (ConnectionMessage) message);
            case OP_PASSWORD_RESPONSE -> onPassword(ctx, (PasswordResponseMessage) message);
            case OP_PLAYER_INFO -> onPlayerInfo(ctx, (PlayerInfoMessage) message);
            case OP_UUID -> onUuid(ctx, (UUIDMessage) message);
            case OP_PLAYER_HP -> onHealth(ctx, (PlayerHealthMessage) message);
            case OP_PLAYER_MANA -> onMana(ctx, (PlayerManaMessage) message);
            case OP_UPDATE_BUFFS -> onBuffs(ctx, (UpdateBuffsMessage) message);
            case OP_SET_INVENTORY_SLOT -> onItem(ctx, (SlotMessage) message);
            default -> false;
        };
        if (consume) {
            event.consume();
        } else {
            log.info("{}", message);
        }
    }

    private boolean onItem(ConnectionCtx<Player> ctx, SlotMessage message) {
        Player player = ctx.attrs();
        if (player.getHandshakeState() != HandshakeState.BUFFS_SET) {
            return false;
        }
        Item item = player.getItem(message.getSlot());
        item.setId(message.getNetId());
        item.setPrefix(message.getPrefix());
        item.setCount(message.getCount());
        return true;
    }

    private boolean onBuffs(ConnectionCtx<Player> ctx, UpdateBuffsMessage message) {
        Player player = ctx.attrs();
        if (player.getHandshakeState() != HandshakeState.MANA_SET) {
            return false;
        }
        message.getBuffs().copyTo(player.getBuffs());
        player.setHandshakeState(HandshakeState.BUFFS_SET);
        return true;
    }

    private boolean onHealth(ConnectionCtx<Player> ctx, PlayerHealthMessage message) {
        Player attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.UUID_SET) {
            return false;
        }
        attrs.setCurrentHp(message.getHp());
        attrs.setMaxHp(message.getMaxHp());
        attrs.setHandshakeState(HandshakeState.HEALTH_SET);
        return true;
    }

    private boolean onMana(ConnectionCtx<Player> ctx, PlayerManaMessage message) {
        Player attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.HEALTH_SET) {
            return false;
        }
        attrs.setCurrentMana(message.getMana());
        attrs.setMaxMana(message.getMaxMana());
        attrs.setHandshakeState(HandshakeState.MANA_SET);
        return true;
    }

    private boolean onPassword(ConnectionCtx<Player> ctx, PasswordResponseMessage message) {
        Player attrs = ctx.attrs();
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

    public boolean onConnect(ConnectionCtx<Player> ctx, ConnectionMessage msg) {
        Player attrs = ctx.attrs();
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

    private boolean registerConnection(ConnectionCtx<Player> ctx) {
        Player attrs = ctx.attrs();
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

    public boolean onPlayerInfo(ConnectionCtx<Player> ctx, PlayerInfoMessage msg) {
        Player attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.ASSIGNED_ID) {
            return false;
        }
        //TODO set user info
        attrs.setHandshakeState(HandshakeState.INFO_SET);
        return true;
    }

    private boolean onUuid(ConnectionCtx<Player> ctx, UUIDMessage message) {
        Player attrs = ctx.attrs();
        if (attrs.getHandshakeState() != HandshakeState.INFO_SET) {
            ctx.disconnect("");
            return true;
        }
        attrs.setUuid(message.getUuid());
        attrs.setHandshakeState(HandshakeState.UUID_SET);
        return true;
    }
}
