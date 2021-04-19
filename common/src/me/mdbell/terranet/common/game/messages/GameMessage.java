package me.mdbell.terranet.common.game.messages;

import me.mdbell.terranet.Opcodes;

public abstract class GameMessage implements Opcodes {

    private final int id;

    public GameMessage(int id){
        this.id = id;
    }

    public final int getId(){
        return id;
    }

    public int modId(){
        return -1;
    }

    public boolean isServer(){
        return false;
    }
}
