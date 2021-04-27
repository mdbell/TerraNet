package me.mdbell.terranet.world.writer;

import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.ext.BufferExtensions;
import me.mdbell.terranet.common.util.UUID;
import me.mdbell.terranet.files.GameMode;
import me.mdbell.terranet.world.MetadataVisitor;

import java.util.List;

@ExtensionMethod({BufferExtensions.class})
class MetadataWriterVisitor implements MetadataVisitor {

    private final Buffer<?> buffer;
    private WorldWriter writer;
    private List<String> fished;
    private int currentQuest;

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
        buffer.writeBoolean(goblin)
                .writeBoolean(wizard)
                .writeBoolean(mechanic);
    }

    @Override
    public void visitEventCompleteFlags(boolean goblin, boolean pumpkinMoon, boolean frostMoon, boolean pirates) {
        buffer.writeBoolean(goblin)
                .writeBoolean(pumpkinMoon)
                .writeBoolean(frostMoon)
                .writeBoolean(pirates);
    }

    @Override
    public void visitShadowOrbSmashed(boolean smashed) {
        buffer.writeBoolean(smashed);
    }

    @Override
    public void visitMeteor(boolean spawned) {
        buffer.writeBoolean(spawned);
    }

    @Override
    public void visitShadowOrbCount(int count) {
        buffer.writeByte(count);
    }

    @Override
    public void visitAlterCount(int alterCount) {
        buffer.writeIntLE(alterCount);
    }

    @Override
    public void visitHardmode(boolean hardmode) {
        buffer.writeBoolean(hardmode);
    }

    @Override
    public void visitInvasion(int delay, int size, int type, double x) {
        buffer.writeIntLE(delay)
                .writeIntLE(size)
                .writeIntLE(type)
                .writeDoubleLE(x);
    }

    @Override
    public void visitSlimerainTime(double time) {
        buffer.writeDoubleLE(time);
    }

    @Override
    public void visitSundialCooldown(int cooldown) {
        buffer.writeByte(cooldown);
    }

    @Override
    public void visitRain(boolean raining, int time, float max) {
        buffer.writeBoolean(raining)
                .writeIntLE(time)
                .writeFloatLE(max);
    }

    @Override
    public void visitOreTiers1(int cobalt, int mythril, int adamantite) {
        buffer.writeIntLE(cobalt)
                .writeIntLE(mythril)
                .writeIntLE(adamantite);
    }

    @Override
    public void visitBG(int index, int value) {
        buffer.writeByte(value);
    }

    @Override
    public void visitClouds(float active, int num) {
        buffer.writeIntLE((int) active)
                .writeShortLE(num);
    }

    @Override
    public void visitWindSpeedTarget(float target) {
        buffer.writeFloatLE(target);
    }

    @Override
    public void visitAnglerQuest(List<String> finished, int current) {
        this.fished = List.copyOf(finished);
        this.currentQuest = current;
    }

    @Override
    public void visitSavedNpcsFlags2(boolean angler, boolean stylist, boolean taxCollector, boolean golfer) {
        this.fished = List.copyOf(fished);
        buffer.writeIntLE(fished.size());
        for (String s : fished) {
            buffer.writeString(s);
        }
        buffer.writeBoolean(angler);
        buffer.writeIntLE(currentQuest);
        buffer.writeBoolean(stylist);
        buffer.writeBoolean(taxCollector);
        buffer.writeBoolean(golfer);

        this.fished = null;
    }

    @Override
    public void visitInvasionSizeStart(int size) {
        buffer.writeIntLE(size);
    }

    @Override
    public void visitCultistDelay(int delay) {
        buffer.writeIntLE(delay);
    }

    @Override
    public void visitKillCounts(List<Integer> counts) {
        buffer.writeShortLE(counts.size());
        for (int i = 0; i < counts.size(); i++) {
            buffer.writeIntLE(counts.get(i));
        }
    }

    @Override
    public void visitFastForward(boolean fastForward) {
        buffer.writeBoolean(fastForward);
    }

    @Override
    public void visitEndgameBossFlags(boolean fishron, boolean martians, boolean ancientCultist, boolean moonlord,
                                      boolean hallowenKing, boolean halloweenTree, boolean christmasIceQueen,
                                      boolean santank, boolean christmasTree) {
        buffer.writeBoolean(fishron)
                .writeBoolean(martians)
                .writeBoolean(ancientCultist)
                .writeBoolean(moonlord)
                .writeBoolean(hallowenKing)
                .writeBoolean(halloweenTree)
                .writeBoolean(christmasIceQueen)
                .writeBoolean(santank)
                .writeBoolean(christmasTree);
    }

    @Override
    public void visitDownedTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        buffer.writeBoolean(solar)
                .writeBoolean(vortex)
                .writeBoolean(nebula)
                .writeBoolean(stardust);
    }

    @Override
    public void visitActiveTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        buffer.writeBoolean(solar)
                .writeBoolean(vortex)
                .writeBoolean(nebula)
                .writeBoolean(stardust);
    }

    @Override
    public void visitApocalypse(boolean ongoing) {
        buffer.writeBoolean(ongoing);
    }

    @Override
    public void visitParty(boolean ongoing, boolean genuine, int cooldown, List<Integer> partying) {
        buffer.writeBoolean(ongoing)
                .writeBoolean(genuine)
                .writeIntLE(cooldown)
                .writeIntLE(partying.size());
        for (int i : partying) {
            buffer.writeIntLE(i);
        }
    }

    @Override
    public void visitSandstorm(boolean happening, int timeLeft, float severity, float intendedSeverity) {
        buffer.writeBoolean(happening)
                .writeIntLE(timeLeft)
                .writeFloatLE(severity)
                .writeFloatLE(intendedSeverity);
    }

    @Override
    public void visitDungeonDefense(boolean savedBartender, boolean invasionT1, boolean invasionT2, boolean invasionT3) {
        buffer.writeBoolean(savedBartender)
                .writeBoolean(invasionT1)
                .writeBoolean(invasionT2)
                .writeBoolean(invasionT3);
    }

    @Override
    public void visitCombatBook(boolean used) {
        buffer.writeBoolean(used);
    }

    @Override
    public void visitLantern(int cooldown, boolean genuine, boolean manual, boolean nextNightGenuine) {
        buffer.writeIntLE(cooldown)
                .writeBoolean(genuine)
                .writeBoolean(manual)
                .writeBoolean(nextNightGenuine);
    }

    @Override
    public void visitTreetopStyleCount(int count) {
        buffer.writeIntLE(count);
    }

    @Override
    public void visitTreetopStyle(int index, int style) {
        buffer.writeIntLE(style);
    }

    @Override
    public void visitForceEvents(boolean halloween, boolean christmas) {
        buffer.writeBoolean(halloween)
                .writeBoolean(christmas);
    }

    @Override
    public void visitOreTiers2(int copper, int iron, int silver, int gold) {
        buffer.writeIntLE(copper)
                .writeIntLE(iron)
                .writeIntLE(silver)
                .writeIntLE(gold);
    }

    @Override
    public void visitBoughtPets(boolean cat, boolean dog, boolean bunny) {
        buffer.writeBoolean(cat)
                .writeBoolean(dog)
                .writeBoolean(bunny);
    }

    @Override
    public void visitHallowBosses(boolean empress, boolean slimeQueen) {
        buffer.writeBoolean(empress)
                .writeBoolean(slimeQueen);
    }

    @Override
    public void visitEnd() {

    }
}
