package me.mdbell.terranet.common.net;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.ext.StringExtensions;
import me.mdbell.terranet.common.game.messages.*;
import me.mdbell.terranet.common.game.messages.modules.OutgoingChatMessage;
import me.mdbell.terranet.common.util.Color;
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
        disconnect(StringExtensions.toLiteral("Disconnected."), true);
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

    default void sendServerMessage(NetworkText message){
        sendServerMessage(message, Color.WHITE);
    }

    default void sendServerMessage(NetworkText message, Color color){
        sendChatMessage(Opcodes.AUTHOR_SERVER, message, color);
    }

    default void sendChatMessage(int author, NetworkText message, Color color){
        send(OutgoingChatMessage.builder()
        .author(author)
        .text(message)
        .color(color)
        .build());
    }

    default void requestPassword(){
        send(PasswordRequestMessage.builder()
        .build());
    }
}
