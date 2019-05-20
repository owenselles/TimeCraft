package com.owenselles.TimeCraft.Commands;

import com.owenselles.TimeCraft.Utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Afk implements CommandExecutor {
    ArrayList<Player> afk = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String cmd, String[] args) {
        if (cmd.equalsIgnoreCase("afk")) {
            Player p = (Player) sender;
            if (!p.hasPermission ("essentials.afk")){
                if(afk.contains(p)){
                    getServer broadcast (p.getName()+" is now AFK");
                    p.sendMessage("You dont have permission to afk longer, you'll get kicked in 10minutes");
                    afk.add(p);
                }else{
                    afk.remove(p);
                    getServer broadcast (p.getName()+" is no longer AFK");
                }
            }
        }
    }
        }
        return false;
    }
}