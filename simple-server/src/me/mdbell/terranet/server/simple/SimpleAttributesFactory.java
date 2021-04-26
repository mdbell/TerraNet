package me.mdbell.terranet.server.simple;

import me.mdbell.terranet.common.net.ConnectionAttributesFactory;
import me.mdbell.terranet.server.simple.engine.Player;

public class SimpleAttributesFactory extends ConnectionAttributesFactory<Player> {
    @Override
    public Player newInstance() {
        return new Player();
    }
}
