package me.mdbell.terranet.world;

import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.util.IOUtil;
import me.mdbell.terranet.files.FileType;
import me.mdbell.terranet.files.GameMode;
import me.mdbell.terranet.files.SharedHeaderVisitor;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@ExtensionMethod({IOUtil.class})
public class WorldReader {

    public static final int SUPPORTED_VERSION = 237;

    private static final int METADATA_OFFSET = 0;
    private static final int TILES_OFFSET = 1;
    private static final int CHESTS_OFFSET = 2;
    private static final int SIGNS_OFFSET = 3;
    private static final int NPC_OFFSET = 4;
    private static final int ENTITIES_OFFSET = 5;
    private static final int PRESSURE_PLATE_OFFSET = 6;
    private static final int TOWN_OFFSET = 7;
    private static final int BESTIARY_OFFSET = 8;
    private static final int CREATIVE_OFFSET = 9;
    private static final int FOOTER_OFFSET = 10;

    private final Buffer<?> buffer;

    public WorldReader(Buffer<?> buffer) {
        this.buffer = buffer;
    }

    public void accept(WorldVisitor visitor) throws ReaderException {
        int version = buffer.readIntLE();
        if (version > SUPPORTED_VERSION) {
            throw new ReaderException("Unsupported game version:" + version);
        }
        visitor.visitStart();
        SharedHeaderVisitor header = visitor.visitFileHeader();
        visitHeader(header);
        int count = buffer.readUnsignedShortLE();
        int[] offsets = new int[count];
        for (int i = 0; i < count; i++) {
            offsets[i] = buffer.readIntLE();
        }
        count = buffer.readUnsignedShortLE();
        boolean[] important = new boolean[count];
        byte somethingElse = 0;
        byte something = (byte) 128;
        for (int i = 0; i < count; i++) {
            if (something == (byte) 128) {
                somethingElse = buffer.readByte();
                something = 1;
            } else {
                something <<= 1;
            }
            if ((something & somethingElse) == something) {
                important[i] = true;
            }
        }
        for (int i = 0; i < offsets.length; i++) {
            int offset = offsets[i];
            if (buffer.readerIndex() != offset) {
                throw new ReaderException("Invalid offset for index:"
                        + i + " Expected:" + offset + " Real:" + buffer.readerIndex());
            }
            switch (i) {
                case METADATA_OFFSET:
                    MetadataVisitor metadata = visitor.visitMetadata();
                    if (metadata != null) {
                        visitMetadata(metadata, version);
                        continue;
                    }
                    break;
                case TILES_OFFSET:
                    TileDataVisitor tiles = visitor.visitTileData();
                    if (tiles != null) {
                        visitTiles(tiles, version, important);
                        continue;
                    }
                    break;
                case CHESTS_OFFSET:
                    ChestVisitor chests = visitor.visitChests();
                    if (chests != null) {
                        visitChests(chests, version);
                        continue;
                    }
                    break;
                case SIGNS_OFFSET:
                    SignsVisitor signs = visitor.visitSigns();
                    if (signs != null) {
                        visitSigns(signs, version);
                        continue;
                    }
                    break;
                case NPC_OFFSET:
                    NPCVisitor npcs = visitor.visitNpcs();
                    if (npcs != null) {
                        visitNpcs(npcs, version);
                        continue;
                    }
                    break;
                case ENTITIES_OFFSET:
                    TileEntitiesVisitor entities = visitor.visitEntities();
                    if (entities != null) {
                        visitEntities(entities, version);
                        continue;
                    }
                    break;
                case PRESSURE_PLATE_OFFSET:
                    PressurePlatesVisitor plates = visitor.visitPressurePlates();
                    if (plates != null) {
                        visitPlates(plates, version);
                        continue;
                    }
                    break;
                case TOWN_OFFSET:
                    TownVisitor town = visitor.visitTown();
                    if (town != null) {
                        visitTown(town, version);
                        continue;
                    }
                    break;
                case BESTIARY_OFFSET:
                    BestiaryVistor bestiary = visitor.visitBestiary();
                    if (bestiary != null) {
                        visitBestiary(bestiary, version);
                        continue;
                    }
                    break;
                case CREATIVE_OFFSET:
                    CreativePowersVisitor creative = visitor.visitCreativePowers();
                    if (creative != null) {
                        visitCreative(creative, version);
                        continue;
                    }
                    break;
                case FOOTER_OFFSET:
                    visitor.visitFooter(buffer.readBoolean(), buffer.readString(), buffer.readIntLE());
                    continue;
                default:
                    throw new ReaderException("Unexpected offset! index:" + i + " offset:" + offset);
            }
            buffer.readerIndex(offsets[i + 1]);
        }
        visitor.visitEnd();
    }

