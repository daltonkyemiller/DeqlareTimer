package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class HelpCommand extends AbstractCommand{
    public static final String NAME = "Start Game";

    public static final String DESCRIPTION = "Displays help information.";

    public static final String PERMISSION = "deqlaretimer.default";

    public static final String USAGE = "/deqlaretimer help";


    public static final String[] SUB_PERMISSIONS = {""};


    public HelpCommand(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.broadcastMessage("Fuk u");
    }
}
