package me.mdbell.terranet.client.netty;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.common.game.messages.GameMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

public class ClientHandler extends SimpleChannelInboundHandler<GameMessage> {

    private static final Logger logger = LoggerFactory.getLogger(ClientHandler.class);

    private final AtomicReference<ClientCtx> client = new AtomicReference<>();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.debug("Exception in client handler", cause);
        ClientCtx client = this.client.get();
        if (client != null) {
            client.close();
        }
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        logger.info("New Connection to {}", ctx.channel().remoteAddress());
        this.client.set(new NettyClientCtx(ctx));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        ClientCtx client = this.client.get();
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ClientCtx client = this.client.getAndSet(null);
        client.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GameMessage message) {
        ClientCtx client = this.client.get();

        if (client != null) {
            client.receive(message);
        }
    }

    public ClientCtx getContext() {
        return client.get();
    }
}
