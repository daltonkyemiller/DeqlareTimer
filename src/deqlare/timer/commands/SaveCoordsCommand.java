package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import deqlare.timer.Global;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class SaveCoordsCommand extends AbstractCommand{
    public static final String NAME = "Starts Stopwatch";

    public static final String DESCRIPTION = "Starts the stopwatch";

    public static final String PERMISSION = "deqlaretimer.default";

    public static final String USAGE = "/deqlaretimer savecoords <name>";


    public static final String[] SUB_PERMISSIONS = {"all"};

    public SaveCoordsCommand(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 2){
            Location playerLoc = Bukkit.getPlayer(sender.getName()).getLocation();
            String str = Integer.toString(playerLoc.getBlockX()) + ','+Integer.toString(playerLoc.getBlockY()) + ','+ Integer.toString(playerLoc.getBlockZ());
            Global.SavedLocs.put(args[1], str);
            dt.playerData.fileConfig.set("savedCoords."+ args[1], str);
            dt.playerData.save();
        }else{
            sender.sendMessage(ChatColor.RED + "Must have a name for the coordinates!");
        }
    }
}
