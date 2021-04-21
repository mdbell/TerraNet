package me.mdbell.terranet.common.game.transcoders;

import me.mdbell.terranet.common.game.messages.*;
import me.mdbell.terranet.common.io.Buffer;
import me.mdbell.terranet.common.util.IOUtil;

public final class DefaultTranscoder extends BufferTranscoder {

    @Override
    public GameMessage decode(int id, int size, Buffer<?> buff) {
        switch (id) {
            case OP_CONNECT:
                return ConnectionMessage.builder()
                        .version(buff.readString())
                        .build();
            case OP_DISCONNECT:
                return DisconnectMessage.builder()
                        .reason(IOUtil.readText(buff))
                        .build();
            case OP_SET_USER_SLOT:
                return UserSlotMessage.builder()
                        .slot(buff.readUnsignedByte())
                        .flag(buff.readBoolean())
                        .build();
            case OP_PLAYER_INFO:
                return PlayerInfoMessage.builder()
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
                        .torches(buff.readUnsignedByte())
                        .build();
            case OP_SET_INVENTORY_SLOT:
                return SlotMessage.builder()
                        .id(buff.readUnsignedByte())
                        .slot(buff.readUnsignedShortLE())
                        .count(buff.readUnsignedShortLE())
                        .prefix(buff.readUnsignedByte())
                        .netId(buff.readUnsignedShortLE())
                        .build();
            case OP_REQUEST_WORLD:
                return WorldDataRequestMessage.builder()
                        .build();
            // TODO opcode 7
            case OP_ESSENTIAL_TILES:
                return EssentialTilesMessage.builder()
                        .x(buff.readIntLE())
                        .y(buff.readIntLE())
                        .build();
            //TODO opcodes 9-15
            case OP_PLAYER_HP:
                return PlayerHealthMessage.builder()
                        .id(buff.readUnsignedByte())
                        .hp(buff.readShortLE())
                        .maxHp(buff.readShortLE())
                        .build();
            //TODO opcodes 17 - 41
            case OP_PLAYER_MANA:
                return PlayerManaMessage.builder()
                        .id(buff.readUnsignedByte())
                        .mana(buff.readShortLE())
                        .maxMana(buff.readShortLE())
                        .build();
            //TODO opcodes 43 - 49
            case OP_UPDATE_BUFFS:
                UpdateBuffsMessage buffs = UpdateBuffsMessage.builder()
                        .id(buff.readByte())
                        .build();
                for (int i = 0; i < MAX_BUFFS; i++) {
                    buffs.setBuff(i, buff.readUnsignedShortLE());
                }
                return buffs;
            //TODO opcodes 51-67
            case OP_UUID:
                return UUIDMessage.builder()
                        .uuid(buff.readString())
                        .build();
            //TODO opcodes 69-255
            default:
                return super.decode(id, size, buff);
        }
    }

    @Override
    public boolean encode(Buffer<?> to, GameMessage message) {
        switch (message.getOpcode()) {
            case OP_CONNECT:
                to.writeString(((ConnectionMessage) message).getVersion());
                return true;
            case OP_DISCONNECT:
                IOUtil.writeText(((DisconnectMessage) message).getReason(), to);
                return true;
            case OP_SET_USER_SLOT:
                to.writeByte(((UserSlotMessage) message).getSlot());
                to.writeBoolean(((UserSlotMessage) message).isFlag());
                return true;
            case OP_PLAYER_INFO:
                PlayerInfoMessage p = (PlayerInfoMessage) message;
                to.writeByte(p.getOpcode());
                to.writeByte(p.getSkin());
                to.writeByte(p.getHair());
                to.writeString(p.getName());
                to.writeByte(p.getHairDye());
                to.writeByte(p.getHideVisual1());
                to.writeByte(p.getHideVisual2());
                to.writeByte(p.getHideMisc());
                IOUtil.writeColor(p.getHairColor(), to);
                IOUtil.writeColor(p.getSkinColor(), to);
                IOUtil.writeColor(p.getEyeColor(), to);
                IOUtil.writeColor(p.getShirtColor(), to);
                IOUtil.writeColor(p.getUnderShirtColor(), to);
                IOUtil.writeColor(p.getPantsColor(), to);
                IOUtil.writeColor(p.getShoesColor(), to);
                to.writeByte(p.getDifficulty());
                to.writeByte(p.getTorches());
                return true;
            case OP_SET_INVENTORY_SLOT:
                SlotMessage slot = (SlotMessage) message;
                to.writeByte(slot.getOpcode());
                to.writeShortLE(slot.getSlot());
                to.writeShortLE(slot.getCount());
                to.writeByte(slot.getPrefix());
                to.writeShortLE(slot.getNetId());
                return true;
            case OP_REQUEST_WORLD:
                return true;
            //TODO opcode 7
            case OP_ESSENTIAL_TILES:
                EssentialTilesMessage ess = (EssentialTilesMessage) message;
                to.writeIntLE(ess.getX());
                to.writeIntLE(ess.getY());
                return true;
            //TODO opcodes 9-15
            case OP_PLAYER_HP:
                PlayerHealthMessage php = (PlayerHealthMessage) message;
                to.writeByte(php.getId());
                to.writeShortLE(php.getHp());
                to.writeShortLE(php.getMaxHp());
                return true;
            //TODO opcodes 17 - 41
            case OP_PLAYER_MANA:
                PlayerManaMessage pmp = (PlayerManaMessage) message;
                to.writeByte(pmp.getId());
                to.writeShortLE(pmp.getMana());
                to.writeShortLE(pmp.getMaxMana());
                return true;
            //TODO opcodes 43 - 49
            case OP_UPDATE_BUFFS:
                UpdateBuffsMessage buffs = (UpdateBuffsMessage) message;
                to.writeByte(buffs.getId());
                for (int i = 0; i < MAX_BUFFS; i++) {
                    to.writeShortLE(buffs.getBuff(i));
                }
                return true;
            //TODO opcodes 51-67
            case OP_UUID:
                to.writeString(((UUIDMessage) message).getUuid());
                return true;
            //TODO opcodes 69-81
            default:
                return super.encode(to, message);
        }
    }
}
