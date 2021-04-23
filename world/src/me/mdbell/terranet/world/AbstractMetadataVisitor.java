package me.mdbell.terranet.world;

import me.mdbell.terranet.files.GameMode;

import java.util.UUID;

public abstract class AbstractMetadataVisitor implements MetadataVisitor{

    private final MetadataVisitor visitor;

    public AbstractMetadataVisitor(){
        this(null);
    }

    public AbstractMetadataVisitor(MetadataVisitor visitor){
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if(visitor != null){
            visitor.visitStart();
        }
    }

    @Override
    public void visitName(String name) {
        if(visitor != null){
            visitor.visitName(name);
        }
    }

    @Override
    public void visitSeed(String seed) {
        if(visitor != null){
            visitor.visitSeed(seed);
        }
    }

    @Override
    public void visitWorldGenVersion(long version) {
        if(visitor != null){
            visitor.visitWorldGenVersion(version);
        }
    }

    @Override
    public void visitGuid(UUID guid) {
        if(visitor != null){
            visitor.visitGuid(guid);
        }
    }

    @Override
    public void visitId(int id) {
        if(visitor != null){
            visitor.visitId(id);
        }
    }

    @Override
    public void visitDimensions(int left, int right, int top, int bottom) {
        if(visitor != null){
            visitor.visitDimensions(left, right, top, bottom);
        }
    }

    @Override
    public void visitSize(int width, int height) {
        if(visitor != null){
            visitor.visitSize(width, height);
        }
    }

    @Override
    public void visitGameMode(GameMode mode) {
        if(visitor != null){
            visitor.visitGameMode(mode);
        }
    }

    @Override
    public void visitDrunkWorld(boolean drunk) {
        if(visitor != null){
            visitor.visitDrunkWorld(drunk);
        }
    }

    @Override
    public void visitGood(boolean good) {
        if(visitor != null){
            visitor.visitGood(good);
        }
    }

    @Override
    public void visitCreationTime(long time) {
        if(visitor != null){
            visitor.visitCreationTime(time);
        }
    }

    @Override
    public void visitMoonType(int type) {
        if(visitor != null){
            visitor.visitMoonType(type);
        }
    }

    @Override
    public void visitTreeX(int index, int value) {
        if(visitor != null){
            visitor.visitTreeX(index, value);
        }
    }

    @Override
    public void visitTreeStyle(int index, int value) {
        if(visitor != null){
            visitor.visitTreeStyle(index, value);
        }
    }

    @Override
    public void visitCaveBackX(int index, int value) {
        if(visitor != null){
            visitor.visitCaveBackX(index, value);
        }
    }

    @Override
    public void visitCaveBackStyle(int index, int value) {
        if(visitor != null){
            visitor.visitCaveBackStyle(index, value);
        }
    }

    @Override
    public void visitIceBackStyle(int style) {
        if(visitor != null){
            visitor.visitIceBackStyle(style);
        }
    }

    @Override
    public void visitJungleBackStyle(int style) {
        if(visitor != null){
            visitor.visitJungleBackStyle(style);
        }
    }

    @Override
    public void visitHellBackStyle(int style) {
        if(visitor != null){
            visitor.visitHellBackStyle(style);
        }
    }

    @Override
    public void visitSpawnLocation(int x, int y) {
        if(visitor != null){
            visitor.visitSpawnLocation(x, y);
        }
    }

    @Override
    public void visitSurface(double value) {
        if(visitor != null){
            visitor.visitSurface(value);
        }
    }

    @Override
    public void visitRockLayer(double value) {
        if(visitor != null){
            visitor.visitRockLayer(value);
        }
    }

    @Override
    public void visitTime(double time) {
        if(visitor != null){
            visitor.visitTime(time);
        }
    }

    @Override
    public void visitDaytime(boolean daytime) {
        if(visitor != null){
            visitor.visitDaytime(daytime);
        }
    }

    @Override
    public void visitMoonPhase(int phase) {
        if(visitor != null){
            visitor.visitMoonPhase(phase);
        }
    }

    @Override
    public void visitBloodmoon(boolean bloodmoon) {
        if(visitor != null){
            visitor.visitBloodmoon(bloodmoon);
        }
    }

    @Override
    public void visitEclipse(boolean eclipse) {
        if(visitor != null){
            visitor.visitEclipse(eclipse);
        }
    }

    @Override
    public void visitDungeonLocation(int x, int y) {
        if(visitor != null){
            visitor.visitDungeonLocation(x, y);
        }
    }

    @Override
    public void visitCrimson(boolean crimson) {
        if(visitor != null){
            visitor.visitCrimson(crimson);
        }
    }

    @Override
    public void visitNormalBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean queenBee) {
        if(visitor != null){
            visitor.visitNormalBossFlags(boss1, boss2, boss3, queenBee);
        }
    }

    @Override
    public void visitMechBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean any) {
        if(visitor != null){
            visitor.visitMechBossFlags(boss1, boss2, boss3, any);
        }
    }

    @Override
    public void visitHardmodeBossFlags(boolean plantera, boolean golem, boolean slimeKing) {
        if(visitor != null){
            visitor.visitHardmodeBossFlags(plantera, golem, slimeKing);
        }
    }

    @Override
    public void visitSavedNpcsFlags(boolean goblin, boolean wizard, boolean mechanic) {
        if(visitor != null){
            visitor.visitSavedNpcsFlags(goblin, wizard, mechanic);
        }
    }

    @Override
    public void visitEventCompleteFlags(boolean goblen, boolean pumpkinMoon, boolean frostMoon, boolean pirates) {
        if(visitor != null){
            visitor.visitEventCompleteFlags(goblen, pumpkinMoon, frostMoon, pirates);
        }
    }

    @Override
    public void visitShadowOrbSmashed(boolean smashed) {
        if(visitor != null){
            visitor.visitShadowOrbSmashed(smashed);
        }
    }

    @Override
    public void visitEnd() {
        if(visitor != null){
            visitor.visitEnd();
        }
    }
}
