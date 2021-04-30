package me.mdbell.terranet.examples.world;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.metadata.TileMetadata;
import me.mdbell.terranet.metadata.TileMetadataFactory;
import me.mdbell.terranet.world.reader.WorldReader;
import me.mdbell.terranet.world.tree.WorldNode;
import me.mdbell.terranet.world.util.CountingWorldVisitor;

import java.io.FileInputStream;

@Slf4j
public class TileCounter {

    private final String file;

    private TileCounter(String file) {
        this.file = file;
    }

    @SneakyThrows
    public void run() {
        Buffer<?> worldBuffer = Buffer.wrap(new FileInputStream(file));
        WorldReader reader = new WorldReader(worldBuffer);

        WorldNode world = new WorldNode();

        //WorldVisitor visitor = new LoggingWorldVisitor(world);
        CountingWorldVisitor visitor = new CountingWorldVisitor(world);

        reader.accept(visitor);

        TileMetadata[] metadata = TileMetadataFactory.getDefaultFactory().getMetadata();
        for (int i = 0; i < metadata.length; i++) {
            int id = metadata[i].id;
            int count = visitor.getCount(id);
            String name = metadata[i].name;
            if(count != 0) {
                log.info("Tile {} ({}) - Total:{}", name, id, count);
            }
        }
    }

    public static void main(String[] args) {
        new TileCounter("./local/Fishing.wld").run();
    }
}
