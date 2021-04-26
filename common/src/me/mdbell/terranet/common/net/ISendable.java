package me.mdbell.terranet.common.net;

import lombok.SneakyThrows;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.messages.ConnectionMessage;
import me.mdbell.terranet.common.game.messages.DisconnectMessage;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.game.messages.UserSlotMessage;
import me.mdbell.terranet.common.util.NetworkText;

import java.io.Closeable;

public interface ISendable extends Opcodes, Closeable {

    void send(GameMessage message);

    default void slot(int id, boolean flag) {
        send(UserSlotMessage.builder()
                .slot(id)
                .flag(flag)
                .build());
    }

    default void connect() {
        connect(DEFAULT_VERSION);
    }

    default void connect(String version) {
        send(ConnectionMessage.builder()
                .version(version)
                .build());
    }

    default void disconnect() {
        disconnect("Disconnected.", true);
    }

    default void disconnect(String reason){
        disconnect(reason, true);
    }

    default void disconnect(String reason, boolean close) {
        disconnect(NetworkText.literal(reason), close);
    }

    default void disconnect(NetworkText reason){
        disconnect(reason, true);
    }
    @SneakyThrows
    default void disconnect(NetworkText reason, boolean close) {
        send(DisconnectMessage.builder()
                .reason(reason)
                .build());
        if(close){
            close();
        }
    }
}
