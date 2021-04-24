package me.mdbell.terranet.world;

public abstract class AbstractTileDataVisitor implements TileDataVisitor{

    private final TileDataVisitor visitor;

    public AbstractTileDataVisitor(){
        this(null);
    }

    public AbstractTileDataVisitor(TileDataVisitor visitor){
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if(visitor != null){
            visitor.visitStart();
        }
    }

    @Override
    public void visitTileX(int x) {
        if(visitor != null){
            visitor.visitTileX(x);
        }
    }

    @Override
    public TileVisitor visitTile(int x, int y) {
        if(visitor != null){
            return visitor.visitTile(x, y);
        }
        return null;
    }

    @Override
    public void visitEnd() {
        if(visitor != null){
            visitor.visitEnd();
        }
    }
}
