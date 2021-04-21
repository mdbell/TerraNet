package me.mdbell.terranet.common.game.messages.modules;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.mdbell.terranet.common.game.messages.NetModuleMessage;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class IncomingChatMessage extends NetModuleMessage {

    private String command, message;

    @Override
    protected int getModIdImpl() {
        return MOD_TEXT;
    }
}
