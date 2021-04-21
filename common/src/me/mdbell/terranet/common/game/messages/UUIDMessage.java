package me.mdbell.terranet.common.game.messages;

import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public final class UUIDMessage extends GameMessage {
    String uuid;

    @Override
    public int getOpcode() {
        return OP_UUID;
    }
}
