package me.mdbell.terranet.world;

public abstract class AbstractChestVisitor implements ChestVisitor{

    private ChestVisitor visitor;

    public AbstractChestVisitor() {
    }

    public AbstractChestVisitor(ChestVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if(visitor != null){
            visitor.visitStart();
        }
    }

    @Override
    public void visitLocation(int x, int y) {
        if(visitor != null){
            visitor.visitLocation(x, y);
        }
    }

    @Override
    public void visitName(String name) {
        if(visitor != null){
            visitor.visitName(name);
        }
    }

    @Override
    public ItemVisitor visitItem() {
        if(visitor != null){
            return visitor.visitItem();
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
