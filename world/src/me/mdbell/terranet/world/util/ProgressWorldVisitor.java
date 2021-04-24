package me.mdbell.terranet.world.util;

import me.mdbell.terranet.world.*;

public class ProgressWorldVisitor extends AbstractWorldVisitor {

    private int width;

    private ProgressListener listener;

    public ProgressWorldVisitor() {
        super();
    }

    public ProgressWorldVisitor(WorldVisitor visitor) {
        super(visitor);
    }

    public void setListener(ProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public MetadataVisitor visitMetadata() {
        return new AbstractMetadataVisitor(super.visitMetadata()) {
            @Override
            public void visitSize(int height, int width) {
                ProgressWorldVisitor.this.width = width;
                super.visitSize(height, width);
            }
        };
    }

    @Override
    public TileDataVisitor visitTileData() {
        return new AbstractTileDataVisitor(super.visitTileData()) {
            @Override
            public void visitTileX(int x) {
                if(listener != null){
                    listener.onProgress(x, width);
                }
                super.visitTileX(x);
            }

            @Override
            public void visitEnd() {
                if(listener != null){
                    listener.onProgress(width, width);
                }
                super.visitEnd();
            }
        };
    }
}
