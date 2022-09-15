package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class StartCommand extends AbstractCommand{
    public static final String NAME = "Starts Stopwatch";

    public static final String DESCRIPTION = "Starts the stopwatch";

    public static final String PERMISSION = "deqlaretimer.admin";

    public static final String USAGE = "/deqlaretimer start";


    public static final String[] SUB_PERMISSIONS = {""};

    public StartCommand(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        dt.worker.StartTimer();
    }
}
