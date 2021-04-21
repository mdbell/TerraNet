package me.mdbell.terranet.common.game.messages;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import me.mdbell.terranet.common.io.Buffer;

@ToString
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public final class BufferedMessage extends GameMessage {

    @Getter
    int opcode;

    @Getter
    Buffer<?> buffer;

    public BufferedMessage(int id, int size){
        this(id, Buffer.allocate(size));
    }

    public BufferedMessage(int opcode, Buffer<?> buffer) {
        this.opcode = opcode;
        this.buffer = buffer;
    }
}
