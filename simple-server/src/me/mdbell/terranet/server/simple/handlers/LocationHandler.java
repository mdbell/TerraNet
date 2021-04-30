package me.mdbell.terranet.server.simple.handlers;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.Subscribe;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.game.messages.modules.PingModule;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.events.IncomingMessageEvent;
import me.mdbell.terranet.server.simple.IHandler;
import me.mdbell.terranet.server.simple.ServerHandler;
import me.mdbell.terranet.server.simple.engine.Player;

@Slf4j
public class LocationHandler implements IHandler {
    @Override
    public void init(ServerHandler handler) {
        ConnectionCtx.bus().subscribe(this);
    }

    @Subscribe
    public void onMessage(IncomingMessageEvent<Player> event){
        GameMessage msg = event.value();
        if(msg.getModId() == Opcodes.MOD_PING) {
            Player p = event.source().attrs();
            PingModule ping = (PingModule) msg;
            log.debug("Setting location of {} to {}", p.getName(), ping.getPosition());
            p.getPosition().set(ping.getPosition());
        }
    }

    @Override
    public void shutdown() {
        ConnectionCtx.bus().unsubscribe(this);
    }
}
