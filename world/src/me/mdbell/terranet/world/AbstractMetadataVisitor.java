package me.mdbell.terranet.world;

import me.mdbell.terranet.common.util.UUID;
import me.mdbell.terranet.files.GameMode;

import java.util.List;

public abstract class AbstractMetadataVisitor implements MetadataVisitor {

    private final MetadataVisitor visitor;

    public AbstractMetadataVisitor() {
        this(null);
    }

    public AbstractMetadataVisitor(MetadataVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if (visitor != null) {
            visitor.visitStart();
        }
    }

    @Override
    public void visitName(String name) {
        if (visitor != null) {
            visitor.visitName(name);
        }
    }

    @Override
    public void visitSeed(String seed) {
        if (visitor != null) {
            visitor.visitSeed(seed);
        }
    }

    @Override
    public void visitWorldGenVersion(long version) {
        if (visitor != null) {
            visitor.visitWorldGenVersion(version);
        }
    }

    @Override
    public void visitGuid(UUID guid) {
        if (visitor != null) {
            visitor.visitGuid(guid);
        }
    }

    @Override
    public void visitId(int id) {
        if (visitor != null) {
            visitor.visitId(id);
        }
    }

    @Override
    public void visitDimensions(int left, int right, int top, int bottom) {
        if (visitor != null) {
            visitor.visitDimensions(left, right, top, bottom);
        }
    }

    @Override
    public void visitSize(int height, int width) {
        if (visitor != null) {
            visitor.visitSize(height, width);
        }
    }

    @Override
    public void visitGameMode(GameMode mode) {
        if (visitor != null) {
            visitor.visitGameMode(mode);
        }
    }

    @Override
    public void visitDrunkWorld(boolean drunk) {
        if (visitor != null) {
            visitor.visitDrunkWorld(drunk);
        }
    }

    @Override
    public void visitGood(boolean good) {
        if (visitor != null) {
            visitor.visitGood(good);
        }
    }

    @Override
    public void visitCreationTime(long time) {
        if (visitor != null) {
            visitor.visitCreationTime(time);
        }
    }

    @Override
    public void visitMoonType(int type) {
        if (visitor != null) {
            visitor.visitMoonType(type);
        }
    }

    @Override
    public void visitTreeX(int index, int value) {
        if (visitor != null) {
            visitor.visitTreeX(index, value);
        }
    }

    @Override
    public void visitTreetopStyleCount(int count) {
        if (visitor != null) {
            visitor.visitTreetopStyleCount(count);
        }
    }

    @Override
    public void visitTreeStyle(int index, int value) {
        if (visitor != null) {
            visitor.visitTreeStyle(index, value);
        }
    }

    @Override
    public void visitCaveBackX(int index, int value) {
        if (visitor != null) {
            visitor.visitCaveBackX(index, value);
        }
    }

    @Override
    public void visitCaveBackStyle(int index, int value) {
        if (visitor != null) {
            visitor.visitCaveBackStyle(index, value);
        }
    }

    @Override
    public void visitIceBackStyle(int style) {
        if (visitor != null) {
            visitor.visitIceBackStyle(style);
        }
    }

    @Override
    public void visitJungleBackStyle(int style) {
        if (visitor != null) {
            visitor.visitJungleBackStyle(style);
        }
    }

    @Override
    public void visitHellBackStyle(int style) {
        if (visitor != null) {
            visitor.visitHellBackStyle(style);
        }
    }

    @Override
    public void visitSpawnLocation(int x, int y) {
        if (visitor != null) {
            visitor.visitSpawnLocation(x, y);
        }
    }

    @Override
    public void visitSurface(double value) {
        if (visitor != null) {
            visitor.visitSurface(value);
        }
    }

    @Override
    public void visitRockLayer(double value) {
        if (visitor != null) {
            visitor.visitRockLayer(value);
        }
    }

    @Override
    public void visitTime(double time) {
        if (visitor != null) {
            visitor.visitTime(time);
        }
    }

    @Override
    public void visitDaytime(boolean daytime) {
        if (visitor != null) {
            visitor.visitDaytime(daytime);
        }
    }

