package me.mdbell.terranet.common.game.messages;

import lombok.*;
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
