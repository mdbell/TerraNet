package me.mdbell.terranet.world;

public interface TileDataVisitor {

    void visitStart();

    TileVisitor visitTile(int x, int y);

    void visitEnd();
}
