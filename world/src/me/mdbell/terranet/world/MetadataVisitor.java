package me.mdbell.terranet.world;

import java.util.UUID;

public interface MetadataVisitor {

    void visitStart();

    void visitName(String name);

    void visitSeed(String seed);

    void visitWorldGenVersion(long version);

    void visitGuid(UUID guid);

    void visitId(int id);

    void visitDimensions(int left, int right, int top, int bottom);

    void visitSize(int width, int height);

    void visitGameMode(int mode);

    void visitDrunkWorld(boolean drunk);

    void visitUnk6(boolean unk6);

    void visitCreationTime(long time);

    void visitEnd();
}
