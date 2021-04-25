package me.mdbell.terranet.world.log;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.files.LoggingHeaderVisitor;
import me.mdbell.terranet.files.SharedHeaderVisitor;
import me.mdbell.terranet.world.*;

@Slf4j
public class LoggingWorldVisitor extends AbstractWorldVisitor {

    public LoggingWorldVisitor() {
        super();
    }

    public LoggingWorldVisitor(WorldVisitor visitor) {
        super(visitor);
    }

    @Override
    public void visitStart() {
        log.info("visitStart()");
        super.visitStart();
    }

    @Override
    public void visitVersion(int version) {
        log.info("visitVersion({})", version);
        super.visitVersion(version);
    }

    @Override
    public SharedHeaderVisitor visitFileHeader() {
        return new LoggingHeaderVisitor(super.visitFileHeader());
    }

    @Override
    public void visitImportantFlags(boolean[] important) {
        log.info("visitImportantFlags({})", important);
        super.visitImportantFlags(important);
    }

    @Override
    public MetadataVisitor visitMetadata() {
        return new LoggingFileHeaderVisitor(super.visitMetadata());
    }

    @Override
    public TileDataVisitor visitTileData() {
        return new LoggingTileDataVisitor(super.visitTileData());
    }

    @Override
    public ChestDataVisitor visitChests() {
        return new LoggingChestDataVisitor(super.visitChests());
    }

    @Override
    public SignsVisitor visitSigns() {
        return super.visitSigns();
    }

    @Override
    public NPCVisitor visitNpcs() {
        return super.visitNpcs();
    }

    @Override
    public DummiesVisitor visitDummies() {
        return super.visitDummies();
    }

    @Override
    public TileEntitiesVisitor visitEntities() {
        return super.visitEntities();
    }

    @Override
    public PressurePlatesVisitor visitPressurePlates() {
        return super.visitPressurePlates();
    }

    @Override
    public TownVisitor visitTown() {
        return super.visitTown();
    }

    @Override
    public BestiaryVistor visitBestiary() {
        return super.visitBestiary();
    }

    @Override
    public CreativePowersVisitor visitCreativePowers() {
        return super.visitCreativePowers();
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
