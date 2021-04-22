package me.mdbell.terranet.files;

import lombok.*;

@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Getter
@Setter
public class SharedHeaderNode implements SharedHeaderVisitor {

    private String magic;
    private FileType type;
    private int revision;
    private boolean favorite;

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
}
