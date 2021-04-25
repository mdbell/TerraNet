package me.mdbell.terranet.common.io;

import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

final class NettyBuffer extends Buffer<ByteBuf> {

    private ByteBuf buffer;

    NettyBuffer(ByteBuf n) {
        this.buffer = n;
    }

    @Override
    public ByteBuf getBuffer() {
        return buffer;
    }

    @Override
    public Buffer<?> writeBoolean(boolean value) {
        buffer.writeBoolean(value);
        return this;
    }

    @Override
    public Buffer<?> writeByte(int value) {
        buffer.writeByte(value);
        return this;
    }

    @Override
    public Buffer<?> writeShort(int value) {
        buffer.writeShort(value);
        return this;
    }

    @Override
    public Buffer<?> writeShortLE(int value) {
        buffer.writeShortLE(value);
        return this;
    }

    @Override
    public Buffer<?> writeInt(int value) {
        buffer.writeInt(value);
        return this;
    }

    @Override
    public Buffer<?> writeIntLE(int value) {
        buffer.writeIntLE(value);
        return this;
    }

    @Override
    public Buffer<?> writeFloat(float value) {
        buffer.writeFloat(value);
        return this;
    }

    @Override
    public Buffer<?> writeFloatLE(float value) {
        buffer.writeFloatLE(value);
        return this;
    }

    @Override
    public Buffer<?> writeLong(long value) {
        buffer.writeLong(value);
        return this;
    }

    @Override
    public Buffer<?> writeLongLE(long value) {
        buffer.writeLongLE(value);
        return this;
    }

    @Override
    public Buffer<?> writeDouble(double value) {
        buffer.writeDouble(value);
        return this;
    }

    @Override
    public Buffer<?> writeDoubleLE(double value) {
        buffer.writeDoubleLE(value);
        return this;
    }

    @Override
    public Buffer<?> writeBytes(byte[] bytes) {
        buffer.writeBytes(bytes);
        return this;
    }

    @Override
    public Buffer<?> writeBytes(ByteBuffer bytes) {
        buffer.writeBytes(bytes);
        return this;
    }

    @Override
    public Buffer<?> writeBytes(ByteBuf bytes) {
        buffer.writeBytes(bytes);
        return this;
    }

    @Override
    public int writerIndex() {
        return buffer.writerIndex();
    }

    @Override
    public Buffer<?> writerIndex(int newIndex) {
        buffer.writerIndex(newIndex);
        return this;
    }

    @Override
    public Buffer<?> markWriterIndex() {
        buffer.markWriterIndex();
        return this;
    }

    @Override
    public Buffer<?> resetWriterIndex() {
        buffer.resetWriterIndex();
        return this;
    }

    @Override
    public boolean isWritable() {
        return buffer.isWritable();
    }

    @Override
    public int readerIndex() {
        return buffer.readerIndex();
    }

    @Override
    public Buffer<?> readerIndex(int newIndex) {
        buffer.readerIndex(newIndex);
        return this;
    }

    @Override
    public Buffer<?> markReaderIndex() {
        buffer.markReaderIndex();
        return this;
    }

    @Override
    public Buffer<?> resetReaderIndex() {
        buffer.resetReaderIndex();
        return this;
    }

    @Override
    public byte readByte() {
        return buffer.readByte();
    }

    @Override
    public int readUnsignedByte() {
        return buffer.readUnsignedByte();
    }

    @Override
    public short readShort() {
        return buffer.readShort();
    }

    @Override
    public short readShortLE() {
        return buffer.readShortLE();
    }

    @Override
    public int readUnsignedShort() {
        return buffer.readUnsignedShort();
    }

    @Override
    public int readUnsignedShortLE() {
        return buffer.readUnsignedShortLE();
    }

    @Override
    public int readInt() {
        return buffer.readInt();
    }

    @Override
    public int readIntLE() {
        return buffer.readIntLE();
    }

    @Override
    public float readFloat() {
        return buffer.readFloat();
    }

    @Override
    public float readFloatLE() {
        return buffer.readFloatLE();
    }

    @Override
    public double readDouble() {
        return buffer.readDouble();
    }

    @Override
    public double readDoubleLE() {
        return buffer.readDoubleLE();
    }

    @Override
    public long readLong() {
        return buffer.readLong();
    }

    @Override
    public long readLongLE() {
        return buffer.readLongLE();
    }

    @Override
    public Buffer<?> readBytes(byte[] data) {
        buffer.readBytes(data);
        return this;
    }

    public Buffer<?> readBytes(ByteBuffer bytes) {
        buffer.readBytes(bytes);
        return this;
    }

    public Buffer<?> readBytes(ByteBuf bytes) {
        buffer.readBytes(bytes);
        return this;
    }

    @Override
    public boolean readBoolean() {
        return buffer.readBoolean();
    }
}
