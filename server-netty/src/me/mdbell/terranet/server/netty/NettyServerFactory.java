package me.mdbell.terranet.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;

public final class NettyServerFactory extends ServerFactory {
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
