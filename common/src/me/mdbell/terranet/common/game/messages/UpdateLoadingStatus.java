package me.mdbell.terranet.common.game.messages;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import me.mdbell.terranet.common.util.NetworkText;

@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Data
public class UpdateLoadingStatus extends GameMessage {

    int delta;
    NetworkText text;
    boolean specialFlag1;
    boolean specialFlag2;

    @Override
    public int getOpcode() {
        return OP_UPDATE_LOADING_STATUS;
    }
}
