package me.mdbell.terranet.server.simple.handlers;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.ext.StringExtensions;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.game.messages.modules.IncomingChatMessage;
import me.mdbell.terranet.common.game.messages.modules.OutgoingChatMessage;
import me.mdbell.terranet.common.util.NetworkText;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.events.IncomingMessageEvent;
import me.mdbell.terranet.server.simple.IHandler;
import me.mdbell.terranet.server.simple.ServerHandler;
import me.mdbell.terranet.server.simple.engine.Player;
import me.mdbell.terranet.server.simple.events.GlobalMessageEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

@Slf4j
@ExtensionMethod({StringExtensions.class})
public class ChatHandler implements Opcodes, IHandler {

    private ServerHandler handler;
    private final Map<String, BiConsumer<ConnectionCtx<Player>, String>> commandHandlers = new HashMap<>();

    public ChatHandler() {

    }

    @Override
    public void init(ServerHandler handler) {
        this.handler = handler;
        commandHandlers.clear();
        commandHandlers.put("Say", (ctx, text) -> {
            Player p = ctx.attrs();
            handler.sendChatMessage(p.getId(), text.toLiteral(), p.getChatColor());
        });
        commandHandlers.put("Help", this::help);
        ServerHandler.bus().subscribe(this);
        ConnectionCtx.bus().subscribe(this);
    }

    @Override
    public void shutdown() {
        ServerHandler.bus().unsubscribe(this);
        ConnectionCtx.bus().unsubscribe(this);
    }

    public void help(ConnectionCtx<Player> ctx, String text){
        Player p = ctx.attrs();
        ctx.sendServerMessage("Name: {0} Id: {1}".toFormatted(p.getName(), p.getId()));
        ctx.sendServerMessage("UUID: {0}".toFormatted(p.getUuid()));
        ctx.sendServerMessage("Position: {0}".toFormatted(p.getPosition()));
        ctx.sendServerMessage("Health: {0}/{1}".toFormatted(p.getCurrentHp(), p.getMaxHp()));
        ctx.sendServerMessage("Mana: {0}/{1}".toFormatted(p.getCurrentMana(), p.getMaxMana()));
        ctx.sendServerMessage("Last Tick: {0} Current: {1}".toFormatted(p.getLastMessage(), handler.getLoop().getTick()));
    }

    @Subscribe
    public void onMessage(IncomingMessageEvent<Player> event) {
        ConnectionCtx<Player> ctx = event.source();
        GameMessage message = event.value();
        if (message.getModId() == Opcodes.MOD_TEXT) {
            IncomingChatMessage icm = (IncomingChatMessage) message;
            String command = icm.getCommand();
            String text = icm.getMessage();
            if (!commandHandlers.containsKey(command)) {
                log.warn("Unknown command:'{}' message:'{}'", command, text);
                ctx.sendServerMessage("Unknown command '{0}'".toFormatted(command));
            } else {
                commandHandlers.get(command).accept(ctx, text);
            }
        }
    }

    @Subscribe
    public void onGlobalMessage(GlobalMessageEvent event) {
        GameMessage message = event.value();
        if (message instanceof OutgoingChatMessage) {
            OutgoingChatMessage ocm = (OutgoingChatMessage) message;
            NetworkText author = "SERVER".toLiteral();
            int id = ocm.getAuthor();
            if (id != AUTHOR_SERVER) {
                ConnectionCtx<Player> c = event.source().getPlayerById(id);
                author = "{0}[{1}]".toFormatted(c == null ? "UNKNOWN" : c.attrs().getName(), String.valueOf(id));
            }
            log.info("[{}]: {}", author, ocm.getText());
        }
    }
}
