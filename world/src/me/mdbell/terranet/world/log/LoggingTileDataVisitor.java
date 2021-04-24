package me.mdbell.terranet.world.log;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.world.AbstractTileDataVisitor;
import me.mdbell.terranet.world.TileDataVisitor;
import me.mdbell.terranet.world.TileVisitor;

@Slf4j
public class LoggingTileDataVisitor extends AbstractTileDataVisitor {

    public LoggingTileDataVisitor() {
        super();
    }

    public LoggingTileDataVisitor(TileDataVisitor visitor) {
        super(visitor);
    }

    @Override
    public void visitStart() {
        log.info("visitStart()");
        super.visitStart();
    }

    @Override
    public TileVisitor visitTile(int x, int y) {
        log.info("visitTile({}, {})", x, y);
        return new LoggingTileVisitor(super.visitTile(x, y));
    }

    @Override
    public void visitEnd() {
        log.info("visitEnd()");
        super.visitEnd();
    }
}
