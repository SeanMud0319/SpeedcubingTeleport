package com.nontage.commands;

import com.nontage.utils.TeleportManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tpaccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if (args.length != 1) {
            player.sendMessage("§cUsage: /tpaccept <player>");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            player.sendMessage("§cPlayer not found!");
            return true;
        }

        if (target == player) {
            player.sendMessage("§cYou can't accept to yourself!");
            return true;
        }

        if (TeleportManager.hasTpaRequest(target.getUniqueId())) {
            TeleportManager.removeTpaRequest(target.getUniqueId());
            player.teleport(target);
            player.sendMessage("§aTeleported to " + target.getName());
        } else {
            player.sendMessage("§cYou don't have any pending teleport requests from " + target.getName());
        }
        return true;
    }
}
