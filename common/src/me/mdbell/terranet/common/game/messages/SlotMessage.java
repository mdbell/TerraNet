package me.mdbell.terranet.common.game.messages;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class SlotMessage extends GameMessage {
    int id;
    int slot;
    int count;
    int prefix;
    int netId;

    @Override
    public int getOpcode() {
        return OP_SET_INVENTORY_SLOT;
    }
}
