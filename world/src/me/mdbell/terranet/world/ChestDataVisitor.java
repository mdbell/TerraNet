package me.mdbell.terranet.world;

public interface ChestDataVisitor {

    void visitStart();

    ChestVisitor visitChest();

    void visitEnd();

}
