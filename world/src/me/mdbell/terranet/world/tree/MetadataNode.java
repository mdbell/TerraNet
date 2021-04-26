package me.mdbell.terranet.world.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import me.mdbell.terranet.common.util.UUID;
import me.mdbell.terranet.files.GameMode;
import me.mdbell.terranet.world.MetadataVisitor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
public class MetadataNode implements MetadataVisitor {

    private String name, seed;
    private long worldGenVersion;
    private UUID guid;
    private int id;
    private int left, right, top, bottom;
    private int width, height;
    private GameMode gameMode;
    private boolean drunk;
    private boolean good;
    private long creationTime;
    private int moonType;
    private final int[] treeX = new int[3];
    private final int[] treeStyle = new int[4];
    private final int[] caveBackX = new int[3];
    private final int[] caveBackStyle = new int[4];
    private int iceBackStyle;
    private int jungleBackStyle;
    private int helLBackStyle;
    private int spawnX, spawnY;
    private double surface;
    private double rockLayer;
    private double time;
    private boolean daytime;
    private int moonPhase;
    private boolean bloodmoon, eclipse;
    private int dungeonX, dungeonY;
    private boolean crimson;
    private boolean downedBoss1, downedBoss2, downedBoss3, downedQueenBee;
    private boolean downedMechBoss1, downedMechBoss2, downedMechBoss3, downedMechAny;
    private boolean downedPantera, downedGolem, downedSlimeKing;
    private boolean savedGoblin, savedWizard, savedMechanic;
    private boolean downedGoblinArmy, downedPumpkinMoon, downedFrostMoon, downedPirates;
    private boolean shadowOrbSmashed;
    private boolean meteorSpawned;
    private int shadowOrbCount;
    private int alterCount;
    private boolean hardmode;
    private int invasionDelay;
    private int invasionSize;
    private int invasionType;
    private double invasionX;
    private double slimeRainTime;
    private int sundialCooldown;
    private boolean raining;
    private int rainTime;
    private float maxRain;
    private int cobaltOreTier, mythrilOreTier, adamantiteOreTier;
    private final int[] bg = new int[13];
    private float cloudsActive;
    private int cloudsCount;
    private float windSpeedTarget;
    private final List<String> fishedPlayers = new LinkedList<>();
    private int fishingQuest;
    private boolean savedAngler;
    private boolean savedStylist;
    private boolean savedTaxCollector;
    private boolean savedGolfer;
    private int invasionSizeStart;
    private int cultistDelay;
    private final List<Integer> killCounts = new ArrayList<>();
    private boolean fastForward;
    private boolean downedFishron;
    private boolean downedMartians;
    private boolean downedAncientCultist;
    private boolean downedMoonlord;
    private boolean downedHalloweenKing;
    private boolean downedHalloweenTree;
    private boolean downedIceQueen;
    private boolean downedSantank;
    private boolean downedChristmasTree;
    private boolean downedSolar;
    private boolean downedVortex;
    private boolean downedNebula;
    private boolean downedStardust;
    private boolean solarActive;
    private boolean vortexActive;
    private boolean nebulaActive;
    private boolean stardustActive;
    private boolean apocalypseOngoing;
    private boolean partyOngoing;
    private boolean partyReal;
    private int partyCooldown;
    private final List<Integer> partying = new ArrayList<>();
    private boolean sandstormOngoing;
    private int sandstormTimeRemaining;
    private float sandstormSeverity;
    private float sandstormIntendedSeverity;
    private boolean savedBartender;
    private boolean invasionT1;
    private boolean invasionT2;
    private boolean invasionT3;
    private boolean combatBookUsed;
    private int lanternsCooldown;
    private boolean lanternsReal;
    private boolean lanternsForced;
    private boolean nextLanternsReal;
    private int[] treeTopStyles;
    private boolean forceHalloween;
    private boolean forceChristmas;
    private int copperOreTier;
    private int ironOreTier;
    private int silverOreTier;
    private int goldOreTier;
    private boolean boughtCat;
    private boolean boughtDog;
    private boolean boughtBunny;
    private boolean downedEmpress;
    private boolean downedSlimeQueen;

