package io.github.pepsidawg.mapbuddy.maptools.commandbinder;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Bind {
    static Bindings bindings;

    public Bind() {
        bindings = Bindings.getInstance();
    }

    @Command(
            aliases = {"bind"},
            desc = "Binds a command to an item",
            usage = "[clear | <command>]",
            anyFlags = true,
            min = 1, max = -1
    )
    @CommandPermissions("mapbuddy.bind")
    public static void bind(CommandContext args, CommandSender sender) throws Exception{
        if(!(sender instanceof Player))
            throw new Exception("Only players can use this command!");

        Player player = (Player) sender;
        ItemStack tool = player.getItemInHand();
        if(tool == null)
            throw new Exception("You must be holding an item!");

        if(args.getString(0).equalsIgnoreCase("clear") && args.argsLength() == 1) {
            if(bindings.getBindings().contains(player.getUniqueId(),tool.getType())) {
                bindings.getBindings().remove(player.getUniqueId(), tool.getType());
                ItemMeta itemmeta = tool.getItemMeta();
                itemmeta.setDisplayName(tool.getType().name());
                itemmeta.setLore(Arrays.asList());

                tool.setItemMeta(itemmeta);
                player.sendMessage(ChatColor.GREEN + "Binding cleared!");
            } else {
                throw new Exception("This item doesn't have a binding!");
            }
        } else {
            String command = args.getJoinedStrings(0);
            if(!command.startsWith("/"))
                command = "/" + command;

            if(!tool.getType().isBlock()) {
                bindings.getBindings().put(player.getUniqueId(), tool.getType(), command);
                ItemMeta itemmeta = tool.getItemMeta();
                itemmeta.setDisplayName(ChatColor.LIGHT_PURPLE + command);
                itemmeta.setLore(Arrays.asList(ChatColor.GREEN + "Use this item to run the command!"));

                tool.setItemMeta(itemmeta);
                player.sendMessage(ChatColor.GREEN + "Binding set!");
            } else
                throw new Exception("You can't bind commands to blocks!");
        }
    }
}
