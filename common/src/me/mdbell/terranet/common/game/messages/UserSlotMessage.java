package me.mdbell.terranet.common.game.messages;

public class UserSlotMessage extends GameMessage {

    public static final int DEFAULT_SLOT = 0;

    private int slot;
    private boolean flag;

    public UserSlotMessage() {
        super(OP_SET_USER_SLOT);
    }

    public UserSlotMessage slot(int slot) {
        this.slot = slot;
        return this;
    }

    public int slot() {
        return slot;
    }

    public UserSlotMessage flag(boolean flag) {
        this.flag = flag;
        return this;
    }

    public boolean flag(){
        return flag;
    }

    @Override
    public String toString() {
        return "UserSlotMessage{" +
                "slot=" + slot +
                ", flag=" + flag +
                '}';
    }
}
