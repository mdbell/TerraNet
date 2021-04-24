package me.mdbell.terranet.world.tree;

import lombok.*;
import me.mdbell.terranet.files.SharedHeaderNode;
import me.mdbell.terranet.files.SharedHeaderVisitor;
import me.mdbell.terranet.world.*;

import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
public class WorldNode implements WorldVisitor {

    private final SharedHeaderNode header = new SharedHeaderNode();
    private final MetadataNode metadata = new MetadataNode();
    private TileNode[][] tiles;
    private final List<ChestNode> chests = new LinkedList<>();

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
        return new TileDataVisitor() {
            @Override
            public void visitStart() {
                tiles = new TileNode[metadata.getWidth()][metadata.getHeight()];
            }

            @Override
            public void visitTileX(int x) {

            }

            @Override
            public TileVisitor visitTile(int x, int y) {
                return tiles[x][y] = new TileNode();
            }

            @Override
            public void visitEnd() {

            }
        };
    }

    @Override
    public ChestDataVisitor visitChests() {
        return new ChestDataVisitor() {
            @Override
            public void visitStart() {

            }

            @Override
            public ChestVisitor visitChest() {
                ChestNode node = new ChestNode();
                chests.add(node);
                return node;
            }

            @Override
            public void visitEnd() {

            }
        };
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
