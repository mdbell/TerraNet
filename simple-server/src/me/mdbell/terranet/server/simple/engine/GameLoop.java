package me.mdbell.terranet.server.simple.engine;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.EventBusFactory;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.server.simple.ServerHandler;
import me.mdbell.terranet.server.simple.engine.events.TickEvent;

@Slf4j
public class GameLoop implements Runnable {

    private static final IEventBus<GameLoop> bus = EventBusFactory.getDefaultFactory().getOrCreate(GameLoop.class);

    public static final long TICKS_PER_SECOND = 60;

    public static final long MS_PER_TICK = 1000 / TICKS_PER_SECOND;

    ServerHandler scene;
    private TickEvent event;
    private long tick = 0;
    private long lastTick = System.currentTimeMillis();

    public GameLoop(ServerHandler scene) {
        this.scene = scene;
        this.event = new TickEvent(bus, this, scene);
    }

    public long getTick(){
        return tick;
    }

    public void run() {
        long lag = System.currentTimeMillis() - lastTick;
        long behind = lag / MS_PER_TICK;
        if (behind > MS_PER_TICK) {
            log.warn("Server is lagging! Missed {} ticks ({} ms)", behind, lag);
        }
        lastTick = System.currentTimeMillis();
        tick++;
        bus().post(event);
    }

    public static IEventBus<GameLoop> bus() {
        return bus;
    }
}
