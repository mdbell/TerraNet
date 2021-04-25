package me.mdbell.terranet.world.writer;

import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.util.IOUtil;
import me.mdbell.terranet.files.HeaderWriterVisitor;
import me.mdbell.terranet.files.SharedHeaderVisitor;
import me.mdbell.terranet.world.*;

import java.util.BitSet;

@ExtensionMethod({IOUtil.class})
public class WorldWriter implements WorldVisitor, WorldFileConstants {

    private final Buffer<?> buffer;
    private final int[] offsets = new int[FOOTER_OFFSET + 1];
    private int countPosition = 0;
    private int id;
    private String name;

    public WorldWriter(Buffer<?> buffer) {
        this.buffer = buffer;
    }

    protected Buffer<?> getBuffer(){
        return buffer;
    }

    @Override
    public void visitStart() {

    }

    @Override
    public void visitVersion(int version) {
        buffer.writeIntLE(version);
    }

    @Override
    public SharedHeaderVisitor visitFileHeader() {
        return new HeaderWriterVisitor(buffer);
    }

    @Override
    public void visitImportantFlags(boolean[] important) {

        buffer.writeShortLE(offsets.length);
        countPosition = buffer.writerIndex();
        for(int i = 0; i < offsets.length; i++){
            buffer.writeInt(0);
        }
        buffer.writeShortLE(important.length);
        BitSet set = new BitSet();
        for(int i = 0; i < important.length; i++) {
            set.set(i, important[i]);
        }
        buffer.writeBytes(set.toByteArray());
    }

    @Override
    public MetadataVisitor visitMetadata() {
        offsets[METADATA_OFFSET] = buffer.writerIndex();
        return new MetadataWriterVisitor(this);
    }

    @Override
    public TileDataVisitor visitTileData() {
        offsets[TILES_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public ChestDataVisitor visitChests() {
        offsets[CHESTS_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public SignsVisitor visitSigns() {
        offsets[SIGNS_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public NPCVisitor visitNpcs() {
        offsets[NPC_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public DummiesVisitor visitDummies() {
        throw new UnsupportedOperationException("Use visitEntities!");
    }

    @Override
    public TileEntitiesVisitor visitEntities() {
        offsets[ENTITIES_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public PressurePlatesVisitor visitPressurePlates() {
        offsets[PRESSURE_PLATE_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public TownVisitor visitTown() {
        offsets[TOWN_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public BestiaryVistor visitBestiary() {
        offsets[BESTIARY_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public CreativePowersVisitor visitCreativePowers() {
        offsets[CREATIVE_OFFSET] = buffer.writerIndex();
        return null;
    }

    @Override
    public void visitEnd() {
        buffer.markWriterIndex();
        buffer.writerIndex(countPosition);
        for(int i = 0; i < offsets.length; i++){
            buffer.writeIntLE(offsets[i]);
        }
        buffer.resetWriterIndex();
        //writeFooter();
    }

    private void writeFooter() {
        buffer.writeBoolean(true);
        buffer.writeString(name);
        buffer.writeIntLE(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
