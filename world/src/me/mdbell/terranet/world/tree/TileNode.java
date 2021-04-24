package me.mdbell.terranet.world.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.mdbell.terranet.world.LiquidType;
import me.mdbell.terranet.world.TileVisitor;

@ToString
@EqualsAndHashCode
@Getter
public class TileNode implements TileVisitor {
    private int type;
    private boolean active;
    private int frameX, frameY;
    private int color;
    private int wall, wallColor;
    private LiquidType liquidType = LiquidType.NONE;
    private int liquidQuantity;
    private int slope;
    private boolean brick;
    private boolean wire1, wire2, wire3, wire4;
    private boolean hasActuator;
    private boolean actuated;

    @Override
    public void visitStart() {

    }

    @Override
    public void visitActive(boolean active) {
        this.active = active;
    }

    @Override
    public void visitType(int type) {
        this.type = type;
    }

    @Override
    public void visitFrame(int frameX, int frameY) {
        this.frameX = frameX;
        this.frameY = frameY;
    }

    @Override
    public void visitColor(int color) {
        this.color = color;
    }

    @Override
    public void visitWall(int wall) {
        this.wall = wall;
    }

    @Override
    public void visitWallColor(int wallColor) {
        this.wallColor = wallColor;
    }

    @Override
    public void visitLiquid(LiquidType type, int quantity) {
        this.liquidQuantity = quantity;
        this.liquidType = type;
    }

    @Override
    public void visitSlope(int slope) {
        this.slope = slope;
    }

    @Override
    public void visitBrick() {
        this.brick = true;
    }

    @Override
    public void visitWire(boolean wire1, boolean wire2, boolean wire3, boolean wire4) {
        this.wire1 = wire1;
        this.wire2 = wire2;
        this.wire3 = wire3;
        this.wire4 = wire4;
    }

    @Override
    public void visitActuator(boolean inactive) {
        this.hasActuator = true;
        this.actuated = inactive;
    }

    @Override
    public void visitEnd() {

    }

    public void accept(TileVisitor visitor) {

    }
}
