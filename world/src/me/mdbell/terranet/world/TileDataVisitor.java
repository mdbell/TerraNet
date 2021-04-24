package me.mdbell.terranet.world;

public interface TileDataVisitor {

    void visitStart();

    void visitTileX(int x);

    TileVisitor visitTile(int x, int y);

    void visitEnd();

}