    @Override
    public void visitMoonPhase(int phase) {
        if (visitor != null) {
            visitor.visitMoonPhase(phase);
        }
    }

    @Override
    public void visitBloodmoon(boolean bloodmoon) {
        if (visitor != null) {
            visitor.visitBloodmoon(bloodmoon);
        }
    }

    @Override
    public void visitEclipse(boolean eclipse) {
        if (visitor != null) {
            visitor.visitEclipse(eclipse);
        }
    }

    @Override
    public void visitDungeonLocation(int x, int y) {
        if (visitor != null) {
            visitor.visitDungeonLocation(x, y);
        }
    }

    @Override
    public void visitCrimson(boolean crimson) {
        if (visitor != null) {
            visitor.visitCrimson(crimson);
        }
    }

    @Override
    public void visitNormalBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean queenBee) {
        if (visitor != null) {
            visitor.visitNormalBossFlags(boss1, boss2, boss3, queenBee);
        }
    }

    @Override
    public void visitMechBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean any) {
        if (visitor != null) {
            visitor.visitMechBossFlags(boss1, boss2, boss3, any);
        }
    }

    @Override
    public void visitHardmodeBossFlags(boolean plantera, boolean golem, boolean slimeKing) {
        if (visitor != null) {
            visitor.visitHardmodeBossFlags(plantera, golem, slimeKing);
        }
    }

    @Override
    public void visitSavedNpcsFlags(boolean goblin, boolean wizard, boolean mechanic) {
        if (visitor != null) {
            visitor.visitSavedNpcsFlags(goblin, wizard, mechanic);
        }
    }

    @Override
    public void visitEventCompleteFlags(boolean goblin, boolean pumpkinMoon, boolean frostMoon, boolean pirates) {
        if (visitor != null) {
            visitor.visitEventCompleteFlags(goblin, pumpkinMoon, frostMoon, pirates);
        }
    }

    @Override
    public void visitShadowOrbSmashed(boolean smashed) {
        if (visitor != null) {
            visitor.visitShadowOrbSmashed(smashed);
        }
    }

    @Override
    public void visitMeteor(boolean spawned) {
        if (visitor != null) {
            visitor.visitMeteor(spawned);
        }
    }

    @Override
    public void visitShadowOrbCount(int count) {
        if (visitor != null) {
            visitor.visitShadowOrbCount(count);
        }
    }

    @Override
    public void visitAlterCount(int alterCount) {
        if (visitor != null) {
            visitor.visitAlterCount(alterCount);
        }
    }

    @Override
    public void visitHardmode(boolean hardmode) {
        if (visitor != null) {
            visitor.visitHardmode(hardmode);
        }
    }

    @Override
    public void visitInvasion(int delay, int size, int type, double x) {
        if (visitor != null) {
            visitor.visitInvasion(delay, size, type, x);
        }
    }

    @Override
    public void visitSlimerainTime(double time) {
        if (visitor != null) {
            visitor.visitSlimerainTime(time);
        }
    }

    @Override
    public void visitSundialCooldown(int cooldown) {
        if (visitor != null) {
            visitor.visitSundialCooldown(cooldown);
        }
    }

    @Override
    public void visitRain(boolean raining, int time, float max) {
        if (visitor != null) {
            visitor.visitRain(raining, time, max);
        }
    }

    @Override
    public void visitOreTiers1(int cobalt, int mythril, int adamantite) {
        if (visitor != null) {
            visitor.visitOreTiers1(cobalt, mythril, adamantite);
        }
    }

    @Override
    public void visitBG(int index, int value) {
        if (visitor != null) {
            visitor.visitBG(index, value);
        }
    }

    @Override
    public void visitClouds(float active, int num) {
        if (visitor != null) {
            visitor.visitClouds(active, num);
        }
    }

    @Override
    public void visitWindSpeedTarget(float target) {
        if (visitor != null) {
            visitor.visitWindSpeedTarget(target);
        }
    }

    @Override
    public void visitAnglerQuest(List<String> finished, int current) {
        if (visitor != null) {
            visitor.visitAnglerQuest(finished, current);
        }
    }

    @Override
    public void visitSavedNpcsFlags2(boolean angler, boolean stylist, boolean taxCollector, boolean golfer) {
        if (visitor != null) {
            visitor.visitSavedNpcsFlags2(angler, stylist, taxCollector, golfer);
        }
    }

    @Override
    public void visitInvasionSizeStart(int size) {
        if (visitor != null) {
            visitor.visitInvasionSizeStart(size);
        }
    }

    @Override
    public void visitCultistDelay(int delay) {
        if (visitor != null) {
            visitor.visitCultistDelay(delay);
        }
    }

    @Override
    public void visitKillCounts(List<Integer> counts) {
        if (visitor != null) {
            visitor.visitKillCounts(counts);
        }
    }

    @Override
    public void visitFastForward(boolean fastForward) {
        if (visitor != null) {
            visitor.visitFastForward(fastForward);
        }
    }

    @Override
    public void visitEndgameBossFlags(boolean fishron, boolean martians, boolean ancientCultist, boolean moonlord, boolean hallowenKing, boolean halloweenTree, boolean christmasIceQueen, boolean santank, boolean christmasTree) {
        if (visitor != null) {
            visitor.visitEndgameBossFlags(fishron, martians, ancientCultist, moonlord, hallowenKing, halloweenTree, christmasIceQueen, santank, christmasTree);
        }
    }

    @Override
    public void visitDownedTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        if (visitor != null) {
            visitor.visitDownedTowers(solar, vortex, nebula, stardust);
        }
    }

    @Override
    public void visitActiveTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        if (visitor != null) {
            visitor.visitActiveTowers(solar, vortex, nebula, stardust);
        }
    }

    @Override
    public void visitApocalypse(boolean ongoing) {
        if (visitor != null) {
            visitor.visitApocalypse(ongoing);
        }
    }

    @Override
    public void visitParty(boolean ongoing, boolean genuine, int cooldown, List<Integer> partying) {
        if (visitor != null) {
            visitor.visitParty(ongoing, genuine, cooldown, partying);
        }
    }

    @Override
    public void visitSandstorm(boolean happening, int timeLeft, float severity, float intendedSeverity) {
        if (visitor != null) {
            visitor.visitSandstorm(happening, timeLeft, severity, intendedSeverity);
        }
    }

    @Override
    public void visitDungeonDefense(boolean savedBartender, boolean invasionT1, boolean invasionT2, boolean invasionT3) {
        if (visitor != null) {
            visitor.visitDungeonDefense(savedBartender, invasionT1, invasionT2, invasionT3);
        }
    }

    @Override
    public void visitCombatBook(boolean used) {
        if (visitor != null) {
            visitor.visitCombatBook(used);
        }
    }

    @Override
    public void visitLantern(int cooldown, boolean genuine, boolean manual, boolean nextNightGenuine) {
        if (visitor != null) {
            visitor.visitLantern(cooldown, genuine, manual, nextNightGenuine);
        }
    }

    @Override
    public void visitTreetopStyle(int index, int style) {
        if (visitor != null) {
            visitor.visitTreetopStyle(index, style);
        }
    }

    @Override
    public void visitForceEvents(boolean halloween, boolean christmas) {
        if (visitor != null) {
            visitor.visitForceEvents(halloween, christmas);
        }
    }

    @Override
    public void visitOreTiers2(int copper, int iron, int silver, int gold) {
        if (visitor != null) {
            visitor.visitOreTiers2(copper, iron, silver, gold);
        }
    }

    @Override
    public void visitBoughtPets(boolean cat, boolean dog, boolean bunny) {
        if (visitor != null) {
            visitor.visitBoughtPets(cat, dog, bunny);
        }
    }

    @Override
    public void visitHallowBosses(boolean empress, boolean slimeQueen) {
        if (visitor != null) {
            visitor.visitHallowBosses(empress, slimeQueen);
        }
    }


    @Override
    public void visitEnd() {
        if (visitor != null) {
            visitor.visitEnd();
        }
    }
}