    private void visitCreative(CreativePowersVisitor visitor, int version) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitBestiary(BestiaryVistor visitor, int version) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitTown(TownVisitor visitor, int version) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitPlates(PressurePlatesVisitor visitor, int version) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitEntities(TileEntitiesVisitor visitor, int version) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitNpcs(NPCVisitor visitor, int version) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitSigns(SignsVisitor visitor, int version) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitChests(ChestVisitor visitor, int version) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitTiles(TileDataVisitor visitor, int version, boolean[] important) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    private void visitMetadata(MetadataVisitor visitor, int version) {
        visitor.visitStart();
        visitor.visitName(buffer.readString());

        String seed = "";
        if (version == 179) {
            seed = String.valueOf(buffer.readIntLE());
        } else if (version > 179) {
            seed = buffer.readString();
        }
        visitor.visitSeed(seed);

        if (version >= 179) {
            visitor.visitWorldGenVersion(buffer.readLongLE());
        } else {
            visitor.visitWorldGenVersion(0);
        }

        if (version > 180) {
            visitor.visitGuid(buffer.readGuid());
        } else {
            visitor.visitGuid(UUID.randomUUID());
        }

        visitor.visitId(buffer.readIntLE());

        visitor.visitDimensions(buffer.readIntLE(), buffer.readIntLE(), buffer.readIntLE(), buffer.readIntLE());

        visitor.visitSize(buffer.readIntLE(), buffer.readIntLE());

        if (version > 209) {
            visitor.visitGameMode(GameMode.values()[buffer.readIntLE()]);
            if (version >= 222) {
                visitor.visitDrunkWorld(buffer.readBoolean());
            } else {
                visitor.visitDrunkWorld(false);
            }
            if (version >= 227) {
                visitor.visitGood(buffer.readBoolean());
            } else {
                visitor.visitGood(false);
            }
        } else {
            GameMode mode;
            if (version < 112) {
                mode = GameMode.NORMAL;
            } else {
                mode = buffer.readBoolean() ? GameMode.EXPERT : GameMode.NORMAL;
            }
            if (version == 208 && buffer.readBoolean()) {
                mode = GameMode.MASTER;
            }
            visitor.visitGameMode(mode);
            visitor.visitDrunkWorld(false);
            visitor.visitGood(false);
        }

        if (version >= 141) {
            visitor.visitCreationTime(buffer.readLongLE());
        } else {
            visitor.visitCreationTime(System.currentTimeMillis());
        }
        visitor.visitMoonType(buffer.readByte());
        for (int i = 0; i < 3; i++) {
            visitor.visitTreeX(i, buffer.readIntLE());
        }
        for (int i = 0; i < 4; i++) {
            visitor.visitTreeStyle(i, buffer.readIntLE());
        }
        for (int i = 0; i < 3; i++) {
            visitor.visitCaveBackX(i, buffer.readIntLE());
        }
        for (int i = 0; i < 4; i++) {
            visitor.visitCaveBackStyle(i, buffer.readIntLE());
        }
        visitor.visitIceBackStyle(buffer.readIntLE());
        visitor.visitJungleBackStyle(buffer.readIntLE());
        visitor.visitHellBackStyle(buffer.readIntLE());
        visitor.visitSpawnLocation(buffer.readIntLE(), buffer.readIntLE());
        visitor.visitSurface(buffer.readDoubleLE());
        visitor.visitRockLayer(buffer.readDoubleLE());
        visitor.visitTime(buffer.readDoubleLE());
        visitor.visitDaytime(buffer.readBoolean());
        visitor.visitMoonPhase(buffer.readIntLE());
        visitor.visitBloodmoon(buffer.readBoolean());
        visitor.visitEclipse(buffer.readBoolean());
        visitor.visitDungeonLocation(buffer.readIntLE(), buffer.readIntLE());
        visitor.visitCrimson(buffer.readBoolean());
        visitor.visitNormalBossFlags(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
        visitor.visitMechBossFlags(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
        boolean plant = buffer.readBoolean();
        boolean golem = buffer.readBoolean();
        boolean slime = false;
        if (version >= 118) {
            slime = buffer.readBoolean();
        }
        visitor.visitHardmodeBossFlags(plant, golem, slime);
        visitor.visitSavedNpcsFlags(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
        visitor.visitEventCompleteFlags(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
        visitor.visitShadowOrbSmashed(buffer.readBoolean());

        //holy shit this is only half the metadata
        visitor.visitEnd();
    }

    private void visitHeader(SharedHeaderVisitor visitor){
        byte[] magicBytes = new byte[7];
        buffer.readBytes(magicBytes);
        String magic = new String(magicBytes, StandardCharsets.UTF_8);
        FileType type = FileType.values()[buffer.readUnsignedByte()];
        int revision = buffer.readIntLE();
        boolean favorite = (buffer.readLongLE() & 1) == 1;
        if (visitor != null) {
            visitor.visitStart();
            visitor.visitMagic(magic);
            visitor.visitType(type);
            visitor.visitRevision(revision);
            visitor.visitFavorite(favorite);
            visitor.visitEnd();
        }
    }
}
