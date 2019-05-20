package com.owenselles.TimeCraft.Commands;

import com.owenselles.TimeCraft.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClaimHelp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (cmd.equalsIgnoreCase("claimhelp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Color.add("&4Error: &cYou are not a player!"));
                return true;
            }
            Player p = (Player) sender;
            p.sendMessage(Color.add("&6Watch this short claiming tutorial on YouTube: https://www.youtube.com/watch?v=VDsjXB-BaE0"));
            return true;
        }
        return false;
    }
}
