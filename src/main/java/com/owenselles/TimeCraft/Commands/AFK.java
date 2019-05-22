package com.owenselles.TimeCraft.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public class Afk implements CommandExecutor, Listener {

    private static ArrayList<String> isAFK = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (isAFK.contains(player.getName())) {
            isAFK.remove(player.getName());
            Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " is no longer AFK");
        } else {
            isAFK.add(player.getName());
            Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " is now AFK");
        }

        return true;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if (isAFK.contains(player.getName())){
            isAFK.remove(player.getName());
            Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " is no longer AFK");
        }

    }
    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();

        if (isAFK.contains(player.getName())){
            isAFK.remove(player.getName());
            Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " is no longer AFK");
        }

    }
    @EventHandler
    public void onChat(PlayerChatEvent event){
        Player player = event.getPlayer();

        if (isAFK.contains(player.getName())){
            isAFK.remove(player.getName());
            Bukkit.broadcastMessage(ChatColor.GRAY + player.getName() + " is no longer AFK");
        }

    }
}