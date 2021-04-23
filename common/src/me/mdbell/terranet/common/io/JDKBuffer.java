package me.mdbell.terranet.common.io;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

final class JDKBuffer extends TrackedBuffer<ByteBuffer> {

    private final ByteBuffer buffer;

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
        buffer.putShort(writeIndex, (short) value);
        writeIndex += Short.BYTES;
        return this;
    }

    @Override
    public Buffer<?> writeInt(int value) {
        buffer.putInt(writeIndex, value);
        writeIndex += Integer.BYTES;
        return this;
    }

    @Override
    public Buffer<?> writeFloat(float value) {
        buffer.putFloat(writeIndex, value);
        writeIndex += Float.BYTES;
        return this;
    }

    @Override
    public Buffer<?> writeLong(long value) {
        buffer.putLong(writeIndex, value);
        writeIndex += Long.BYTES;
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
        short value = buffer.getShort(readIndex);
        readIndex += Short.BYTES;
        return value;
    }

    @Override
    public int readInt() {
        int value = buffer.getInt(readIndex);
        readIndex += Integer.BYTES;
        return value;
    }

    @Override
    public float readFloat() {
        float value = buffer.getFloat(readIndex);
        readIndex += Float.BYTES;
        return value;
    }

    @Override
    public double readDouble() {
        double value = buffer.getDouble(readIndex);
        readIndex += Double.BYTES;
        return value;
    }

    @Override
    public long readLong() {
        long value = buffer.getLong(readIndex);
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
    protected Buffer<?> writeBuffer(int len) {
        buffer.put(tmp.array(), 0, len);
        writeIndex += len;
        return this;
    }

    @Override
    protected void readIntoBuffer(int len) {
        buffer.get(tmp.array(), 0, len);
        readIndex += len;
    }
}
