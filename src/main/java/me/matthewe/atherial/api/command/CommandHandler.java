package me.matthewe.atherial.api.command;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matthew E on 1/1/2018.
 */
public abstract class CommandHandler<C extends DefaultCommand> {
    protected final Map<String, C> commandMap;
    protected boolean isDebug;

    public CommandHandler() {
        this.commandMap = new HashMap<>();
    }

    public void registerCommand(C defaultCommand) {
        Command annotation = defaultCommand.getClass().getAnnotation(Command.class);
        if (annotation != null) {
            defaultCommand.setCommand(annotation);
            commandMap.put(annotation.name(), defaultCommand);
        }
    }

    public abstract void registerCommands();

    /**
     * Getter for property 'commandMap'.
     *
     * @return Value for property 'commandMap'.
     */
    public Map<String, C> getCommandMap() {
        return commandMap;
    }

    public void setDebug(boolean debug) {
        this.isDebug = debug;
    }
}
