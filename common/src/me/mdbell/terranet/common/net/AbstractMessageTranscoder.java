package me.mdbell.terranet.common.net;

public abstract class AbstractMessageTranscoder implements MessageTranscoder{

    private boolean server = false;

    @Override
    public final boolean isServer() {
        return this.server;
    }

    @Override
    public final void setServer(boolean isServer) {
        this.server = isServer;
    }
}
