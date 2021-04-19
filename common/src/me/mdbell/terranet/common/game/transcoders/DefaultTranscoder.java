package me.mdbell.terranet.common.game.transcoders;

import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.util.IOUtil;
import me.mdbell.terranet.common.game.messages.*;

public final class DefaultTranscoder extends BufferTranscoder {

    @Override
    public GameMessage decode(int id, int size, Buffer<?> buff) {
        switch (id) {
            case OP_CONNECT:
                return new ConnectionMessage().version(IOUtil.readString(buff));
            case OP_DISCONNECT:
                return new DisconnectMessage().reason(IOUtil.readText(buff));
            case OP_SET_USER_SLOT:
                return new UserSlotMessage().slot(buff.readUnsignedByte())
                        .flag(buff.readBoolean());
            case OP_PLAYER_INFO:
                return new PlayerInfoMessage()
                        .id(buff.readUnsignedByte())
                        .skin(buff.readUnsignedByte())
                        .hair(buff.readUnsignedByte())
                        .name(IOUtil.readString(buff))
                        .hairDye(buff.readUnsignedByte())
                        .hideVisual1(buff.readUnsignedByte())
                        .hideVisual2(buff.readUnsignedByte())
                        .hideMisc(buff.readUnsignedByte())
                        .hairColor(IOUtil.readColor(buff))
                        .skinColor(IOUtil.readColor(buff))
                        .eyeColor(IOUtil.readColor(buff))
                        .shirtColor(IOUtil.readColor(buff))
                        .underShirtColor(IOUtil.readColor(buff))
                        .pantsColor(IOUtil.readColor(buff))
                        .shoesColor(IOUtil.readColor(buff))
                        .difficulty(buff.readUnsignedByte())
                        .torches(buff.readUnsignedByte());
            case OP_SET_INVENTORY_SLOT:
                return new SlotMessage().id(buff.readUnsignedByte())
                        .slot(buff.readUnsignedShortLE())
                        .count(buff.readUnsignedShortLE())
                        .prefix(buff.readUnsignedByte())
                        .netId(buff.readUnsignedShortLE());
            case OP_REQUEST_WORLD:
                return new WorldDataRequestMessage();
            // TODO opcode 7
            case OP_ESSENTIAL_TILES:
                return new EssentialTilesMessage()
                        .x(buff.readIntLE())
                        .y(buff.readIntLE());
            //TODO opcodes 9-15
            case OP_PLAYER_HP:
                return new PlayerHealthMessage()
                        .id(buff.readUnsignedByte())
                        .hp(buff.readShortLE())
                        .maxHp(buff.readShortLE());
            //TODO opcodes 17 - 41
            case OP_PLAYER_MANA:
                return new PlayerManaMessage()
                        .id(buff.readUnsignedByte())
                        .mana(buff.readShortLE())
                        .maxMana(buff.readShortLE());
            //TODO opcodes 43 - 49
            case OP_UPDATE_BUFFS:
                UpdateBuffsMessage buffs = new UpdateBuffsMessage().id(buff.readByte());
                for (int i = 0; i < MAX_BUFFS; i++) {
                    buffs.buff(i, buff.readUnsignedShortLE());
                }
                return buffs;
            //TODO opcodes 51-67
            case OP_UUID:
                return new UUIDMessage().uuid(IOUtil.readString(buff));
            //TODO opcodes 69-255
            default:
                return super.decode(id, size, buff);
        }
    }

    @Override
    public boolean encode(Buffer<?> to, GameMessage message) {
        switch (message.getId()) {
            case OP_CONNECT:
                to.writeString(((ConnectionMessage) message).version());
                return true;
            case OP_DISCONNECT:
                IOUtil.writeText(((DisconnectMessage) message).reason(), to);
                return true;
            case OP_SET_USER_SLOT:
                to.writeByte(((UserSlotMessage) message).slot());
                to.writeBoolean(((UserSlotMessage) message).flag());
                return true;
            case OP_PLAYER_INFO:
                PlayerInfoMessage p = (PlayerInfoMessage) message;
                to.writeByte(p.id());
                to.writeByte(p.skin());
                to.writeByte(p.hair());
                to.writeString(p.name());
                to.writeByte(p.hairDye());
                to.writeByte(p.hideVisual1());
                to.writeByte(p.hideVisual2());
                to.writeByte(p.hideMisc());
                IOUtil.writeColor(p.hairColor(), to);
                IOUtil.writeColor(p.skinColor(), to);
                IOUtil.writeColor(p.eyeColor(), to);
                IOUtil.writeColor(p.shirtColor(), to);
                IOUtil.writeColor(p.underShirtColor(), to);
                IOUtil.writeColor(p.pantsColor(), to);
                IOUtil.writeColor(p.shoesColor(), to);
                to.writeByte(p.difficulty());
                to.writeByte(p.torches());
                return true;
            case OP_SET_INVENTORY_SLOT:
                SlotMessage slot = (SlotMessage) message;
                to.writeByte(slot.id());
                to.writeShortLE(slot.slot());
                to.writeShortLE(slot.count());
                to.writeByte(slot.prefix());
                to.writeShortLE(slot.netId());
                return true;
            case OP_REQUEST_WORLD:
                return true;
            //TODO opcode 7
            case OP_ESSENTIAL_TILES:
                EssentialTilesMessage ess = (EssentialTilesMessage) message;
                to.writeIntLE(ess.x());
                to.writeIntLE(ess.y());
                return true;
            //TODO opcodes 9-15
            case OP_PLAYER_HP:
                PlayerHealthMessage php = (PlayerHealthMessage) message;
                to.writeByte(php.id());
                to.writeShortLE(php.hp());
                to.writeShortLE(php.maxHp());
                return true;
            //TODO opcodes 17 - 41
            case OP_PLAYER_MANA:
                PlayerManaMessage pmp = (PlayerManaMessage) message;
                to.writeByte(pmp.id());
                to.writeShortLE(pmp.mana());
                to.writeShortLE(pmp.maxMana());
                return true;
            //TODO opcodes 43 - 49
            case OP_UPDATE_BUFFS:
                UpdateBuffsMessage buffs = (UpdateBuffsMessage) message;
                to.writeByte(buffs.id());
                for (int i = 0; i < MAX_BUFFS; i++) {
                    to.writeShortLE(buffs.buff(i));
                }
                return true;
            //TODO opcodes 51-67
            case OP_UUID:
                to.writeString(((UUIDMessage) message).uuid());
                return true;
            //TODO opcodes 69-81
            default:
                return super.encode(to, message);
        }
    }
}
