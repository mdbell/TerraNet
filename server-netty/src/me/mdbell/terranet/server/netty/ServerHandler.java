package me.mdbell.terranet.server.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.common.game.messages.GameMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServerHandler extends SimpleChannelInboundHandler<GameMessage> {

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private static final AttributeKey<ConnectionCtx> CONNECTION_CTX = AttributeKey.valueOf(ServerHandler.class, "conn");

    private final NettyServerCtx server;

    public ServerHandler(NettyServerCtx server) {
        this.server = server;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.debug("Exception in server handler", cause);
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) {
        logger.debug("Connection {} registered", ctx.channel().remoteAddress());
        ConnectionCtx conn = server.newInstance(ctx);
        ctx.channel().attr(CONNECTION_CTX).set(conn);
        server.register(conn);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) {
        logger.debug("Connection {} unregistered", ctx.channel().remoteAddress());
        ConnectionCtx conn = ctx.channel().attr(CONNECTION_CTX).getAndSet(null);
        server.deregister(conn);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GameMessage message) {
        ConnectionCtx conn = ctx.channel().attr(CONNECTION_CTX).get();

        if (conn == null) {
            ctx.close();
            return;
        }
        conn.receive(message);
    }
}
