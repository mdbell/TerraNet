package me.mdbell.terranet.world;

public abstract class AbstractTileVisitor implements TileVisitor {

    private final TileVisitor visitor;

    public AbstractTileVisitor() {
        this(null);
    }

    public AbstractTileVisitor(TileVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if (visitor != null) {
            visitor.visitStart();
        }
    }

    @Override
    public void visitActive(boolean active) {
        if (visitor != null) {
            visitor.visitActive(active);
        }
    }

    @Override
    public void visitType(int type) {
        if (visitor != null) {
            visitor.visitType(type);
        }
    }

    @Override
    public void visitFrame(int frameX, int frameY) {
        if (visitor != null) {
            visitor.visitFrame(frameX, frameY);
        }
    }

    @Override
    public void visitColor(int color) {
        if (visitor != null) {
            visitor.visitColor(color);
        }
    }

    @Override
    public void visitWall(int wall) {
        if (visitor != null) {
            visitor.visitWall(wall);
        }
    }

    @Override
    public void visitWallColor(int wallColor) {
        if (visitor != null) {
            visitor.visitWallColor(wallColor);
        }
    }

    @Override
    public void visitLiquid(LiquidType type, int quantity) {
        if (visitor != null) {
            visitor.visitLiquid(type, quantity);
        }
    }

    @Override
    public void visitSlope(int slope) {
        if (visitor != null) {
            visitor.visitSlope(slope);
        }
    }

    @Override
    public void visitBrick() {
        if (visitor != null) {
            visitor.visitBrick();
        }
    }

    @Override
    public void visitWire(boolean wire1, boolean wire2, boolean wire3, boolean wire4) {
        if (visitor != null) {
            visitor.visitWire(wire1, wire2, wire3, wire4);
        }
    }

    @Override
    public void visitActuator(boolean inactive) {
        if (visitor != null) {
            visitor.visitActuator(inactive);
        }
    }

    @Override
    public void visitEnd() {
        if (visitor != null) {
            visitor.visitEnd();
        }
    }
}
