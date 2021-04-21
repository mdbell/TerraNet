package me.mdbell.terranet.common.game.messages;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class UpdateBuffsMessage extends GameMessage {

    private int id;
    private final int[] buffs = new int[MAX_BUFFS];

    @Override
    public int getOpcode() {
        return OP_UPDATE_BUFFS;
    }

    public void setBuff(int index, int buff) {
        buffs[index] = buff;
    }

    public int getBuff(int index) {
        return buffs[index];
    }
}
