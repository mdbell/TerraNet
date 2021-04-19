package me.mdbell.terranet.common.game.messages.modules;

import me.mdbell.terranet.common.game.messages.NetModuleMessage;
import me.mdbell.terranet.common.util.Color;
import me.mdbell.terranet.common.util.NetworkText;

public class OutgoingChatMessage extends NetModuleMessage {
    private int author;
    private NetworkText text;
    private Color color;

    public OutgoingChatMessage() {
        super(MOD_TEXT);
    }

    public int author(){
        return author;
    }

    public OutgoingChatMessage author(int author){
        this.author = author;
        return this;
    }

    public NetworkText text(){
        return text;
    }

    public OutgoingChatMessage text(NetworkText text){
        this.text = text;
        return this;
    }

    public Color color(){
        return color;
    }

    public OutgoingChatMessage color(Color color){
        this.color = color;
        return this;
    }

    @Override
    public String toString() {
        return "OutgoingChatMessage{" +
                "author=" + author +
                ", text=" + text +
                ", color=" + color +
                '}';
    }
}
