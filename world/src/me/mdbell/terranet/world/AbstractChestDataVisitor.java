package me.mdbell.terranet.world;

public abstract class AbstractChestDataVisitor implements ChestDataVisitor{

    private ChestDataVisitor visitor;

    public AbstractChestDataVisitor() {
    }

    public AbstractChestDataVisitor(ChestDataVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if(visitor != null){
            visitor.visitStart();
        }
    }

    @Override
    public ChestVisitor visitChest() {
        if(visitor != null){
            return visitor.visitChest();
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
