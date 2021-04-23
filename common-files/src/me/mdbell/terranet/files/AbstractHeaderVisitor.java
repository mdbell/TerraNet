package me.mdbell.terranet.files;

public abstract class AbstractHeaderVisitor implements SharedHeaderVisitor{

    private final SharedHeaderVisitor visitor;

    public AbstractHeaderVisitor(){
        this(null);
    }

    public AbstractHeaderVisitor(SharedHeaderVisitor visitor){
        this.visitor = visitor;
    }

    @Override
    public void visitStart() {
        if(visitor != null){
            visitor.visitStart();
        }
    }

    @Override
    public void visitMagic(String magic) {
        if(visitor != null){
            visitor.visitMagic(magic);
        }
    }

    @Override
    public void visitType(FileType type) {
        if(visitor != null){
            visitor.visitType(type);
        }
    }

    @Override
    public void visitRevision(int revision) {
        if(visitor != null){
            visitor.visitRevision(revision);
        }
    }

    @Override
    public void visitFavorite(boolean favorite) {
        if(visitor != null){
            visitor.visitFavorite(favorite);
        }
    }

    @Override
    public void visitEnd() {
        if(visitor != null){
            visitor.visitEnd();
        }
    }
}
