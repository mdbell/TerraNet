package me.mdbell.terranet.client.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.client.ClientFactory;

@Slf4j
public final class NettyClientFactory extends ClientFactory {

    Bootstrap bootstrap = NettyUtil.createClientBootstrap();

    @Override
    public ClientCtx connectImpl(String host, int port) {
        try {
            ChannelFuture f = bootstrap.connect(host, port).sync();
            return f.channel().pipeline().get(ClientHandler.class).getContext();
        } catch (InterruptedException e) {
            log.debug("Exception thrown in connect!", e);
        }
        return null;
    }

    @Override
    public void shutdown() {
        NettyUtil.shutdownGraceFully();
    }
}
