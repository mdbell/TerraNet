package me.mdbell.terranet.world.writer;

import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.util.IOUtil;
import me.mdbell.terranet.common.util.UUID;
import me.mdbell.terranet.files.GameMode;
import me.mdbell.terranet.world.MetadataVisitor;

import java.util.List;

@ExtensionMethod({IOUtil.class})
class MetadataWriterVisitor implements MetadataVisitor {

    private final Buffer<?> buffer;
    private WorldWriter writer;

    public MetadataWriterVisitor(WorldWriter writer) {
        this.writer = writer;
        this.buffer = writer.getBuffer();
    }

    @Override
    public void visitStart() {

    }

    @Override
    public void visitName(String name) {
        buffer.writeString(name);
        writer.setName(name); // needed for writing the footer
    }

    @Override
    public void visitSeed(String seed) {
        buffer.writeString(seed);
    }

    @Override
    public void visitWorldGenVersion(long version) {
        buffer.writeLongLE(version);
    }

    @Override
    public void visitGuid(UUID guid) {
        buffer.writeGuid(guid);
    }

    @Override
    public void visitId(int id) {
        buffer.writeIntLE(id);
        writer.setId(id); // needed for footer
    }

    @Override
    public void visitDimensions(int left, int right, int top, int bottom) {
        buffer.writeIntLE(left)
                .writeIntLE(right)
                .writeIntLE(top)
                .writeIntLE(bottom);
    }

    @Override
    public void visitSize(int height, int width) {
        buffer.writeIntLE(height)
                .writeIntLE(width);
    }

    @Override
    public void visitGameMode(GameMode mode) {
        buffer.writeIntLE(mode.ordinal());
    }

    @Override
    public void visitDrunkWorld(boolean drunk) {
        buffer.writeBoolean(drunk);
    }

    @Override
    public void visitGood(boolean good) {
        buffer.writeBoolean(good);
    }

    @Override
    public void visitCreationTime(long time) {
        buffer.writeLongLE(time);
    }

    @Override
    public void visitMoonType(int type) {
        buffer.writeByte(type);
    }

    @Override
    public void visitTreeX(int index, int value) {
        buffer.writeIntLE(value);
    }

    @Override
    public void visitTreeStyle(int index, int value) {
        buffer.writeIntLE(value);
    }

    @Override
    public void visitCaveBackX(int index, int value) {
        buffer.writeIntLE(value);
    }

    @Override
    public void visitCaveBackStyle(int index, int value) {
        buffer.writeIntLE(value);
    }

    @Override
    public void visitIceBackStyle(int style) {
        buffer.writeIntLE(style);
    }

    @Override
    public void visitJungleBackStyle(int style) {
        buffer.writeIntLE(style);
    }

    @Override
    public void visitHellBackStyle(int style) {
        buffer.writeIntLE(style);
    }

    @Override
    public void visitSpawnLocation(int x, int y) {
        buffer.writeIntLE(x)
                .writeIntLE(y);
    }

    @Override
    public void visitSurface(double value) {
        buffer.writeDoubleLE(value);
    }

    @Override
    public void visitRockLayer(double value) {
        buffer.writeDoubleLE(value);
    }

    @Override
    public void visitTime(double time) {
        buffer.writeDoubleLE(time);
    }

    @Override
    public void visitDaytime(boolean daytime) {
        buffer.writeBoolean(daytime);
    }

    @Override
    public void visitMoonPhase(int phase) {
        buffer.writeIntLE(phase);
    }

    @Override
    public void visitBloodmoon(boolean bloodmoon) {
        buffer.writeBoolean(bloodmoon);
    }

    @Override
    public void visitEclipse(boolean eclipse) {
        buffer.writeBoolean(eclipse);
    }

    @Override
    public void visitDungeonLocation(int x, int y) {
        buffer.writeIntLE(x)
                .writeIntLE(y);
    }

    @Override
    public void visitCrimson(boolean crimson) {
        buffer.writeBoolean(crimson);
    }

