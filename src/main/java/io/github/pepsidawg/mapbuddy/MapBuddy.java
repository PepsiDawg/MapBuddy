package io.github.pepsidawg.mapbuddy;

import com.sk89q.bukkit.util.CommandsManagerRegistration;
import com.sk89q.minecraft.util.commands.*;
import io.github.pepsidawg.mapbuddy.maptools.commandbinder.Bind;
import io.github.pepsidawg.mapbuddy.maptools.commandbinder.BindListener;
import io.github.pepsidawg.mapbuddy.maptools.velocity.Velocity;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MapBuddy extends JavaPlugin {
    private CommandsManager<CommandSender> commands;

    public void onEnable() {
        setupCommands();
        setupListeners();
    }

    public void onDisable() {

    }

    private void setupCommands() {
        this.commands = new CommandsManager<CommandSender>() {
            @Override
            public boolean hasPermission(CommandSender sender, String perm) {
                return sender instanceof ConsoleCommandSender || sender.hasPermission(perm);
            }
        };

        CommandsManagerRegistration cmdRegister = new CommandsManagerRegistration(this, this.commands);
        cmdRegister.register(Velocity.class);
        cmdRegister.register(new Bind().getClass());
    }

    private void setupListeners() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new BindListener(), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        String[] _args = debug(args);

        try {
            this.commands.execute(cmd.getName(), _args, sender, sender);
        } catch (CommandPermissionsException e) {
            sender.sendMessage(ChatColor.RED + "You don't have permission.");
        } catch (MissingNestedCommandException e) {
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (CommandUsageException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            sender.sendMessage(ChatColor.RED + e.getUsage());
        } catch (WrappedCommandException e) {
            if (e.getCause() instanceof NumberFormatException) {
                sender.sendMessage(ChatColor.RED + "Number expected, string received instead.");
            } else {
                sender.sendMessage(ChatColor.RED + e.getCause().getMessage());
                if (debug) {
                    e.getCause().printStackTrace();
                    debug = false;
                }
            }
        } catch (CommandException e) {
            sender.sendMessage(ChatColor.RED + e.getMessage());
            if (debug) {
                e.getCause().printStackTrace();
                debug = false;
            }
        }

        return true;
    }

    private boolean debug = false;
    private String[] debug(String[] args) {
        List<String> argList = new LinkedList<>(Arrays.asList(args));
        String[] res;

        if(argList.contains("--debug")) {
            debug = true;
            argList.remove("--debug");
            res = new String[argList.size()];
            return argList.toArray(res);
        }
        return args;
    }
}
