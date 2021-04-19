package me.mdbell.terranet.common.game.messages;

import me.mdbell.terranet.common.io.Buffer;

public final class BufferedMessage extends GameMessage {

    private final Buffer<?> buffer;

    public BufferedMessage(int id, int size){
        this(id, Buffer.allocate(size));
    }

    public BufferedMessage(int id, Buffer<?> buffer) {
        super(id);
        this.buffer = buffer;
    }

    public Buffer<?> buffer() {
        return buffer;
    }

    @Override
    public String toString() {
        return "BufferedMessage{" +
                "id=" + getId() +
                ",buffer=" + buffer +
                '}';
    }
}
