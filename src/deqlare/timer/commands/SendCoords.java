package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SendCoords extends AbstractCommand{
    public static final String NAME = "Starts Stopwatch";

    public static final String DESCRIPTION = "Starts the stopwatch";

    public static final String PERMISSION = "deqlaretimer.default";

    public static final String USAGE = "/deqlaretimer sendcoords";


    public static final String[] SUB_PERMISSIONS = {""};

    public SendCoords(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.broadcastMessage(sender.getName() + "'s coordinates are: " + Integer.toString((int) Bukkit.getPlayer(sender.getName()).getLocation().getBlockX()) + ", " + Integer.toString((int) Bukkit.getPlayer(sender.getName()).getLocation().getBlockY()) + ", " + Double.toString(Bukkit.getPlayer(sender.getName()).getLocation().getBlockZ()));
    }
}
