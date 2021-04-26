package me.mdbell.terranet.server.simple;

import me.mdbell.terranet.common.net.ConnectionAttributesFactory;

public class SimpleAttributesFactory extends ConnectionAttributesFactory<SimpleAttributes> {
    @Override
    public SimpleAttributes newInstance() {
        return new SimpleAttributes();
    }
}
