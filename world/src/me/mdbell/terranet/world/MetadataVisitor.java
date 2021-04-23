package me.mdbell.terranet.world;

import me.mdbell.terranet.files.GameMode;

import java.util.UUID;

public interface MetadataVisitor {

    void visitStart();

    void visitName(String name);

    void visitSeed(String seed);

    void visitWorldGenVersion(long version);

    void visitGuid(UUID guid);

    void visitId(int id);

    void visitDimensions(int left, int right, int top, int bottom);

    void visitSize(int width, int height);

    void visitGameMode(GameMode mode);

    void visitDrunkWorld(boolean drunk);

    void visitGood(boolean good);

    void visitCreationTime(long time);

    void visitMoonType(int type);

    void visitTreeX(int index, int value);

    void visitTreeStyle(int index, int value);

    void visitCaveBackX(int index, int value);

    void visitCaveBackStyle(int index, int value);

    void visitIceBackStyle(int style);

    void visitJungleBackStyle(int style);

    void visitHellBackStyle(int style);

    void visitSpawnLocation(int x, int y);

    void visitSurface(double value);

    void visitRockLayer(double value);

    void visitTime(double time);

    void visitDaytime(boolean daytime);

    void visitMoonPhase(int phase);

    void visitBloodmoon(boolean bloodmoon);

    void visitEclipse(boolean eclipse);

    void visitDungeonLocation(int x, int y);

    void visitCrimson(boolean crimson);

    void visitNormalBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean queenBee);

    void visitMechBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean any);

    void visitHardmodeBossFlags(boolean plantera, boolean golem, boolean slimeKing);

    void visitSavedNpcsFlags(boolean goblin, boolean wizard, boolean mechanic);

    void visitEventCompleteFlags(boolean goblen, boolean pumpkinMoon, boolean frostMoon, boolean pirates);

    void visitShadowOrbSmashed(boolean smashed);

    void visitEnd();
}
