package me.mdbell.terranet.world.log;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.world.AbstractItemVisitor;
import me.mdbell.terranet.world.ItemVisitor;

@Slf4j
public class LoggingItemVisitor extends AbstractItemVisitor {
    public LoggingItemVisitor() {
        super();
    }

    public LoggingItemVisitor(ItemVisitor visitor) {
        super(visitor);
    }

    @Override
    public void visitStart() {
        log.info("visitStart()");
        super.visitStart();
    }

    @Override
    public void visitId(int id) {
        log.info("visitId({})", id);
        super.visitId(id);
    }

    @Override
    public void visitCount(int count) {
        log.info("visitCount({})", count);
        super.visitCount(count);
    }

    @Override
    public void visitPrefix(int prefix) {
        log.info("visitPrefix({})", prefix);
        super.visitPrefix(prefix);
    }

    @Override
    public void visitEnd() {
        log.info("visitEnd()");
        super.visitEnd();
    }
}
