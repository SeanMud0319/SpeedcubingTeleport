package com.nontage.utils;

import com.nontage.SpeedcubingTeleport;
import io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler;
import org.bukkit.Bukkit;

public class TeleportTimer {
    public void start() {
        if (ServerUtils.isFolia) {
            GlobalRegionScheduler scheduler = Bukkit.getGlobalRegionScheduler();
            scheduler.runAtFixedRate(SpeedcubingTeleport.plugin, task -> {
                TeleportManager.decreaseExpires();
            }, 1L, 20L);
        } else {
            Bukkit.getScheduler().runTaskTimerAsynchronously(SpeedcubingTeleport.plugin, new Runnable() {
                @Override
                public void run() {
                    TeleportManager.decreaseExpires();
                }
            }, 0, 20);
        }
    }

    public void stop() {
        Bukkit.getScheduler().cancelTasks(SpeedcubingTeleport.plugin);
    }

}
