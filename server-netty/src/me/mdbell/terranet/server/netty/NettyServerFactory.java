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
    public ServerCtx newInstance() {
        NettyServerCtx ctx = new NettyServerCtx();
        ServerBootstrap bootstrap = NettyUtil.createServerBootstrap(ctx);
        ctx.setBootstrap(bootstrap);
        return ctx;
    }

    @Override
    public void shutdown() {
        NettyUtil.shutdownGraceFully();
    }
}
