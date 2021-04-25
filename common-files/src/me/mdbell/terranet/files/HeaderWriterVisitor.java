package me.mdbell.terranet.files;

import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.files.FileType;
import me.mdbell.terranet.files.SharedHeaderVisitor;

import java.nio.charset.StandardCharsets;

public class HeaderWriterVisitor implements SharedHeaderVisitor {

    private Buffer<?> buffer;

    public HeaderWriterVisitor(Buffer<?> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void visitStart() {

    }

    @Override
    public void visitMagic(String magic) {
        buffer.writeBytes(magic.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void visitType(FileType type) {
        buffer.writeByte(type.ordinal());
    }

    @Override
    public void visitRevision(int revision) {
        buffer.writeIntLE(revision);
    }

    @Override
    public void visitFavorite(boolean favorite) {
        buffer.writeLongLE(favorite ? 1 : 0);
    }

    @Override
    public void visitEnd() {

    }
}
