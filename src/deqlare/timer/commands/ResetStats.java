package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ResetStats extends AbstractCommand{
    public static final String NAME = "Reset Stats";

    public static final String DESCRIPTION = "Resets a player's stats";

    public static final String PERMISSION = "deqlaretimer.admin";

    public static final String USAGE = "/deqlaretimer resetstats";


    public static final String[] SUB_PERMISSIONS = {""};


    public ResetStats(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        dt.worker.ResetStats();
    }
}
