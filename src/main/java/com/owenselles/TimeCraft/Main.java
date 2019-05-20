package com.owenselles.TimeCraft;

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

        Bukkit.getPluginCommand("claimhelp").setExecutor(new ClaimHelp());
        Bukkit.getPluginCommand("discord").setExecutor(new Discord());
        Bukkit.getPluginCommand("ranks").setExecutor(new Ranks());
        getServer().getPluginManager().registerEvents(new OnPlayerChat(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(), this);

        Logger.log(Level.INFO, "Success. Plugin Enabled!");
    }

    @Override
    public void onDisable() {
        Logger.log(Level.INFO, "Plugin Disabled!");
    }



}
