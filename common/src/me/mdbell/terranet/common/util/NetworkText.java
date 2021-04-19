package me.mdbell.terranet.common.util;

import java.util.Arrays;

public final class NetworkText {

    private Mode mode = Mode.LITERAL;
    private String text = "";
    private NetworkText[] sub;

    public NetworkText mode(Mode mode){
        this.mode = mode;
        return this;
    }

    public Mode mode(){
        return mode;
    }

    public NetworkText text(String text){
        if(text == null){
            text = "";
        }
        this.text = text;
        return this;
    }

    public String text(){
        return text;
    }

    public NetworkText[] sub(){
        return sub;
    }

    public NetworkText sub(NetworkText[] sub) {
        this.sub = sub;
        return this;
    }

    public static NetworkText literal(String text){
        return new NetworkText().mode(Mode.LITERAL).text(text);
    }

    @Override
    public String toString() {
        return "NetworkText{" +
                "mode=" + mode +
                ", text='" + text + '\'' +
                ", sub=" + Arrays.toString(sub) +
                '}';
    }

    public enum Mode{
        LITERAL, FORMAT, LOCAL
    }
}
