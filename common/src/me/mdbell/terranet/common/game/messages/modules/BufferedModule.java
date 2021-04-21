package me.mdbell.terranet.common.game.messages.modules;

import lombok.Getter;
import me.mdbell.terranet.common.game.messages.NetModuleMessage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BufferedModule extends NetModuleMessage {
    private final ByteBuffer buffer;

    @Getter
    private final int modIdImpl;

    public BufferedModule(int id, int size) {
        this(id, ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN));
    }

    public BufferedModule(int id, ByteBuffer buffer) {
        modIdImpl = id;
        this.buffer = buffer;
    }

    public ByteBuffer buffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return "BufferedModule{" +
                "modId=" + getModId() +
                ",buffer=" + buffer +
                '}';
    }
}
