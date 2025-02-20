package com.nontage.utils;

import com.nontage.SpeedcubingTeleport;
import org.bukkit.Bukkit;

public class BukkitTimer {
    public void start() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(SpeedcubingTeleport.plugin, new Runnable() {
            @Override
            public void run() {
                TeleportManager.decreaseExpires();
            }
        }, 0, 20);
    }
    public void stop() {
        Bukkit.getScheduler().cancelTasks(SpeedcubingTeleport.plugin);
    }

}
