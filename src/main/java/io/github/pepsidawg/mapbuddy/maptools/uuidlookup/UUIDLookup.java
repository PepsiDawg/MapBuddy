package io.github.pepsidawg.mapbuddy.maptools.uuidlookup;

import com.sk89q.minecraft.util.commands.ChatColor;
import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class UUIDLookup {

    @Command(
            aliases = {"uuid"},
            desc = "Find the uuid of a player!",
            usage = "<player>",
            min = 1, max = 1
    )
    @CommandPermissions({"mapbuddy.uuid"})
    public static void findUUID(CommandContext args, CommandSender sender) throws Exception {
        UUID uuid = GETHandler.getUsernameUUID(args.getString(0));
        sender.sendMessage(ChatColor.GREEN + args.getString(0) + ": " + uuid.toString());
    }
}