    @Override
    public void visitStart() {

    }

    @Override
    public void visitName(String name) {
        this.name = name;
    }

    @Override
    public void visitSeed(String seed) {
        this.seed = seed;
    }

    @Override
    public void visitWorldGenVersion(long version) {
        this.worldGenVersion = version;
    }

    @Override
    public void visitGuid(UUID guid) {
        this.guid = guid;
    }

    @Override
    public void visitId(int id) {
        this.id = id;
    }

    @Override
    public void visitDimensions(int left, int right, int top, int bottom) {
        this.left = left;
        this.right = right;
        this.top = top;
        this.bottom = bottom;
    }

    @Override
    public void visitSize(int height, int width) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void visitGameMode(GameMode mode) {
        this.gameMode = mode;
    }

    @Override
    public void visitDrunkWorld(boolean drunk) {
        this.drunk = drunk;
    }

    @Override
    public void visitGood(boolean good) {
        this.good = good;
    }

    @Override
    public void visitCreationTime(long time) {
        this.creationTime = time;
    }

    @Override
    public void visitMoonType(int type) {
        this.moonType = type;
    }

    @Override
    public void visitTreeX(int index, int value) {
        treeX[index] = value;
    }

    @Override
    public void visitTreeStyle(int index, int value) {
        treeStyle[index] = value;
    }

    @Override
    public void visitCaveBackX(int index, int value) {
        caveBackX[index] = value;
    }

    @Override
    public void visitCaveBackStyle(int index, int value) {
        caveBackStyle[index] = value;
    }

    @Override
    public void visitIceBackStyle(int style) {
        this.iceBackStyle = style;
    }

    @Override
    public void visitJungleBackStyle(int style) {
        this.jungleBackStyle = style;
    }

    @Override
    public void visitHellBackStyle(int style) {
        this.helLBackStyle = style;
    }

    @Override
    public void visitSpawnLocation(int x, int y) {
        this.spawnX = x;
        this.spawnY = y;
    }

    @Override
    public void visitSurface(double value) {
        this.surface = value;
    }

    @Override
    public void visitRockLayer(double value) {
        this.rockLayer = value;
    }

    @Override
    public void visitTime(double time) {
        this.time = time;
    }

    @Override
    public void visitDaytime(boolean daytime) {
        this.daytime = daytime;
    }

    @Override
    public void visitMoonPhase(int phase) {
        this.moonPhase = phase;
    }

    @Override
    public void visitBloodmoon(boolean bloodmoon) {
        this.bloodmoon = bloodmoon;
    }

    @Override
    public void visitEclipse(boolean eclipse) {
        this.eclipse = eclipse;
    }

    @Override
    public void visitDungeonLocation(int x, int y) {
        this.dungeonX = x;
        this.dungeonY = y;
    }

    @Override
    public void visitCrimson(boolean crimson) {
        this.crimson = crimson;
    }

