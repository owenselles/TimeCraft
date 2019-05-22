package com.owenselles.TimeCraft;

import com.owenselles.TimeCraft.Commands.ClaimHelp;
import com.owenselles.TimeCraft.Commands.Discord;
import com.owenselles.TimeCraft.Commands.Ranks;
import com.owenselles.TimeCraft.Events.OnPlayerChat;
import com.owenselles.TimeCraft.Events.OnPlayerJoin;
import com.owenselles.TimeCraft.Events.OnPlayerLeave;
import com.owenselles.TimeCraft.Utils.Logger;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

  FileConfiguration config = getConfig();

  @Override
  public void onEnable() {
    // Default config options
    config.addDefault("claimhelp", true);
    config.options().copyDefaults(true);
    saveConfig();

    //register commands
    if (config.getBoolean("claimhelp")) {
      Bukkit.getPluginCommand("claimhelp").setExecutor(new ClaimHelp());
    } else {
      Logger.log(Level.INFO, "Claimhelp is disabled in config!!");
    }
    Bukkit.getPluginCommand("discord").setExecutor(new Discord());
    Bukkit.getPluginCommand("ranks").setExecutor(new Ranks());

    // register events
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
