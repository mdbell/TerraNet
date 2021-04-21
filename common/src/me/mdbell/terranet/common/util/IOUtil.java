package me.mdbell.terranet.common.util;


import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.mdbell.terranet.common.io.Buffer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@UtilityClass
public class IOUtil {

    public String readString(Buffer<?> buf) {
        return readString(buf, StandardCharsets.UTF_8);
    }

    public String readString(Buffer<?> buf, Charset charset) {
        int len = buf.readUnsignedByte();
        byte[] data = new byte[len];
        buf.readBytes(data);
        return new String(data, charset);
    }

    public void writeString(Buffer<?> to, String str) {
        writeString(to, str, StandardCharsets.UTF_8);
    }

    public void writeString(Buffer<?> to, String str, Charset charset) {
        if (str.length() > 255) {
            throw new IllegalStateException("Max String size is 255! String size:" + str.length());
        }
        byte[] bytes = str.getBytes(charset);
        to.writeByte(bytes.length);
        to.writeBytes(bytes);
    }

    public Color readColor(Buffer<?> buf) {
        int red = buf.readUnsignedByte();
        int green = buf.readUnsignedByte();
        int blue = buf.readUnsignedByte();
        return new Color(red, green, blue);
    }

    public Buffer<?> writeColor(Buffer<?> to, @NonNull Color color) {
        return to.writeByte(color.getRed())
                .writeByte(color.getGreen())
                .writeByte(color.getBlue());
    }

    public NetworkText readText(Buffer<?> buffer) {
        int m = buffer.readUnsignedByte();
        String text = readString(buffer);
        NetworkText.Mode mode = NetworkText.Mode.values()[m];
        NetworkText res = new NetworkText().mode(mode).text(text);
        if (mode != NetworkText.Mode.LITERAL) {
            int subLen = buffer.readUnsignedByte();
            NetworkText[] sub = new NetworkText[subLen];
            for (int i = 0; i < subLen; i++) {
                sub[i] = readText(buffer);
            }
            res.sub(sub);
        }
        return res;
    }

    public Buffer<?> writeText(Buffer<?> to, NetworkText text) {
        to.writeByte(text.mode().ordinal());
        writeString(to, text.text());
        if (text.mode() != NetworkText.Mode.LITERAL) {
            NetworkText[] subs = text.sub();
            to.writeByte(subs.length);
            for (NetworkText sub : subs) {
                writeText(to, sub);
            }
        }
        return to;
    }
}
