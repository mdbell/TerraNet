module me.mdbell.terranet.common {
    exports me.mdbell.terranet;
    exports me.mdbell.terranet.common.net;
    exports me.mdbell.terranet.common.game.messages;
    exports me.mdbell.terranet.common.game.events;
    exports me.mdbell.terranet.common.net.netty;
    exports me.mdbell.terranet.common.util;
    requires me.mdbell.bus;
    requires io.netty.all;
    requires slf4j.api;
    requires lombok;
}