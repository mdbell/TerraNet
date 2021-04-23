package me.mdbell.terranet.world.tree;

import lombok.*;
import me.mdbell.terranet.files.SharedHeaderNode;
import me.mdbell.terranet.files.SharedHeaderVisitor;
import me.mdbell.terranet.world.*;

import java.util.UUID;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
public class WorldNode implements WorldVisitor {

    private final SharedHeaderNode header = new SharedHeaderNode();
    private final MetadataNode metadata = new MetadataNode();

    @Override
    public void visitStart() {

    }

    @Override
    public void visitVersion(int version) {

    }

    @Override
    public SharedHeaderVisitor visitFileHeader() {
        return header;
    }

    @Override
    public MetadataVisitor visitMetadata() {
        return metadata;
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

    @Override
    public void visitFooter(boolean flag, String name, int id) {
        //ignored
    }
}
