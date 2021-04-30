package me.mdbell.terranet.metadata;

public class TileMetadata {

    public int id;
    public String name;

    public boolean lighted;
    public boolean solid;
    public boolean mergeDirt;
    public boolean brick;
    public boolean blockLight;
    public boolean frameImportant;
    public boolean waterDeath;
    public boolean lavaDeath;
    public boolean solidTop;
    public boolean table;
    public boolean stone;
    public boolean noFail;
    public boolean noAttach;
    public boolean noSunlight;
    public boolean spelunker;
    public Short glowMask;
    public boolean flame;
    public boolean rope;
    public boolean pile;
    public boolean bouncy;
    public boolean obsidianKill;
    public Byte largeFrames;
    public boolean blendAll;
    public boolean container;
    public boolean sign;
    public boolean cut;
    public boolean moss;
    public boolean cracked;
    public boolean hammer;
    public boolean axe;
    public boolean sand;
    public boolean alch;
    public boolean dungeon;
    public Integer shine;
    public boolean shine2;

    public boolean forceSaveSlope;

    public TileMetadata(int id) {
        this.id = id;
    }
}
