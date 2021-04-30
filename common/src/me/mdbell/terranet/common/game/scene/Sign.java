package me.mdbell.terranet.common.game.scene;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sign {
    private int id;
    private int x, y;
    private String text;
}
