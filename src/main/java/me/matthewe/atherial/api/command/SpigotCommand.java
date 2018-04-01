package me.matthewe.atherial.api.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Matthew E on 1/1/2018.
 */
public abstract class SpigotCommand extends DefaultCommand<CommandSender> implements CommandExecutor {

    @Override
    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        execute(sender, args);
        return true;
    }
}