package me.mdbell.terranet.common.game.messages;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class UserSlotMessage extends GameMessage {
    int slot;
    boolean flag;

    @Override
    public int getOpcode() {
        return OP_SET_USER_SLOT;
    }
}
