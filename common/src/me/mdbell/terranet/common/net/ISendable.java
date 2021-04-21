package me.mdbell.terranet.common.net;

import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.messages.ConnectionMessage;
import me.mdbell.terranet.common.game.messages.DisconnectMessage;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.util.NetworkText;

public interface ISendable extends Opcodes {

    void send(GameMessage message);

    default void connect() {
        connect(DEFAULT_VERSION);
    }

    default void connect(String version) {
        send(ConnectionMessage.builder()
                .version(version)
                .build());
    }

    default void disconnect() {
        disconnect("Disconnected.");
    }

    default void disconnect(String reason) {
        disconnect(NetworkText.literal(reason));
    }

    default void disconnect(NetworkText reason) {
        send(DisconnectMessage.builder()
                .reason(reason)
                .build());
    }
}
