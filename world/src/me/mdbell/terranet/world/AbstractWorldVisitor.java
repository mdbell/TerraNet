package me.mdbell.terranet.world;

import me.mdbell.terranet.files.SharedHeaderVisitor;

public abstract class AbstractWorldVisitor implements WorldVisitor{

    private final WorldVisitor visitor;

    public AbstractWorldVisitor(){
        this(null);
    }

    public AbstractWorldVisitor(WorldVisitor visitor){
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if(visitor != null){
            visitor.visitStart();
        }
    }

    @Override
    public void visitVersion(int version) {
        if(visitor != null){
            visitor.visitVersion(version);
        }
    }

    @Override
    public SharedHeaderVisitor visitFileHeader() {
        if(visitor != null){
            return visitor.visitFileHeader();
        }
        return null;
    }

    @Override
    public MetadataVisitor visitMetadata() {
        if(visitor != null){
            return visitor.visitMetadata();
        }
        return null;
    }

    @Override
    public TileDataVisitor visitTileData() {
        if(visitor != null){
            return visitor.visitTileData();
        }
        return null;
    }

    @Override
    public ChestDataVisitor visitChests() {
        if(visitor != null){
            return visitor.visitChests();
        }
        return null;
    }

    @Override
    public SignsVisitor visitSigns() {
        if(visitor != null){
            return visitor.visitSigns();
        }
        return null;
    }

    @Override
    public NPCVisitor visitNpcs() {
        if(visitor != null){
            return visitor.visitNpcs();
        }
        return null;
    }

    @Override
    public DummiesVisitor visitDummies() {
        if(visitor != null){
            return visitor.visitDummies();
        }
        return null;
    }

    @Override
    public TileEntitiesVisitor visitEntities() {
        if(visitor != null){
            return visitor.visitEntities();
        }
        return null;
    }

    @Override
    public PressurePlatesVisitor visitPressurePlates() {
        if(visitor != null){
            return visitor.visitPressurePlates();
        }
        return null;
    }

    @Override
    public TownVisitor visitTown() {
        if(visitor != null){
            return visitor.visitTown();
        }
        return null;
    }

    @Override
    public BestiaryVistor visitBestiary() {
        if(visitor != null){
            return visitor.visitBestiary();
        }
        return null;
    }

    @Override
    public CreativePowersVisitor visitCreativePowers() {
        if(visitor != null){
            return visitor.visitCreativePowers();
        }
        return null;
    }

    @Override
    public void visitFooter(boolean flag, String name, int id) {
        if(visitor != null){
            visitor.visitFooter(flag, name, id);
        }
    }

    @Override
    public void visitEnd() {

    }
}
