package me.mdbell.terranet.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import me.mdbell.terranet.common.net.MessageTranscoder;
import me.mdbell.terranet.common.util.NetUtil;

import java.util.List;

final class NettyUtil {

    private static final EventLoopGroup INTERNAL_CLIENT_GROUP = new NioEventLoopGroup();

    private NettyUtil() {

    }

    public static Bootstrap createClientBootstrap() {
        return createClientBootstrap(NetUtil.loadTranscoders(false));
    }

    public static Bootstrap createClientBootstrap(List<MessageTranscoder> transcoders) {
        return createClientBootstrap(INTERNAL_CLIENT_GROUP, transcoders);
    }

    public static Bootstrap createClientBootstrap(EventLoopGroup group,
                                                  List<MessageTranscoder> transcoders) {
        return new Bootstrap().group(group)
                .channel(NioSocketChannel.class)
                .handler(new ClientInitializer(transcoders))
                .option(ChannelOption.AUTO_READ, true);
    }

    public static void shutdownGraceFully() {
        INTERNAL_CLIENT_GROUP.shutdownGracefully();
    }
}
