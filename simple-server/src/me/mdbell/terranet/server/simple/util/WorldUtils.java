package me.mdbell.terranet.server.simple.util;

import lombok.experimental.UtilityClass;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.world.ReaderException;
import me.mdbell.terranet.world.WorldVisitor;
import me.mdbell.terranet.world.reader.WorldReader;
import me.mdbell.terranet.world.tree.WorldNode;
import me.mdbell.terranet.world.util.ProgressListener;
import me.mdbell.terranet.world.util.ProgressWorldVisitor;

@UtilityClass
public class WorldUtils {

    public WorldNode loadWorld(Buffer<?> buffer) throws ReaderException {
        return loadWorld(buffer, null);
    }

    public WorldNode loadWorld(Buffer<?> buffer, ProgressListener listener) throws ReaderException {
        WorldNode node = new WorldNode();
        WorldReader reader = new WorldReader(buffer);

        WorldVisitor visitor = node;

        if(listener != null){
            ProgressWorldVisitor prog = new ProgressWorldVisitor(visitor);
            prog.setListener(listener);
            visitor = prog;
        }

        reader.accept(visitor);
        return node;
    }
}
