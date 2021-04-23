package me.mdbell.terranet.world;

import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.util.IOUtil;
import me.mdbell.terranet.files.FileConstants;
import me.mdbell.terranet.files.FileType;
import me.mdbell.terranet.files.GameMode;
import me.mdbell.terranet.files.SharedHeaderVisitor;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@ExtensionMethod({IOUtil.class})
@Slf4j
public class WorldReader {

    public static final int SUPPORTED_VERSION = 237;

    private static final int METADATA_OFFSET = 0;

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
        log.debug("Reading {} offsets", count);
        for (int i = 0; i < count; i++) {
            offsets[i] = buffer.readIntLE();
            log.debug("offset[{}] = {}", i, offsets[i]);
        }
        count = buffer.readUnsignedShortLE();
        boolean[] important = new boolean[count];
        log.debug("Reading {} important flags", count);
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
                    } else {
                        buffer.readerIndex(offsets[i + 1]);
                    }
                    break;
                default:
                    throw new ReaderException("Unexpected offset! index:" + i + " offset:" + offset);
            }
        }
        log.debug("Position: {} Expected:{}", buffer.readerIndex(), offsets[0]);
        visitor.visitEnd();
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
            if(version < 112){
                mode = GameMode.NORMAL;
            }else {
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
        for(int i = 0; i < 4; i++){
            visitor.visitTreeStyle(i, buffer.readIntLE());
        }
        for(int i = 0; i < 3; i++){
            visitor.visitCaveBackX(i, buffer.readIntLE());
        }
        for(int i = 0; i < 4; i++){
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
        if(version >= 118){
            slime = buffer.readBoolean();
        }
        visitor.visitHardmodeBossFlags(plant, golem, slime);
        visitor.visitSavedNpcsFlags(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
        visitor.visitEventCompleteFlags(buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean(), buffer.readBoolean());
        visitor.visitShadowOrbSmashed(buffer.readBoolean());
        visitor.visitEnd();
    }

    private void visitHeader(SharedHeaderVisitor visitor) throws ReaderException {
        byte[] magicBytes = new byte[7];
        buffer.readBytes(magicBytes);
        String magic = new String(magicBytes, StandardCharsets.UTF_8);

        if (!FileConstants.HEADER_MAGIC.equals(magic)) {
            throw new ReaderException("Invalid magic value:" + magic);
        }

        FileType type = FileType.values()[buffer.readUnsignedByte()];

        if (type != FileType.WORLD) {
            throw new ReaderException("Unexpected world type:" + type);
        }

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
