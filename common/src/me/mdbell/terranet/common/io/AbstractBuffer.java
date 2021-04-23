package me.mdbell.terranet.common.io;

import lombok.SneakyThrows;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

abstract class AbstractBuffer<T> extends Buffer<T>{

    protected final T buffer;

    AbstractBuffer(T buffer){
        this.buffer = buffer;
    }

    protected int writeIndex, readIndex;
    protected int writeMark, readMark;

    final ByteBuffer tmp = ByteBuffer.wrap(new byte[Long.BYTES]).order(ByteOrder.LITTLE_ENDIAN);

    @Override
    public final T getBuffer(){
        return buffer;
    }

    @Override
    public final int writerIndex() {
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
    public final Buffer<?> writeShortLE(int value) {
        tmp.putShort(0, (short) value);
        return writeBuffer(Short.BYTES);
    }

    @Override
    public final Buffer<?> writeIntLE(int value) {
        tmp.putInt(0, value);
        return writeBuffer(Integer.BYTES);
    }

    @Override
    public final Buffer<?> writeFloatLE(float value) {
        tmp.putFloat(0, value);
        return writeBuffer(Float.BYTES);
    }

    @Override
    public final Buffer<?> writeLongLE(long value) {
        tmp.putLong(0, value);
        return writeBuffer(Long.BYTES);
    }

    protected abstract Buffer<?> writeBuffer(int len);

    @Override
    public final int readerIndex() {
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

    @Override
    public final short readShortLE() {
        readIntoBuffer(Short.BYTES);
        return tmp.getShort(0);
    }

    @Override
    public final int readIntLE() {
        readIntoBuffer(Integer.BYTES);
        return tmp.getInt(0);
    }

    @Override
    public final float readFloatLE() {
        readIntoBuffer(Float.BYTES);
        return tmp.getFloat(0);
    }

    @Override
    public final double readDoubleLE() {
        readIntoBuffer(Double.BYTES);
        return tmp.getDouble(0);
    }

    @Override
    public final long readLongLE() {
        readIntoBuffer(Long.BYTES);
        return tmp.getLong(0);
    }

    protected abstract void readIntoBuffer(int len);
}
