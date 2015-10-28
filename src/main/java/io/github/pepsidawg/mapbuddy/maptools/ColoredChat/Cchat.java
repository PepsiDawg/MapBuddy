package io.github.pepsidawg.mapbuddy.maptools.ColoredChat;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import com.sk89q.minecraft.util.commands.CommandPermissionsException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Cchat {

    @Command(
            aliases = {"cchat","coloredchat"},
            desc = "Test out chat colors with a message!",
            usage = "<message>",
            flags = "a",
            min = 1, max = -1
    )
    @CommandPermissions("mapbuddy.coloredchat.self")
    public static void cchat(CommandContext args, CommandSender sender) throws CommandPermissionsException {
        String message = args.getJoinedStrings(0);

        message = ChatColor.translateAlternateColorCodes('`',message);
        message = ChatColor.translateAlternateColorCodes('&',message);

        if(args.hasFlag('a') && sender.hasPermission("mapbuddy.coloredchat.other"))
            Bukkit.broadcastMessage(message);
        else if(!args.hasFlag('a'))
            sender.sendMessage(message);
        else
            throw new CommandPermissionsException();
    }
}
