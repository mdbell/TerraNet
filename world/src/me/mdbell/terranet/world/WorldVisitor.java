package me.mdbell.terranet.world;

import me.mdbell.terranet.files.SharedHeaderVisitor;

public interface WorldVisitor {

    void visitStart();

    void visitVersion(int version);

    SharedHeaderVisitor visitFileHeader();

    MetadataVisitor visitMetadata();

    TileDataVisitor visitTileData();

    ChestVisitor visitChests();

    SignsVisitor visitSigns();

    NPCVisitor visitNpcs();

    DummiesVisitor visitDummies();

    TileEntitiesVisitor visitEntities();

    PressurePlatesVisitor visitPressurePlates();

    TownVisitor visitTown();

    BestiaryVistor visitBestiary();

    CreativePowersVisitor visitCreativePowers();

    void visitFooter(boolean flag, String name, int id);

    void visitEnd();
}
