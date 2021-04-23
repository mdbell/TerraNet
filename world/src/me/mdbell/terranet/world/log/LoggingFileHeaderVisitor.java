package me.mdbell.terranet.world.log;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.files.GameMode;
import me.mdbell.terranet.world.AbstractMetadataVisitor;
import me.mdbell.terranet.world.MetadataVisitor;

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
    public void visitEnd() {
        log.info("visitEnd()");
        super.visitEnd();
    }
}
