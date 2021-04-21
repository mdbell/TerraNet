package me.mdbell.terranet.common.io;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import me.mdbell.terranet.common.util.IOUtil;

import java.nio.ByteBuffer;
import java.util.UUID;

public abstract class Buffer<T> {

    private int bitByte;
    private int bitReadPos = Byte.SIZE, bitWritePos = 0;

    @Getter
    @Setter
    private BitMode bitMode = BitMode.ONE_TRUE;

    public static Buffer<ByteBuf> wrap(ByteBuf buffer) {
        return new NettyBuffer(buffer);
    }

    public static Buffer<ByteBuffer> allocate(int capacity) {
        return wrap(ByteBuffer.allocate(capacity));
    }

    public static Buffer<ByteBuffer> allocateDirect(int capacity) {
        return wrap(ByteBuffer.allocateDirect(capacity));
    }

    public static Buffer<ByteBuffer> wrap(byte[] b) {
        return wrap(ByteBuffer.wrap(b));
    }

    public static Buffer<ByteBuffer> wrap(ByteBuffer buffer) {
        return new JDKBuffer(buffer);
    }

    public abstract T getBuffer();

    public abstract Buffer<?> writeBoolean(boolean value);

    public abstract Buffer<?> writeByte(int value);

    public abstract Buffer<?> writeShort(int value);

    public abstract Buffer<?> writeShortLE(int value);

    public abstract Buffer<?> writeInt(int value);

    public abstract Buffer<?> writeIntLE(int value);

    public abstract Buffer<?> writeBytes(byte[] bytes);

    public abstract Buffer<?> writeBytes(ByteBuffer bytes);

    public abstract Buffer<?> writeBytes(ByteBuf bytes);

    public final Buffer<?> writeBytes(Buffer<?> bytes) {
        Object b = bytes.getBuffer();
        if (b instanceof ByteBuf) {
            return writeBytes((ByteBuf) b);
        } else if (b instanceof ByteBuffer) {
            return writeBytes((ByteBuffer) b);
        } else {
            throw new IllegalArgumentException("Unsupported underlying type:" + b.getClass().getName());
        }
    }

    public abstract int writerIndex();

    public abstract Buffer<?> writerIndex(int newIndex);

    public abstract Buffer<?> markWriterIndex();

    public abstract Buffer<?> resetWriterIndex();

    public abstract boolean isWritable();

    public abstract byte readByte();

    public abstract int readUnsignedByte();

    public abstract short readShort();

    public abstract short readShortLE();

    public int readUnsignedShort() {
        return Short.toUnsignedInt(readShort());
    }

    public int readUnsignedShortLE() {
        return Short.toUnsignedInt(readShortLE());
    }

    public abstract int readInt();

    public abstract int readIntLE();

    public abstract float readFloat();

    public abstract float readFloatLE();

    public abstract long readLong();

    public abstract long readLongLE();

    public abstract Buffer<?> readBytes(byte[] data);

    public abstract Buffer<?> readBytes(ByteBuf bytes);

    public abstract Buffer<?> readBytes(ByteBuffer bytes);

    public final Buffer<?> readBytes(Buffer<?> data) {
        Object b = data.getBuffer();
        if (b instanceof ByteBuf) {
            return readBytes((ByteBuf) b);
        } else if (b instanceof ByteBuffer) {
            return readBytes((ByteBuffer) b);
        } else {
            throw new IllegalArgumentException("Unsupported underlying type:" + b.getClass().getName());
        }
    }

    public final boolean readBit() {
        if (bitReadPos == Byte.SIZE) {
            bitByte = readUnsignedByte();
            bitReadPos = 0;
        }
        int value = (bitByte >> bitReadPos) & 1;
        bitReadPos++;
        return value == bitMode.ordinal();
    }

    public final Buffer<?> skipReaderBits(int count) {
        bitReadPos += count;
        while (bitReadPos >= Byte.SIZE) {
            bitByte = readUnsignedByte();
            bitReadPos -= Byte.SIZE;
        }
        return this;
    }

    public final Buffer<?> resetBitPosition() {
        bitReadPos = Byte.SIZE;
        return this;
    }

    public abstract boolean readBoolean();

    public abstract int readerIndex();

    public abstract Buffer<?> readerIndex(int newIndex);

    public abstract Buffer<?> markReaderIndex();

    public abstract Buffer<?> resetReaderIndex();

    public UUID readGuid() {
        byte[] data = new byte[16];
        readBytes(data);
        return UUID.nameUUIDFromBytes(data);
    }
}
