package com.owenselles.TimeCraft.Events;

import com.owenselles.TimeCraft.Main;
import com.owenselles.TimeCraft.Utils.ReflectionUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.logging.Level;

@SuppressWarnings("Duplicates")
public class OnPlayerChat implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent event) throws InvocationTargetException, IllegalAccessException, InstantiationException {
        Player player = event.getPlayer();
        String message = event.getMessage();
        for (Player on : Bukkit.getServer().getOnlinePlayers()) {
            if (message.contains(on.getName())) {
                event.setMessage(message
                        .replaceAll(on.getName(), ChatColor.GREEN + on.getName() + ChatColor.RESET));
                on.playNote(on.getLocation(), Instrument.PIANO, Note.natural(1, Note.Tone.A));
                on.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(
                        ChatColor.GREEN + player.getName() + ChatColor.GOLD + ChatColor.BOLD
                                + " mentioned you in chat!"));
            }
        }

        if (message.contains("<item>")){
            if (player.getItemInHand().getType() != Material.AIR) {
                ItemStack item = player.getItemInHand();

                Class<?> craftItemStackClazz = ReflectionUtil.getOBCClass("inventory.CraftItemStack");
                Method asNMSCopyMethod = ReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);
                assert asNMSCopyMethod != null;
                Object nmsItemStackObj = asNMSCopyMethod.invoke(null, item);

                Class<?> nmsItemStackClazz = ReflectionUtil.getNMSClass("ItemStack");
                Class<?> nbtTagCompoundClazz = ReflectionUtil.getNMSClass("NBTTagCompound");
                Method saveNmsItemStackMethod = ReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);

                Object nmsNbtTagCompoundObj;
                Object itemAsJsonObject;
                nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance();
                assert saveNmsItemStackMethod != null;
                itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);

                String json = itemAsJsonObject.toString(); // reflection object

                event.setMessage(event.getMessage().replaceAll("<item>","§7"+item.getType().name()+"§r"));
                this.sendItemTooltipMessage("§7"+player.getDisplayName()+"§a tagged an item:§r ", item, event.getRecipients());

            }
        }
    }

    /**
     * Sends a message to a player with an item as it's tooltip
     *  @param message  the message to send
     * @param item        the item to display in the tooltip
     * @param recipients players to send message to
     */
    private void sendItemTooltipMessage(String message, ItemStack item, Set<Player> recipients) {

        new BukkitRunnable() {
            @Override
            public void run() {

                String itemJson = convertItemStackToJson(item);

                // Prepare a BaseComponent array with the itemJson as a text component
                BaseComponent[] hoverEventComponents = new BaseComponent[]{
                        new TextComponent(itemJson) // The only element of the hover events basecomponents is the item json
                };

                HoverEvent event = new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents);

                TextComponent component = new TextComponent(message);

                TextComponent itemcomponent = new TextComponent("§7"+item.getType().name());
                itemcomponent.setHoverEvent(event);

                component.addExtra(itemcomponent);

                for (Player p : recipients){
                    p.spigot().sendMessage(component);
                }

            }
        }.runTaskLater(plugin,5);
    }

    private String convertItemStackToJson(ItemStack itemStack) {
        // ItemStack methods to get a net.minecraft.server.ItemStack object for serialization
        Class<?> craftItemStackClazz = ReflectionUtil.getOBCClass("inventory.CraftItemStack");
        Method asNMSCopyMethod = ReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);

        // NMS Method to serialize a net.minecraft.server.ItemStack to a valid Json string
        Class<?> nmsItemStackClazz = ReflectionUtil.getNMSClass("ItemStack");
        Class<?> nbtTagCompoundClazz = ReflectionUtil.getNMSClass("NBTTagCompound");
        Method saveNmsItemStackMethod = ReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);

        Object nmsNbtTagCompoundObj; // This will just be an empty NBTTagCompound instance to invoke the saveNms method
        Object nmsItemStackObj; // This is the net.minecraft.server.ItemStack object received from the asNMSCopy method
        Object itemAsJsonObject; // This is the net.minecraft.server.ItemStack after being put through saveNmsItem method

        try {
            nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance();
            assert asNMSCopyMethod != null;
            nmsItemStackObj = asNMSCopyMethod.invoke(null, itemStack);
            assert saveNmsItemStackMethod != null;
            itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);
        } catch (Throwable t) {
            Bukkit.getLogger().log(Level.SEVERE, "failed to serialize itemstack to nms item", t);
            return null;
        }

        // Return a string representation of the serialized object
        return itemAsJsonObject.toString();
    }

}
