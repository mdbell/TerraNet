package me.mdbell.terranet.common.game.scene;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Chest {
    private int index;
    private int x, y;
    private String name;
}
