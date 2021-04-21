package me.mdbell.terranet.common.game.messages;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import me.mdbell.terranet.Opcodes;

@ToString
@EqualsAndHashCode
public abstract class GameMessage implements Opcodes {

    public abstract int getOpcode();

    public int getModId() {
        return -1;
    }

    public boolean isServer() {
        return false;
    }
}
