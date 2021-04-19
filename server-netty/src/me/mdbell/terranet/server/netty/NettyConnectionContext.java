package me.mdbell.terranet.server.netty;

import io.netty.channel.ChannelHandlerContext;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;

final class NettyConnectionContext extends ConnectionCtx {

    private final ChannelHandlerContext ctx;

    NettyConnectionContext(ServerCtx serverCtx, ChannelHandlerContext ctx) {
        super(serverCtx);
        this.ctx = ctx;
    }

    @Override
    public void send(GameMessage message) {
        ctx.writeAndFlush(message);
    }
}
