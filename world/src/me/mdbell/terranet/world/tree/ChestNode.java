package me.mdbell.terranet.world.tree;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import me.mdbell.terranet.world.ChestVisitor;
import me.mdbell.terranet.world.ItemVisitor;

import java.util.LinkedList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class ChestNode implements ChestVisitor {
    private int x, y;
    private String name;
    private final List<ItemNode> items = new LinkedList<>();

    @Override
    public void visitStart() {

    }

    @Override
    public void visitLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void visitName(String name) {
        this.name = name;
    }

    @Override
    public ItemVisitor visitItem() {
        ItemNode node = new ItemNode();
        items.add(node);
        return node;
    }

    @Override
    public void visitEnd() {

    }
}
