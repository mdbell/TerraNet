package me.mdbell.terranet.server.simple.handlers;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.ext.ArrayExtensions;
import me.mdbell.terranet.common.ext.StringExtensions;
import me.mdbell.terranet.common.game.messages.*;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.events.IncomingMessageEvent;
import me.mdbell.terranet.server.simple.IHandler;
import me.mdbell.terranet.server.simple.ServerHandler;
import me.mdbell.terranet.server.simple.data.ConnectionState;
import me.mdbell.terranet.server.simple.data.Item;
import me.mdbell.terranet.server.simple.engine.Player;

@Slf4j
@ExtensionMethod({ArrayExtensions.class, StringExtensions.class})
public class ConnectionHandshakeHandler implements Opcodes, IHandler {

    private ServerHandler handler;
    private final String version = Opcodes.DEFAULT_VERSION;
    private String password = null; //TODO get this is a property somehow

    public ConnectionHandshakeHandler(){

    }

    @Override
    public void init(ServerHandler handler) {
        this.handler = handler;
        ConnectionCtx.bus().subscribe(this);
    }

    @Override
    public void shutdown() {
        ConnectionCtx.bus().unsubscribe(this);
    }

    @Subscribe(priority = 10)
    public void onMessage(IncomingMessageEvent<Player> event) {
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
            case OP_REQUEST_WORLD -> onWorldRequest(ctx, (WorldDataRequestMessage) message);
            case OP_REQUEST_SPAWN -> onSpawnRequest(ctx, (RequestSpawnTilesMessage) message);
            default -> false;
        };
        if (consume) {
            event.consume();
        } else {
            //log.info("{} - {}", message, ctx.attrs().getConnectionState());
        }
    }

    private boolean onSpawnRequest(ConnectionCtx<Player> ctx, RequestSpawnTilesMessage message) {
        Player player = ctx.attrs();
        if(player.getConnectionState() != ConnectionState.WORLD_REQUESTED) {
            return false;
        }
        handler.sendWorldSection(ctx, message.getX(), message.getY());
        BufferedMessage msg = new BufferedMessage(129, 0);
        ctx.send(msg);
        //49 = loading complete, spawn player
        msg = new BufferedMessage(49, 0);
        ctx.send(msg);
        return true;
    }

    private boolean onWorldRequest(ConnectionCtx<Player> ctx, WorldDataRequestMessage message) {
        Player player = ctx.attrs();
        if (player.getConnectionState() != ConnectionState.BUFFS_SET) {
            return false;
        }
        player.setConnectionState(ConnectionState.WORLD_REQUESTED);
        handler.sendWorldInfo(ctx);
        return true;
    }

    private boolean onItem(ConnectionCtx<Player> ctx, SlotMessage message) {
        Player player = ctx.attrs();
        if (player.getConnectionState() != ConnectionState.BUFFS_SET) {
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
        if (player.getConnectionState() != ConnectionState.MANA_SET) {
            return false;
        }
        message.getBuffs().copyTo(player.getBuffs());
        player.setConnectionState(ConnectionState.BUFFS_SET);
        return true;
    }

    private boolean onHealth(ConnectionCtx<Player> ctx, PlayerHealthMessage message) {
        Player attrs = ctx.attrs();
        if (attrs.getConnectionState() != ConnectionState.UUID_SET) {
            return false;
        }
        attrs.setCurrentHp(message.getHp());
        attrs.setMaxHp(message.getMaxHp());
        attrs.setConnectionState(ConnectionState.HEALTH_SET);
        return true;
    }

    private boolean onMana(ConnectionCtx<Player> ctx, PlayerManaMessage message) {
        Player player = ctx.attrs();
        if (player.getConnectionState() != ConnectionState.HEALTH_SET) {
            return false;
        }
        player.setCurrentMana(message.getMana());
        player.setMaxMana(message.getMaxMana());
        player.setConnectionState(ConnectionState.MANA_SET);
        return true;
    }

    private boolean onPassword(ConnectionCtx<Player> ctx, PasswordResponseMessage message) {
        Player player = ctx.attrs();
        if (player.getConnectionState() != ConnectionState.PASSWORD_REQUESTED || this.password == null) {
            ctx.disconnect();
            return true;
        }
        if (!this.password.equals(message.getPassword())) {
            log.info("Received wrong password '{}'", message.getPassword());
            ctx.disconnect("Incorrect password".toLiteral());
            return true;
        }
        return registerConnection(ctx);
    }

    public boolean onConnect(ConnectionCtx<Player> ctx, ConnectionMessage msg) {
        Player attrs = ctx.attrs();
        if (attrs.getConnectionState() == ConnectionState.NEW) {
            if (!version.equals(msg.getVersion())) {
                ctx.disconnect("Unsupported version:{0}".toFormatted(msg.getVersion()));
            } else if (password != null) {
                ctx.requestPassword();
                attrs.setConnectionState(ConnectionState.PASSWORD_REQUESTED);
            } else {
                return registerConnection(ctx);
            }
        } else {
            ctx.disconnect("Bad connection".toLiteral());
        }
        return true;
    }

    private boolean registerConnection(ConnectionCtx<Player> ctx) {
        Player player = ctx.attrs();
        int id = handler.addConnection(ctx);
        if (id == -1) {
            log.info("Disconnecting connection due to max players");
            ctx.disconnect("Server full!".toLiteral());
        } else {
            ctx.slot(id, false);
            player.setId(id);
            player.setConnectionState(ConnectionState.ASSIGNED_ID);
        }
        return true;
    }

    public boolean onPlayerInfo(ConnectionCtx<Player> ctx, PlayerInfoMessage msg) {
        Player player = ctx.attrs();
        if (player.getConnectionState() != ConnectionState.ASSIGNED_ID) {
            return false;
        }
        //TODO set user info
        player.update(msg);
        player.setConnectionState(ConnectionState.INFO_SET);
        handler.sendServerMessage("{0} {1}".toFormatted(msg.getName(), "has joined!"));
        return true;
    }

    private boolean onUuid(ConnectionCtx<Player> ctx, UUIDMessage message) {
        Player attrs = ctx.attrs();
        if (attrs.getConnectionState() != ConnectionState.INFO_SET) {
            ctx.disconnect("Bad State".toLiteral());
            return true;
        }
        String uuid = message.getUuid();
        ConnectionCtx<Player> connected = handler.getPlayerByUuid(uuid);
        if (connected != null) {
            Player p = connected.attrs();
            log.info("{} is already on the server!", p.getName());
            ctx.disconnect("{0} {1}".toFormatted(p.getName(), "is already on the server"));
        } else {
            attrs.setUuid(uuid);
            attrs.setConnectionState(ConnectionState.UUID_SET);
        }
        return true;
    }
}
