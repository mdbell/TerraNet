package me.mdbell.terranet.common.game.messages;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import me.mdbell.terranet.common.util.NetworkText;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public final class DisconnectMessage extends GameMessage {

    NetworkText reason;

    @Override
    public int getOpcode() {
        return OP_DISCONNECT;
    }
}
