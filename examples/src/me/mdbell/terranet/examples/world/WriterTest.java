package me.mdbell.terranet.examples.world;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.world.WorldVisitor;
import me.mdbell.terranet.world.reader.WorldReader;
import me.mdbell.terranet.world.tree.WorldNode;
import me.mdbell.terranet.world.util.ProgressListener;
import me.mdbell.terranet.world.util.ProgressWorldVisitor;
import me.mdbell.terranet.world.writer.WorldWriter;

import java.io.FileInputStream;
import java.io.RandomAccessFile;

@Slf4j
public class WriterTest {

    private String fileIn, fileOut;

    public WriterTest(String in, String out){
        this.fileIn = in;
        this.fileOut = out;
    }

    @SneakyThrows
    public void run(){
        Buffer<?> worldBuffer = Buffer.wrap(new RandomAccessFile(fileIn, "r"));
        Buffer<RandomAccessFile> outBuffer = Buffer.wrap(new RandomAccessFile(fileOut, "rw"));
        outBuffer.getBuffer().setLength(0);
        WorldNode world = new WorldNode();

        WorldVisitor visitor = new ProgressWorldVisitor(world){
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
        WorldWriter writer = new WorldWriter(outBuffer);

        world.accept(writer);
    }

    public static void main(String[] args){
        new WriterTest("./local/test.wld", "./local/test_2.wld").run();
    }
}
