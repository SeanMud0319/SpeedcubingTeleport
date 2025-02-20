package com.nontage.listeners;

import com.nontage.utils.TeleportManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        TeleportManager.removeTpaRequest(e.getPlayer().getUniqueId());
        TeleportManager.removeTpahereRequest(e.getPlayer().getUniqueId());
    }
}
