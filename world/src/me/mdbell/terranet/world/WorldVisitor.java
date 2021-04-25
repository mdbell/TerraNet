package me.mdbell.terranet.world;

import me.mdbell.terranet.files.SharedHeaderVisitor;

public interface WorldVisitor {

    void visitStart();

    void visitVersion(int version);

    SharedHeaderVisitor visitFileHeader();

    void visitImportantFlags(boolean[] important);

    MetadataVisitor visitMetadata();

    TileDataVisitor visitTileData();

    ChestDataVisitor visitChests();

    SignsVisitor visitSigns();

    NPCVisitor visitNpcs();

    DummiesVisitor visitDummies();

    TileEntitiesVisitor visitEntities();

    PressurePlatesVisitor visitPressurePlates();

    TownVisitor visitTown();

    BestiaryVistor visitBestiary();

    CreativePowersVisitor visitCreativePowers();

    void visitEnd();
}
