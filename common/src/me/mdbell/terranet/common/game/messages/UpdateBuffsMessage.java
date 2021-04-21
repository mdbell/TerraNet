package me.mdbell.terranet.common.game.messages;

import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public final class UpdateBuffsMessage extends GameMessage {

    int id;
    final int[] buffs = new int[MAX_BUFFS];

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
