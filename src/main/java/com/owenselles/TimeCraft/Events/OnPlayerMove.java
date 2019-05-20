package com.owenselles.TimeCraft.Events;

import com.owenselles.TimeCraft.Commands.Afk;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMove implements Listener {

  @EventHandler(priority = EventPriority.MONITOR)
  public static void onPlayerMove(PlayerMoveEvent event) {
    if (Afk.afk.contains(event.getPlayer())) {
      Afk.afk.remove(event.getPlayer());
      Bukkit.getServer().broadcastMessage(event.getPlayer().getName() + " is no longer AFK");
    }
  }
}
