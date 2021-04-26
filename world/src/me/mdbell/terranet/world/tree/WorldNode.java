package me.mdbell.terranet.world.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.common.util.IOUtil;
import me.mdbell.terranet.files.SharedHeaderNode;
import me.mdbell.terranet.files.SharedHeaderVisitor;
import me.mdbell.terranet.world.*;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@ExtensionMethod({IOUtil.class})
public class WorldNode implements WorldVisitor {

    private int version;
    private final SharedHeaderNode header = new SharedHeaderNode();
    private final MetadataNode metadata = new MetadataNode();
    private BitSet important;
    private TileNode[] tiles;
    private final List<ChestNode> chests = new LinkedList<>();

    @Override
    public void visitStart() {
    }

    @Override
    public void visitVersion(int version) {
        this.version = version;
    }

    @Override
    public SharedHeaderVisitor visitFileHeader() {
        return header;
    }

    @Override
    public void visitImportantFlags(BitSet important) {
        this.important = important;
    }

    @Override
    public MetadataVisitor visitMetadata() {
        return metadata;
    }

    @Override
    public TileDataVisitor visitTileData() {
        return new TileDataVisitor() {
            private int height;
            @Override
            public void visitStart() {
                this.height = metadata.getHeight();
                tiles = new TileNode[metadata.getWidth() * metadata.getHeight()]
                        .fill(TileNode::new);
            }

            @Override
            public void visitTileX(int x) {

            }

            @Override
            public TileVisitor visitTile(int x, int y) {
                return tiles[y * height + x];
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

    public void accept(WorldVisitor visitor) {
        visitor.visitStart();
        visitor.visitVersion(version);
        SharedHeaderVisitor headerVisitor = visitor.visitFileHeader();

        if (headerVisitor != null) {
            header.accept(headerVisitor);
        }

        visitor.visitImportantFlags(important);

        MetadataVisitor metadataVisitor = visitor.visitMetadata();

        if (metadataVisitor != null) {
            metadata.accept(metadataVisitor);
        }

        TileDataVisitor tileVisitor = visitor.visitTileData();
        if (tileVisitor != null) {
            accept(tileVisitor);
        }

        visitor.visitEnd();
    }

    private void accept(TileDataVisitor visitor) {
        visitor.visitStart();
        int height = metadata.getHeight();;
        int width = metadata.getWidth();
        for (int x = 0; x < width; x++) {
            visitor.visitTileX(x);
            for (int y = 0; y < height; y++) {
                int index = y * height + x;
                TileVisitor tv = visitor.visitTile(x, y);
                if(tv != null){
                    tiles[index].accept(tv);
                }
            }
        }
        visitor.visitEnd();
    }
}