    @Override
    public void visitNormalBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean queenBee) {
        this.downedBoss1 = boss1;
        this.downedBoss2 = boss2;
        this.downedBoss3 = boss3;
        this.downedQueenBee = queenBee;
    }

    @Override
    public void visitMechBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean any) {
        this.downedMechBoss1 = boss1;
        this.downedMechBoss2 = boss2;
        this.downedMechBoss3 = boss3;
        this.downedMechAny = any;
    }

    @Override
    public void visitHardmodeBossFlags(boolean plantera, boolean golem, boolean slimeKing) {
        this.downedPantera = plantera;
        this.downedGolem = golem;
        this.downedSlimeKing = slimeKing;
    }

    @Override
    public void visitSavedNpcsFlags(boolean goblin, boolean wizard, boolean mechanic) {
        this.savedGoblin = goblin;
        this.savedWizard = wizard;
        this.savedMechanic = mechanic;
    }

    @Override
    public void visitEventCompleteFlags(boolean goblin, boolean pumpkinMoon, boolean frostMoon, boolean pirates) {
        this.downedGoblinArmy = goblin;
        this.downedPumpkinMoon = pumpkinMoon;
        this.downedFrostMoon = frostMoon;
        this.downedPirates = pirates;
    }

    @Override
    public void visitShadowOrbSmashed(boolean smashed) {
        this.shadowOrbSmashed = smashed;
    }

    @Override
    public void visitMeteor(boolean spawned) {
        this.meteorSpawned = spawned;
    }

    @Override
    public void visitShadowOrbCount(int count) {
        this.shadowOrbCount = count;
    }

    @Override
    public void visitAlterCount(int alterCount) {
        this.alterCount = alterCount;
    }

    @Override
    public void visitHardmode(boolean hardmode) {
        this.hardmode = hardmode;
    }

    @Override
    public void visitInvasion(int delay, int size, int type, double x) {
        this.invasionDelay = delay;
        this.invasionSize = size;
        this.invasionType = type;
        this.invasionX = x;
    }

    @Override
    public void visitSlimerainTime(double time) {
        this.slimeRainTime = time;
    }

    @Override
    public void visitSundialCooldown(int cooldown) {
        this.sundialCooldown = cooldown;
    }

    @Override
    public void visitRain(boolean raining, int time, float max) {
        this.raining = raining;
        this.rainTime = time;
        this.maxRain = max;
    }

    @Override
    public void visitOreTiers1(int cobalt, int mythril, int adamantite) {
        this.cobaltOreTier = cobalt;
        this.mythrilOreTier = mythril;
        this.adamantiteOreTier = adamantite;
    }

    @Override
    public void visitBG(int index, int value) {
        this.bg[index] = value;
    }

    @Override
    public void visitClouds(float active, int num) {
        this.cloudsActive = active;
        this.cloudsCount = num;
    }

    @Override
    public void visitWindSpeedTarget(float target) {
        this.windSpeedTarget = target;
    }

    @Override
    public void visitAnglerQuest(List<String> finished, int current) {
        this.fishedPlayers.clear();
        fishedPlayers.addAll(finished);
        this.fishingQuest = current;
    }

    @Override
    public void visitSavedNpcsFlags2(boolean angler, boolean stylist, boolean taxCollector, boolean golfer) {
        this.savedAngler = angler;
        this.savedStylist = stylist;
        this.savedTaxCollector = taxCollector;
        this.savedGolfer = golfer;
    }

    @Override
    public void visitInvasionSizeStart(int size) {
        this.invasionSizeStart = size;
    }

    @Override
    public void visitCultistDelay(int delay) {
        this.cultistDelay = delay;
    }

    @Override
    public void visitKillCounts(List<Integer> counts) {
        this.killCounts.clear();
        this.killCounts.addAll(counts);
    }

    @Override
    public void visitFastForward(boolean fastForward) {
        this.fastForward = fastForward;
    }

    @Override
    public void visitEndgameBossFlags(boolean fishron, boolean martians, boolean ancientCultist, boolean moonlord,
                                      boolean hallowenKing, boolean halloweenTree, boolean christmasIceQueen,
                                      boolean santank, boolean christmasTree) {
        this.downedFishron = fishron;
        this.downedMartians = martians;
        this.downedAncientCultist = ancientCultist;
        this.downedMoonlord = moonlord;
        this.downedHalloweenKing = hallowenKing;
        this.downedHalloweenTree = halloweenTree;
        this.downedIceQueen = christmasIceQueen;
        this.downedSantank = santank;
        this.downedChristmasTree = christmasTree;
    }

    @Override
    public void visitDownedTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        this.downedSolar = solar;
        this.downedVortex = vortex;
        this.downedNebula = nebula;
        this.downedStardust = stardust;
    }

    @Override
    public void visitActiveTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        this.solarActive = solar;
        this.vortexActive = vortex;
        this.nebulaActive = nebula;
        this.stardustActive = stardust;
    }

    @Override
    public void visitApocalypse(boolean ongoing) {
        this.apocalypseOngoing = ongoing;
    }

    @Override
    public void visitParty(boolean ongoing, boolean genuine, int cooldown, List<Integer> partying) {
        this.partyOngoing = ongoing;
        this.partyReal = genuine;
        this.partyCooldown = cooldown;
        this.partying.clear();
        this.partying.addAll(partying);
    }

    @Override
    public void visitSandstorm(boolean happening, int timeLeft, float severity, float intendedSeverity) {
        this.sandstormOngoing = happening;
        this.sandstormTimeRemaining = timeLeft;
        this.sandstormSeverity = severity;
        this.sandstormIntendedSeverity = intendedSeverity;
    }

    @Override
    public void visitDungeonDefense(boolean savedBartender, boolean invasionT1, boolean invasionT2, boolean invasionT3) {
        this.savedBartender = savedBartender;
        this.invasionT1 = invasionT1;
        this.invasionT2 = invasionT2;
        this.invasionT3 = invasionT3;
    }

    @Override
    public void visitCombatBook(boolean used) {
        this.combatBookUsed = used;
    }

    @Override
    public void visitLantern(int cooldown, boolean genuine, boolean manual, boolean nextNightGenuine) {
        this.lanternsCooldown = cooldown;
        this.lanternsReal = genuine;
        this.lanternsForced = manual;
        this.nextLanternsReal = nextNightGenuine;
    }

    public void visitTreetopStyleCount(int count){
        this.treeTopStyles = new int[count];
    }

    @Override
    public void visitTreetopStyle(int index, int style) {
        this.treeTopStyles[index] = style;
    }

    @Override
    public void visitForceEvents(boolean halloween, boolean christmas) {
        this.forceHalloween = halloween;
        this.forceChristmas = christmas;
    }

    @Override
    public void visitOreTiers2(int copper, int iron, int silver, int gold) {
        this.copperOreTier = copper;
        this.ironOreTier = iron;
        this.silverOreTier = silver;
        this.goldOreTier = gold;
    }

    @Override
    public void visitBoughtPets(boolean cat, boolean dog, boolean bunny) {
        this.boughtCat = cat;
        this.boughtDog = dog;
        this.boughtBunny = bunny;
    }

    @Override
    public void visitHallowBosses(boolean empress, boolean slimeQueen) {
        this.downedEmpress = empress;
        this.downedSlimeQueen = slimeQueen;
    }


    @Override
    public void visitEnd() {

    }

    public void accept(MetadataVisitor visitor) {
        visitor.visitStart();

        visitor.visitName(name);
        visitor.visitSeed(seed);
        visitor.visitWorldGenVersion(worldGenVersion);
        visitor.visitGuid(guid);
        visitor.visitId(id);
        visitor.visitDimensions(left, right, top, bottom);
        visitor.visitSize(height, width);
        visitor.visitGameMode(gameMode);
        visitor.visitDrunkWorld(drunk);
        visitor.visitGood(good);
        visitor.visitCreationTime(creationTime);
        visitor.visitMoonType(moonType);
        for (int i = 0; i < treeX.length; i++) {
            visitor.visitTreeX(i, treeX[i]);
        }
        for (int i = 0; i < treeStyle.length; i++) {
            visitor.visitTreeStyle(i, treeStyle[i]);
        }
        for (int i = 0; i < 3; i++) {
            visitor.visitCaveBackX(i, caveBackX[i]);
        }
        for (int i = 0; i < 4; i++) {
            visitor.visitCaveBackStyle(i, caveBackStyle[i]);
        }
        visitor.visitIceBackStyle(iceBackStyle);
        visitor.visitJungleBackStyle(jungleBackStyle);
        visitor.visitHellBackStyle(helLBackStyle);
        visitor.visitSpawnLocation(spawnX, spawnY);
        visitor.visitSurface(surface);
        visitor.visitRockLayer(rockLayer);
        visitor.visitTime(time);
        visitor.visitDaytime(daytime);
        visitor.visitMoonPhase(moonPhase);
        visitor.visitBloodmoon(bloodmoon);
        visitor.visitEclipse(eclipse);
        visitor.visitDungeonLocation(dungeonX, dungeonY);
        visitor.visitCrimson(crimson);
        visitor.visitNormalBossFlags(downedBoss1, downedBoss2, downedBoss3, downedQueenBee);
        visitor.visitMechBossFlags(downedMechBoss1, downedMechBoss2, downedMechBoss3, downedMechAny);
        visitor.visitHardmodeBossFlags(downedPantera, downedGolem, downedSlimeKing);
        visitor.visitSavedNpcsFlags(savedGoblin, savedWizard, savedMechanic);
        visitor.visitEventCompleteFlags(downedGoblinArmy, downedPumpkinMoon, downedFrostMoon, downedPirates);
        visitor.visitShadowOrbSmashed(shadowOrbSmashed);
        visitor.visitMeteor(meteorSpawned);
        visitor.visitShadowOrbCount(shadowOrbCount);
        visitor.visitAlterCount(alterCount);
        visitor.visitHardmode(hardmode);
        visitor.visitInvasion(invasionDelay, invasionSize, invasionType, invasionX);
        visitor.visitSlimerainTime(slimeRainTime);
        visitor.visitSundialCooldown(sundialCooldown);
        visitor.visitRain(raining, rainTime, maxRain);
        visitor.visitOreTiers1(cobaltOreTier, mythrilOreTier, adamantiteOreTier);
        for (int i = 0; i < 8; i++) {
            visitor.visitBG(i, bg[i]);
        }
        visitor.visitClouds(cloudsActive, cloudsCount);
        visitor.visitWindSpeedTarget(windSpeedTarget);
        visitor.visitAnglerQuest(fishedPlayers, fishingQuest);
        visitor.visitSavedNpcsFlags2(savedAngler, savedStylist, savedTaxCollector, savedGolfer);
        visitor.visitInvasionSizeStart(invasionSizeStart);
        visitor.visitCultistDelay(cultistDelay);
        visitor.visitKillCounts(killCounts);
        visitor.visitFastForward(fastForward);
        visitor.visitEndgameBossFlags(downedFishron,
                downedMartians,
                downedAncientCultist,
                downedMoonlord,
                downedHalloweenKing,
                downedHalloweenTree,
                downedIceQueen,
                downedSantank,
                downedChristmasTree);

        visitor.visitDownedTowers(downedSolar,
                downedVortex,
                downedNebula,
                downedStardust);

        visitor.visitActiveTowers(solarActive,
                vortexActive,
                nebulaActive,
                stardustActive);
        visitor.visitApocalypse(apocalypseOngoing);
        visitor.visitParty(partyOngoing, partyReal, partyCooldown, partying);
        visitor.visitSandstorm(sandstormOngoing, sandstormTimeRemaining,
                sandstormSeverity, sandstormIntendedSeverity);
        visitor.visitDungeonDefense(savedBartender, invasionT1, invasionT2, invasionT3);
        for(int i = 8; i < 13; i++){
            visitor.visitBG(i, bg[i]);
        }
        visitor.visitCombatBook(combatBookUsed);
        visitor.visitLantern(lanternsCooldown,
                lanternsReal,
                lanternsForced,
                nextLanternsReal);
        visitor.visitTreetopStyleCount(treeTopStyles.length);
        for(int i = 0; i < treeTopStyles.length; i++){
            visitor.visitTreetopStyle(i, treeTopStyles[i]);
        }
        visitor.visitForceEvents(forceHalloween, forceChristmas);
        visitor.visitOreTiers2(copperOreTier,
                ironOreTier,
                silverOreTier,
                goldOreTier);
        visitor.visitBoughtPets(boughtCat, boughtDog, boughtBunny);
        visitor.visitHallowBosses(downedEmpress, downedSlimeQueen);
        visitor.visitEnd();
    }
}
