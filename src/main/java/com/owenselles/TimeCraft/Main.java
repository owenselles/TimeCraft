package com.owenselles.TimeCraft;

import com.owenselles.TimeCraft.Commands.AFK;
import com.owenselles.TimeCraft.Commands.ClaimHelp;
import com.owenselles.TimeCraft.Commands.Discord;
import com.owenselles.TimeCraft.Commands.Ranks;
import com.owenselles.TimeCraft.Events.OnPlayerJoin;
import com.owenselles.TimeCraft.Events.OnPlayerLeave;
import com.owenselles.TimeCraft.Utils.Logger;
import com.owenselles.TimeCraft.Events.OnPlayerChat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable() {

        regCommands();
        regEvents();

        Logger.log(Level.INFO, "Success. Plugin Enabled!");
    }

    private void regCommands(){
        getCommand("claimhelp").setExecutor(new ClaimHelp());
        getCommand("discord").setExecutor(new Discord());
        getCommand("ranks").setExecutor(new Ranks());
        getCommand("afk").setExecutor(new AFK());
    }

    private void regEvents(){
        getServer().getPluginManager().registerEvents(new OnPlayerChat(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeave(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(),this);
    }

    @Override
    public void onDisable() {
        Logger.log(Level.INFO, "Plugin Disabled!");
    }

}
