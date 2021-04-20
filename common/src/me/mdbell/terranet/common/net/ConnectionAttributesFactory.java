package me.mdbell.terranet.common.net;

public abstract class ConnectionAttributesFactory<T extends ConnectionAttributes> {

    public abstract T newInstance();
}
