package me.mdbell.terranet.common.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode
@Data
@ToString
@AllArgsConstructor
public class Vector2 {
    private float x;
    private float y;

    public void set(Vector2 position) {
        setX(position.x);
        setY(position.y);
    }
}
