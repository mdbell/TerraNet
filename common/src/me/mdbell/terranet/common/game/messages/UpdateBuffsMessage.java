package me.mdbell.terranet.common.game.messages;

import java.util.Arrays;

public class UpdateBuffsMessage extends GameMessage {

    private int id;
    private final int buffs[] = new int[MAX_BUFFS];

    public UpdateBuffsMessage() {
        super(OP_UPDATE_BUFFS);
    }

    public UpdateBuffsMessage buff(int idx, int value){
        buffs[idx] = value;
        return this;
    }

    public int buff(int idx){
        return buffs[idx];
    }

    public int id(){
        return id;
    }

    public UpdateBuffsMessage id(int id){
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "UpdateBuffsMessage{" +
                "id=" + id +
                ", buffs=" + Arrays.toString(buffs) +
                '}';
    }
}
