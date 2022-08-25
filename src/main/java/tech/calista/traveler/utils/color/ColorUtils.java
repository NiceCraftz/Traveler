package tech.calista.traveler.utils.color;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

@UtilityClass
public class ColorUtils {
    public Component colorize(String string) {
        return MiniMessage.miniMessage().deserialize(string);
    }
}
