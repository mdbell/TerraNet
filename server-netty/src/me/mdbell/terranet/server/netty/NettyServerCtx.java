package me.mdbell.terranet.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NettyServerCtx<T extends ConnectionAttributes> extends ServerCtx<T> {

    private static final Logger logger = LoggerFactory.getLogger(NettyServerCtx.class);

    private ServerBootstrap bootstrap;
    private Channel channel;

    NettyServerCtx() {

    }

    ConnectionCtx<T> newInstance(ChannelHandlerContext ctx) {
        return new NettyConnectionContext<>(this, ctx);
    }

    @Override
    public void bind(int port) {
        try {
            this.channel = bootstrap.bind(port).sync().channel();
        } catch (InterruptedException e) {
            logger.warn("Intterrupted", e);
        }
    }

    @Override
    public void awaitClose() throws InterruptedException {
        channel.closeFuture().sync();
    }

    final void setBootstrap(ServerBootstrap bootstrap) {
        this.bootstrap = bootstrap;
    }
}
