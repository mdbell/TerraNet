package me.mdbell.terranet.world.log;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.files.GameMode;
import me.mdbell.terranet.world.AbstractMetadataVisitor;
import me.mdbell.terranet.world.MetadataVisitor;

import java.util.List;
import java.util.UUID;

@Slf4j
public class LoggingFileHeaderVisitor extends AbstractMetadataVisitor {

    public LoggingFileHeaderVisitor() {
        super();
    }

    public LoggingFileHeaderVisitor(MetadataVisitor visitor) {
        super(visitor);
    }

    @Override
    public void visitStart() {
        log.info("visitStart()");
        super.visitStart();
    }

    @Override
    public void visitName(String name) {
        log.info("visitName('{}')", name);
        super.visitName(name);
    }

    @Override
    public void visitSeed(String seed) {
        log.info("visitSeed('{}')", seed);
        super.visitSeed(seed);
    }

    @Override
    public void visitWorldGenVersion(long version) {
        log.info("visitWorldGenVersion({})", version);
        super.visitWorldGenVersion(version);
    }

    @Override
    public void visitGuid(UUID guid) {
        log.info("visitGuid({})", guid);
        super.visitGuid(guid);
    }

    @Override
    public void visitId(int id) {
        log.info("visitId({})", id);
        super.visitId(id);
    }

    @Override
    public void visitDimensions(int left, int right, int top, int bottom) {
        log.info("visitDimensions({}, {}, {}, {})", left, right, top, bottom);
        super.visitDimensions(left, right, top, bottom);
    }

    @Override
    public void visitSize(int width, int height) {
        log.info("visitSize({}, {})", width, height);
        super.visitSize(width, height);
    }

    @Override
    public void visitGameMode(GameMode mode) {
        log.info("visitGameMode({})", mode);
        super.visitGameMode(mode);
    }

    @Override
    public void visitDrunkWorld(boolean drunk) {
        log.info("visitDrunkWorld({})", drunk);
        super.visitDrunkWorld(drunk);
    }

    @Override
    public void visitGood(boolean good) {
        log.info("visitGood({})", good);
        super.visitGood(good);
    }

    @Override
    public void visitCreationTime(long time) {
        log.info("visitCreationTime({})", time);
        super.visitCreationTime(time);
    }

    @Override
    public void visitMoonType(int type) {
        log.info("visitMoonType({})", type);
        super.visitMoonType(type);
    }

    @Override
    public void visitTreeX(int index, int value) {
        log.info("visitTreeX({}, {})", index, value);
        super.visitTreeX(index, value);
    }

    @Override
    public void visitTreeStyle(int index, int value) {
        log.info("visitTreeStyle({}, {})", index, value);
        super.visitTreeStyle(index, value);
    }

    @Override
    public void visitCaveBackX(int index, int value) {
        log.info("visitCaveBackX({}, {})", index, value);
        super.visitCaveBackX(index, value);
    }

    @Override
    public void visitCaveBackStyle(int index, int value) {
        log.info("visitCaveBackStyle({}, {})", index, value);
        super.visitCaveBackStyle(index, value);
    }

    @Override
    public void visitIceBackStyle(int style) {
        log.info("visitIceBackStyle({})", style);
        super.visitIceBackStyle(style);
    }

    @Override
    public void visitJungleBackStyle(int style) {
        log.info("visitJungleBackStyle({})", style);
        super.visitJungleBackStyle(style);
    }

    @Override
    public void visitHellBackStyle(int style) {
        log.info("visitHellBackStyle({})", style);
        super.visitHellBackStyle(style);
    }

    @Override
    public void visitSpawnLocation(int x, int y) {
        log.info("visitSpawnLocation({}, {})", x, y);
        super.visitSpawnLocation(x, y);
    }

    @Override
    public void visitSurface(double value) {
        log.info("visitSurface({})", value);
        super.visitSurface(value);
    }

    @Override
    public void visitRockLayer(double value) {
        log.info("visitRockLayer({})", value);
        super.visitRockLayer(value);
    }

    @Override
    public void visitTime(double time) {
        log.info("visitTime({})", time);
        super.visitTime(time);
    }

    @Override
    public void visitDaytime(boolean daytime) {
        log.info("visitDaytime({})", daytime);
        super.visitDaytime(daytime);
    }

    @Override
    public void visitMoonPhase(int phase) {
        log.info("visitMoonPhase({})", phase);
        super.visitMoonPhase(phase);
    }

    @Override
    public void visitBloodmoon(boolean bloodmoon) {
        log.info("visitBloodmoon({})", bloodmoon);
        super.visitBloodmoon(bloodmoon);
    }

