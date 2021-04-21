package me.mdbell.terranet.common.game.messages;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import me.mdbell.terranet.common.util.Color;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public final class PlayerInfoMessage extends GameMessage {
    int id;
    int skin;
    int hair;
    String name;
    int hairDye;
    int hideVisual1;
    int hideVisual2;
    int hideMisc;

    Color hairColor;
    Color skinColor;
    Color eyeColor;
    Color shirtColor;
    Color underShirtColor;
    Color pantsColor;
    Color shoesColor;

    int difficulty;
    int torches;

    @Override
    public int getOpcode() {
        return OP_PLAYER_INFO;
    }
}
