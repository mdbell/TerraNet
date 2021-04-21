package me.mdbell.terranet.common.game.messages;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class PlayerManaMessage extends GameMessage {

    int id;
    int mana;
    int maxMana;

    @Override
    public int getOpcode() {
        return OP_PLAYER_MANA;
    }
}
