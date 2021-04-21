package me.mdbell.terranet.common.game.messages;

import lombok.*;
import me.mdbell.terranet.common.util.NetworkText;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class DisconnectMessage extends GameMessage {

    NetworkText reason;

    @Override
    public int getOpcode() {
        return OP_DISCONNECT;
    }
}
