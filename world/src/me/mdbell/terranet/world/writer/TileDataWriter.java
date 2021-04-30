package me.mdbell.terranet.world.writer;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.game.scene.LiquidType;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.world.TileDataVisitor;
import me.mdbell.terranet.world.TileVisitor;
import me.mdbell.terranet.world.WorldFileConstants;
import me.mdbell.terranet.world.tree.TileNode;

@Slf4j
public class TileDataWriter implements TileDataVisitor, WorldFileConstants {

    private final Buffer<?> buffer;

    private int count;
    private TileNode prev;
    private TileNode curr;

    public TileDataWriter(Buffer<?> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void visitStart() {

    }

    @Override
    public void visitTileX(int x) {
        writePrev();
        prev = curr;
        writePrev();
        this.count = 0;
        this.prev = this.curr = null;
    }

    @Override
    public TileVisitor visitTile(int x, int y) {
        if (curr == null) {
            return curr = new TileNode();
        } else if (prev != null) {
            if (prev.equals(curr)) {
                count++;
            } else {
                writePrev();
            }
        }
        prev = curr;
        return curr = new TileNode();
    }

    @Override
    public void visitEnd() {
        writePrev();
        prev = curr;
        writePrev();
    }

    private void writePrev() {
        if (prev == null) {
            return;
        }
        int flag1 = 0;
        int flag2 = 0;
        int flag3 = 0;

        if (count > 0) {
            if (count <= 255) {
                flag1 |= TILE_RLE_BYTE;
            } else {
                flag1 |= TILE_RLE_WIDE;
            }
        }

        if (prev.getWall() > 255) {
            flag3 |= TILE_WALL_EXTENDED_ID_MASK;
        }
        if(prev.isWire4()) {
            flag3 |= TILE_WIRE_4_MASK;
        }
        if (prev.isActuator()) {
            flag3 |= TILE_ACTUATED_MASK;
        }
        if (prev.isInActive()) {
            flag3 |= TILE_ACTUATOR_MASK;
        }
        if (prev.isHalfBrick()) {
            flag2 |= TILE_SLOPE_BRICK_MASK;
        } else if (prev.getSlope() > 0) {
            flag2 |= (prev.getSlope() + 1) << 4;
        }

        if (prev.isWire3()) {
            flag2 |= TILE_WIRE_3_MASK;
        }

        if (prev.isWire2()) {
            flag2 |= TILE_WIRE_2_MASK;
        }

        if (prev.isWire()) {
            flag2 |= TILE_WIRE_1_MASK;
        }

        if (prev.getLiquidType() != LiquidType.NONE) {
            flag1 |= prev.getLiquidType().getMask();
        }
        if (prev.getWallColor() != 0) {
            flag3 |= TILE_WALL_COLOR_MASK;
        }
        if (prev.getWall() != 0) {
            flag1 |= TILE_WALL_MASK;
        }
        if (prev.getColor() != 0) {
            flag3 |= TILE_COLOR_MASK;
        }
        if (prev.getType() > 255) {
            flag1 |= TILE_WIDE_TYPE_MASK;
        }
        if (prev.isActive()) {
            flag1 |= TILE_ACTIVE_MASK;
        }

        if (flag3 != 0) {
            flag2 |= TILE_EXT_FLAGS_2_MASK;
        }

        if (flag2 != 0) {
            flag1 |= TILE_EXT_FLAGS_MASK;
        }
        log.info("flags: {}, {}, {}", flag1, flag2, flag3);
        buffer.writeByte(flag1);
        if (flag2 != 0) {
            buffer.writeByte(flag2);
            if (flag3 != 0) {
                buffer.writeByte(flag3);
            }
        }
        if (prev.isActive()) {
            int type = prev.getType();
            if (type > 255) {
                buffer.writeShortLE(type);
            } else {
                buffer.writeByte(type);
            }
            if (prev.getFrameX() != -1 || prev.getFrameY() != -1) {
                buffer.writeShortLE(prev.getFrameX());
                buffer.writeShortLE(prev.getFrameY());
            }
            if (prev.getColor() != 0) {
                buffer.writeByte(prev.getColor());
            }
        }
        if (prev.getWall() != 0) {
            int wall = prev.getWall();
            if (wall > 255) {
                wall >>= 8;
                wall &= 0xFF;
            }
            buffer.writeByte(wall);
            if (prev.getWallColor() != 0) {
                buffer.writeByte(prev.getWallColor());
            }
        }
        if (prev.getLiquid() > 0) {
            buffer.writeByte(prev.getLiquid());
        }

        if (prev.getWall() > 255) {
            buffer.writeByte(prev.getWall() & 0xFF);
        }
        if (count > 255) {
            buffer.writeShortLE(count);
        } else if (count > 0) {
            buffer.writeByte(count);
        }
        count = 0;
    }
}
