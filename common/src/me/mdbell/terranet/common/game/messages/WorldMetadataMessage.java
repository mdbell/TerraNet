package me.mdbell.terranet.common.game.messages;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public final class WorldMetadataMessage extends GameMessage {

    int worldTime;
    boolean dayTime;
    boolean bloodMoon;
    boolean eclipse;
    int moonPhase;
    int maxTilesX, maxTilesY;
    int spawnX, spawnY;
    int worldSurface, rockLayer;
    int worldId;
    String worldName;
    int gameMode;
    UUID guid;
    long worldGenVersion;
    int moonType;
    int treeBG1, treeBG2, treeBG3, treeBG4;
    int corruptBG;
    int jungleBG;
    int snowBG;
    int hallowBG;
    int crimsonBG;
    int desertBG;
    int oceanBG;
    int mushroomBG;
    int underworldBG;
    int iceBackStyle;
    int jungleBackStyle;
    int hellBackStyle;
    float windSpeedTarget;
    int numClouds;
    final int[] treeX = new int[3];
    final int[] treeStyle = new int[4];
    final int[] caveBackX = new int[3];
    final int[] caveBackStyle = new int[4];
    final int[] treetopTypes = new int[13];
    float maxRaining;
    boolean shadowOrbSmashed;
    boolean downedBoss1, downedBoss2, downedBoss3;
    boolean hardmode;
    boolean serverSideChar;
    boolean downedClown;
    boolean downedPlantera;
    boolean downedMechBoss1, downedMechBoss2, downedMechBoss3, downedMechBossAny;
    boolean cloudBgActive;
    boolean crimson;
    boolean pumpkinMoon;
    boolean frostMoon;
    boolean fastFowardTime;
    boolean slimeRain;
    boolean downedSlimeKing;
    boolean downedQueenBee;
    boolean downedFishron;
    boolean downedMartians;
    boolean downedAncientCultist;
    boolean downedMoonlord;
    boolean downedHalloweenKing;
    boolean downedHaloweenTree;
    boolean downedIceQueen;
    boolean downedSantank;
    boolean downedChristmasTree;
    boolean downedGolemBoss;
    boolean birthdayParty;
    boolean downedPirates;
    boolean downedFrontmoon;
    boolean downedGoblins;
    boolean sandstorm;
    boolean invasionOngoing;
    boolean downedInvasionT1, downedInvasionT2, downedInvasionT3;
    boolean combatBookUsed;
    boolean lanternsUp;
    boolean downedSolarTower, downedVortexTower, downedNebulaTower, downedStardustTower;
    boolean forceHalloween, forceChristmas;
    boolean boughtCat, boughtDog, boughtBunny;
    boolean freeCake;
    boolean drunkWorld;
    boolean downedEmpress;
    boolean downedQueenSlime;
    boolean goodWorld;
    int copperOreTier;
    int ironOreTier;
    int silverOreTier;
    int goldOreTier;
    int cobaltOreTier;
    int mythrilOreTier;
    int adamantiteOreTier;
    int invasionType;
    long lobbyId;
    float sandstormSeverity;

    @Override
    public int getOpcode() {
        return OP_WORLD_METADATA;
    }
}
