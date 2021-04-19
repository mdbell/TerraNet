package me.mdbell.terranet.common.net;

import me.mdbell.terranet.common.game.messages.GameMessage;

public interface ISendable {

    void send(GameMessage message);
}
