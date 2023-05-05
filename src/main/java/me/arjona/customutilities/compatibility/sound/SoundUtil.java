package me.arjona.customutilities.compatibility.sound;

import lombok.experimental.UtilityClass;
import me.arjona.customutilities.Logger;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

@UtilityClass
public class SoundUtil {

    public void play(Player player, Sound sound) {
        if (sound == null) {
            Logger.warn("&cSound '" + sound + "' is not valid, please use a valid sound.");
            return;
        }

        try {
            player.playSound(player.getLocation(), sound, 1F, 1F);
        }
        catch (Exception ex) {
            Logger.err("&cSound '" + sound + "' is not valid, please use a valid sound.");
        }
    }

    public void play(Player player, String sound) {
        if (!sound.isEmpty()) {
            play(player, getSound(sound));
        }
    }

    public Sound getSound(String sound) {
        if (sound != null && !sound.isEmpty()) {
            try {
                return Sound.valueOf(sound);
            } catch (Exception e) {
                return null;
            }
        }

        return null;
    }
}