    @Override
    public void visitNormalBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean queenBee) {
        buffer.writeBoolean(boss1)
                .writeBoolean(boss2)
                .writeBoolean(boss3)
                .writeBoolean(queenBee);
    }

    @Override
    public void visitMechBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean any) {
        buffer.writeBoolean(boss1)
                .writeBoolean(boss2)
                .writeBoolean(boss3)
                .writeBoolean(any);
    }

    @Override
    public void visitHardmodeBossFlags(boolean plantera, boolean golem, boolean slimeKing) {
        buffer.writeBoolean(plantera)
                .writeBoolean(golem)
                .writeBoolean(slimeKing);
    }

    @Override
    public void visitSavedNpcsFlags(boolean goblin, boolean wizard, boolean mechanic) {

    }

    @Override
    public void visitEventCompleteFlags(boolean goblen, boolean pumpkinMoon, boolean frostMoon, boolean pirates) {

    }

    @Override
    public void visitShadowOrbSmashed(boolean smashed) {

    }

    @Override
    public void visitMeteor(boolean spawned) {

    }

    @Override
    public void visitShadowOrbCount(int count) {

    }

    @Override
    public void visitAlterCount(int alterCount) {

    }

    @Override
    public void visitHardmode(boolean hardmode) {

    }

    @Override
    public void visitInvasion(int delay, int size, int type, double x) {

    }

    @Override
    public void visitSlimerainTime(double time) {

    }

    @Override
    public void visitSundialCooldown(int cooldown) {

    }

    @Override
    public void visitRain(boolean raining, int time, float max) {

    }

    @Override
    public void visitOreTiers1(int cobalt, int mythril, int adamantite) {

    }

    @Override
    public void visitBG(int index, int value) {

    }

    @Override
    public void visitClouds(float active, int num) {

    }

    @Override
    public void visitWindSpeedTarget(float target) {

    }

    @Override
    public void visitAnglerQuest(List<String> finished, int current) {

    }

    @Override
    public void visitSavedNpcsFlags2(boolean angler, boolean stylist, boolean taxCollector, boolean golfer) {

    }

    @Override
    public void visitInvasionSizeStart(int size) {

    }

    @Override
    public void visitCultistDelay(int delay) {

    }

    @Override
    public void visitKillCounts(List<Integer> counts) {

    }

    @Override
    public void visitFastForward(boolean fastForward) {

    }

    @Override
    public void visitEndgameBossFlags(boolean fishron, boolean martians, boolean ancientCultist, boolean moonlord, boolean hallowenKing, boolean halloweenTree, boolean christmasIceQueen, boolean santank, boolean christmasTree) {

    }

    @Override
    public void visitDownedTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {

    }

    @Override
    public void visitActiveTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {

    }

    @Override
    public void visitApocalypse(boolean ongoing) {

    }

    @Override
    public void visitParty(boolean ongoing, boolean genuine, int cooldown, List<Integer> partying) {

    }

    @Override
    public void visitSandstorm(boolean happening, int timeLeft, float severity, float intendedSeverity) {

    }

    @Override
    public void visitDungeonDefense(boolean savedBartender, boolean invasionT1, boolean invasionT2, boolean invasionT3) {

    }

    @Override
    public void visitCombatBook(boolean used) {

    }

    @Override
    public void visitLantern(int cooldown, boolean genuine, boolean manual, boolean nextNightGenuine) {

    }

    @Override
    public void visitTreetopStyle(int index, int style) {

    }

    @Override
    public void visitForceEvents(boolean halloween, boolean christmas) {

    }

    @Override
    public void visitOreTiers2(int copper, int iron, int silver, int gold) {

    }

    @Override
    public void visitBoughtPets(boolean cat, boolean dog, boolean bunny) {

    }

    @Override
    public void visitHallowBosses(boolean empress, boolean slimeQueen) {

    }

    @Override
    public void visitEnd() {

    }
}
