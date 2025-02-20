package com.nontage.commands;

import com.nontage.utils.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpahere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUsage: /tpahere <player>");
            return true;
        }

        Player target = player.getServer().getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage("§cPlayer not found!");
            return true;
        }

        if (target == player) {
            player.sendMessage("§cYou can't send request to yourself!");
            return true;
        }

        if (TeleportManager.hasTpaRequest(player.getUniqueId(), target.getUniqueId()) ||
                TeleportManager.hasTpaRequest(target.getUniqueId(), player.getUniqueId())) {
            player.sendMessage("§cYou already have a pending teleport request to " + target.getName());
            return true;
        }

        TeleportManager.addTpahereRequest(player.getUniqueId(), target.getUniqueId());
        player.sendMessage("§aTeleport request sent to " + target.getName());
        target.sendMessage("§a" + player.getName() + " has requested to teleport you to them. Type /tpaccept to accept or /tpdeny to deny.");
        return true;
    }
}
