package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

public abstract class AbstractCommand {
    private final CommandSender sender;
    private final String name;
    private final String description;
    private final String permission;
    private final String usage;
    private final String[] subPermissions;
    public DeqlareTimer dt;
    public AbstractCommand(CommandSender sender, String name, String description, String permission, String[] subPermissions, String usage, DeqlareTimer dt) {
        this.sender = sender;
        this.name = name;
        this.description = description;
        this.permission = permission;
        this.subPermissions = subPermissions;
        this.usage = usage;
        this.dt = dt;
    }


    public CommandSender getSender() {
        return sender;
    }

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public String getPermission() {
        return permission;
    }


    public String[] getSubPermissions() {
        return subPermissions;
    }


    public String getUsage() {
        return usage;
    }


    public boolean hasPermission() {
        return sender.hasPermission(permission) || isSenderConsole() || isSenderRemoteConsole();
    }


    public boolean hasPermission(String sub) {
        String permission = this.permission + "." + sub;
        return sender.hasPermission(permission) || isSenderConsole() || isSenderRemoteConsole();
    }


    public boolean isSenderPlayer() {
        return (sender instanceof Player);
    }


    public boolean isSenderConsole() {
        return (sender instanceof ConsoleCommandSender);
    }


    public boolean isSenderRemoteConsole() {
        return (sender instanceof RemoteConsoleCommandSender);
    }


    public abstract void execute(CommandSender sender, Command command, String label, String[] args);
}
