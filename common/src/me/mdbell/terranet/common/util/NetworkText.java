package me.mdbell.terranet.common.util;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.text.MessageFormat;
import java.util.Arrays;

@Builder
@Accessors(fluent = true)
@Getter
public final class NetworkText {

    @Builder.Default
    private Mode mode = Mode.LITERAL;
    @Builder.Default
    private String text = "";
    @Builder.Default
    private NetworkText[] sub = new NetworkText[0];

    public String toString() {
        switch (mode) {
            case LITERAL:
                return text;
            case FORMAT:
                return toStringFormatted();
            default:
                return toObjectString();
        }
//        MessageFormat format
    }

    private String toStringFormatted() {
        MessageFormat format = new MessageFormat(text);
        return format.format(sub);
    }

    public String toObjectString() {
        return "NetworkText{" +
                "mode=" + mode +
                ", text='" + text + '\'' +
                ", sub=" + Arrays.toString(sub) +
                '}';
    }

    public enum Mode {
        LITERAL, FORMAT, LOCAL
    }
}
