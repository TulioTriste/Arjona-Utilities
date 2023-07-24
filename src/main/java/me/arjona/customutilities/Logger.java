package me.arjona.customutilities;

import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

@UtilityClass
public class Logger {

    public void deb(String msg) {
        msg("&7[&2DEBUG&7] &a" + msg);
    }

    public void log(String msg) {
        msg("&7[&9LOG&7] &3" + msg);
    }

    public void err(String msg) {
        msg("&7[&4ERROR&7] &c" + msg);
    }

    public void warn(String msg) {
        msg("&7[&cWARN&7] &c" + msg);
    }

    private void msg(String msg) {
        Bukkit.getConsoleSender().sendMessage(CC.translate(msg));
    }
}
