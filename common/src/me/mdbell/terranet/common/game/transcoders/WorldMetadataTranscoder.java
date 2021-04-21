package me.mdbell.terranet.common.game.transcoders;


import me.mdbell.terranet.common.game.messages.WorldMetadataMessage;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.net.FilteredMessageTranscoder;

public class WorldMetadataTranscoder extends FilteredMessageTranscoder<WorldMetadataMessage> {

    public WorldMetadataTranscoder() {
        super(OP_WORLD_METADATA);
    }

    @Override
    protected WorldMetadataMessage filteredDecode(int size, Buffer<?> buff) {
        return WorldMetadataMessage.builder()
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
                .build();
    }

    @Override
    protected boolean filteredEncode(Buffer<?> to, WorldMetadataMessage packet) {
        throw new UnsupportedOperationException("Unimplemented");
    }
}
