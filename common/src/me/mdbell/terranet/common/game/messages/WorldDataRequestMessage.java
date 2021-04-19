package me.mdbell.terranet.common.game.messages;

public class WorldDataRequestMessage extends GameMessage {
    public WorldDataRequestMessage() {
        super(OP_REQUEST_WORLD);
    }

    @Override
    public String toString() {
        return "WorldDataRequestMessage{}";
    }
}
