package me.mdbell.terranet.common.ext;

import lombok.experimental.ExtensionMethod;
import lombok.experimental.UtilityClass;
import me.mdbell.terranet.common.util.NetworkText;

@UtilityClass
@ExtensionMethod({ArrayExtensions.class})
public class StringExtensions {

    public NetworkText toLiteral(String str){
        return NetworkText.builder().text(str).build();
    }

    public NetworkText toFormatted(String format, String... args){
        return toFormatted(format, args.map(NetworkText.class, StringExtensions::toLiteral));
    }

    public NetworkText toFormatted(String format, NetworkText... parts){
        return NetworkText.builder()
                .mode(NetworkText.Mode.FORMAT)
                .text(format)
                .sub(parts)
                .build();
    }
}
