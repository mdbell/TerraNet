package me.mdbell.terranet.common.io;

abstract class TrackedBuffer<T> extends Buffer<T>{
    protected int writeIndex, readIndex;
    protected int writeMark, readMark;

    @Override
    public final int writerIndex() {
        return writeIndex;
    }

    @Override
    public final Buffer<?> writerIndex(int newIndex) {
        this.writeIndex = newIndex;
        return this;
    }

    @Override
    public final Buffer<?> markWriterIndex() {
        writeMark = writeIndex;
        return this;
    }

    @Override
    public final Buffer<?> resetWriterIndex() {
        writeIndex = writeMark;
        return this;
    }


    @Override
    public final int readerIndex() {
        return readIndex;
    }

    @Override
    public final Buffer<?> readerIndex(int newIndex) {
        readIndex = newIndex;
        return this;
    }

    @Override
    public final Buffer<?> markReaderIndex() {
        readMark = readIndex;
        return this;
    }

    @Override
    public final Buffer<?> resetReaderIndex() {
        readIndex = readMark;
        return this;
    }
}
