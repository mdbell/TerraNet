package me.mdbell.terranet.common.io;

import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


final class RAFBuffer extends TrackedBuffer<RandomAccessFile>{

    private final RandomAccessFile buffer;

    RAFBuffer(RandomAccessFile buffer){
        this.buffer = buffer;
    }

    @Override
    public RandomAccessFile getBuffer() {
        return buffer;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeBoolean(boolean value) {
        buffer.seek(writeIndex);
        buffer.writeBoolean(value);
        writeIndex++;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeByte(int value) {
        buffer.seek(writeIndex);
        buffer.writeByte(value);
        writeIndex++;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeShort(int value) {
        buffer.seek(writeIndex);
        buffer.writeShort(value);
        writeIndex+= Short.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeInt(int value) {
        buffer.seek(writeIndex);
        buffer.writeInt(value);
        writeIndex+= Integer.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeFloat(float value) {
        buffer.seek(writeIndex);
        buffer.writeFloat(value);
        writeIndex+= Float.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeLong(long value) {
        buffer.seek(writeIndex);
        buffer.writeLong(value);
        writeIndex+= Short.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeBytes(byte[] bytes) {
        buffer.seek(writeIndex);
        buffer.write(bytes);
        writeIndex += bytes.length;
        return this;
    }

    @Override
    public Buffer<?> writeBytes(ByteBuffer bytes) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public Buffer<?> writeBytes(ByteBuf bytes) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public boolean isWritable() {
        return true; //TODO actually check if it's writable
    }

    @SneakyThrows
    @Override
    public byte readByte() {
        buffer.seek(readIndex);
        readIndex++;
        return buffer.readByte();
    }

    @Override
    public int readUnsignedByte() {
        return readByte() & 0xFF;
    }

    @SneakyThrows
    @Override
    public short readShort() {
        buffer.seek(readIndex);
        readIndex += Short.BYTES;
        return buffer.readShort();
    }

    @SneakyThrows
    @Override
    public int readInt() {
        buffer.seek(readIndex);
        readIndex += Integer.BYTES;
        return buffer.readInt();
    }

    @SneakyThrows
    @Override
    public float readFloat() {
        buffer.seek(readIndex);
        readIndex += Float.BYTES;
        return buffer.readFloat();
    }

    @SneakyThrows
    @Override
    public double readDouble() {
        buffer.seek(readIndex);
        readIndex += Double.BYTES;
        return buffer.readDouble();
    }

    @SneakyThrows
    @Override
    public long readLong() {
        buffer.seek(readIndex);
        readIndex += Long.BYTES;
        return buffer.readLong();
    }

    @SneakyThrows
    @Override
    public Buffer<?> readBytes(byte[] data) {
        buffer.readFully(data);
        readIndex += data.length;
        return this;
    }

    @Override
    public Buffer<?> readBytes(ByteBuf bytes) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @Override
    public Buffer<?> readBytes(ByteBuffer bytes) {
        throw new UnsupportedOperationException("Unimplemented");
    }

    @SneakyThrows
    @Override
    public boolean readBoolean() {
        return readByte() != 0;
    }


    @SneakyThrows
    @Override
    protected void readIntoBuffer(int len){
        buffer.seek(readIndex);
        buffer.read(tmp.array(), 0, len);
        readIndex += len;
    }

    @SneakyThrows
    @Override
    protected Buffer<?> writeBuffer(int len){
        buffer.seek(writeIndex);
        buffer.write(tmp.array(), 0, len);
        writeIndex += len;
        return this;
    }
}
