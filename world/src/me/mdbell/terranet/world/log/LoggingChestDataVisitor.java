package me.mdbell.terranet.world.log;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.world.AbstractChestDataVisitor;
import me.mdbell.terranet.world.ChestDataVisitor;
import me.mdbell.terranet.world.ChestVisitor;

@Slf4j
public class LoggingChestDataVisitor extends AbstractChestDataVisitor {

    public LoggingChestDataVisitor() {
        super();
    }

    public LoggingChestDataVisitor(ChestDataVisitor visitor) {
        super(visitor);
    }

    @Override
    public void visitStart() {
        log.info("visitStart()");
        super.visitStart();
    }

    @Override
    public ChestVisitor visitChest() {
        return new LoggingChestVisitor(super.visitChest());
    }

    @Override
    public void visitEnd() {
        log.info("visitEnd()");
        super.visitEnd();
    }
}
