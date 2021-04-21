package me.mdbell.terranet.common.game.messages;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class EssentialTilesMessage extends GameMessage {
    int x = -1, y = -1;

    @Override
    public int getOpcode() {
        return OP_ESSENTIAL_TILES;
    }
}
