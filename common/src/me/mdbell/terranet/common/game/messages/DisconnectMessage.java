package me.mdbell.terranet.common.game.messages;

import me.mdbell.terranet.common.util.NetworkText;

public class DisconnectMessage extends GameMessage {

    private NetworkText reason;

    public DisconnectMessage() {
        super(OP_DISCONNECT);
    }

    public NetworkText reason(){
        return reason;
    }

    public DisconnectMessage reason(NetworkText text){
        this.reason = text;
        return this;
    }

    @Override
    public String toString() {
        return "DisconnectMessage{" +
                "reason=" + reason +
                '}';
    }
}
