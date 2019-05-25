package com.owenselles.TimeCraft.Events;

import com.owenselles.TimeCraft.Main;
import com.owenselles.TimeCraft.Utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnPlayerLeave implements Listener {

  Main main = Main.getPlugin(Main.class);

  private boolean show;
  private String message;

  @EventHandler
  public void onPlayerLeave(PlayerQuitEvent event) {
    show = Boolean.valueOf(String.valueOf(main.getConfig().get("show-leave-message")));
    if (show) {
      message = Color.add(String.valueOf(main.getConfig().get("leave-message")).replaceAll("<player>",event.getPlayer().getName()));
      Bukkit.broadcastMessage(message);
    }
  }
}