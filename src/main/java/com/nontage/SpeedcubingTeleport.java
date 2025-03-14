package com.nontage;

import com.nontage.commands.tpa;
import com.nontage.commands.tpaccept;
import com.nontage.commands.tpahere;
import com.nontage.commands.tpdeny;
import com.nontage.listeners.PlayerQuit;
import com.nontage.utils.TeleportTimer;
import com.nontage.utils.ServerUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SpeedcubingTeleport extends JavaPlugin {
    public static SpeedcubingTeleport plugin;
    private TeleportTimer timer;

    @Override
    public void onEnable() {
        getLogger().info("SpeedcubingTeleport has been enabled!");
        getLogger().info("Author: Nontage");
        ServerUtils.init();
        plugin = this;
        timer = new TeleportTimer();
        timer.start();
        registerCommands();
        registerListeners();
        getLogger().info("Commands and listeners have been registered!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SpeedcubingTeleport has been disabled!");
        timer.stop();
    }

    private void registerCommands() {
        Objects.requireNonNull(getCommand("tpa")).setExecutor(new tpa());
        Objects.requireNonNull(getCommand("tpaccept")).setExecutor(new tpaccept());
        Objects.requireNonNull(getCommand("tpdeny")).setExecutor(new tpdeny());
        Objects.requireNonNull(getCommand("tpahere")).setExecutor(new tpahere());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerQuit(), this);
    }
}
