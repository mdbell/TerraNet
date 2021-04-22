package me.mdbell.terranet.common.game.transcoders;


import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.common.game.messages.WorldMetadataMessage;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.net.FilteredMessageTranscoder;
import me.mdbell.terranet.common.util.IOUtil;

@ExtensionMethod({IOUtil.class})
public class WorldMetadataTranscoder extends FilteredMessageTranscoder<WorldMetadataMessage> {

    public WorldMetadataTranscoder() {
        super(OP_WORLD_METADATA);
    }

    @Override
    protected WorldMetadataMessage filteredDecode(int size, Buffer<?> buff) {
        int[] treeX = new int[3];
        byte[] treeStyle = new byte[4];
        int[] caveBackX = new int[3];
        byte[] caveBackStyle = new byte[4];
        byte[] treetopTypes = new byte[13];

        WorldMetadataMessage.WorldMetadataMessageBuilder builder = WorldMetadataMessage.builder()
                .worldTime(buff.readIntLE())
                .dayTime(buff.readBit())
                .bloodMoon(buff.readBit())
                .eclipse(buff.readBit())
                .moonPhase(buff.resetBitPosition().readByte())
                .maxTilesX(buff.readUnsignedShortLE())
                .maxTilesY(buff.readUnsignedShortLE())
                .spawnX(buff.readUnsignedShortLE())
                .spawnY(buff.readUnsignedShortLE())
                .worldSurface(buff.readUnsignedShortLE())
                .rockLayer(buff.readUnsignedShortLE())
                .worldId(buff.readIntLE())
                .worldName(buff.readString())
                .gameMode(buff.readUnsignedByte())
                .guid(buff.readGuid())
                .worldGenVersion(buff.readLongLE())
                .moonType(buff.readUnsignedByte())
                .treeBG1(buff.readUnsignedByte())
                .treeBG2(buff.readUnsignedByte())
                .treeBG3(buff.readUnsignedByte())
                .treeBG4(buff.readUnsignedByte())
                .corruptBG(buff.readUnsignedByte())
                .jungleBG(buff.readUnsignedByte())
                .snowBG(buff.readUnsignedByte())
                .hallowBG(buff.readUnsignedByte())
                .crimsonBG(buff.readUnsignedByte())
                .desertBG(buff.readUnsignedByte())
                .oceanBG(buff.readUnsignedByte())
                .mushroomBG(buff.readUnsignedByte())
                .underworldBG(buff.readUnsignedByte())
                .iceBackStyle(buff.readUnsignedByte())
                .jungleBackStyle(buff.readUnsignedByte())
                .hellBackStyle(buff.readUnsignedByte())
                .windSpeedTarget(buff.readFloatLE())
                .numClouds(buff.readUnsignedByte());

        buff.readInto(treeX, Buffer::readIntLE);
        buff.readBytes(treeStyle);
        buff.readInto(caveBackX, Buffer::readIntLE);
        buff.readBytes(caveBackStyle);
        buff.readBytes(treetopTypes);

        builder.maxRaining(buff.readFloatLE())
                .shadowOrbSmashed(buff.readBit())
                .downedBoss1(buff.readBit())
                .downedBoss2(buff.readBit())
                .downedBoss3(buff.readBit())
                .hardmode(buff.readBit())
                .downedClown(buff.readBit())
                .downedPlantera(buff.skipReaderBits(1).readBit())
                .downedMechBoss1(buff.readBit())
                .downedMechBoss2(buff.readBit())
                .downedMechBoss3(buff.readBit())
                .downedMechBossAny(buff.readBit())
                .cloudBgActive(buff.readBit())
                .crimson(buff.readBit())
                .pumpkinMoon(buff.readBit())
                .frostMoon(buff.readBit())
                .fastFowardTime(buff.skipReaderBits(1).readBit())
                .slimeRain(buff.readBit())
                .downedSlimeKing(buff.readBit())
                .downedQueenBee(buff.readBit())
                .downedFishron(buff.readBit())
                .downedMartians(buff.readBit())
                .downedAncientCultist(buff.readBit())
                .downedMoonlord(buff.readBit())
                .downedHalloweenKing(buff.readBit())
                .downedIceQueen(buff.readBit())
                .downedSantank(buff.readBit())
                .downedChristmasTree(buff.readBit())
                .downedGolemBoss(buff.readBit())
                .birthdayParty(buff.readBit())
                .downedPirates(buff.readBit())
                .downedFrontmoon(buff.readBit())
                .downedGoblins(buff.readBit())
                .sandstorm(buff.readBit())
                .invasionOngoing(buff.readBit())
                .downedInvasionT1(buff.readBit())
                .downedInvasionT2(buff.readBit())
                .downedInvasionT3(buff.readBit())
                .combatBookUsed(buff.readBit())
                .lanternsUp(buff.readBit())
                .downedSolarTower(buff.readBit())
                .downedVortexTower(buff.readBit())
                .downedNebulaTower(buff.readBit())
                .downedStardustTower(buff.readBit())
                .forceHalloween(buff.readBit())
                .forceChristmas(buff.readBit())
                .boughtCat(buff.readBit())
                .boughtDog(buff.readBit())
                .boughtBunny(buff.readBit())
                .freeCake(buff.readBit())
                .drunkWorld(buff.readBit())
                .downedEmpress(buff.readBit())
                .downedQueen(buff.readBit())
                .goodWorld(buff.readBit())
                .copperOreTier(buff.resetBitPosition().readShortLE())
                .ironOreTier(buff.readShortLE())
                .silverOreTier(buff.readShortLE())
                .goldOreTier(buff.readShortLE())
                .cobaltOreTier(buff.readShortLE())
                .mythrilOreTier(buff.readShortLE())
                .adamantiteOreTier(buff.readShortLE())
                .invasionType(buff.readUnsignedByte())
                .lobbyId(buff.readLongLE())
                .sandstormSeverity(buff.readFloatLE());

        WorldMetadataMessage message = builder.build();

        treeX.copyTo(message.getTreeX());
        treeStyle.copyTo(message.getTreeStyle());
        caveBackX.copyTo(message.getCaveBackX());
        caveBackStyle.copyTo(message.getCaveBackStyle());
        treetopTypes.copyTo(message.getTreetopTypes());

        return message;
    }

