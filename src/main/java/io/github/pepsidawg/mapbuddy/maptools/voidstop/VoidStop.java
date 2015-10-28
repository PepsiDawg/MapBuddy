package io.github.pepsidawg.mapbuddy.maptools.voidstop;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidStop implements Listener {

    @EventHandler
    public void onFallOutOfWorld(PlayerMoveEvent event) {
        if(event.getPlayer().getLocation().getBlockY() < -63)
            event.getPlayer().teleport(event.getPlayer().getWorld().getSpawnLocation());
    }
}
