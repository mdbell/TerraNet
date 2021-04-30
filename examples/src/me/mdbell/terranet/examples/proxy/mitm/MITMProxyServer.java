package me.mdbell.terranet.examples.proxy.mitm;

import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.client.events.ClientMessageEvent;
import me.mdbell.terranet.common.ext.StringExtensions;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.game.messages.modules.IncomingChatMessage;
import me.mdbell.terranet.common.game.messages.modules.OutgoingChatMessage;
import me.mdbell.terranet.common.util.Color;
import me.mdbell.terranet.common.util.NetworkText;
import me.mdbell.terranet.examples.proxy.ProxyServer;
import me.mdbell.terranet.server.events.IncomingMessageEvent;

@ExtensionMethod({StringExtensions.class})
public class MITMProxyServer extends ProxyServer {
    public MITMProxyServer(String remoteHost, int remotePort, int localPort) {
        super(remoteHost, remotePort, localPort);
    }

    @Override
    public void onIncomingMessage(IncomingMessageEvent<?> event) {
        GameMessage message = event.value();
        if (message.getModId() == Opcodes.MOD_TEXT && !message.isServer()) {
            String text = ((IncomingChatMessage) message).getMessage();
            if ("/ping".equalsIgnoreCase(text)) {
                event.source().send(OutgoingChatMessage.builder()
                        .author(-1)
                        .color(Color.GREEN)
                        .text("pong".toLiteral())
                        .build());
                return;
            }
        }
        super.onIncomingMessage(event);
    }

    @Override
    public void onOutgoingMessage(ClientMessageEvent<?> event) {
        GameMessage message = event.value();
        if (message.getModId() == Opcodes.MOD_TEXT && message.isServer()) {
            OutgoingChatMessage ocm = (OutgoingChatMessage) event.value();
            NetworkText text = ocm.text();
            if (text.mode() == NetworkText.Mode.FORMAT && "{0} {1}!".equals(text.text())) {
                ocm.text("Welcome to the Jungle".toLiteral());
                ocm.color(Color.GREEN);
            }
        }
        super.onOutgoingMessage(event);
    }

    public static void main(String[] args) throws Exception {
        new MITMProxyServer("127.0.0.1", 7777, 1337).run();
    }
}
