package me.mdbell.terranet.common.game.transcoders;

import lombok.SneakyThrows;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.ext.BufferExtensions;
import me.mdbell.terranet.common.game.messages.TileSectionMessage;
import me.mdbell.terranet.common.game.scene.*;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.net.FilteredMessageTranscoder;
import me.mdbell.terranet.metadata.TileMetadata;
import me.mdbell.terranet.metadata.TileMetadataFactory;

import java.nio.ByteBuffer;
import java.util.zip.Inflater;

@Slf4j
@ExtensionMethod({BufferExtensions.class})
public class TileSectionTranscoder extends FilteredMessageTranscoder<TileSectionMessage> {

    private static final TileMetadata[] meta = TileMetadataFactory.getDefaultFactory().getMetadata();

    public TileSectionTranscoder() {
        super(OP_TILE_SECTION);
    }

    @SneakyThrows
    @Override
    protected TileSectionMessage filteredDecode(int size, Buffer<?> buff) {
        boolean compressed = buff.readBoolean();
        if (compressed) {
            try {
                byte[] tmp = new byte[size - 1];
                byte[] decompressed = new byte[tmp.length * 4];
                buff.readBytes(tmp);
                Inflater inflater = new Inflater(true);
                inflater.setInput(tmp);
                size = inflater.inflate(decompressed);
                buff = Buffer.wrap(decompressed, 0, size);
            } catch (Throwable t) {
                log.error("Unable to decompress frame!", t);
                return null;
            }
        }
        int width, height;
        TileSectionMessage.TileSectionMessageBuilder builder = TileSectionMessage.builder();
        builder.x(buff.readIntLE())
                .y(buff.readIntLE())
                .width(width = buff.readUnsignedShortLE())
                .height(height = buff.readUnsignedShortLE());
        Tile[] tiles = new Tile[width * height];
        decodeTiles(buff, tiles);
        int count = buff.readUnsignedShortLE();
        Chest[] chests = new Chest[count];
        decodeChests(buff, chests);
        count = buff.readUnsignedShortLE();
        Sign[] signs = new Sign[count];
        decodeSigns(buff, signs);
        count = buff.readUnsignedShortLE();
        TileEntity[] entities = new TileEntity[count];
        decodeEntities(buff, entities);
        builder.tiles(tiles)
                .chests(chests)
                .signs(signs)
                .entities(entities);
        return builder.build();
    }

    private void decodeTiles(Buffer<?> buff, Tile[] tiles) {
        int rle = 0;
        Tile.TileBuilder rleBuilder = null;
        for (int i = 0; i < tiles.length; i++) {
            if (rle > 0) {
                rle--;
                tiles[i] = rleBuilder.build();
            } else {
                Tile.TileBuilder builder = Tile.builder();

                rle = decodeTile(buff, builder);

                tiles[i] = builder.build();

                if (rle > 0) {
                    rleBuilder = builder;
                }
            }
        }
    }

    private int decodeTile(Buffer<?> reader, Tile.TileBuilder tile) {
        int type = 0, wall = 0;
        int flag1 = reader.readUnsignedByte();
        int flag2 = 0;
        int flag3 = 0;
        if ((flag1 & 1) == 1) {
            flag2 = reader.readUnsignedByte();
            if ((flag2 & 1) == 1) {
                flag3 = reader.readUnsignedByte();
            }
        }
        boolean active = false;
        if ((flag1 & 2) == 2) {
            tile.active(true);
            int index3;
            if ((flag1 & 32) == 32) {
                index3 = reader.readUnsignedShortLE();
            } else {
                index3 = reader.readUnsignedByte();
            }
            tile.type(index3);

            if (meta[index3].frameImportant) {
                tile.frameX(reader.readUnsignedShortLE())
                        .frameY(reader.readUnsignedShortLE());
            } else {
                tile.frameX(-1).frameY(-1);
            }
            if ((flag3 & 8) == 8) {
                tile.color(reader.readUnsignedByte());
            }
        }
        if ((flag1 & 4) == 4) {
            tile.wall(wall = reader.readUnsignedByte());
            if ((flag3 & 16) == 16) {
                tile.wallColor(reader.readUnsignedByte());
            }
        }
        int num6 = ((flag1 & 24) >> 3);
        if (num6 != 0) {
            tile.liquid(reader.readUnsignedByte());
            tile.liquidType(LiquidType.valueOf(num6));
        }
        if (flag2 > (byte) 1) {
            if ((flag2 & 2) == 2) {
                tile.wire(true);
            }
            if ((flag2 & 4) == 4) {
                tile.wire2(true);
            }
            if ((flag2 & 8) == 8) {
                tile.wire3(true);
            }
            byte num5 = (byte) ((flag2 & 112) >> 4);

            if (num5 != (byte) 0 && meta[type].solid) {
                if (num5 == (byte) 1) {
                    tile.halfBrick(true);
                } else {
                    tile.slope(num5 - 1);
                }
            }
        }
        if (flag3 > (byte) 0) {
            if ((flag3 & 2) == 2) {
                tile.actuator(true);
            }
            if ((flag3 & 4) == 4) {
                tile.inActive(true);
            }
            if ((flag3 & 32) == 32) {
                tile.wire4(true);
            }
            if ((flag3 & 64) == 64) {
                int num5 = reader.readUnsignedByte();
                wall <<= 8;
                tile.wall(wall & num5);
            }
        }
        return switch ((byte) ((flag1 & 192) >> 6)) {
            case 0 -> 0;
            case 1 -> reader.readUnsignedByte();
            default -> reader.readUnsignedShortLE();
        };
    }

