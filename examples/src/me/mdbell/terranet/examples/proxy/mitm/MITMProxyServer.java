package me.mdbell.terranet.examples.proxy.mitm;

import me.mdbell.terranet.client.events.ClientMessageEvent;
import me.mdbell.terranet.common.game.messages.modules.OutgoingChatMessage;
import me.mdbell.terranet.common.util.Color;
import me.mdbell.terranet.common.util.NetworkText;
import me.mdbell.terranet.examples.proxy.ProxyServer;

public class MITMProxyServer extends ProxyServer {
    public MITMProxyServer(String remoteHost, int remotePort, int localPort) {
        super(remoteHost, remotePort, localPort);
    }

    @Override
    public void onClientMessage(ClientMessageEvent event) {
        if(event.message() instanceof OutgoingChatMessage) {
            OutgoingChatMessage message = (OutgoingChatMessage)event.message();
            NetworkText text = message.text();
            if(text.mode() == NetworkText.Mode.FORMAT && "{0} {1}!".equals(text.text())) {
                message.text(NetworkText.literal("Welcome to the Jungle"));
                message.color(Color.GREEN);
            }
        }
        super.onClientMessage(event);
    }

    public static void main(String[] args) throws Exception {
        new MITMProxyServer("127.0.0.1", 7777, 1337).run();
    }
}
