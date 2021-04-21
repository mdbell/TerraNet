package me.mdbell.terranet.common.game.messages;

import lombok.Getter;
import lombok.ToString;
import me.mdbell.terranet.common.io.Buffer;

@ToString
public final class BufferedMessage extends GameMessage {

    @Getter
    private final int opcode;

    @Getter
    private final Buffer<?> buffer;

    public BufferedMessage(int id, int size){
        this(id, Buffer.allocate(size));
    }

    public BufferedMessage(int opcode, Buffer<?> buffer) {
        this.opcode = opcode;
        this.buffer = buffer;
    }
}
