package me.mdbell.terranet.common.io;

import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;
import me.mdbell.terranet.common.util.Tuple;

import java.io.DataInput;
import java.io.DataOutput;
import java.nio.ByteBuffer;

class TupleBuffer extends AbstractBuffer<Tuple<DataInput, DataOutput>> {

    TupleBuffer(DataInput in, DataOutput out){
        super(new Tuple<>(in, out));
    }

    private DataInput in() {
        return buffer.getFirst();
    }

    private DataOutput out() {
        return buffer.getSecond();
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeBoolean(boolean value) {
        out().writeBoolean(value);
        writeIndex++;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeByte(int value) {
        out().writeByte(value);
        writeIndex++;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeShort(int value) {
        out().writeShort(value);
        writeIndex += Short.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeInt(int value) {
        out().writeInt(value);
        writeIndex += Integer.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeFloat(float value) {
        out().writeFloat(value);
        writeIndex += Float.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeLong(long value) {
        out().writeLong(value);
        writeIndex += Long.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeDouble(double value) {
        out().writeDouble(value);
        writeIndex += Double.BYTES;
        return this;
    }

    @SneakyThrows
    @Override
    public Buffer<?> writeBytes(byte[] bytes) {
        out().write(bytes);
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
        return out() != null;
    }

    @Override
    public Buffer<?> writerIndex(int newIndex) {
        throw new UnsupportedOperationException("Unable to set index in " + getClass().getName());
    }

    @Override
    public Buffer<?> markWriterIndex() {
        throw new UnsupportedOperationException("Unable to set mark index in " + getClass().getName());
    }

    @Override
    public Buffer<?> resetWriterIndex() {
        throw new UnsupportedOperationException("Unable to set index in " + getClass().getName());
    }

    @SneakyThrows
    @Override
    public byte readByte() {
        readIndex++;
        return in().readByte();
    }

    @SneakyThrows
    @Override
    public int readUnsignedByte() {
        readIndex++;
        return in().readUnsignedByte();
    }

    @SneakyThrows
    @Override
    public short readShort() {
        readIndex += Short.BYTES;
        return in().readShort();
    }

    @SneakyThrows
    @Override
    public int readInt() {
        readIndex += Integer.BYTES;
        return in().readInt();
    }

    @SneakyThrows
    @Override
    public float readFloat() {
        readIndex += Float.BYTES;
        return in().readFloat();
    }

    @SneakyThrows
    @Override
    public double readDouble() {
        readIndex += Double.BYTES;
        return in().readDouble();
    }

    @SneakyThrows
    @Override
    public long readLong() {
        readIndex += Long.BYTES;
        return in().readLong();
    }

    @SneakyThrows
    @Override
    public Buffer<?> readBytes(byte[] data) {
        readIndex += data.length;
        in().readFully(data);
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
        readIndex++;
        return in().readBoolean();
    }

    @SneakyThrows
    @Override
    public Buffer<?> readerIndex(int newIndex) {
        if(newIndex >= readIndex){
            in().skipBytes(newIndex - readIndex);
            readIndex = newIndex;
            return this;
        }
        throw new UnsupportedOperationException("Can't rewind DataInput");
    }

    @Override
    public Buffer<?> markReaderIndex() {
        throw new UnsupportedOperationException("Can't mark DataInput");
    }

    @Override
    public Buffer<?> resetReaderIndex() {
        throw new UnsupportedOperationException("Can't mark DataInput");
    }

    @SneakyThrows
    @Override
    protected Buffer<?> writeBuffer(int len) {
        out().write(tmp.array(), 0, len);
        writeIndex += len;
        return this;
    }

    @SneakyThrows
    @Override
    protected void readIntoBuffer(int len) {
        readIndex += len;
        in().readFully(tmp.array(), 0, len);
    }
}
