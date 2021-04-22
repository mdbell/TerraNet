package me.mdbell.terranet.files;

public interface SharedHeaderVisitor {

    void visitStart();

    void visitMagic(String magic);

    void visitType(FileType type);

    void visitRevision(int revision);

    void visitFavorite(boolean favorite);

    void visitEnd();
}
