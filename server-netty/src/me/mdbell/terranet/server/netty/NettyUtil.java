package me.mdbell.terranet.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import me.mdbell.terranet.common.net.MessageTranscoder;
import me.mdbell.terranet.common.util.NetUtil;

import java.util.List;

public final class NettyUtil {
    private static final EventLoopGroup INTERNAL_SERVER_BOSS_GROUP = new NioEventLoopGroup();
    private static final EventLoopGroup INTERNAL_SERVER_WORKER_GROUP = new NioEventLoopGroup();

    private NettyUtil() {

    }

    public static ServerBootstrap createServerBootstrap(NettyServerCtx server){
        return createServerBootstrap(server, NetUtil.loadTranscoders(true));
    }

    public static ServerBootstrap createServerBootstrap(NettyServerCtx server, List<MessageTranscoder> transcoders) {
        return createServerBootstrap(INTERNAL_SERVER_BOSS_GROUP, INTERNAL_SERVER_WORKER_GROUP, server, transcoders);
    }

    public static ServerBootstrap createServerBootstrap(EventLoopGroup bossGroup,
                                                        EventLoopGroup workerGroup,
                                                        NettyServerCtx server,
                                                        List<MessageTranscoder> transcoders) {
        return new ServerBootstrap().group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ServerInitializer(server, transcoders))
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    public static void shutdownGraceFully() {
        INTERNAL_SERVER_BOSS_GROUP.shutdownGracefully();
        INTERNAL_SERVER_WORKER_GROUP.shutdownGracefully();
    }
}
