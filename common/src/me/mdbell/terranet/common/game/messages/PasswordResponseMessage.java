package me.mdbell.terranet.common.game.messages;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class PasswordResponseMessage extends GameMessage{

    String password;

    @Override
    public int getOpcode() {
        return OP_PASSWORD_RESPONSE;
    }
}
