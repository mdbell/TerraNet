package me.mdbell.terranet.world;

import me.mdbell.terranet.common.util.UUID;
import me.mdbell.terranet.files.GameMode;

import java.util.List;

public interface MetadataVisitor {

    void visitStart();

    void visitName(String name);

    void visitSeed(String seed);

    void visitWorldGenVersion(long version);

    void visitGuid(UUID guid);

    void visitId(int id);

    void visitDimensions(int left, int right, int top, int bottom);

    void visitSize(int height, int width);

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

    void visitEventCompleteFlags(boolean goblin, boolean pumpkinMoon, boolean frostMoon, boolean pirates);

    void visitShadowOrbSmashed(boolean smashed);

    void visitMeteor(boolean spawned);

    void visitShadowOrbCount(int count);

    void visitAlterCount(int alterCount);

    void visitHardmode(boolean hardmode);

    void visitInvasion(int delay, int size, int type, double x);

    void visitSlimerainTime(double time);

    void visitSundialCooldown(int cooldown);

    void visitRain(boolean raining, int time, float max);

    void visitOreTiers1(int cobalt, int mythril, int adamantite);

    void visitBG(int index, int value);

    void visitClouds(float active, int num);

    void visitWindSpeedTarget(float target);

    void visitAnglerQuest(List<String> finished, int current);

    void visitSavedNpcsFlags2(boolean angler, boolean stylist, boolean taxCollector, boolean golfer);

    void visitInvasionSizeStart(int size);

    void visitCultistDelay(int delay);

    void visitKillCounts(List<Integer> counts);

    void visitFastForward(boolean fastForward);

    void visitEndgameBossFlags(boolean fishron, boolean martians, boolean ancientCultist, boolean moonlord,
                               boolean hallowenKing, boolean halloweenTree, boolean christmasIceQueen, boolean santank,
                               boolean christmasTree);

    void visitDownedTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust);

    void visitActiveTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust);

    void visitApocalypse(boolean ongoing);

    void visitParty(boolean ongoing, boolean genuine, int cooldown, List<Integer> partying);

    void visitSandstorm(boolean happening, int timeLeft, float severity, float intendedSeverity);

    void visitDungeonDefense(boolean savedBartender, boolean invasionT1, boolean invasionT2, boolean invasionT3);

    void visitCombatBook(boolean used);

    void visitLantern(int cooldown, boolean genuine, boolean manual, boolean nextNightGenuine);

    void visitTreetopStyleCount(int count);

    void visitTreetopStyle(int index, int style);

    void visitForceEvents(boolean halloween, boolean christmas);

    void visitOreTiers2(int copper, int iron, int silver, int gold);

    void visitBoughtPets(boolean cat, boolean dog, boolean bunny);

    void visitHallowBosses(boolean empress, boolean slimeQueen);

    void visitEnd();
}
