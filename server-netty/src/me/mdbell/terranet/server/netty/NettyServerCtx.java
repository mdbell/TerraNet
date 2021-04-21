package me.mdbell.terranet.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;

@Slf4j
public final class NettyServerCtx<T extends ConnectionAttributes> extends ServerCtx<T> {

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
            log.warn("Interrupted", e);
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
