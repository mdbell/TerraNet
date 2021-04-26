package me.mdbell.terranet.world;

public interface ChestVisitor {

    void visitStart();

    void visitLocation(int x, int y);

    void visitName(String name);

    ItemVisitor visitItem();

    void visitEnd();
}
