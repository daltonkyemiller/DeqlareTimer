package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class StopCommand extends AbstractCommand{
    public static final String NAME = "Stops Stopwatch";

    public static final String DESCRIPTION = "Stops the stopwatch";

    public static final String PERMISSION = "deqlaretimer.admin";

    public static final String USAGE = "/deqlaretimer stop";


    public static final String[] SUB_PERMISSIONS = {""};


    public StopCommand(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        dt.worker.StopTimer();
    }
}
