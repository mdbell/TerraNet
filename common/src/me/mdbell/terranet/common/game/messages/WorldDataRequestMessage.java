package me.mdbell.terranet.common.game.messages;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class WorldDataRequestMessage extends GameMessage {
    @Override
    public int getOpcode() {
        return OP_REQUEST_WORLD;
    }
}
