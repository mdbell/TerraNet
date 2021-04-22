package me.mdbell.terranet.examples.proxy.mitm;

import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.client.events.ClientMessageEvent;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.game.messages.modules.IncomingChatMessage;
import me.mdbell.terranet.common.game.messages.modules.OutgoingChatMessage;
import me.mdbell.terranet.common.util.Color;
import me.mdbell.terranet.common.util.NetworkText;
import me.mdbell.terranet.examples.proxy.ProxyServer;
import me.mdbell.terranet.server.events.ServerMessageEvent;

public class MITMProxyServer extends ProxyServer {
    public MITMProxyServer(String remoteHost, int remotePort, int localPort) {
        super(remoteHost, remotePort, localPort);
    }

    @Override
    public void onIncomingMessage(ServerMessageEvent<?> event) {
        GameMessage message = event.message();
        if (message.getModId() == Opcodes.MOD_TEXT && !message.isServer()) {
            String text = ((IncomingChatMessage) message).getMessage();
            if ("/ping".equalsIgnoreCase(text)) {
                event.source().send(OutgoingChatMessage.builder()
                        .author(-1)
                        .color(Color.GREEN)
                        .text(NetworkText.literal("pong"))
                        .build());
                return;
            }
        }
        super.onIncomingMessage(event);
    }

    @Override
    public void onOutgoingMessage(ClientMessageEvent<?> event) {
        GameMessage message = event.message();
        if (message.getModId() == Opcodes.MOD_TEXT && message.isServer()) {
            OutgoingChatMessage ocm = (OutgoingChatMessage) event.message();
            NetworkText text = ocm.text();
            if (text.mode() == NetworkText.Mode.FORMAT && "{0} {1}!".equals(text.text())) {
                ocm.text(NetworkText.literal("Welcome to the Jungle"));
                ocm.color(Color.GREEN);
            }
        }
        super.onOutgoingMessage(event);
    }

    public static void main(String[] args) throws Exception {
        new MITMProxyServer("127.0.0.1", 7777, 1337).run();
    }
}
