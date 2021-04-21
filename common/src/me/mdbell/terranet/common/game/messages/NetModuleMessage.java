package me.mdbell.terranet.common.game.messages;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public abstract class NetModuleMessage extends GameMessage {

    protected abstract int getModIdImpl();

    @Override
    public final int getModId() {
        return getModIdImpl();
    }

    @Override
    public final int getOpcode() {
        return OP_LOAD_MODULE;
    }
}
