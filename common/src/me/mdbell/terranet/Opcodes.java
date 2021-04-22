package me.mdbell.terranet;

public interface Opcodes {

    int DEFAULT_REV = 236;

    String DEFAULT_VERSION = "Terraria" + DEFAULT_REV;

    int MESSAGE_HEADER_SIZE = 3;

    int DEFAULT_PRIORITY = 0;

    int MAX_PLAYERS = 256;

    //Used in the update buff packet
    int MAX_BUFFS = 22;

    int MAX_ITEMS = 260;

    // Outgoing chat messages
    int AUTHOR_SERVER = -1;

    int OP_CONNECT = 1;
    int OP_DISCONNECT = 2;
    int OP_SET_USER_SLOT = 3;
    int OP_PLAYER_INFO = 4;
    int OP_SET_INVENTORY_SLOT = 5;
    int OP_REQUEST_WORLD = 6;
    int OP_WORLD_METADATA = 7;
    int OP_ESSENTIAL_TILES = 8;
    int OP_UPDATE_LOADING_STATUS = 9;

    int OP_PLAYER_HP = 16;

    int OP_PLAYER_MANA = 42;

    int OP_UPDATE_BUFFS = 50;

    int OP_UUID = 68;

    int OP_LOAD_MODULE = 82;

    //opcodes for the module packet
    int MOD_LIQUID = 0;
    int MOD_TEXT = 1;
    int MOD_PING = 2;
    int MOD_AMBIANCE = 3;
    int MOD_BESTIARY = 4;
    int MOD_CREATIVE_UNLOCKS = 5;
    int MOD_CREATIVE_POWERS = 6;
    int MOD_CREATIVE_UNLOCKS_PLAYER_REPORT = 7;
    int MOD_TELEPORT_PYLON = 8;
    int MOD_PARTICLES = 9;
    int MOD_CREATIVE_POWER_PERMISSIONS = 10;
}