    @Override
    protected boolean filteredEncode(Buffer<?> to, WorldMetadataMessage message) {
        to.writeIntLE(message.getWorldTime())
                .writeBit(message.isDayTime())
                .writeBit(message.isBloodMoon())
                .writeBit(message.isEclipse()).writeBits()
                .writeByte(message.getMoonPhase())
                .writeShortLE(message.getMaxTilesX())
                .writeShortLE(message.getMaxTilesY())
                .writeShortLE(message.getSpawnX())
                .writeShortLE(message.getSpawnY())
                .writeShortLE(message.getWorldSurface())
                .writeShortLE(message.getRockLayer())
                .writeIntLE(message.getWorldId())
                .writeString(message.getWorldName())
                .writeByte(message.getGameMode())
                .writeGuid(message.getGuid())
                .writeLongLE(message.getWorldGenVersion())
                .writeByte(message.getMoonType())
                .writeByte(message.getTreeBG1())
                .writeByte(message.getTreeBG2())
                .writeByte(message.getTreeBG3())
                .writeByte(message.getTreeBG4())
                .writeByte(message.getCorruptBG())
                .writeByte(message.getJungleBG())
                .writeByte(message.getSnowBG())
                .writeByte(message.getHallowBG())
                .writeByte(message.getCrimsonBG())
                .writeByte(message.getDesertBG())
                .writeByte(message.getOceanBG())
                .writeByte(message.getMushroomBG())
                .writeByte(message.getUnderworldBG())
                .writeByte(message.getIceBackStyle())
                .writeByte(message.getJungleBackStyle())
                .writeByte(message.getHellBackStyle())
                .writeFloatLE(message.getWindSpeedTarget())
                .writeByte(message.getNumClouds())
                .writeInto(message.getTreeX(), Buffer::writeIntLE)
                .writeBytes(message.getTreeStyle())
                .writeInto(message.getCaveBackX(), Buffer::writeIntLE)
                .writeBytes(message.getCaveBackStyle())
                .writeBytes(message.getTreetopTypes())
                .writeFloatLE(message.getMaxRaining())
                .writeBit(message.isShadowOrbSmashed())
                .writeBit(message.isDownedBoss1())
                .writeBit(message.isDownedBoss2())
                .writeBit(message.isDownedBoss3())
                .writeBit(message.isHardmode())
                .writeBit(message.isDownedClown())
                .writeBit(false) // Unused
                .writeBit(message.isDownedPlantera())
                .writeBit(message.isDownedMechBoss1())
                .writeBit(message.isDownedMechBoss2())
                .writeBit(message.isDownedMechBoss3())
                .writeBit(message.isDownedMechBossAny())
                .writeBit(message.isCloudBgActive())
                .writeBit(message.isCrimson())
                .writeBit(message.isPumpkinMoon())
                .writeBit(message.isFrostMoon())
                .writeBit(false)// Unused
                .writeBit(message.isFastFowardTime())
                .writeBit(message.isSlimeRain())
                .writeBit(message.isDownedSlimeKing())
                .writeBit(message.isDownedQueenBee())
                .writeBit(message.isDownedFishron())
                .writeBit(message.isDownedMartians())
                .writeBit(message.isDownedAncientCultist())
                .writeBit(message.isDownedMoonlord())
                .writeBit(message.isDownedHalloweenKing())
                .writeBit(message.isDownedIceQueen())
                .writeBit(message.isDownedSantank())
                .writeBit(message.isDownedChristmasTree())
                .writeBit(message.isDownedGolemBoss())
                .writeBit(message.isBirthdayParty())
                .writeBit(message.isDownedPirates())
                .writeBit(message.isDownedFrontmoon())
                .writeBit(message.isDownedGoblins())
                .writeBit(message.isSandstorm())
                .writeBit(message.isInvasionOngoing())
                .writeBit(message.isDownedInvasionT1())
                .writeBit(message.isDownedInvasionT2())
                .writeBit(message.isDownedInvasionT3())
                .writeBit(message.isCombatBookUsed())
                .writeBit(message.isLanternsUp())
                .writeBit(message.isDownedSolarTower())
                .writeBit(message.isDownedVortexTower())
                .writeBit(message.isDownedNebulaTower())
                .writeBit(message.isDownedStardustTower())
                .writeBit(message.isForceHalloween())
                .writeBit(message.isForceChristmas())
                .writeBit(message.isBoughtCat())
                .writeBit(message.isBoughtDog())
                .writeBit(message.isBoughtBunny())
                .writeBit(message.isFreeCake())
                .writeBit(message.isDrunkWorld())
                .writeBit(message.isDownedEmpress())
                .writeBit(message.isDownedQueen())
                .writeBit(message.isGoodWorld())
                .writeBits()
                .writeShortLE(message.getCopperOreTier())
                .writeShortLE(message.getIronOreTier())
                .writeShortLE(message.getSilverOreTier())
                .writeShortLE(message.getGoldOreTier())
                .writeShortLE(message.getCobaltOreTier())
                .writeShortLE(message.getMythrilOreTier())
                .writeShortLE(message.getAdamantiteOreTier())
                .writeByte(message.getInvasionType())
                .writeLongLE(message.getLobbyId())
                .writeFloatLE(message.getSandstormSeverity());
        return true;
    }
}
