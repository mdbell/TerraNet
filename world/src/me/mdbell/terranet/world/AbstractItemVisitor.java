package me.mdbell.terranet.world;

public abstract class AbstractItemVisitor implements ItemVisitor {

    private ItemVisitor visitor;

    public AbstractItemVisitor() {
    }

    public AbstractItemVisitor(ItemVisitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if (visitor != null) {
            visitor.visitStart();
        }
    }

    @Override
    public void visitId(int id) {
        if (visitor != null) {
            visitor.visitId(id);
        }
    }

    @Override
    public void visitCount(int count) {
        if (visitor != null) {
            visitor.visitCount(count);
        }
    }

    @Override
    public void visitPrefix(int prefix) {
        if (visitor != null) {
            visitor.visitPrefix(prefix);
        }
    }

    @Override
    public void visitEnd() {
        if (visitor != null) {
            visitor.visitEnd();
        }
    }
}
