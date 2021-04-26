package me.mdbell.terranet.world.reader;

import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.ext.BufferExtensions;
import me.mdbell.terranet.common.util.UUID;
import me.mdbell.terranet.files.FileType;
import me.mdbell.terranet.files.GameMode;
import me.mdbell.terranet.files.SharedHeaderVisitor;
import me.mdbell.terranet.world.*;

import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

@ExtensionMethod({BufferExtensions.class})
public class WorldReader implements WorldFileConstants {

    private final Buffer<?> buffer;
    private final TileReader tileReader;

    private String name = null;
    private int id = -1;
    private int width, height;
    private double worldSurface;

    public WorldReader(Buffer<?> buffer) {
        this.buffer = buffer;
        this.tileReader = new TileReader(buffer);
    }

    public void accept(WorldVisitor visitor) throws ReaderException {
        visitor.visitStart();
        int version = buffer.readIntLE();
        if (version > SUPPORTED_VERSION) {
            throw new ReaderException("Unsupported game version:" + version);
        }
        visitor.visitVersion(version);
        SharedHeaderVisitor header = visitor.visitFileHeader();
        visitHeader(header);
        int count = buffer.readUnsignedShortLE();
        int[] offsets = new int[count];
        for (int i = 0; i < count; i++) {
            offsets[i] = buffer.readIntLE();
        }
        count = buffer.readUnsignedShortLE();
        BitSet important = buffer.readBits(count / 8);
        visitor.visitImportantFlags(important);

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
                    ChestDataVisitor chests = visitor.visitChests();
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
                    boolean b = buffer.readBoolean();
                    if (!b) {
                        throw new ReaderException("Invalid footer flag");
                    }
                    String name = buffer.readString();
                    if (!name.equals(this.name)) {
                        throw new ReaderException("Invalid footer name");
                    }
                    int id = buffer.readIntLE();
                    if (id != this.id) {
                        throw new ReaderException("Invalid footer id");
                    }
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

    private void visitChests(ChestDataVisitor visitor, int version) {
        visitor.visitStart();
        int chestCount = buffer.readUnsignedShortLE();
        int itemCount = buffer.readUnsignedShortLE();
        int num3;
        int num4;
        if (itemCount < 40) {
            num3 = itemCount;
            num4 = 0;
        } else {
            num3 = 40;
            num4 = itemCount - 40;
        }
        for (int i = 0; i < chestCount; i++) {
            ChestVisitor cv = visitor.visitChest();
            int x = buffer.readIntLE();
            int y = buffer.readIntLE();
            String name = buffer.readString();
            if (cv != null) {
                cv.visitStart();
                cv.visitLocation(x, y);
                cv.visitName(name);
            }
            visitItems(cv, num3, num4);
        }
        visitor.visitEnd();
    }

    private void visitItems(ChestVisitor cv, int itemCount, int num4) {
        for (int j = 0; j < itemCount; j++) {
            int stack = buffer.readUnsignedShortLE();
            int id;
            int prefix;
            if (stack != 0) {
                if (stack < 0) {
                    stack = 1;
                }
                id = buffer.readIntLE();
                prefix = buffer.readByte();
                if (cv != null) {
                    ItemVisitor iv = cv.visitItem();
                    visitItem(iv, id, prefix, stack);
                }
            }
            for (int i = 0; i < num4; i++) {
                if (buffer.readShortLE() > 0) {
                    buffer.readIntLE();
                    buffer.readByte();
                }
            }
        }
    }

    private void visitItem(ItemVisitor item, int id, int prefix, int stack) {
        if (item == null) {
            return;
        }
        item.visitStart();
        item.visitId(id);
        item.visitCount(stack);
        item.visitPrefix(prefix);
        item.visitEnd();
    }

    private void visitTiles(TileDataVisitor visitor, int version, BitSet important) {
        visitor.visitStart();
        tileReader.setVisitor(visitor);
        tileReader.setSize(width, height);
        tileReader.setImportant(important);
        tileReader.visitAllTiles();
        visitor.visitEnd();
    }

    private void visitMetadata(MetadataVisitor visitor, int version) {
        visitor.visitStart();
        visitor.visitName(this.name = buffer.readString());

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

        visitor.visitId(this.id = buffer.readIntLE());

        visitor.visitDimensions(buffer.readIntLE(), buffer.readIntLE(), buffer.readIntLE(), buffer.readIntLE());

        visitor.visitSize(height = buffer.readIntLE(), width = buffer.readIntLE());

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
        visitor.visitSurface(worldSurface = buffer.readDoubleLE());
        visitor.visitRockLayer(buffer.readDoubleLE());
        visitor.visitTime(buffer.readDoubleLE());
        visitor.visitDaytime(buffer.readBoolean());
        visitor.visitMoonPhase(buffer.readIntLE());
        visitor.visitBloodmoon(buffer.readBoolean());
        visitor.visitEclipse(buffer.readBoolean());
        visitor.visitDungeonLocation(buffer.readIntLE(),
                buffer.readIntLE());
        visitor.visitCrimson(buffer.readBoolean());
        visitor.visitNormalBossFlags(buffer.readBoolean()
                , buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean());
        visitor.visitMechBossFlags(buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean());
        boolean plant = buffer.readBoolean();
        boolean golem = buffer.readBoolean();
        boolean slime = false;
        if (version >= 118) {
            slime = buffer.readBoolean();
        }
        visitor.visitHardmodeBossFlags(plant, golem, slime);

        visitor.visitSavedNpcsFlags(buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean());

        visitor.visitEventCompleteFlags(buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean());

        visitor.visitShadowOrbSmashed(buffer.readBoolean());

        visitor.visitMeteor(buffer.readBoolean());

        visitor.visitShadowOrbCount(buffer.readByte());

        visitor.visitAlterCount(buffer.readIntLE());
        visitor.visitHardmode(buffer.readBoolean());
        visitor.visitInvasion(buffer.readIntLE(),
                buffer.readIntLE(),
                buffer.readIntLE(),
                buffer.readDoubleLE());

        if (version >= 118) {
            visitor.visitSlimerainTime(buffer.readDoubleLE());
        } else {
            visitor.visitSlimerainTime(0);
        }

        if (version >= 113) {
            visitor.visitSundialCooldown(buffer.readByte());
        } else {
            visitor.visitSundialCooldown(0);
        }

        visitor.visitRain(buffer.readBoolean(),
                buffer.readIntLE(),
                buffer.readFloatLE());

        visitor.visitOreTiers1(buffer.readIntLE(),
                buffer.readIntLE(),
                buffer.readIntLE());

        for (int i = 0; i < 8; i++) {
            visitor.visitBG(i, buffer.readByte());
        }

        visitor.visitClouds((float) buffer.readIntLE(),
                buffer.readShortLE());

        visitor.visitWindSpeedTarget(buffer.readFloatLE());

        if (version < 95) {
            visitor.visitEnd();
            return;
        }
        List<String> fished = new LinkedList<>();
        boolean angler = false;
        boolean stylist = false;
        boolean taxCollector = false;
        boolean golfer = false;
        int quest = 0;
        int count = buffer.readIntLE();
        for (int i = 0; i < count; i++) {
            fished.add(buffer.readString());
        }
        if (version >= 99) {
            angler = buffer.readBoolean();
        }
        if (version >= 101) {
            quest = buffer.readIntLE();
            stylist = buffer.readBoolean();
        }
        if (version >= 129) {
            taxCollector = buffer.readBoolean();
        }
        if (version >= 201) {
            golfer = buffer.readBoolean();
        }
        visitor.visitAnglerQuest(fished, quest);
        visitor.visitSavedNpcsFlags2(angler, stylist, taxCollector, golfer);

        if (version < 104) {
            visitor.visitEnd();
            return;
        }
        if (version >= 107) {
            visitor.visitInvasionSizeStart(buffer.readIntLE());
        }

        if (version >= 109) {
            visitor.visitCultistDelay(buffer.readIntLE());
        } else {
            visitor.visitCultistDelay(86400);
            visitor.visitEnd();
            return;
        }
        List<Integer> killCounts = new LinkedList<>();
        count = buffer.readUnsignedShortLE();
        for (int i = 0; i < count; i++) {
            killCounts.add(buffer.readIntLE());
        }
        visitor.visitKillCounts(killCounts);

        if (version < 128) {
            visitor.visitEnd();
            return;
        }
        visitor.visitFastForward(buffer.readBoolean());

        if (version < 131) {
            visitor.visitEnd();
            return;
        }

        visitor.visitEndgameBossFlags(buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean());

        if (version < 140) {
            visitor.visitEnd();
            return;
        }

        visitor.visitDownedTowers(buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean());

        visitor.visitActiveTowers(buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean(),
                buffer.readBoolean());

        visitor.visitApocalypse(buffer.readBoolean());

        if (version > 169) {
            boolean ongoing = buffer.readBoolean();
            boolean genuine = buffer.readBoolean();
            int cooldown = buffer.readIntLE();
            count = buffer.readIntLE();
            List<Integer> partying = new LinkedList<>();
            for (int i = 0; i < count; i++) {
                partying.add(buffer.readIntLE());
            }
            visitor.visitParty(ongoing, genuine, cooldown, partying);
        }

        if (version > 173) {
            visitor.visitSandstorm(buffer.readBoolean(),
                    buffer.readIntLE(),
                    buffer.readFloatLE(),
                    buffer.readFloatLE());
        }

        if (version > 177) {
            visitor.visitDungeonDefense(buffer.readBoolean(),
                    buffer.readBoolean(),
                    buffer.readBoolean(),
                    buffer.readBoolean());
        }

        if (version > 194) {
            visitor.visitBG(8, buffer.readUnsignedByte());
        }

        if (version >= 215) {
            visitor.visitBG(9, buffer.readUnsignedByte());
        }

        if (version > 195) {
            visitor.visitBG(10, buffer.readUnsignedByte());
            visitor.visitBG(11, buffer.readUnsignedByte());
            visitor.visitBG(12, buffer.readUnsignedByte());
        }

        if (version > 204) {
            visitor.visitCombatBook(buffer.readBoolean());
        }

        if (version >= 207) {
            visitor.visitLantern(buffer.readIntLE(),
                    buffer.readBoolean(),
                    buffer.readBoolean(),
                    buffer.readBoolean());
        }

        if (version >= 211) {
            count = buffer.readIntLE();
            visitor.visitTreetopStyleCount(count);
            for (int i = 0; i < count; i++) {
                visitor.visitTreetopStyle(i, buffer.readIntLE());
            }
        }

        if (version >= 212) {
            visitor.visitForceEvents(buffer.readBoolean(),
                    buffer.readBoolean());
        }

        if (version >= 216) {
            visitor.visitOreTiers2(buffer.readIntLE(),
                    buffer.readIntLE(),
                    buffer.readIntLE(),
                    buffer.readIntLE());
        }

        if (version >= 217) {
            visitor.visitBoughtPets(buffer.readBoolean(),
                    buffer.readBoolean(),
                    buffer.readBoolean());
        }

        if (version >= 223) {
            visitor.visitHallowBosses(buffer.readBoolean(),
                    buffer.readBoolean());
        }

        visitor.visitEnd();
    }

    private void visitHeader(SharedHeaderVisitor visitor) {
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
