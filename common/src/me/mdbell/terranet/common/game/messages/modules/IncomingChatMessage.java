package me.mdbell.terranet.common.game.messages.modules;

import me.mdbell.terranet.common.game.messages.NetModuleMessage;

public class IncomingChatMessage extends NetModuleMessage {

    private String command, message;

    public IncomingChatMessage() {
        super(MOD_TEXT);
    }

    public String command(){
        return command;
    }

    public IncomingChatMessage command(String command){
        this.command = command;
        return this;
    }

    public String message(){
        return message;
    }

    public IncomingChatMessage message(String message){
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "IncomingChatMessage{" +
                "command='" + command + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
