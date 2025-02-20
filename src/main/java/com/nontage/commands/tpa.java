package com.nontage.commands;

import com.nontage.utils.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class tpa implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUsage: /tpa <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage("§cPlayer not found!");
            return true;
        }

        if (target == player) {
            player.sendMessage("§cYou can't send request to yourself!");
            return true;
        }

        if (TeleportManager.hasTpahereRequest(player.getUniqueId(), target.getUniqueId())) {
            player.sendMessage("§cYou already have a pending teleport request to " + target.getName());
            return true;
        }

        if (TeleportManager.hasTpaRequest(player.getUniqueId(), target.getUniqueId())) {
            player.sendMessage("§cYou already have a pending teleport request to " + target.getName());
            return true;
        }

        TeleportManager.addTpaRequest(player.getUniqueId(), target.getUniqueId());
        player.sendMessage("§aTeleport request sent to " + target.getName());
        target.sendMessage("§a" + player.getName() + " has requested to teleport to you. Type /tpaccept to accept or /tpdeny to deny.");
        return true;
    }
}
