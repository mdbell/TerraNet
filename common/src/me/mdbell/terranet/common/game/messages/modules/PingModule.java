package me.mdbell.terranet.common.game.messages.modules;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.mdbell.terranet.common.game.messages.NetModuleMessage;
import me.mdbell.terranet.common.util.Vector2;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class PingModule extends NetModuleMessage {
    private final Vector2 position;

    @Override
    protected int getModIdImpl() {
        return MOD_PING;
    }
}
