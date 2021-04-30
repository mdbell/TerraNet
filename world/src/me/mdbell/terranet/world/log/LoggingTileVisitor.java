package me.mdbell.terranet.world.log;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.game.scene.LiquidType;
import me.mdbell.terranet.world.*;

@Slf4j
public class LoggingTileVisitor extends AbstractTileVisitor {

    public LoggingTileVisitor() {
        super();
    }

    public LoggingTileVisitor(TileVisitor visitor) {
        super(visitor);
    }

    @Override
    public void visitStart() {
        log.info("visitStart()");
        super.visitStart();
    }

    @Override
    public void visitActive(boolean active) {
        log.info("visitActive({})", active);
        super.visitActive(active);
    }

    @Override
    public void visitType(int type) {
        log.info("visitType({})", type);
        super.visitType(type);
    }

    @Override
    public void visitFrame(int frameX, int frameY) {
        log.info("visitFrame({}, {})", frameX, frameY);
        super.visitFrame(frameX, frameY);
    }

    @Override
    public void visitColor(int color) {
        log.info("visitColor({})", color);
        super.visitColor(color);
    }

    @Override
    public void visitWall(int wall) {
        log.info("visitWall({})", wall);
        super.visitWall(wall);
    }

    @Override
    public void visitWallColor(int wallColor) {
        log.info("visitWalLColor({})", wallColor);
        super.visitWallColor(wallColor);
    }

    @Override
    public void visitLiquid(LiquidType type, int quantity) {
        log.info("visitLiquid({}, {})", type, quantity);
        super.visitLiquid(type, quantity);
    }

    @Override
    public void visitSlope(int slope) {
        log.info("visitSlope({})", slope);
        super.visitSlope(slope);
    }

    @Override
    public void visitBrick() {
        log.info("visitBrick()");
        super.visitBrick();
    }

    @Override
    public void visitWire(boolean wire1, boolean wire2, boolean wire3, boolean wire4) {
        log.info("visitWire({}, {}, {}, {})", wire1, wire2, wire3, wire4);
        super.visitWire(wire1, wire2, wire3, wire4);
    }

    @Override
    public void visitActuator(boolean inactive) {
        log.info("visitActuator({})", inactive);
        super.visitActuator(inactive);
    }

    @Override
    public void visitEnd() {
        log.info("visitEnd()");
        super.visitEnd();
    }
}
