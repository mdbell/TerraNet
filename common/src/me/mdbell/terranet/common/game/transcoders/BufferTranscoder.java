package me.mdbell.terranet.common.game.transcoders;

import me.mdbell.terranet.common.game.messages.BufferedMessage;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.net.AbstractMessageTranscoder;

public class BufferTranscoder extends AbstractMessageTranscoder {
    @Override
    public GameMessage decode(int id, int size, Buffer<?> in) {
        BufferedMessage msg = new BufferedMessage(id, size);
        msg.getBuffer().writeBytes(in);
        return msg;
    }

    @Override
    public boolean encode(Buffer<?> out, GameMessage message) {
        if (!(message instanceof BufferedMessage)) {
            throw new IllegalStateException("Unsupported Packet Type:" + message.getClass().getName()
                    + " Did you forget to add a case?");
        }
        out.writeBytes(((BufferedMessage) message).getBuffer());
        return true;
    }
}
