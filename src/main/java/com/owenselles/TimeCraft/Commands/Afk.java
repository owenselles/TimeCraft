package com.owenselles.TimeCraft.Commands;

import com.owenselles.TimeCraft.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Afk implements CommandExecutor {

  public static ArrayList<Player> afk = new ArrayList<>();

  @Override
  public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
    if (cmd.equalsIgnoreCase("afk")) {
      Player p = (Player) sender;
      if (!p.hasPermission("essentials.afk")) {
        p.sendMessage(
            Color.add("&6You dont have permissions to use /afk!"));
        if (afk.contains(p)) {
          Bukkit.getServer().broadcastMessage(p.getName() + " is no longer AFK");
          afk.remove(p);
          return true;
        } else {
          afk.add(p);
          Bukkit.getServer().broadcastMessage(p.getName() + " is now AFK");
          return true;
        }
      }
    }
    return false;
  }
}