package me.mdbell.terranet.client.netty;


import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.common.game.messages.GameMessage;

import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<GameMessage> {

    private final AtomicReference<ClientCtx> client = new AtomicReference<>();

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.debug("Exception in client handler", cause);
        ClientCtx client = this.client.get();
        if (client != null) {
            client.close();
        }
        ctx.close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("New Connection to {}", ctx.channel().remoteAddress());
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
