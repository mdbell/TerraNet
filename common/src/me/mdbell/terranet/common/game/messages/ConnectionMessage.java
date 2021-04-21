package me.mdbell.terranet.common.game.messages;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public final class ConnectionMessage extends GameMessage {

    String version;

    @Override
    public int getOpcode() {
        return OP_CONNECT;
    }
}
