package me.mdbell.terranet.client.netty;

import io.netty.channel.socket.SocketChannel;
import me.mdbell.terranet.common.net.netty.BaseInitializer;
import me.mdbell.terranet.common.net.MessageTranscoder;

import java.util.List;

public class ClientInitializer extends BaseInitializer {

    public ClientInitializer(List<MessageTranscoder> transcoderList) {
        super(transcoderList);
    }

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        super.initChannel(channel);
        channel.pipeline().addLast(new ClientHandler());
    }
}
