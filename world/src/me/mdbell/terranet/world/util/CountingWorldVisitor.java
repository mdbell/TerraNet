package me.mdbell.terranet.world.util;

import me.mdbell.terranet.world.*;

public class CountingWorldVisitor extends AbstractWorldVisitor {

    private final int[] count = new int[1000];

    public CountingWorldVisitor() {
    }

    public CountingWorldVisitor(WorldVisitor visitor) {
        super(visitor);
    }

    public int getCount(int type){
        return count[type];
    }

    @Override
    public TileDataVisitor visitTileData() {
        return new AbstractTileDataVisitor(super.visitTileData()) {
            @Override
            public TileVisitor visitTile(int x, int y) {
                return new CountingTileVisitor(super.visitTile(x, y));
            }
        };
    }

    private class CountingTileVisitor extends AbstractTileVisitor {

        public CountingTileVisitor(TileVisitor visitor) {
            super(visitor);
        }

        @Override
        public void visitType(int type) {
            count[type]++;
            super.visitType(type);
        }
    }
}
