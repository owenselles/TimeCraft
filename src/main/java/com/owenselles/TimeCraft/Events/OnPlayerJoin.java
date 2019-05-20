package com.owenselles.TimeCraft.Events;

import com.owenselles.TimeCraft.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnPlayerJoin implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!event.getPlayer().hasPlayedBefore()) {
            Bukkit.broadcastMessage(Color.add("&7[&a+&7] &7" + event.getPlayer().getName() + " welcome to &6TimeCraft&7!"));
        } else {
            Bukkit.broadcastMessage(Color.add("&7[&a+&7] &7" + event.getPlayer().getName()));
        }
    }
}
