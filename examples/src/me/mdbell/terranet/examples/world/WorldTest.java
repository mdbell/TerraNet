package me.mdbell.terranet.examples.world;

import lombok.SneakyThrows;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.world.WorldReader;
import me.mdbell.terranet.world.WorldVisitor;
import me.mdbell.terranet.world.log.LoggingWorldVisitor;
import me.mdbell.terranet.world.tree.WorldNode;

import java.io.RandomAccessFile;

public class WorldTest {

    private final String file;

    private WorldTest(String file){
        this.file = file;
    }

    @SneakyThrows
    public void run(){
        Buffer<RandomAccessFile> worldBuffer = Buffer.wrap(new RandomAccessFile(file, "rw"));
        WorldNode world = new WorldNode();
        WorldVisitor visitor = new LoggingWorldVisitor(world);
        WorldReader reader = new WorldReader(worldBuffer);

        reader.accept(visitor);

        System.out.println(world);
    }

    public static void main(String[] args){
        //new WorldTest("./local/The_Foundation_of_Deathweed.wld").run();
        new WorldTest("./local/Fishing.wld").run();
    }
}
