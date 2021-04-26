package me.mdbell.terranet.server.simple;

import lombok.Data;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.net.ConnectionAttributes;

@Data
public class SimpleAttributes implements ConnectionAttributes {

    HandshakeState handshakeState = HandshakeState.NEW;
    int id;
    String uuid;
    int currentHp, maxHp;
    int currentMana, maxMana;
    final byte[] buffs = new byte[Opcodes.MAX_BUFFS];
}
