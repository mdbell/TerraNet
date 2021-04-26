package me.mdbell.terranet.world;

public interface ItemVisitor {

    void visitStart();
    void visitId(int id);

    void visitCount(int count);

    void visitPrefix(int prefix);

    void visitEnd();
}
