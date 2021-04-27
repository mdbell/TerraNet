package me.mdbell.terranet.server.simple;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.server.ConnectionCtx;
import me.mdbell.terranet.server.ServerCtx;
import me.mdbell.terranet.server.ServerFactory;
import me.mdbell.terranet.server.simple.engine.GameLoop;
import me.mdbell.terranet.server.simple.engine.Player;
import me.mdbell.terranet.server.simple.handlers.InitialHandshakeHandler;
import me.mdbell.terranet.server.simple.util.WorldUtils;
import me.mdbell.terranet.world.tree.WorldNode;
import me.mdbell.terranet.world.util.ProgressListener;

import java.io.RandomAccessFile;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class SimpleServerBootstrap {


    private final int port;
    private final String world;

    public SimpleServerBootstrap(int port, String world) {
        this.port = port;
        this.world = world;
    }

    public void run() throws Exception {
        log.info("Loading World '{}'...", this.world);
        WorldNode world = loadWorld(this.world);

        log.info("Initializing Server...");
        ServerFactory<Player> factory = ServerFactory.createDefaultFactory();
        ServerCtx<Player> ctx = factory.newInstance();
        ctx.setAttributesFactory(new SimpleAttributesFactory());

        ServerHandler handler = new ServerHandler(16);
        ConnectionCtx.bus().subscribe(handler);

        log.info("Loading event handlers...");
        ConnectionCtx.bus().subscribe(new InitialHandshakeHandler(handler));

        log.info("Setting up main loop...");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        GameLoop loop = new GameLoop(handler);
        executor.scheduleAtFixedRate(loop, 0, GameLoop.MS_PER_TICK, TimeUnit.MILLISECONDS);

        log.info("Starting Server on port {}", port);
        ctx.bind(port);

        log.info("Server is ready!");

        ctx.awaitClose();
        executor.shutdown();
    }

    @SneakyThrows
    private WorldNode loadWorld(String world) {
        Buffer<RandomAccessFile> buffer = Buffer.wrap(new RandomAccessFile(world, "rw"));
        return WorldUtils.loadWorld(buffer, new ProgressListener() {
            private int percent = -1;

            @Override
            public void onProgress(int x, int width) {
                int p = x * 100 / width;
                if (percent != p) {
                    log.info("Loading World Data {}%", p);
                    percent = p;
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        new SimpleServerBootstrap(1337, "./local/test.wld").run();
    }
}
