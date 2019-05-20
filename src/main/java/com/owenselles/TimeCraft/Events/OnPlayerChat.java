package com.owenselles.TimeCraft.Events;

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
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPlayerChat implements Listener {
    @EventHandler(priority = EventPriority.MONITOR)
    public static void onPlayerChat(AsyncPlayerChatEvent event) {
        for (Player on : Bukkit.getServer().getOnlinePlayers()) {
            if (event.getMessage().contains(on.getName())) {
                event.setMessage(event.getMessage().replaceAll(on.getName(), ChatColor.GREEN + on.getName() + ChatColor.RESET));
                on.playNote(on.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
                on.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GREEN + event.getPlayer().getName() + ChatColor.GOLD + ChatColor.BOLD + " mentioned you in chat!"));
            }
        }
    }
}
