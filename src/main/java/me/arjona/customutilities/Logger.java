package me.arjona.customutilities;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class Logger {

    public void deb(String msg) {
        msg("&7[&aDEBUG&7] " + msg);
    }

    public void log(String msg) {
        msg("&7[&9LOG&7] " + msg);
    }

    public void err(String msg) {
        msg("&7[&4ERROR&7] " + msg);
    }

    public void warn(String msg) {
        msg("&7[&cWARN&7] " + msg);
    }

    private void msg(String msg) {
        Bukkit.getConsoleSender().sendMessage(CC.translate(msg));
    }
}
