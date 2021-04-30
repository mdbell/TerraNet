package me.mdbell.terranet.common.game.scene;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tile implements Cloneable{
    @Builder.Default
    private boolean active = false;
    @Builder.Default
    private int type = 0;
    @Builder.Default
    private int frameX = -1, frameY = -1;
    @Builder.Default
    private int color = 0;
    @Builder.Default
    private int wall = 0, wallColor = 0;
    @Builder.Default
    private LiquidType liquidType = LiquidType.NONE;
    @Builder.Default
    private int liquid = 0;
    @Builder.Default
    private boolean wire = false, wire2 = false, wire3 = false, wire4 = false, halfBrick = false;
    @Builder.Default
    private int slope = 0;
    @Builder.Default
    private boolean actuator = false, inActive = false;

}
