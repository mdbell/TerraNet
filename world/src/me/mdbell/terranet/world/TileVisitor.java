package me.mdbell.terranet.world;

import me.mdbell.terranet.common.game.scene.LiquidType;

public interface TileVisitor {

    void visitStart();

    void visitActive(boolean active);

    void visitType(int type);

    void visitFrame(int frameX, int frameY);

    void visitColor(int color);

    void visitWall(int wall);

    void visitWallColor(int wallColor);

    void visitLiquid(LiquidType type, int quantity);

    void visitSlope(int slope);

    void visitBrick();

    void visitWire(boolean wire1, boolean wire2, boolean wire3, boolean wire4);

    void visitActuator(boolean inactive);

    void visitEnd();
}
