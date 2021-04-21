package me.mdbell.terranet.common.net;

import me.mdbell.terranet.common.game.messages.GameMessage;
import me.mdbell.terranet.common.io.Buffer;

public abstract class FilteredMessageTranscoder<T extends GameMessage> extends AbstractMessageTranscoder {

    private final int id;

    public FilteredMessageTranscoder(int id) {
        this.id = id;
    }

    @Override
    public final GameMessage decode(int id, int size, Buffer<?> buff) {
        if (id != this.id) {
            return null;
        }
        return filteredDecode(size, buff);
    }

    @Override
    public final boolean encode(Buffer<?> to, GameMessage message) {
        if (message.getOpcode() != this.id) {
            return false;
        }
        return filteredEncode(to, (T) message);
    }

    protected abstract T filteredDecode(int size, Buffer<?> buff);

    protected abstract boolean filteredEncode(Buffer<?> to, T packet);
}
