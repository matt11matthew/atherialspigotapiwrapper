package me.matthewe.atherial.api.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.util.Arrays;


/**
 * Created by Matthew E on 1/1/2018.
 */
public class SpigotCommandHandler extends CommandHandler<SpigotCommand> {

    @Override
    public void registerCommands() {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap spigotCommandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.forEach((s, spigotCommand) -> {
                ReflectCommand reflectCommand = new ReflectCommand(spigotCommand.getCommand().name());
                reflectCommand.setAliases(Arrays.asList(spigotCommand.getCommand().aliases()));
                reflectCommand.setDescription(spigotCommand.getCommand().description());
                reflectCommand.setUsage(spigotCommand.getCommand().usage());
                reflectCommand.setExecutor(spigotCommand);
                spigotCommandMap.register(reflectCommand.spigotCommand.getCommand().name(), reflectCommand);
                if (isDebug) {
                    System.out.println("Registered command " + spigotCommand.getCommand().name());
                }
            });
            if (isDebug) {
                System.out.println("Registered " + commandMap.keySet().size() + " commands");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final class ReflectCommand extends Command {
        private SpigotCommand spigotCommand;

        public ReflectCommand(String command) {
            super(command);
        }

        public void setExecutor(SpigotCommand exe) {
            this.spigotCommand = exe;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            return spigotCommand != null && spigotCommand.onCommand(sender, this, commandLabel, args);
        }
    }
}
