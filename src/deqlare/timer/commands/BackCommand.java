package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import deqlare.timer.Global;
import deqlare.timer.models.DeqlarePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand extends AbstractCommand{
    public static final String NAME = "Starts Stopwatch";

    public static final String DESCRIPTION = "Starts the stopwatch";

    public static final String PERMISSION = "deqlaretimer.admin";

    public static final String USAGE = "/deqlaretimer back";


    public static final String[] SUB_PERMISSIONS = {""};

    public BackCommand(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Player p = Bukkit.getPlayer(sender.getName());
        DeqlarePlayer dp = Global.getPlayer(p.getUniqueId());
        p.teleport(dp.lastDeathLocation);
    }
}
