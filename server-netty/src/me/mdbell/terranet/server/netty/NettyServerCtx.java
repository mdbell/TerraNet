package me.mdbell.terranet.server.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;

public final class NettyServerCtx extends ServerCtx {

    private Channel channel;

    NettyServerCtx(){

    }

    void setChannel(Channel channel) {
        this.channel = channel;
    }

    ConnectionCtx newInstance(ChannelHandlerContext ctx) {
        return new NettyConnectionContext(this, ctx);
    }

    @Override
    public void awaitClose() throws InterruptedException {
        channel.closeFuture().sync();
    }
}
