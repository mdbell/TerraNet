package me.mdbell.terranet.common.net.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.net.MessageTranscoder;

import java.util.List;

@Slf4j
public class MessageEncoder extends MessageToByteEncoder<GameMessage> implements Opcodes {

    private final List<MessageTranscoder> transcoders;


    public MessageEncoder(List<MessageTranscoder> transcoders) {
        this.transcoders = transcoders;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, GameMessage message, ByteBuf n) {
        Buffer<ByteBuf> out = Buffer.wrap(n);
        int startIndex = out.writerIndex();
        out.writeShortLE(0);
        out.writeByte(message.getOpcode());

        boolean written = false;
        for (MessageTranscoder t : transcoders) {
            if (t.encode(out, message)) {
                written = true;
                break;
            }
        }

        if (!written) {
            throw new IllegalStateException("Unable to encode message:" + message);
        }

        int endIndex = out.writerIndex();
        int length = endIndex - startIndex;
        out.writerIndex(startIndex); // reset to the start of the message
        out.writeShortLE(length); // write the length
        out.writerIndex(endIndex); //go back to the end of the message

        log.debug("Encoded Message: {} Size: {}", message, length);
    }
}
