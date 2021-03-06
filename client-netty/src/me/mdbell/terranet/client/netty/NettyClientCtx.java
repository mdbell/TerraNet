package me.mdbell.terranet.client.netty;

import io.netty.channel.ChannelHandlerContext;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.common.game.messages.GameMessage;

import java.net.SocketAddress;
import java.util.Objects;

final class NettyClientCtx extends ClientCtx {

    private final ChannelHandlerContext ctx;

    public NettyClientCtx(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    @Override
    public SocketAddress getRemoteAddress() {
        return ctx.channel().remoteAddress();
    }

    @Override
    public void send(GameMessage message) {
        Objects.requireNonNull(message);
        ctx.writeAndFlush(message);
    }

    @Override
    public void close() {
        try {
            ctx.close().sync();
        } catch (InterruptedException ignored) {
        }
    }
}
