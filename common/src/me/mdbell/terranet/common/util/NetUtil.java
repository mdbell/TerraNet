package me.mdbell.terranet.common.util;

import me.mdbell.terranet.common.net.MessageTranscoder;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public final class NetUtil {

    private NetUtil() {

    }

    public static List<MessageTranscoder> loadTranscoders(boolean isServer) {
        ArrayList<MessageTranscoder> transcoders = new ArrayList<>();

        ServiceLoader<MessageTranscoder> loader = ServiceLoader.load(MessageTranscoder.class);

        loader.forEach(t -> {
            transcoders.add(t);
            t.setServer(isServer);
        });

        transcoders.trimToSize();
        return transcoders;
    }
}
