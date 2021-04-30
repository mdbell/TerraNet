package me.mdbell.terranet.common.game.scene;

public enum LiquidType {
    NONE(0), WATER(1), LAVA(2), HONEY(3);

    int type;

    LiquidType(int type) {
        this.type = type;
    }

    public int getInternalType() {
        return type;
    }

    public int getMask(){
        return type << 3;
    }

    public static LiquidType valueOf(int type) {
        for (LiquidType liquidType : values()) {
            if (liquidType.type == type) {
                return liquidType;
            }
        }
        return NONE;
    }
}
