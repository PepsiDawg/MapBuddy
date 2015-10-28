package io.github.pepsidawg.mapbuddy.maptools.velocity;

import com.sk89q.minecraft.util.commands.Command;
import com.sk89q.minecraft.util.commands.CommandContext;
import com.sk89q.minecraft.util.commands.CommandPermissions;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Velocity {

    @Command(
            aliases = {"velocity", "vel"},
            desc = "Launch yourself in at a Velocity!",
            usage = "<x> <y> <z>",
            min =  3, max = 3
    )
    @CommandPermissions("mapbuddy.velocity")
    public static void velocity(CommandContext args, CommandSender sender) throws Exception {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.setVelocity(new Vector(args.getDouble(0),args.getDouble(1),args.getDouble(2)));
        } else
            throw new Exception("Only players can use this command!");
    }
}
