package com.owenselles.TimeCraft.Commands;

import com.owenselles.TimeCraft.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandManager implements CommandExecutor, Listener {

    Main main = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (cmd.getName().equalsIgnoreCase("afk")){
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.DARK_RED + "Only Players can use this command!");
            } else {
                Player pl = (Player) sender;
                String name = pl.getWorld().getName();
                boolean canafk = Boolean.valueOf(String.valueOf(main.getConfig().get(name+".afk")));
                if (canafk){
                    new Afk().onCommand(sender, cmd, s, args);
                }else{
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&',String.valueOf(main.getConfig().get("cant-afk-message"))));
                }
            }
        }
        return true;
    }
}