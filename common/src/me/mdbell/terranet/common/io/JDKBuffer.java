package me.mdbell.terranet.common.io;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class JDKBuffer extends Buffer<ByteBuffer> {

    private ByteBuffer buffer;
    private int writeIndex, readIndex;
    private int writeMark, readMark;

    JDKBuffer(ByteBuffer buffer) {
        this.buffer = buffer;
        this.writeIndex = this.readIndex = buffer.position();
    }

    @Override
    public ByteBuffer getBuffer() {
        return buffer;
    }

    @Override
    public Buffer<?> writeBoolean(boolean value) {
        buffer.put(writeIndex++, (byte) (value ? 1 : 0));
        return this;
    }

    @Override
    public Buffer<?> writeByte(int value) {
        buffer.put(writeIndex++, (byte) value);
        return null;
    }

    @Override
    public Buffer<?> writeShort(int value) {
        buffer.order(ByteOrder.BIG_ENDIAN).putShort(writeIndex, (short) value);
        writeIndex += Short.BYTES;
        return this;
    }

    @Override
    public Buffer<?> writeShortLE(int value) {
        buffer.order(ByteOrder.LITTLE_ENDIAN).putShort(writeIndex, (short) value);
        writeIndex += Short.BYTES;
        return this;
    }

    @Override
    public Buffer<?> writeInt(int value) {
        buffer.order(ByteOrder.BIG_ENDIAN).putInt(writeIndex, value);
        writeIndex += Integer.BYTES;
        return this;
    }

    @Override
    public Buffer<?> writeIntLE(int value) {
        buffer.order(ByteOrder.LITTLE_ENDIAN).putInt(writeIndex, value);
        writeIndex += Integer.BYTES;
        return this;
    }

    @Override
    public Buffer<?> writeBytes(byte[] bytes) {
        int old = buffer.position();
        buffer.position(writeIndex);
        buffer.put(bytes);
        writeIndex += buffer.position() - old;
        buffer.position(old);
        return this;
    }

    @Override
    public Buffer<?> writeBytes(ByteBuffer bytes) {
        int old = buffer.position();
        buffer.position(writeIndex);
        buffer.put(bytes);
        writeIndex += buffer.position() - old;
        buffer.position(old);
        return this;
    }

    @Override
    public Buffer<?> writeBytes(ByteBuf bytes) {
        int old = buffer.position();
        buffer.position(writeIndex);
        bytes.readBytes(buffer);
        writeIndex += buffer.position() - old;
        buffer.position(old);
        return this;
    }

    @Override
    public int writerIndex() {
        return writeIndex;
    }

    @Override
    public Buffer<?> writerIndex(int newIndex) {
        this.writeIndex = newIndex;
        return this;
    }

    @Override
    public Buffer<?> markWriterIndex() {
        writeMark = writeIndex;
        return this;
    }

    @Override
    public Buffer<?> resetWriterIndex() {
        writeIndex = writeMark;
        return this;
    }

    @Override
    public boolean isWritable() {
        return !buffer.isReadOnly();
    }

    @Override
    public byte readByte() {
        return buffer.get(readIndex++);
    }

    @Override
    public int readUnsignedByte() {
        return buffer.get(readIndex++) & 0xFF;
    }

    @Override
    public short readShort() {
        short value = buffer.order(ByteOrder.BIG_ENDIAN).getShort(readIndex);
        readIndex += Short.BYTES;
        return value;
    }

    @Override
    public short readShortLE() {
        short value = buffer.order(ByteOrder.LITTLE_ENDIAN).getShort(readIndex);
        readIndex += Short.BYTES;
        return value;
    }

    @Override
    public int readInt() {
        int value = buffer.order(ByteOrder.BIG_ENDIAN).getInt(readIndex);
        readIndex += Integer.BYTES;
        return value;
    }

    @Override
    public int readIntLE() {
        int value = buffer.order(ByteOrder.LITTLE_ENDIAN).getInt(readIndex);
        readIndex += Integer.BYTES;
        return value;
    }

    @Override
    public float readFloat() {
        float value = buffer.order(ByteOrder.BIG_ENDIAN).getFloat(readIndex);
        readIndex += Float.BYTES;
        return value;
    }

    @Override
    public float readFloatLE() {
        float value = buffer.order(ByteOrder.LITTLE_ENDIAN).getFloat(readIndex);
        readIndex += Float.BYTES;
        return value;
    }

    @Override
    public long readLong() {
        long value = buffer.order(ByteOrder.BIG_ENDIAN).getLong(readIndex);
        readIndex += Long.BYTES;
        return value;
    }

    @Override
    public long readLongLE() {
        long value = buffer.order(ByteOrder.LITTLE_ENDIAN).getLong(readIndex);
        readIndex += Long.BYTES;
        return value;
    }

    @Override
    public Buffer<?> readBytes(byte[] data) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public Buffer<?> readBytes(ByteBuf bytes) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public Buffer<?> readBytes(ByteBuffer bytes) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public boolean readBoolean() {
        return readByte() != 0;
    }

    @Override
    public int readerIndex() {
        return readIndex;
    }

    @Override
    public Buffer<?> readerIndex(int newIndex) {
        readIndex = newIndex;
        return this;
    }

    @Override
    public Buffer<?> markReaderIndex() {
        readMark = readIndex;
        return this;
    }

    @Override
    public Buffer<?> resetReaderIndex() {
        readIndex = readMark;
        return this;
    }
}
