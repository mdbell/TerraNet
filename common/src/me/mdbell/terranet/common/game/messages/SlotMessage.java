package me.mdbell.terranet.common.game.messages;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
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
