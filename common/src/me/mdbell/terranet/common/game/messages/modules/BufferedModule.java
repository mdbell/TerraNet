package me.mdbell.terranet.common.game.messages.modules;

import me.mdbell.terranet.common.game.messages.NetModuleMessage;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class BufferedModule extends NetModuleMessage {
    private final ByteBuffer buffer;

    public BufferedModule(int id, int size){
        this(id, ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN));
    }

    public BufferedModule(int id, ByteBuffer buffer) {
        super(id);
        this.buffer = buffer;
    }

    public ByteBuffer buffer(){
        return buffer;
    }
}
