package me.mdbell.terranet.common.game.messages;

public class UUIDMessage extends GameMessage {
    private String uuid;

    public UUIDMessage() {
        super(OP_UUID);
    }

    public UUIDMessage uuid(String uuid){
        this.uuid = uuid;
        return this;
    }

    public String uuid(){
        return uuid;
    }

    @Override
    public String toString() {
        return "UUIDMessage{" +
                "uuid='" + uuid + '\'' +
                '}';
    }
}
