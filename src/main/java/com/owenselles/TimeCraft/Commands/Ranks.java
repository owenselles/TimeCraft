package com.owenselles.TimeCraft.Commands;

import com.owenselles.TimeCraft.Utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Ranks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (cmd.equalsIgnoreCase("ranks")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Color.add("&4Error: &cYou are not a player!"));
                return true;
            }
            Player p = (Player) sender;
            p.sendMessage(Color.add("&6You can buy ranks at our store: https://store.timecraft-mc.net/ or by using /buy"));
            return true;
        }
        return false;
    }
}
