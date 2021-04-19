package me.mdbell.terranet.common.game.messages;

public class EssentialTilesMessage extends GameMessage {

    private int x = -1, y = -1;

    public EssentialTilesMessage() {
        super(OP_ESSENTIAL_TILES);
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public EssentialTilesMessage x(int x) {
        this.x = x;
        return this;
    }

    public EssentialTilesMessage y(int y) {
        this.y = y;
        return this;
    }

    @Override
    public String toString() {
        return "EssentialTilesMessage{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
