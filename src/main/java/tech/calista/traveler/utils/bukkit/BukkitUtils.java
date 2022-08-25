package tech.calista.traveler.utils.bukkit;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.title.Title;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import tech.calista.traveler.utils.color.ColorUtils;

import java.time.Duration;

@UtilityClass
public class BukkitUtils {

    public PotionEffect deserializePotionEffect(String string) {
        String[] split = string.split(":");
        PotionEffectType potionEffectType = PotionEffectType.getByName(split[0]);

        if (potionEffectType == null) {
            return null;
        }

        int duration = Integer.parseInt(split[1]) * 20;
        int amplifier = Integer.parseInt(split[2]);

        return new PotionEffect(potionEffectType, duration, amplifier);
    }


    public long ticksToMillis(int ticks) {
        return ticks * 50L;
    }

    public Title getTitle(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        return Title.title(
                ColorUtils.colorize(title),
                ColorUtils.colorize(subtitle),
                Title.Times.times(
                        Duration.ofMillis(ticksToMillis(fadeIn)),
                        Duration.ofMillis(ticksToMillis(stay)),
                        Duration.ofMillis(ticksToMillis(fadeOut))
                )
        );
    }
}
