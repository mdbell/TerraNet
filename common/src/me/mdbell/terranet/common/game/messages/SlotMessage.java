package me.mdbell.terranet.common.game.messages;

public class SlotMessage extends GameMessage {

    private int id;
    private int slot;
    private int count;
    private int prefix;
    private int netId;

    public SlotMessage() {
        super(OP_SET_INVENTORY_SLOT);
    }

    public int id(){
        return id;
    }

    public SlotMessage id(int id){
        this.id = id;
        return this;
    }

    public int slot(){
        return slot;
    }

    public SlotMessage slot(int slot){
        this.slot = slot;
        return this;
    }

    public int count(){
        return count;
    }

    public SlotMessage count(int count){
        this.count = count;
        return this;
    }

    public int prefix(){
        return prefix;
    }

    public SlotMessage prefix(int prefix){
        this.prefix = prefix;
        return this;
    }

    public int netId(){
        return netId;
    }

    public SlotMessage netId(int netId){
        this.netId = netId;
        return this;
    }

    @Override
    public String toString() {
        return "SlotMessage{" +
                "id=" + id +
                ", slot=" + slot +
                ", count=" + count +
                ", prefix=" + prefix +
                ", netId=" + netId +
                '}';
    }
}
