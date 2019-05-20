package com.owenselles.TimeCraft.Events;

import com.owenselles.TimeCraft.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeave implements Listener {
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Bukkit.broadcastMessage(Color.add("&7[&c-&7] &7" + event.getPlayer().getName()));
    }
}

