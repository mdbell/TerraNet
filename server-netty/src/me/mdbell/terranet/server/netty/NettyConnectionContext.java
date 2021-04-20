package me.mdbell.terranet.server.netty;

import io.netty.channel.ChannelHandlerContext;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;

import java.io.IOException;

final class NettyConnectionContext<T extends ConnectionAttributes> extends ConnectionCtx<T> {

    private final ChannelHandlerContext ctx;

    NettyConnectionContext(ServerCtx<T> serverCtx, ChannelHandlerContext ctx) {
        super(serverCtx);
        this.ctx = ctx;
    }

    @Override
    public void send(GameMessage message) {
        ctx.writeAndFlush(message);
    }

    @Override
    public void close() throws IOException {
        try {
            ctx.close().sync();
        } catch (InterruptedException ignored) {
        }
    }
}
