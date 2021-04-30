package me.mdbell.terranet.server.simple.handlers;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.events.IncomingMessageEvent;
import me.mdbell.terranet.server.events.OutgoingMessageEvent;
import me.mdbell.terranet.server.simple.IHandler;
import me.mdbell.terranet.server.simple.ServerHandler;
import me.mdbell.terranet.server.simple.engine.GameLoop;
import me.mdbell.terranet.server.simple.engine.Player;
import me.mdbell.terranet.server.simple.events.TickEvent;

@Slf4j
public class IdleHandler implements IHandler {

    public static final long TICKS_BETWEEN = 120;

    private ServerHandler handler;

    @Override
    public void init(ServerHandler handler) {
        this.handler = handler;
        ConnectionCtx.bus().subscribe(this);
        GameLoop.bus().subscribe(this);
    }

    @Subscribe(priority = 1)
    public void onMessage(OutgoingMessageEvent<Player> event){
        ConnectionCtx<Player> ctx = event.source();
        ctx.attrs().setLastMessageTick(handler.getLoop().getTick());
    }

    @Subscribe
    public void onLoop(TickEvent event){
        long curr = event.source().getTick();
        ConnectionCtx<Player>[] conns = event.value().getPlayers();
        for(ConnectionCtx<Player> ctx : conns){
            if(ctx == null){
                continue;
            }
            long last = ctx.attrs().getLastMessage();

            if(curr - last > TICKS_BETWEEN) {
                handler.ping(ctx);
            }
        }
    }

    @Override
    public void shutdown() {
        ConnectionCtx.bus().unsubscribe(this);
        GameLoop.bus().unsubscribe(this);
    }
}
