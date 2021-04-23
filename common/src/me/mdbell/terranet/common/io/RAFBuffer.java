package me.mdbell.terranet.common.io;

import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;


final class RAFBuffer extends TrackedBuffer<RandomAccessFile>{

    private final RandomAccessFile buffer;
    private final ByteBuffer tmp = ByteBuffer.wrap(new byte[Long.BYTES]).order(ByteOrder.LITTLE_ENDIAN);

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
    public Buffer<?> writeShortLE(int value) {
        tmp.putShort(0, (short) value);
        return writeBuffer(Short.BYTES);
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
    public Buffer<?> writeIntLE(int value) {
        tmp.putInt(0, value);
        return writeBuffer(Integer.BYTES);
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
    public Buffer<?> writeFloatLE(float value) {
        tmp.putFloat(0, value);
        return writeBuffer(Float.BYTES);
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
    public Buffer<?> writeLongLE(long value) {
        tmp.putLong(0, value);
        return writeBuffer(Long.BYTES);
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
    public short readShortLE() {
        readIntoBuffer(Short.BYTES);
        return tmp.getShort(0);
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
    public int readIntLE() {
        readIntoBuffer(Integer.BYTES);
        return tmp.getInt(0);
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
    public float readFloatLE() {
        readIntoBuffer(Float.BYTES);
        return tmp.getFloat(0);
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
    public double readDoubleLE() {
        readIntoBuffer(Double.BYTES);
        return tmp.getDouble(0);
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
    public long readLongLE() {
        readIntoBuffer(Long.BYTES);
        return tmp.getLong(0);
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

    private void readIntoBuffer(int len) throws IOException {
        buffer.read(tmp.array(), 0, len);
        readIndex += len;
    }

    private Buffer<?> writeBuffer(int len) throws IOException {
        buffer.seek(writeIndex);
        buffer.write(tmp.array(), 0, len);
        writeIndex += len;
        return this;
    }
}
