package me.mdbell.terranet.common.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
public final class Tuple<F, S> {

    private final F first;
    private final S second;

    public Tuple(F first, S second) {
        this.first = first;
        this.second = second;
    }
}
