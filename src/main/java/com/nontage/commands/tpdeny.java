package com.nontage.commands;

import com.nontage.utils.TeleportManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpdeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }
        if (args.length != 1) {
            sender.sendMessage("§cUsage: /tpdeny <player>");
            return true;
        }

        Player target = sender.getServer().getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage("§cPlayer not found!");
            return true;
        }

        if (target == sender) {
            sender.sendMessage("§cYou can't deny yourself!");
            return true;
        }

        if (TeleportManager.hasTpaRequest(target.getUniqueId(), player.getUniqueId())) {
            TeleportManager.removeTpaRequest(target.getUniqueId());
            sender.sendMessage("§cYou have denied the teleport request from " + target.getName());
            target.sendMessage("§c" + sender.getName() + " has denied your teleport request.");
        } else if (TeleportManager.hasTpahereRequest(target.getUniqueId(), player.getUniqueId())) {
            TeleportManager.removeTpahereRequest(target.getUniqueId());
            sender.sendMessage("§cYou have denied the teleport request from " + target.getName());
            target.sendMessage("§c" + sender.getName() + " has denied your teleport request.");
        } else {
            sender.sendMessage("§cYou don't have any pending teleport requests from " + target.getName());
            return true;
        }
        return true;
    }
}
