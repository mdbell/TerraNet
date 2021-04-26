package me.mdbell.terranet.server.simple.engine;

import lombok.Data;
import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.ext.ArrayExtensions;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.server.simple.data.HandshakeState;
import me.mdbell.terranet.server.simple.data.Item;
import me.mdbell.terranet.server.simple.engine.Tickable;

@Data
@ExtensionMethod({ArrayExtensions.class})
public class Player implements ConnectionAttributes, Tickable {

    HandshakeState handshakeState = HandshakeState.NEW;
    int id;
    String uuid;
    int currentHp, maxHp;
    int currentMana, maxMana;
    final int[] buffs = new int[Opcodes.MAX_BUFFS];
    final Item[] items = new Item[Opcodes.MAX_ITEMS].fill(Item::new);

    public final Item getItem(int slot){
        return items[slot];
    }

    @Override
    public void tick(long tick) {

    }
}