    @Override
    public void visitEclipse(boolean eclipse) {
        log.info("visitEclipse({})", eclipse);
        super.visitEclipse(eclipse);
    }

    @Override
    public void visitDungeonLocation(int x, int y) {
        log.info("visitDungeonLocation({}, {})", x, y);
        super.visitDungeonLocation(x, y);
    }

    @Override
    public void visitCrimson(boolean crimson) {
        log.info("visitCrimson({})", crimson);
        super.visitCrimson(crimson);
    }

    @Override
    public void visitNormalBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean queenBee) {
        log.info("visitNormaBossFlags({}, {}, {}, {})", boss1, boss2, boss3, queenBee);
        super.visitNormalBossFlags(boss1, boss2, boss3, queenBee);
    }

    @Override
    public void visitMechBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean any) {
        log.info("visitMechBossFlags({}, {}, {}, {})", boss1, boss2, boss3, any);
        super.visitMechBossFlags(boss1, boss2, boss3, any);
    }

    @Override
    public void visitHardmodeBossFlags(boolean plantera, boolean golem, boolean slimeKing) {
        log.info("visitHardmodeBossFlags({}, {}, {})", plantera, golem, slimeKing);
        super.visitHardmodeBossFlags(plantera, golem, slimeKing);
    }

    @Override
    public void visitSavedNpcsFlags(boolean goblin, boolean wizard, boolean mechanic) {
        log.info("visitSavedNpcsFlags({}, {}, {})", goblin, wizard, mechanic);
        super.visitSavedNpcsFlags(goblin, wizard, mechanic);
    }

    @Override
    public void visitEventCompleteFlags(boolean goblen, boolean pumpkinMoon, boolean frostMoon, boolean pirates) {
        log.info("visitEventCompleteFlags({}, {}, {}, {})", goblen, pumpkinMoon, frostMoon, pirates);
        super.visitEventCompleteFlags(goblen, pumpkinMoon, frostMoon, pirates);
    }

    @Override
    public void visitShadowOrbSmashed(boolean smashed) {
        log.info("visitShadowOrbSmashed({})", smashed);
        super.visitShadowOrbSmashed(smashed);
    }

    @Override
    public void visitMeteor(boolean spawned) {
        log.info("visitMeteor({})", spawned);
        super.visitMeteor(spawned);
    }

    @Override
    public void visitShadowOrbCount(int count) {
        log.info("visitShadowOrbCount({})", count);
        super.visitShadowOrbCount(count);
    }

    @Override
    public void visitAlterCount(int alterCount) {
        log.info("visitAlterCount({})", alterCount);
        super.visitAlterCount(alterCount);
    }

    @Override
    public void visitHardmode(boolean hardmode) {
        log.info("visitHardmode({})", hardmode);
        super.visitHardmode(hardmode);
    }

    @Override
    public void visitInvasion(int delay, int size, int type, double x) {
        log.info("visitInvasion({}, {}, {}, {})", delay, size, type, x);
        super.visitInvasion(delay, size, type, x);
    }

    @Override
    public void visitSlimerainTime(double time) {
        log.info("visitSlimerainTime({})", time);
        super.visitSlimerainTime(time);
    }

    @Override
    public void visitSundialCooldown(int cooldown) {
        log.info("visitSundialCooldown({})", cooldown);
        super.visitSundialCooldown(cooldown);
    }

    @Override
    public void visitRain(boolean raining, int time, float max) {
        log.info("visitRain({}, {}, {})", raining, time, max);
        super.visitRain(raining, time, max);
    }

    @Override
    public void visitOreTiers1(int cobalt, int mythril, int adamantite) {
        log.info("visitOreTiers1({}, {}, {})", cobalt, mythril, adamantite);
        super.visitOreTiers1(cobalt, mythril, adamantite);
    }

    @Override
    public void visitBG(int index, int value) {
        log.info("visitBG({}, {})", index, value);
        super.visitBG(index, value);
    }

    @Override
    public void visitClouds(float active, int num) {
        log.info("visitClouds({}, {})", active, num);
        super.visitClouds(active, num);
    }

    @Override
    public void visitWindSpeedTarget(float target) {
        log.info("visitWindSpeedTarget({})", target);
        super.visitWindSpeedTarget(target);
    }

    @Override
    public void visitAnglerQuest(List<String> finished, int current) {
        log.info("visitAnglerQuest({}, {})", finished, current);
        super.visitAnglerQuest(finished, current);
    }

    @Override
    public void visitSavedNpcsFlags2(boolean angler, boolean stylist, boolean taxCollector, boolean golfer) {
        log.info("visitSavedNpcsFlags2({}, {}, {}, {})", angler, stylist, taxCollector, golfer);
        super.visitSavedNpcsFlags2(angler, stylist, taxCollector, golfer);
    }

    @Override
    public void visitInvasionSizeStart(int size) {
        log.info("visitInvasionSizeStart({})", size);
        super.visitInvasionSizeStart(size);
    }

    @Override
    public void visitCultistDelay(int delay) {
        log.info("visitCultistDelay({})", delay);
        super.visitCultistDelay(delay);
    }

    @Override
    public void visitKillCounts(List<Integer> counts) {
        log.info("visitKillCounts({})", counts);
        super.visitKillCounts(counts);
    }

    @Override
    public void visitFastForward(boolean fastForward) {
        log.info("visitFastForward({})", fastForward);
        super.visitFastForward(fastForward);
    }

    @Override
    public void visitEndgameBossFlags(boolean fishron, boolean martians, boolean ancientCultist, boolean moonlord,
                                      boolean hallowenKing, boolean halloweenTree, boolean christmasIceQueen,
                                      boolean santank, boolean christmasTree) {
        log.info("visitEndgameBossFlags({}, {}, {}, {}, {}, {}, {}, {}, {})", fishron, martians, ancientCultist,
                martians, halloweenTree, halloweenTree, christmasIceQueen, santank, christmasIceQueen);
        super.visitEndgameBossFlags(fishron, martians, ancientCultist, moonlord, hallowenKing, halloweenTree,
                christmasIceQueen, santank, christmasTree);
    }

    @Override
    public void visitDownedTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        log.info("visitDownedTowers({}, {}, {}, {})", solar, vortex, nebula, solar);
        super.visitDownedTowers(solar, vortex, nebula, stardust);
    }

    @Override
    public void visitActiveTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        log.info("visitActiveTowers({}, {}, {}, {})", solar, vortex, nebula, stardust);
        super.visitActiveTowers(solar, vortex, nebula, stardust);
    }

    @Override
    public void visitApocalypse(boolean ongoing) {
        log.info("visitApocalypse({})", ongoing);
        super.visitApocalypse(ongoing);
    }

    @Override
    public void visitParty(boolean ongoing, boolean genuine, int cooldown, List<Integer> partying) {
        log.info("visitParty({}, {}, {}, {})", ongoing, genuine, cooldown, partying);
        super.visitParty(ongoing, genuine, cooldown, partying);
    }

    @Override
    public void visitSandstorm(boolean happening, int timeLeft, float severity, float intendedSeverity) {
        log.info("visitSandstorm({}, {}, {}, {})", happening, timeLeft, severity, intendedSeverity);
        super.visitSandstorm(happening, timeLeft, severity, intendedSeverity);
    }

    @Override
    public void visitDungeonDefense(boolean savedBartender, boolean invasionT1, boolean invasionT2, boolean invasionT3) {
        log.info("visitDungeonDefense({}, {}, {}, {})", savedBartender, invasionT1, invasionT2, invasionT3);
        super.visitDungeonDefense(savedBartender, invasionT1, invasionT2, invasionT3);
    }

    @Override
    public void visitCombatBook(boolean used) {
        log.info("visitCombatBook({})", used);
        super.visitCombatBook(used);
    }

    @Override
    public void visitLantern(int cooldown, boolean genuine, boolean manual, boolean nextNightGenuine) {
        log.info("visitLantern({}, {}, {}, {})", cooldown, genuine, manual, nextNightGenuine);
        super.visitLantern(cooldown, genuine, manual, nextNightGenuine);
    }

    @Override
    public void visitTreetopStyle(int index, int style) {
        log.info("visitTreetopStyle({}, {})", index, style);
        super.visitTreetopStyle(index, style);
    }

    @Override
    public void visitForceEvents(boolean halloween, boolean christmas) {
        log.info("visitForceEvents({}, {})", halloween, christmas);
        super.visitForceEvents(halloween, christmas);
    }

    @Override
    public void visitOreTiers2(int copper, int iron, int silver, int gold) {
        log.info("visitOreTiers2({}, {}, {}, {})", copper, iron, silver, gold);
        super.visitOreTiers2(copper, iron, silver, gold);
    }

    @Override
    public void visitBoughtPets(boolean cat, boolean dog, boolean bunny) {
        log.info("visitBoughtPets({}, {}, {})", cat, dog, bunny);
        super.visitBoughtPets(cat, dog, bunny);
    }

    @Override
    public void visitHallowBosses(boolean empress, boolean slimeQueen) {
        log.info("visitHallowBosses({}, {})", empress, slimeQueen);
        super.visitHallowBosses(empress, slimeQueen);
    }

    @Override
    public void visitEnd() {
        log.info("visitEnd()");
        super.visitEnd();
    }
}
