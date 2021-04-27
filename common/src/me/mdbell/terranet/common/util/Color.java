package me.mdbell.terranet.common.util;

import lombok.Data;
@Data
public final class Color {

    private final int red;
    private final int green;
    private final int blue;

    public static final Color RED = new Color(255, 0, 0);
    public static final Color GREEN = new Color(0, 255, 0);
    public static final Color BLUE = new Color(0, 0, 255);

    public static final Color YELLOW = new Color(255, 255, 0);

    public static final Color BLACK = new Color(0, 0, 0);
    public static final Color WHITE = new Color(255, 255, 255);
}
