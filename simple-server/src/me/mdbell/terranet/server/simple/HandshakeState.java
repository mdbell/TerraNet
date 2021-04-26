package me.mdbell.terranet.server.simple;

public enum HandshakeState {
    NEW, PASSWORD_REQUESTED, ASSIGNED_ID, INFO_SET, UUID_SET, HEALTH_SET, MANA_SET
}
