package com.owenselles.TimeCraft;

import com.owenselles.TimeCraft.Commands.*;
import com.owenselles.TimeCraft.Events.OnPlayerChat;
import com.owenselles.TimeCraft.Events.OnPlayerJoin;
import com.owenselles.TimeCraft.Events.OnPlayerLeave;
import com.owenselles.TimeCraft.Utils.CommandManager;
import com.owenselles.TimeCraft.Utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class Main extends JavaPlugin
{

    public List<String> commands = new ArrayList<>();

    private CommandManager cmng;

    private void addCommands(){
        commands.add(cmng.claimhelp);
        commands.add(cmng.discord);
        commands.add(cmng.ranks);
        commands.add(cmng.afk);
    }

    @Override
    public void onEnable() {
        cmng = new CommandManager();

        addCommands();
        regCommands();
        regEvents();
        regConfig();

        Logger.log(Level.INFO, "Success. Plugin Enabled!");
    }



    private void regCommands(){
        for (String cmd : commands){
            getCommand(cmd).setExecutor(new CommandManager());
        }
    }

    private void regEvents(){
        getServer().getPluginManager().registerEvents(new OnPlayerChat(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerLeave(),this);
        getServer().getPluginManager().registerEvents(new OnPlayerJoin(),this);
        getServer().getPluginManager().registerEvents(new Afk(),this);
    }

    private void regConfig() {
        getConfig().options().copyDefaults(true);
        if (!getConfig().contains("cant-message")) {
            getConfig().set("cant-message", "Unknown command. Type \"/help\" for help");
        } else {
            if (getConfig().get("cant-message") == null){
                getConfig().set("cant-message", "Unknown command. Type \"/help\" for help");
            }
        }
        if (!getConfig().contains("join-message")){
            getConfig().set("join-message","&7[&a+&7] <player>");
        } else {
            if (getConfig().get("join-message") == null){
                getConfig().set("join-message","&7[&a+&7] <player>");
            }
        }
        if (!getConfig().contains("show-join-message")){
            getConfig().set("show-join-message",true);
        } else {
            if (getConfig().get("show-join-message") == null){
                getConfig().set("show-join-message",true);
            }
        }
        if (!getConfig().contains("leave-message")){
            getConfig().set("leave-message","&7[&c-&7] <player>");
        } else {
            if (getConfig().get("leave-message") == null){
                getConfig().set("leave-message","&7[&c-&7] <player>");
            }
        }
        if (!getConfig().contains("show-leave-message")){
            getConfig().set("show-leave=-message",true);
        } else {
            if (getConfig().get("show-leave-message") == null){
                getConfig().set("show-leave-message",true);
            }
        }

        for (World world : Bukkit.getWorlds()) {
            String name = world.getName();
            for (String command : commands) {
                if (!getConfig().contains(name)) {
                    getConfig().set(name + "." + command, true);
                } else {
                    if (getConfig().get(name + "." + command) == null) {
                        getConfig().set(name + "." + command, true);
                    }
                }
            }
        }
        saveConfig();
        reloadConfig();
    }

    @Override
    public void onDisable() {
        Logger.log(Level.INFO, "Plugin Disabled!");
    }

}