package io.github.pepsidawg.mapbuddy.maptools.ColoredChat;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ColoredChat implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if(event.getPlayer().hasPermission("mapbuddy.coloredchat.chat")) {
            String message = event.getMessage();

            message = ChatColor.translateAlternateColorCodes('`',message);
            message = ChatColor.translateAlternateColorCodes('&',message);
            event.setMessage(message);
        }
    }
}
