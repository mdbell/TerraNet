package me.mdbell.terranet.common.game.messages;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import me.mdbell.terranet.common.game.scene.Chest;
import me.mdbell.terranet.common.game.scene.Sign;
import me.mdbell.terranet.common.game.scene.Tile;
import me.mdbell.terranet.common.game.scene.TileEntity;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class TileSectionMessage extends GameMessage{
    int x, y;
    int width, height;
    Tile[] tiles;
    Chest[] chests;
    Sign[] signs;
    TileEntity[] entities;

    @Override
    public int getOpcode() {
        return OP_TILE_SECTION;
    }

}
