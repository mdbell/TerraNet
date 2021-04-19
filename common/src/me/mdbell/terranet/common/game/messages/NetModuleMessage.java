package me.mdbell.terranet.common.game.messages;

public abstract class NetModuleMessage extends GameMessage {
    private final int id;

    public NetModuleMessage(int id) {
        super(OP_LOAD_MODULE);
        this.id = id;
    }

    public final int modId(){
        return this.id;
    }
}
