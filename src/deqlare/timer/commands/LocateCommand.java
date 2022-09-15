package deqlare.timer.commands;

import deqlare.timer.DeqlareTimer;
import deqlare.timer.Global;
import deqlare.timer.models.DeqlarePlayer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;

public class LocateCommand extends AbstractCommand{
    public static final String NAME = "Starts Stopwatch";

    public static final String DESCRIPTION = "Starts the stopwatch";

    public static final String PERMISSION = "deqlaretimer.default";

    public static final String USAGE = "/deqlaretimer start";


    public static final String[] SUB_PERMISSIONS = {"all"};
    public static int trackPlayerTaskId;

    public LocateCommand(CommandSender sender, DeqlareTimer dt) {
        super(sender, NAME, DESCRIPTION, PERMISSION, SUB_PERMISSIONS, USAGE, dt);
    }

    @Override
    public void execute(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration fileConfig =  dt.playerData.fileConfig;
        DeqlarePlayer dp = Global.getPlayer(Bukkit.getPlayer(sender.getName()).getUniqueId());

        if(fileConfig.contains("savedCoords."+args[1])){
            dt.getServer().getScheduler().cancelTask(trackPlayerTaskId);
            for(String key : fileConfig.getConfigurationSection("savedCoords").getKeys(false)){
                if(key.equals(args[1])){
                    String[] arrofCoords = fileConfig.get("savedCoords."+args[1]).toString().split(",", 3);
                    Vector loc = new Vector(Integer.parseInt(arrofCoords[0]), Integer.parseInt(arrofCoords[1]), Integer.parseInt(arrofCoords[2]));
                    dp.Locate(args[1], loc);
                }

            }


        }else if(Bukkit.getPlayer(args[1]) != null){
            Player p = Bukkit.getPlayer(args[1]);
            trackPlayerTaskId = dt.getServer().getScheduler().scheduleSyncRepeatingTask(dt, () -> {
                dp.Locate(p.getName(), p.getLocation().toVector());
            }, 1L, 20L);
        }else{
            sender.sendMessage("There aren't any locations saved with that name");
        }
    }
}
