package io.github.pepsidawg.mapbuddy.maptools.commandbinder;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class BindListener implements Listener {
    Bindings bindings;

    public BindListener() {
        bindings = Bindings.getInstance();
    }

    @EventHandler
    public void onItemUse(PlayerInteractEvent event) {
        if(event.getPlayer().getItemInHand() != null) {
            Player player = event.getPlayer();
            ItemStack tool = player.getItemInHand();

            if(bindings.getBindings().contains(player.getUniqueId(), tool.getType())) {
                player.chat(bindings.getBindings().get(player.getUniqueId(), tool.getType()).toString());
            }
        }
    }
}
