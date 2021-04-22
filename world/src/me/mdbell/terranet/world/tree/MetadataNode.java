package me.mdbell.terranet.world.tree;

import lombok.*;
import me.mdbell.terranet.world.MetadataVisitor;

import java.util.UUID;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class MetadataNode implements MetadataVisitor {

    private String name, seed;
    private long worldGenVersion;
    private UUID guid;
    private int id;
    private int left, right, top, bottom;
    private int width, height;
    private int gameMode;
    private boolean drunk;
    private boolean unk6;
    private long creationTime;
    private int moonType;
    private final int[] treeX = new int[3];
    private final int[] treeStyle = new int[4];
    private final int[] caveBackX = new int[3];
    private final int[] caveBackStyle = new int[4];
    private int iceBackStyle;
    private int jungleBackStyle;
    private int helLBackStyle;
    private int spawnX, spawnY;
    private double worldSurface;
    private double rockLayer;
    private double time;
    private boolean daytime;
    private int moonPhase;
    private boolean bloodmoon, eclipse;
    private int dungeonX, dungeonY;
    boolean crimson;


    @Override
    public void visitStart() {

    }

    @Override
    public void visitName(String name) {
        setName(name);
    }

    @Override
    public void visitSeed(String seed) {
        setSeed(seed);
    }

    @Override
    public void visitWorldGenVersion(long version) {
        setWorldGenVersion(version);
    }

    @Override
    public void visitGuid(UUID guid) {
        setGuid(guid);
    }

    @Override
    public void visitId(int id) {
        setId(id);
    }

    @Override
    public void visitDimensions(int left, int right, int top, int bottom) {
        setLeft(left);
        setRight(right);
        setTop(top);
        setBottom(bottom);
    }

    @Override
    public void visitSize(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    @Override
    public void visitGameMode(int mode) {
        setGameMode(mode);
    }

    @Override
    public void visitDrunkWorld(boolean drunk) {
        setDrunk(drunk);
    }

    @Override
    public void visitUnk6(boolean unk6) {
        setUnk6(unk6);
    }

    @Override
    public void visitCreationTime(long time) {
        setCreationTime(time);
    }

    @Override
    public void visitEnd() {

    }
}
