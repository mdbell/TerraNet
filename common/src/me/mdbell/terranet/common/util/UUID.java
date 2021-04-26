package me.mdbell.terranet.common.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Random;

@EqualsAndHashCode
@Getter
@ToString
public class UUID {

    private static final Random rng = new Random();

    private final byte[] data = new byte[16];

    public static UUID randomUUID() {
        UUID res = new UUID();
        rng.nextBytes(res.getData());
        return res;
    }
}
