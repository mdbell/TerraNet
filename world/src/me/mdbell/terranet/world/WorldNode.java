package me.mdbell.terranet.world;

import lombok.*;
import me.mdbell.terranet.files.SharedHeaderNode;
import me.mdbell.terranet.files.SharedHeaderVisitor;

import java.util.UUID;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class WorldNode implements WorldVisitor {

    private SharedHeaderNode header;
    private String name, seed;
    private long worldGenVersion;
    private UUID guid;
    private int id;
    private int left, right, top, bottom;
    private int width, height;

    @Override
    public void visitStart() {

    }

    @Override
    public void visitVersion(int version) {

    }

    @Override
    public SharedHeaderVisitor visitFileHeader() {
        return header = new SharedHeaderNode();
    }

    @Override
    public MetadataVisitor visitMetadata() {
        return null;
    }

    @Override
    public TileDataVisitor visitTileData() {
        return null;
    }

    @Override
    public ChestVisitor visitChests() {
        return null;
    }

    @Override
    public SignsVisitor visitSigns() {
        return null;
    }

    @Override
    public NPCVisitor visitNpcs() {
        return null;
    }

    @Override
    public DummiesVisitor visitDummies() {
        return null;
    }

    @Override
    public TileEntitiesVisitor visitEntities() {
        return null;
    }

    @Override
    public PressurePlatesVisitor visitPressurePlates() {
        return null;
    }

    @Override
    public TownVisitor visitTown() {
        return null;
    }

    @Override
    public BestiaryVistor visitBestiary() {
        return null;
    }

    @Override
    public CreativePowersVisitor visitCreativePowers() {
        return null;
    }

    @Override
    public void visitEnd() {

    }

    private class WorldMetadataVisitorImpl implements MetadataVisitor {

        @Override
        public void visitStart() {

        }

        @Override
        public void visitName(String name) {
            setName(name);
        }

        @Override
        public void visitSeed(String seed) {
            setSeed(seed);
        }

        @Override
        public void visitWorldGenVersion(long version) {
            setWorldGenVersion(version);
        }

        @Override
        public void visitGuid(UUID guid) {
            setGuid(guid);
        }

        @Override
        public void visitId(int id) {
            setId(id);
        }

        @Override
        public void visitDimensions(int left, int right, int top, int bottom) {
            setLeft(left);
            setRight(right);
            setTop(top);
            setBottom(bottom);
        }

        @Override
        public void visitSize(int width, int height) {
            setWidth(width);
            setHeight(height);
        }

        @Override
        public void visitGameMode(int mode) {

        }

        @Override
        public void visitDrunkWorld(boolean drunk) {

        }

        @Override
        public void visitUnk6(boolean unk6) {

        }

        @Override
        public void visitCreationTime(long time) {

        }

        @Override
        public void visitEnd() {

        }
    }
}
