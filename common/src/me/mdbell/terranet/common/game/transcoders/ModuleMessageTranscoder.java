package me.mdbell.terranet.common.game.transcoders;

import lombok.experimental.ExtensionMethod;
import me.mdbell.terranet.common.game.messages.NetModuleMessage;
import me.mdbell.terranet.common.game.messages.modules.BufferedModule;
import me.mdbell.terranet.common.game.messages.modules.IncomingChatMessage;
import me.mdbell.terranet.common.game.messages.modules.OutgoingChatMessage;
import me.mdbell.terranet.common.game.messages.modules.PingModule;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.net.FilteredMessageTranscoder;
import me.mdbell.terranet.common.ext.BufferExtensions;

@ExtensionMethod({BufferExtensions.class})
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
                            .text(buff.readText())
                            .color(buff.readColor())
                            .build();
                }
            case MOD_PING:
                return PingModule.builder()
                        .position(buff.readVector())
                        .build();
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
                to.writeByte(text.author())
                        .writeText(text.text())
                        .writeColor(text.color());
                return true;
            case MOD_PING:
                PingModule ping = (PingModule) mod;
                to.writeVector(ping.getPosition());
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
