package deqlare.timer;

import deqlare.timer.commands.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandler implements CommandExecutor {
    private DeqlareTimer parent;
    public CommandHandler(DeqlareTimer parent){
        this.parent = parent;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        AbstractCommand cmd = new HelpCommand(sender, parent);
        switch(args[0].toLowerCase()){
            case "start":
                cmd = new StartCommand(sender, parent);
                break;
            case "stop":
                cmd = new StopCommand(sender, parent);
                break;
            case "restart":
                cmd = new RestartCommand(sender, parent);
                break;
            case "resetstats":
                cmd = new ResetStats(sender, parent);
                break;
            case "sendcoords":
                cmd = new SendCoords(sender, parent);
                break;
            case "locate":
                cmd = new LocateCommand(sender, parent);
                break;
            case "savecoords":
                cmd = new SaveCoordsCommand(sender, parent);
                break;
            case "back":
                cmd = new BackCommand(sender, parent);
                break;
        }

        cmd.execute(sender, command, label, args);
        return true;
    }
}
