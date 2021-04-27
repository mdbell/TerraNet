package me.mdbell.terranet.server.simple;

public interface IHandler {

    default String getUid(){
        return getClass().getName();
    }

    default void restart() {

    }

    void init(ServerHandler handler);

    void shutdown();
}
