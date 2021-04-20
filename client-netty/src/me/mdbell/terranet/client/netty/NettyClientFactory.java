package me.mdbell.terranet.client.netty;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import me.mdbell.terranet.client.ClientCtx;
import me.mdbell.terranet.client.ClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class NettyClientFactory extends ClientFactory {

    Logger logger = LoggerFactory.getLogger(NettyClientFactory.class);

    Bootstrap bootstrap = NettyUtil.createClientBootstrap();

    @Override
    public ClientCtx connectImpl(String host, int port) {
        try {
            ChannelFuture f = bootstrap.connect(host, port).sync();
            return f.channel().pipeline().get(ClientHandler.class).getContext();
        } catch (InterruptedException e) {
            logger.debug("Exception thrown in connect!", e);
        }
        return null;
    }

    @Override
    public void shutdown() {
        NettyUtil.shutdownGraceFully();
    }
}
