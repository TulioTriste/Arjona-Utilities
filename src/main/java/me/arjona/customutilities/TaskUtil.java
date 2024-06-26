package me.arjona.customutilities;

import lombok.experimental.UtilityClass;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

@UtilityClass
public class TaskUtil {


    public void run(JavaPlugin plugin, Runnable runnable) {
        plugin.getServer().getScheduler().runTask(plugin, runnable);
    }

    public void runTimer(JavaPlugin plugin, Runnable runnable, long delay, long timer) {
        plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, delay, timer);
    }

    public void runTimer(JavaPlugin plugin, BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimer(plugin, delay, timer);
    }

    public void runTimerAsync(JavaPlugin plugin, Runnable runnable, long delay, long timer) {
        plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, timer);
    }

    public void runTimerAsync(JavaPlugin plugin, BukkitRunnable runnable, long delay, long timer) {
        runnable.runTaskTimerAsynchronously(plugin, delay, timer);
    }

    public void runLater(JavaPlugin plugin, Runnable runnable, long delay) {
        plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
    }

    public void runLaterAsync(JavaPlugin plugin, Runnable runnable, long delay) {
        try {
            plugin.getServer().getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
        } catch (IllegalStateException e) {
            plugin.getServer().getScheduler().runTaskLater(plugin, runnable, delay);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void runTaskTimerAsynchronously(JavaPlugin plugin, Runnable runnable, int delay) {
        try {
            plugin.getServer().getScheduler().runTaskTimerAsynchronously(plugin, runnable, 20L * delay, 20L * delay);
        } catch (IllegalStateException e) {
            plugin.getServer().getScheduler().runTaskTimer(plugin, runnable, 20L * delay, 20L * delay);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void runAsync(JavaPlugin plugin, Runnable runnable) {
        try {
            plugin.getServer().getScheduler().runTaskAsynchronously(plugin, runnable);
        } catch (IllegalStateException e) {
            plugin.getServer().getScheduler().runTask(plugin, runnable);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}