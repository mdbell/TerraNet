package me.mdbell.terranet.server.simple.engine;

import lombok.Data;
import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.Opcodes;
import me.mdbell.terranet.common.ext.ArrayExtensions;
import me.mdbell.terranet.common.game.messages.PlayerInfoMessage;
import me.mdbell.terranet.common.net.ConnectionAttributes;
import me.mdbell.terranet.common.util.Color;
import me.mdbell.terranet.server.simple.data.ConnectionState;
import me.mdbell.terranet.server.simple.data.Item;

@Data
@ExtensionMethod({ArrayExtensions.class})
public class Player implements ConnectionAttributes {

    ConnectionState connectionState = ConnectionState.NEW;

    int id = -1;
    String name;

    String uuid;

    int currentHp, maxHp;
    int currentMana, maxMana;

    Color chatColor = Color.YELLOW;

    final int[] buffs = new int[Opcodes.MAX_BUFFS];
    final Item[] items = new Item[Opcodes.MAX_ITEMS].fill(Item::new);

    public final Item getItem(int slot) {
        return items[slot];
    }

    public void update(PlayerInfoMessage msg) {
        setName(msg.getName());
        //TODO get rest of the info
    }
}
