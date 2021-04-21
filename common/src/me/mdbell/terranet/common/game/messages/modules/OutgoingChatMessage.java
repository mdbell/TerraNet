package me.mdbell.terranet.common.game.messages.modules;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.mdbell.terranet.common.game.messages.NetModuleMessage;
import me.mdbell.terranet.common.util.Color;
import me.mdbell.terranet.common.util.NetworkText;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class OutgoingChatMessage extends NetModuleMessage {
    private int author;
    private NetworkText text;
    private Color color;

    @Override
    protected final int getModIdImpl() {
        return MOD_TEXT;
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

    public OutgoingChatMessage text(String text){
        this.text = NetworkText.literal(text);
        return this;
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
    public final boolean isServer() {
        return true;
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
