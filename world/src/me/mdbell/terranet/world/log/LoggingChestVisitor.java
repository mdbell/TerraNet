package me.mdbell.terranet.world.log;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.world.AbstractChestVisitor;
import me.mdbell.terranet.world.ChestVisitor;
import me.mdbell.terranet.world.ItemVisitor;

@Slf4j
public class LoggingChestVisitor extends AbstractChestVisitor {

    public LoggingChestVisitor() {
        super();
    }

    public LoggingChestVisitor(ChestVisitor visitor) {
        super(visitor);
    }

    @Override
    public void visitStart() {
        log.info("visitStart()");
        super.visitStart();
    }

    @Override
    public void visitLocation(int x, int y) {
        log.info("visitLocation({}, {})", x, y);
        super.visitLocation(x, y);
    }

    @Override
    public void visitName(String name) {
        log.info("visitName('{}')", name);
        super.visitName(name);
    }

    @Override
    public ItemVisitor visitItem() {
        return new LoggingItemVisitor(super.visitItem());
    }

    @Override
    public void visitEnd() {
        log.info("visitEnd()");
        super.visitEnd();
    }
}
