package me.mdbell.terranet.common.net.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;
import me.mdbell.terranet.common.net.MessageTranscoder;

import java.util.List;

public abstract class BaseInitializer extends ChannelInitializer<SocketChannel> {

    private List<MessageTranscoder> transcoders;

    protected BaseInitializer(List<MessageTranscoder> transcoderList) {
        this.transcoders = transcoderList;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        MessageDecoder decoder = new MessageDecoder(transcoders);
        MessageEncoder encoder = new MessageEncoder(transcoders);
        pipeline.addLast(new ReadTimeoutHandler(10)); //TODO make this timeout configurable
        pipeline.addLast(decoder);
        pipeline.addLast(encoder);
    }
}
