package me.mdbell.terranet.world.reader;

import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.files.metadata.TileMetadata;
import me.mdbell.terranet.files.metadata.TileMetadataFactory;
import me.mdbell.terranet.world.LiquidType;
import me.mdbell.terranet.world.TileDataVisitor;
import me.mdbell.terranet.world.TileVisitor;
import me.mdbell.terranet.world.WorldFileConstants;

import java.util.BitSet;

public class TileReader implements WorldFileConstants {

    private TileDataVisitor visitor;
    private final Buffer<?> buffer;

    private int width;
    private int height;

    private BitSet important;
    private static final TileMetadata[] TILE_METADATA = TileMetadataFactory.getDefaultFactory().getMetadata();

    public TileReader(Buffer<?> buffer) {
        this.buffer = buffer;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setImportant(BitSet important) {
        this.important = important;
    }

    public void setVisitor(TileDataVisitor visitor){
        this.visitor = visitor;
    }

    public void visitAllTiles(){
        for(int x = 0; x < width; x++){
            visitor.visitTileX(x);
            for(int y = 0; y < height;) {
                y += visitNextTile(x, y);
            }
        }
    }

    public int visitNextTile(int x, int y) {
        int result = 0;
        int type = 0;
        boolean active = false;
        int frameX = -1, frameY = -1;
        int color = 0;
        int wall = 0, wallColor = 0;
        int liquidQuantity = 0;
        LiquidType liquidType = LiquidType.NONE;
        boolean wire1 = false, wire2 = false, wire3 = false;
        boolean halfBrick = false;
        int slope = 0;
        boolean actuator = false, inactive = false, wire4 = false;

        int index3;
        byte flags3;
        byte flags2 = flags3 = 0;

        int flags1 = buffer.readUnsignedByte();
        if ((flags1 & TILE_EXT_FLAGS_MASK) != 0) {
            flags2 = buffer.readByte();
            if ((flags2 & TILE_EXT_FLAGS_2_MASK) != 0) {
                flags3 = buffer.readByte();
            }
        }
        if ((flags1 & TILE_ACTIVE_MASK) != 0) {
            active = true;
            if ((flags1 & TILE_WIDE_TYPE_MASK) != 0) {
                index3 = buffer.readUnsignedShortLE();
            } else {
                index3 = buffer.readUnsignedByte();
            }
            type = index3;
            if (important.get(index3)) {
                frameX = buffer.readShortLE();
                frameY = buffer.readShortLE();
                if (frameY == 144) {
                    frameY = 0;
                }
            } else {
                frameX = -1;
                frameY = -1;
            }
            if ((flags3 & TILE_COLOR_MASK) != 0) {
                color = buffer.readByte();
            }
        }
        if ((flags1 & TILE_WALL_MASK) != 0) {
            wall = buffer.readUnsignedByte();
            if ((flags3 & TILE_WALL_COLOR_MASK) != 0) {
                wallColor = buffer.readUnsignedByte();
            }
        }
        byte num6 = (byte) ((flags1 & TILE_LIQUID_MASK) >> 3);
        if (num6 != 0) {
            liquidQuantity = buffer.readUnsignedByte();
            liquidType = switch (num6) {
                case 2 -> LiquidType.LAVA;
                case 3 -> LiquidType.HONEY;
                default -> LiquidType.WATER;
            };
        }
        if (flags2 > 1) {
            wire1 = (flags2 & TILE_WIRE_1_MASK) != 0;
            wire2 = (flags2 & TILE_WIRE_2_MASK) != 0;
            wire3 = (flags2 & TILE_WIRE_3_MASK) != 0;

            byte num5 = (byte) (((int) flags2 & TILE_SLOPE_MASK) >> 4);
            if (num5 != 0 && (TILE_METADATA[type].solid || TILE_METADATA[type].forceSaveSlope)) {
                if (num5 == (byte) 1) {
                    halfBrick = true;
                } else {
                    slope = num5 - 1;
                }
            }
        }
        if (flags3 > 0) {
            if (((int) flags3 & TILE_ACTUATOR_MASK) != 0) {
                actuator = true;
            }
            if (((int) flags3 & TILE_ACTUATED_MASK) != 0) {
                inactive = true;
            }
            if (((int) flags3 & TILE_WIRE_4_MASK) != 0) {
                wire4 = true;
            }
            if ((flags3 & TILE_WALL_EXTENDED_ID_MASK) != 0) {
                int num5 = buffer.readUnsignedByte();
                //wall <<= 8;
                num5 <<= 8;
                wall |= num5;
                if (wall >= 316) {
                    wall = 0;
                }
            }
        }
        int rleCount = switch ((flags1 & TILE_RLE_MASK) >> 6) {
            case 0 -> 0;
            case 1 -> buffer.readUnsignedByte();
            default -> buffer.readUnsignedShortLE();
        };
        for (; rleCount >= 0; --rleCount) {
            TileVisitor tv = visitor.visitTile(x, y);
            if (tv != null) {
                tv.visitStart();
                tv.visitActive(active);
                if (active) {
                    tv.visitType(type);
                    tv.visitFrame(frameX, frameY);
                }
                if (color != 0) {
                    tv.visitColor(color);
                }
                if (wall != 0) {
                    tv.visitWall(wall);
                }
                if (wallColor != 0) {
                    tv.visitWallColor(wallColor);
                }
                if (liquidQuantity != 0) {
                    tv.visitLiquid(liquidType, liquidQuantity);
                }
                if (slope != 0) {
                    tv.visitSlope(slope);
                } else if (halfBrick) {
                    tv.visitBrick();
                }
                tv.visitWire(wire1, wire2, wire3, wire4);
                if (actuator) {
                    tv.visitActuator(inactive);
                }
                tv.visitEnd();
                result++;
            }
        }
        return result;
    }
}