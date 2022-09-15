package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class RestartCommand extends AbstractCommand{
    public static final String NAME = "Restarts Stopwatch";

    public static final String DESCRIPTION = "Restarts the stopwatch";

    public static final String PERMISSION = "deqlaretimer.admin";

    public static final String USAGE = "/deqlaretimer restart";


    public static final String[] SUB_PERMISSIONS = {""};


    public RestartCommand(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        dt.worker.RestartTimer();
    }
}
