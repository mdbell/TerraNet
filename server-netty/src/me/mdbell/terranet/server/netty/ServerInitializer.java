package me.mdbell.terranet.server.netty;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import me.mdbell.terranet.common.net.MessageTranscoder;
import me.mdbell.terranet.common.net.netty.BaseInitializer;

import java.util.List;

public final class ServerInitializer extends BaseInitializer {

    private final NettyServerCtx server;

    public ServerInitializer(NettyServerCtx server, List<MessageTranscoder> transcoderList) {
        super(transcoderList);
        this.server = server;
    }

    @Override
    protected void initChannel(SocketChannel Channel) throws Exception {
        super.initChannel(Channel);
        ChannelPipeline pipeline = Channel.pipeline();
        pipeline.addLast(new ServerHandler(server));
    }
}
