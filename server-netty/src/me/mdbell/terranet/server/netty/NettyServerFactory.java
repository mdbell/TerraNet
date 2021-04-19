package me.mdbell.terranet.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NettyServerFactory extends ServerFactory {

    private static final Logger logger = LoggerFactory.getLogger(NettyServerFactory.class);

    @Override
    public ServerCtx bind(int port) {
        NettyServerCtx ctx = new NettyServerCtx();
        ServerBootstrap bootstrap = NettyUtil.createServerBootstrap(ctx);
        try {
            ChannelFuture f = bootstrap.bind(port).sync();
            ctx.setChannel(f.channel());
        } catch (InterruptedException e) {
            logger.debug("Exception Thrown", e);
        }
        return ctx;
    }

    @Override
    public void shutdown() {
        NettyUtil.shutdownGraceFully();
    }
}