    private void decodeChests(Buffer<?> buff, Chest[] chests) {
        for (int i = 0; i < chests.length; i++) {
            chests[i] = Chest.builder()
                    .index(buff.readShortLE())
                    .x(buff.readShortLE())
                    .y(buff.readShortLE())
                    .name(buff.readString())
                    .build();
        }
    }

    private void decodeSigns(Buffer<?> buff, Sign[] signs) {
        for (int i = 0; i < signs.length; i++) {
            signs[i] = Sign.builder()
                    .id(buff.readShortLE())
                    .x(buff.readShortLE())
                    .y(buff.readShortLE())
                    .text(buff.readString())
                    .build();
        }
    }

    private void decodeEntities(Buffer<?> buff, TileEntity[] entities) {
        if (entities.length > 0) {
            throw new UnsupportedOperationException("Unimplemented");
        }
    }

    private final byte[] tmp = new byte[65535];

    @Override
    protected synchronized boolean filteredEncode(Buffer<?> to, TileSectionMessage message) {
        int compressedOffset = to.writerIndex();
        boolean compressed = false;
        to.writeByte(0); // skip the compressed section (for now)
        Buffer<ByteBuffer> uncompressed = Buffer.wrap(tmp);
        uncompressed.writeIntLE(message.getX())
                .writeIntLE(message.getY())
                .writeShortLE(message.getWidth())
                .writeShortLE(message.getHeight());

        Tile[] tiles = message.getTiles();
        if (tiles != null) {
            int last = tiles.length - 1;
            int rle = 0;
            for (int i = 0; i < tiles.length; i++) {
                Tile curr = tiles[i];
                Tile next = i < last ? tiles[i + 1] : null;
                if (curr.equals(next)) {
                    rle++;
                } else {
                    encodeTile(uncompressed, rle, curr);
                    rle = 0;
                }
            }
        }
        //TODO chests
        uncompressed.writeShortLE(0);
        //TODO signs
        uncompressed.writeShortLE(0);
        //TODO entities
        uncompressed.writeShortLE(0);
        to.writeBytes(tmp, 0, uncompressed.writerIndex());
        int tmp = to.writerIndex();
        to.writerIndex(compressedOffset);
        to.writeBoolean(compressed);
        to.writerIndex(tmp);
        return true;
    }

    private void encodeTile(Buffer<ByteBuffer> to, int rle, Tile tile) {
        int[] flags = getFlags(tile, rle);
        to.writeByte(flags[0]);
        if (flags[1] != 0) {
            to.writeByte(flags[1]);
            if (flags[2] != 0) {
                to.writeByte(flags[2]);
            }
        }
        if (tile.isActive()) {
            int type = tile.getType();
            if (type > 255) {
                to.writeShortLE(type);
            } else {
                to.writeByte(type);
            }
            if (meta[type].frameImportant) {
                to.writeShortLE(tile.getFrameX())
                        .writeShortLE(tile.getFrameY());
            }
            if (tile.getColor() != 0) {
                to.writeByte(tile.getColor());
            }
        }
        if (tile.getWall() > 0) {
            int wall = tile.getWall();
            if (wall > 255) {
                wall = (wall >> 8) & 0xFF;
            }
            to.writeByte(wall);
            if (tile.getWallColor() != 0) {
                to.writeByte(tile.getWallColor());
            }
        }
        if (tile.getLiquidType() != LiquidType.NONE) {
            to.writeByte(tile.getLiquid());
        }
        if (tile.getWall() > 255) {
            to.writeByte(tile.getWall() & 0xFF);
        }
        if (rle > 255) {
            to.writeShortLE(rle);
        } else if (rle > 0) {
            to.writeByte(rle);
        }
    }

    private int[] getFlags(Tile tile, int rle) {
        int flag1 = 0, flag2 = 0, flag3 = 0;

        if (tile.isActive()) {
            flag1 |= 2;
            if (tile.getType() > 255) {
                flag1 |= 32;
            }
            if (tile.getColor() != 0) {
                flag3 |= 8;
            }
        }
        if (tile.getWall() != 0) {
            flag1 |= 4;
            if (tile.getWallColor() != 0) {
                flag3 |= 16;
            }
        }
        flag1 |=  tile.getLiquidType().getMask();
        if (tile.isWire()) {
            flag2 |= 2;
        }
        if (tile.isWire2()) {
            flag2 |= 4;
        }
        if (tile.isWire3()) {
            flag2 |= 8;
        }

        if (tile.getSlope() > 0) {
            {
                flag2 |= (tile.getSlope() + 1) << 4;
            }
        }

        if (tile.isHalfBrick()) {
            flag2 |= 1 << 4;
        }

        if (tile.isActuator()) {
            flag3 |= 2;
        }

        if (tile.isInActive()) {
            flag3 |= 4;
        }

        if (tile.isWire4()) {
            flag3 |= 32;
        }

        if (tile.getWall() > 255) {
            flag3 |= 64;
        }

        if (flag3 > 0) {
            flag2 |= 1;
        }

        if (flag2 > 0) {
            flag1 |= 1;
        }

        if (rle > 255) {
            flag1 |= 2 << 6;
        } else if (rle > 0) {
            flag1 |= 1 << 6;
        }

        return new int[]{flag1, flag2, flag3};
    }
}
