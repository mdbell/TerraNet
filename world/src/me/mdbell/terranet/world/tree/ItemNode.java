package me.mdbell.terranet.world.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.mdbell.terranet.world.ItemVisitor;

@Getter
@EqualsAndHashCode
@ToString
public class ItemNode implements ItemVisitor {
    private int id;
    private int count;
    private int prefix;

    @Override
    public void visitStart() {

    }

    @Override
    public void visitId(int id) {
        this.id = id;
    }

    @Override
    public void visitCount(int count) {
        this.count = count;
    }

    @Override
    public void visitPrefix(int prefix) {
        this.prefix = prefix;
    }

    @Override
    public void visitEnd() {

    }
}
