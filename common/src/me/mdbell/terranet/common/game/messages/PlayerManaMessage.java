package me.mdbell.terranet.common.game.messages;

import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
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
