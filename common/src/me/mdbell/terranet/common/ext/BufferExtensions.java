package me.mdbell.terranet.common.ext;


import lombok.NonNull;
import lombok.experimental.UtilityClass;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.util.Color;
import me.mdbell.terranet.common.util.NetworkText;
import me.mdbell.terranet.common.util.UUID;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.BitSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

@UtilityClass
public class BufferExtensions {

    public Buffer<?> readInto(Buffer<?> source, int[] array, Function<Buffer<?>, Integer> func) {
        for (int i = 0; i < array.length; i++) {
            array[i] = func.apply(source);
        }
        return source;
    }

    public Buffer<?> writeInto(Buffer<?> source, int[] array, BiConsumer<Buffer<?>, Integer> func) {
        for (int j : array) {
            func.accept(source, j);
        }
        return source;
    }

    public String readString(Buffer<?> buf) {
        return readString(buf, StandardCharsets.UTF_8);
    }

    public String readString(Buffer<?> buf, Charset charset) {
        int len = buf.readUnsignedByte();
        byte[] data = new byte[len];
        buf.readBytes(data);
        return new String(data, charset);
    }

    public Buffer<?> writeString(Buffer<?> to, String str) {
        return writeString(to, str, StandardCharsets.UTF_8);
    }

    public Buffer<?> writeString(Buffer<?> to, String str, Charset charset) {
        if (str.length() > 255) {
            throw new IllegalStateException("Max String size is 255! String size:" + str.length());
        }
        byte[] bytes = str.getBytes(charset);
        to.writeByte(bytes.length);
        to.writeBytes(bytes);
        return to;
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

    public BitSet readBits(Buffer<?> to, int byteCount){
        byte[] data = new byte[byteCount];
        to.readBytes(data);
        return BitSet.valueOf(data);
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

    public Buffer<?> writeGuid(Buffer<?> to, UUID id){
        return to.writeBytes(id.getData());
    }

    public UUID readGuid(Buffer<?> from) {
        UUID res = new UUID();
        from.readBytes(res.getData());
        return res;
    }
}
