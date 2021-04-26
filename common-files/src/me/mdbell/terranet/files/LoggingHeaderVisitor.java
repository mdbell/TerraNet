package me.mdbell.terranet.files;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingHeaderVisitor extends AbstractHeaderVisitor{

    public LoggingHeaderVisitor() {
        super();
    }

    public LoggingHeaderVisitor(SharedHeaderVisitor visitor) {
        super(visitor);
    }

    @Override
    public void visitStart() {
        log.info("visitStart()");
        super.visitStart();
    }

    @Override
    public void visitMagic(String magic) {
        log.info("visitMagic('{}')", magic);
        super.visitMagic(magic);
    }

    @Override
    public void visitType(FileType type) {
        log.info("visitType({})", type);
        super.visitType(type);
    }

    @Override
    public void visitRevision(int revision) {
        log.info("visitRevision({})", revision);
        super.visitRevision(revision);
    }

    @Override
    public void visitFavorite(boolean favorite) {
        log.info("visitFavorite({})", favorite);
        super.visitFavorite(favorite);
    }

    @Override
    public void visitEnd() {
        log.info("visitEnd()");
        super.visitEnd();
    }
}
