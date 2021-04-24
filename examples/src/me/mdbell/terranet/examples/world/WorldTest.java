package me.mdbell.terranet.examples.world;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.world.WorldReader;
import me.mdbell.terranet.world.WorldVisitor;
import me.mdbell.terranet.world.log.LoggingWorldVisitor;
import me.mdbell.terranet.world.tree.WorldNode;
import me.mdbell.terranet.world.util.ProgressListener;
import me.mdbell.terranet.world.util.ProgressWorldVisitor;

import java.io.FileInputStream;

@Slf4j
public class WorldTest {

    private final String file;

    private WorldTest(String file) {
        this.file = file;
    }

    @SneakyThrows
    public void run() {
        Buffer<?> worldBuffer = Buffer.wrap(new FileInputStream(file));
        //Buffer<?> worldBuffer = Buffer.wrap(new RandomAccessFile(file, "rw"));
        WorldNode world = new WorldNode();

        WorldVisitor visitor = new LoggingWorldVisitor(world);
        visitor = new ProgressWorldVisitor(visitor){
            {
                //for the love of god do not do this, it's terrible.
                setListener(new ProgressListener() {
                    private int percent = -1;

                    @Override
                    public void onProgress(int x, int width) {
                        int p = x * 100 / width;
                        if(p != percent) {
                            percent = p;
                            log.info("Loading Tile Data: {}%", p);
                        }
                    }
                });
            }
        };
        WorldReader reader = new WorldReader(worldBuffer);

        reader.accept(visitor);
    }

    public static void main(String[] args) {
        //new WorldTest("./local/The_Foundation_of_Deathweed.wld").run();
        new WorldTest("./local/Fishing.wld").run();
    }
}
