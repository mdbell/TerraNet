package me.mdbell.terranet.world.adapters;

import me.mdbell.terranet.common.game.messages.WorldMetadataMessage;
import me.mdbell.terranet.common.util.UUID;
import me.mdbell.terranet.world.AbstractMetadataVisitor;

import java.util.List;

public class MetadataAdapter extends AbstractMetadataVisitor {

    private final WorldMetadataMessage.WorldMetadataMessageBuilder builder = WorldMetadataMessage.builder();

    @Override
    public void visitName(String name) {
        builder.worldName(name);
    }

    @Override
    public void visitWorldGenVersion(long version) {
        builder.worldGenVersion(version);
    }

    @Override
    public void visitGuid(UUID guid) {
        builder.guid(guid);
    }

    @Override
    public void visitId(int id) {
        builder.worldId(id);
    }

    @Override
    public void visitSize(int height, int width) {
        builder.maxTilesX(width).maxTilesY(height);
    }

    @Override
    public void visitGameMode(me.mdbell.terranet.files.GameMode mode) {
        builder.gameMode(mode.ordinal());
    }

    @Override
    public void visitDrunkWorld(boolean drunk) {
        builder.drunkWorld(drunk);
    }

    @Override
    public void visitGood(boolean good) {
        builder.goodWorld(good);
    }

    @Override
    public void visitMoonType(int type) {
        builder.moonType(type);
    }

    @Override
    public void visitIceBackStyle(int style) {
        builder.iceBackStyle(style);
    }

    @Override
    public void visitJungleBackStyle(int style) {
        builder.jungleBackStyle(style);
    }

    @Override
    public void visitHellBackStyle(int style) {
        builder.hellBackStyle(style);
    }

    @Override
    public void visitSpawnLocation(int x, int y) {
        builder.spawnX(x).spawnY(y);
    }

    @Override
    public void visitSurface(double value) {
        builder.worldSurface((int) value);
    }

    @Override
    public void visitRockLayer(double value) {
        builder.rockLayer((int) value);
    }

    @Override
    public void visitTime(double time) {
        builder.worldTime((int) time);
    }

    @Override
    public void visitDaytime(boolean daytime) {
        builder.dayTime(daytime);
    }

    @Override
    public void visitMoonPhase(int phase) {
        builder.moonPhase(phase);
    }

    @Override
    public void visitBloodmoon(boolean bloodmoon) {
        builder.bloodMoon(bloodmoon);
    }

    @Override
    public void visitEclipse(boolean eclipse) {
        builder.eclipse(eclipse);
    }

    @Override
    public void visitCrimson(boolean crimson) {
        builder.crimson(crimson);
    }

    @Override
    public void visitNormalBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean queenBee) {
        builder.downedBoss1(boss1).downedBoss2(boss2).downedBoss3(boss3).downedQueenBee(queenBee);
    }

    @Override
    public void visitMechBossFlags(boolean boss1, boolean boss2, boolean boss3, boolean any) {
        builder.downedMechBoss1(boss1).downedMechBoss2(boss2).downedMechBoss3(boss3).downedMechBossAny(any);
    }

    @Override
    public void visitHardmodeBossFlags(boolean plantera, boolean golem, boolean slimeKing) {
        builder.downedPlantera(plantera).downedGolemBoss(golem).downedSlimeKing(slimeKing);
    }
    @Override
    public void visitEventCompleteFlags(boolean goblin, boolean pumpkinMoon, boolean frostMoon, boolean pirates) {
        builder.downedPirates(pirates)
                .downedFrostMoon(frostMoon)
                .downedGoblins(goblin);
    }

    @Override
    public void visitShadowOrbSmashed(boolean smashed) {
        builder.shadowOrbSmashed(smashed);
    }

    @Override
    public void visitHardmode(boolean hardmode) {
        builder.hardmode(hardmode);
    }

    @Override
    public void visitInvasion(int delay, int size, int type, double x) {
        builder.invasionType(type);
    }

    @Override
    public void visitRain(boolean raining, int time, float max) {
        builder.maxRaining(max);
    }

    @Override
    public void visitOreTiers1(int cobalt, int mythril, int adamantite) {
        builder.cobaltOreTier(cobalt).mythrilOreTier(mythril).adamantiteOreTier(adamantite);
    }

    @Override
    public void visitBG(int index, int value) {
        //TODO
    }

    @Override
    public void visitClouds(float active, int num) {
        //TODO verify we got the active set correctly
        builder.cloudBgActive(active > 0).numClouds(num);
    }

    @Override
    public void visitWindSpeedTarget(float target) {
        builder.windSpeedTarget(target);
    }

    @Override
    public void visitEndgameBossFlags(boolean fishron, boolean martians, boolean ancientCultist,
                                      boolean moonlord, boolean hallowenKing, boolean halloweenTree,
                                      boolean christmasIceQueen, boolean santank, boolean christmasTree) {
        builder.downedFishron(fishron)
                .downedMartians(martians)
                .downedAncientCultist(ancientCultist)
                .downedMoonlord(moonlord)
                .downedHalloweenKing(hallowenKing)
                .downedHaloweenTree(halloweenTree)
                .downedIceQueen(christmasIceQueen)
                .downedSantank(santank)
                .downedChristmasTree(christmasTree);

    }

    @Override
    public void visitDownedTowers(boolean solar, boolean vortex, boolean nebula, boolean stardust) {
        builder.downedSolarTower(solar)
                .downedVortexTower(vortex)
                .downedNebulaTower(nebula)
                .downedStardustTower(stardust);
    }

    @Override
    public void visitParty(boolean ongoing, boolean genuine, int cooldown, List<Integer> partying) {
        builder.birthdayParty(ongoing);
    }

    @Override
    public void visitSandstorm(boolean happening, int timeLeft, float severity, float intendedSeverity) {
        builder.sandstorm(happening).sandstormSeverity(severity);
    }

    @Override
    public void visitDungeonDefense(boolean savedBartender, boolean invasionT1, boolean invasionT2, boolean invasionT3) {
        builder.downedInvasionT1(invasionT1)
                .downedInvasionT2(invasionT2)
                .downedInvasionT3(invasionT3);
    }

    @Override
    public void visitCombatBook(boolean used) {
        builder.combatBookUsed(used);
    }

    @Override
    public void visitLantern(int cooldown, boolean genuine, boolean manual, boolean nextNightGenuine) {
        builder.lanternsUp(genuine | manual);
    }

    @Override
    public void visitForceEvents(boolean halloween, boolean christmas) {
        builder.forceChristmas(christmas)
                .forceHalloween(halloween);
    }

    @Override
    public void visitOreTiers2(int copper, int iron, int silver, int gold) {
        builder.copperOreTier(copper)
                .ironOreTier(iron)
                .silverOreTier(silver)
                .goldOreTier(gold);
    }

    @Override
    public void visitBoughtPets(boolean cat, boolean dog, boolean bunny) {
        builder.boughtCat(cat)
                .boughtDog(dog)
                .boughtBunny(bunny);
    }

    @Override
    public void visitHallowBosses(boolean empress, boolean slimeQueen) {
        builder.downedEmpress(empress)
                .downedSlimeKing(slimeQueen);
    }

    public WorldMetadataMessage build(){
        return builder.build();
    }
}
