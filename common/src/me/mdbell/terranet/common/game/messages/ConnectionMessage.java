package me.mdbell.terranet.common.game.messages;

public final class ConnectionMessage extends GameMessage {

    private String version = DEFAULT_VERSION;

    public ConnectionMessage(){
        super(OP_CONNECT);
    }

    public ConnectionMessage version(String version){
        this.version = version;
        return this;
    }

    public String version(){
        return version;
    }

    @Override
    public String toString() {
        return "ConnectionMessage{" +
                "version='" + version + '\'' +
                '}';
    }
}
