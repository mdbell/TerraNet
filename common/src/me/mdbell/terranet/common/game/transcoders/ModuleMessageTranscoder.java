package me.mdbell.terranet.common.game.transcoders;

import me.mdbell.terranet.common.game.messages.NetModuleMessage;
import me.mdbell.terranet.common.game.messages.modules.BufferedModule;
import me.mdbell.terranet.common.game.messages.modules.IncomingChatMessage;
import me.mdbell.terranet.common.game.messages.modules.OutgoingChatMessage;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.net.FilteredMessageTranscoder;
import me.mdbell.terranet.common.util.IOUtil;

public class ModuleMessageTranscoder extends FilteredMessageTranscoder<NetModuleMessage> {

    public ModuleMessageTranscoder() {
        super(OP_LOAD_MODULE);
    }

    @Override
    protected NetModuleMessage filteredDecode(int size, Buffer<?> buff) {
        int modId = buff.readShortLE();
        size -= 2;
        switch (modId) {
            case MOD_TEXT:
                if (this.isServer()) {
                    return IncomingChatMessage.builder()
                            .command(buff.readString())
                            .message(buff.readString())
                            .build();
                } else {
                    return OutgoingChatMessage.builder()
                            .author(buff.readByte())
                            .text(IOUtil.readText(buff))
                            .color(IOUtil.readColor(buff))
                            .build();
                }
            default:
                BufferedModule mod = new BufferedModule(modId, size);
                buff.readBytes(mod.buffer());
                return mod;
        }
    }

    @Override
    protected boolean filteredEncode(Buffer<?> to, NetModuleMessage mod) {
        to.markWriterIndex();
        int modId = mod.getModId();
        to.writeShortLE(modId);
        switch (modId) {
            case MOD_TEXT:
                if (mod instanceof IncomingChatMessage) {
                    IncomingChatMessage msg = (IncomingChatMessage) mod;
                    to.writeString(msg.getCommand());
                    to.writeString(msg.getMessage());
                    return true;
                }
                OutgoingChatMessage text = (OutgoingChatMessage) mod;
                to.writeByte(text.author());
                IOUtil.writeText(text.text(), to);
                IOUtil.writeColor(text.color(), to);
                return true;
            default:
                if (!(mod instanceof BufferedModule)) {
                    to.resetWriterIndex();
                    throw new IllegalStateException("Unsupported Module Type:" + mod.getClass().getName()
                            + " Did you forget to add a case?");
                }
                to.writeBytes(((BufferedModule) mod).buffer());
                return true;
        }
    }
}
