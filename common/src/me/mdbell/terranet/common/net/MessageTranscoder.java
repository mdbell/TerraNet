package me.mdbell.terranet.common.net;

import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.game.messages.GameMessage;

public interface MessageTranscoder extends Opcodes {

    GameMessage decode(int id, int size, Buffer<?> in);

    boolean encode(Buffer<?> out, GameMessage message);

    boolean isServer();

    void setServer(boolean isServer);
}
