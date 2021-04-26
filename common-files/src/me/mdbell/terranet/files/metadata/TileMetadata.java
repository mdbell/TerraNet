package me.mdbell.terranet.files.metadata;

public class TileMetadata {

    public int id;
    public String name;

    public Boolean lighted;
    public Boolean solid;
    public Boolean mergeDirt;
    public Boolean brick;
    public Boolean blockLight;
    public Boolean frameImportant;
    public Boolean waterDeath;
    public Boolean lavaDeath;
    public Boolean solidTop;
    public Boolean table;
    public Boolean stone;
    public Boolean noFail;
    public Boolean noAttach;
    public Boolean noSunlight;
    public Boolean spelunker;
    public Short glowMask;
    public Boolean flame;
    public Boolean rope;
    public Boolean pile;
    public Boolean bouncy;
    public Boolean obsidianKill;
    public Byte largeFrames;
    public Boolean blendAll;
    public Boolean container;
    public Boolean sign;
    public Boolean cut;
    public Boolean moss;
    public Boolean cracked;
    public Boolean hammer;
    public Boolean axe;
    public Boolean sand;
    public Boolean alch;
    public Boolean dungeon;
    public Integer shine;
    public Boolean shine2;

    public Boolean forceSaveSlope;

    public TileMetadata(int id) {
        this.id = id;
    }
}
