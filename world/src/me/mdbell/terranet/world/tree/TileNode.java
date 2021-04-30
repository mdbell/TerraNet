package me.mdbell.terranet.world.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.mdbell.terranet.common.game.scene.LiquidType;
import me.mdbell.terranet.common.game.scene.Tile;
import me.mdbell.terranet.world.TileVisitor;

@ToString
@EqualsAndHashCode
@Getter
public class TileNode extends Tile implements TileVisitor {

    public TileNode(){

    }

    @Override
    public void visitStart() {

    }

    @Override
    public void visitActive(boolean active) {
        setActive(active);
    }

    @Override
    public void visitType(int type) {
        setType(type);
    }

    @Override
    public void visitFrame(int frameX, int frameY) {
        setFrameX(frameX);
        setFrameY(frameY);
    }

    @Override
    public void visitColor(int color) {
        setColor(color);
    }

    @Override
    public void visitWall(int wall) {
        setWall(wall);
    }

    @Override
    public void visitWallColor(int wallColor) {
        setWallColor(wallColor);
    }

    @Override
    public void visitLiquid(LiquidType type, int quantity) {
        setLiquidType(type);
        setLiquid(quantity);
    }

    @Override
    public void visitSlope(int slope) {
        setSlope(slope);
    }

    @Override
    public void visitBrick() {
        setHalfBrick(true);
    }

    @Override
    public void visitWire(boolean wire1, boolean wire2, boolean wire3, boolean wire4) {
        setWire(wire1);
        setWire2(wire2);
        setWire3(wire3);
        setWire4(wire4);
    }

    @Override
    public void visitActuator(boolean inactive) {
        setActuator(true);
        setInActive(inactive);
    }

    @Override
    public void visitEnd() {

    }

    public void accept(TileVisitor visitor) {
        visitor.visitStart();
        visitor.visitActive(isActive());
        visitor.visitType(getType());
        visitor.visitFrame(getFrameX(), getFrameY());
        visitor.visitColor(getColor());
        visitor.visitWall(getWall());
        visitor.visitWallColor(getWallColor());
        visitor.visitLiquid(getLiquidType(), getLiquid());
        visitor.visitSlope(getSlope());
        if (isHalfBrick()) {
            visitor.visitBrick();
        }
        visitor.visitWire(isWire(), isWire2(), isWire3(), isWire4());
        if (isActuator()) {
            visitor.visitActuator(isInActive());
        }
        visitor.visitEnd();
    }
}
