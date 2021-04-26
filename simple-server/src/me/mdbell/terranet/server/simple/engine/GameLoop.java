package me.mdbell.terranet.server.simple.engine;

import lombok.extern.slf4j.Slf4j;
import me.mdbell.bus.EventBusFactory;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.server.simple.engine.events.TickEvent;

@Slf4j
public class GameLoop implements Runnable {

    private static final IEventBus<GameLoop> bus = EventBusFactory.getDefaultFactory().getOrCreate(GameLoop.class);

    public static final long TICKS_PER_SECOND = 60;

    public static final long MS_PER_TICK = 1000 / TICKS_PER_SECOND;

    Tickable scene;
    private long tick = 0;
    private long lastTick = System.currentTimeMillis();

    public GameLoop(Tickable scene) {
        this.scene = scene;
    }

    public void run() {
        long lag = System.currentTimeMillis() - lastTick - MS_PER_TICK;
        long behind = lag / MS_PER_TICK;
        if (lag > MS_PER_TICK) {
            log.warn("Server is lagging! Missed {} ticks ({} ms)", behind, lag);
        }
        scene.tick(++tick);
        bus().post(new TickEvent(bus, this, tick));
        lastTick = System.currentTimeMillis();
    }

    public static IEventBus<GameLoop> bus() {
        return bus;
    }
}
