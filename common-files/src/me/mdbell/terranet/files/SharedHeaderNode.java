package me.mdbell.terranet.files;

import lombok.*;
import me.mdbell.terranet.Opcodes;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class SharedHeaderNode implements SharedHeaderVisitor {

    private String magic = FileConstants.HEADER_MAGIC;
    private FileType type = FileType.NONE;
    private int revision = Opcodes.DEFAULT_REV;
    private boolean favorite = false;

    @Override
    public void visitStart() {

    }

    @Override
    public void visitMagic(String magic) {
        setMagic(magic);
    }

    @Override
    public void visitType(FileType type) {
        setType(type);
    }

    @Override
    public void visitRevision(int revision) {
        setRevision(revision);
    }

    @Override
    public void visitFavorite(boolean favorite) {
        setFavorite(favorite);
    }

    @Override
    public void visitEnd() {

    }

    public void accept(SharedHeaderVisitor visitor) {
        visitor.visitStart();
        visitor.visitMagic(magic);
        visitor.visitType(type);
        visitor.visitRevision(revision);
        visitor.visitFavorite(favorite);
        visitor.visitEnd();
    }
}
