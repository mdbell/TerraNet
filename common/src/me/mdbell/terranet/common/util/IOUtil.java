package me.mdbell.terranet.common.util;


import me.mdbell.terranet.common.io.Buffer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public final class IOUtil {

    private IOUtil() {

    }

    public static String readString(Buffer<?> buf) {
        return readString(buf, StandardCharsets.UTF_8);
    }

    public static String readString(Buffer<?> buf, Charset charset) {
        int len = buf.readUnsignedByte();
        byte[] data = new byte[len];
        buf.readBytes(data);
        return new String(data, charset);
    }

    public static void writeString(String str, Buffer<?> to) {
        writeString(str, to, StandardCharsets.UTF_8);
    }

    public static void writeString(String str, Buffer<?> to, Charset charset) {
        if(str.length() > 255){
            throw new IllegalStateException("Max String size is 255! String size:" + str.length());
        }
        byte[] bytes = str.getBytes(charset);
        to.writeByte(bytes.length);
        to.writeBytes(bytes);
    }

    public static Color readColor(Buffer<?> buf){
        int red = buf.readUnsignedByte();
        int green = buf.readUnsignedByte();
        int blue = buf.readUnsignedByte();
        return new Color(red, green, blue);
    }

    public static void writeColor(Color color, Buffer<?> to) {
        if(color == null){
            color = Color.BLACK;
        }
        to.writeByte(color.getRed());
        to.writeByte(color.getGreen());
        to.writeByte(color.getBlue());
    }

    public static NetworkText readText(Buffer<?> buffer){
        int m = buffer.readUnsignedByte();
        String text = buffer.readString();
        NetworkText.Mode mode = NetworkText.Mode.values()[m];
        NetworkText res = new NetworkText().mode(mode).text(text);
        if(mode != NetworkText.Mode.LITERAL) {
            int subLen = buffer.readUnsignedByte();
            NetworkText[] sub = new NetworkText[subLen];
            for(int i = 0; i < subLen; i++) {
                sub[i] = readText(buffer);
            }
            res.sub(sub);
        }
        return res;
    }

    public static void writeText(NetworkText text, Buffer<?> to) {
        to.writeByte(text.mode().ordinal());
        to.writeString(text.text());
        if(text.mode() != NetworkText.Mode.LITERAL) {
            NetworkText[] subs = text.sub();
            to.writeByte(subs.length);
            for (NetworkText sub : subs) {
                writeText(sub, to);
            }
        }
    }
}
