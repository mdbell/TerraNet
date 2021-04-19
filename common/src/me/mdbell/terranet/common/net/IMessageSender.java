package me.mdbell.terranet.common.net;

import me.mdbell.terranet.common.game.messages.GameMessage;

public interface IMessageSender {

    boolean isActive();

    void sendMessage(GameMessage message);

    void close();
}
