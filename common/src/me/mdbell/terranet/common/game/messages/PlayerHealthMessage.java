package me.mdbell.terranet.common.game.messages;

import lombok.*;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public final class PlayerHealthMessage extends GameMessage {
    int id;
    int hp;
    int maxHp;

    @Override
    public int getOpcode() {
        return OP_PLAYER_HP;
    }
}
