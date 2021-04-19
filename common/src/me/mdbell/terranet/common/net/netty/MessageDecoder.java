package me.mdbell.terranet.common.net.netty;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.net.MessageTranscoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class MessageDecoder extends ByteToMessageDecoder implements Opcodes {

    private static final Logger logger = LoggerFactory.getLogger(MessageDecoder.class);

    private final List<MessageTranscoder> transcoders;

    public MessageDecoder(List<MessageTranscoder> transcoders) {
        this.transcoders = transcoders;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < MESSAGE_HEADER_SIZE) {
            return;
        }

        //read the header
        in.markReaderIndex();
        int len = in.readUnsignedShortLE();
        int op = in.readUnsignedByte();

        len -= MESSAGE_HEADER_SIZE;

        if (in.readableBytes() < len) {
            in.resetReaderIndex();
            return;
        }
        Buffer<ByteBuf> buffer = Buffer.wrap(in);
        int pos = in.readerIndex();
        GameMessage message = decode(op, len, buffer);
        if (message == null) {
            throw new IllegalStateException("Unable to read message! op:" + op + " len:" + len);
        }
        int readSize = in.readerIndex() - pos;
        if (readSize != len) {
            throw new IllegalStateException
                    (String.format("Trailing bytes at end of message %d Expected size:%d Read:%d Message:%s",
                            op, len, readSize, message));
        }
        logger.debug("Decoded Message: {} Size: {}", message, readSize + MESSAGE_HEADER_SIZE);
        out.add(message);
    }

    private GameMessage decode(int id, int size, Buffer<?> in) {
        for (MessageTranscoder t : transcoders) {
            in.markReaderIndex();
            GameMessage packet = t.decode(id, size, in);
            if (packet != null) {
                return packet;
            } else {
                in.resetReaderIndex();
            }
        }
        return null;
    }
}
