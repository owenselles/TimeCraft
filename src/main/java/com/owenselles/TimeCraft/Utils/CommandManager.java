package com.owenselles.TimeCraft.Utils;

import com.owenselles.TimeCraft.Commands.Afk;
import com.owenselles.TimeCraft.Commands.ClaimHelp;
import com.owenselles.TimeCraft.Commands.Discord;
import com.owenselles.TimeCraft.Commands.Ranks;
import com.owenselles.TimeCraft.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class CommandManager implements CommandExecutor, Listener {

    Main main = Main.getPlugin(Main.class);

    private boolean can = false;

    public String afk = "afk";
    public String discord = "discord";
    public String claimhelp = "claimhelp";
    public String ranks = "ranks";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {

        if (main.commands.contains(cmd.getName())){
            if (sender instanceof Player){
                Player player = (Player) sender;
                String worldname = player.getWorld().getName();
                can = Boolean.valueOf(String.valueOf(main.getConfig().get(worldname+"."+cmd.getName())));
                if (!can){
                    player.sendMessage(Color.add(String.valueOf(main.getConfig().get("cant-message"))));
                    return true;
                }else{
                    if (cmd.getName().equalsIgnoreCase(afk)){
                        new Afk().onCommand(sender,cmd,s,args);
                    }else if (cmd.getName().equalsIgnoreCase(discord)){
                        new Discord().onCommand(sender,cmd,s,args);
                    }else if (cmd.getName().equalsIgnoreCase(ranks)){
                        new Ranks().onCommand(sender,cmd,s,args);
                    }else if (cmd.getName().equalsIgnoreCase(claimhelp)){
                        new ClaimHelp().onCommand(sender,cmd,s,args);
                    }
                }
            }/*else{
                sender.sendMessage(ChatColor.DARK_RED + "Only Players can use this command!");
            }*/
        }
        return true;
    }
}