package me.mdbell.terranet.common.game.events;

import me.mdbell.bus.AbstractBusEvent;
import me.mdbell.bus.IEventBus;
import me.mdbell.terranet.common.game.messages.GameMessage;

public abstract class GameMessageEvent<T> extends AbstractBusEvent<T, GameMessage> {

    public GameMessageEvent(IEventBus<T> bus, T source, GameMessage value) {
        super(bus, source, value);
    }
}
