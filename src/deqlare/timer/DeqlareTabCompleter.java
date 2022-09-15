package deqlare.timer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DeqlareTabCompleter implements TabCompleter {
    private static final String[] COMMANDS = {"start", "stop", "restart", "resetstats", "sendcoords", "locate", "savecoords", "back"};
    private static final String[] DEFAULT_COMMANDS = {"sendcoords", "locate", "savecoords"};

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp() || sender.hasPermission("deqlaretimer.admin") || sender.hasPermission("deqlaretimer." + args[0]) || sender.hasPermission("deqlaretimer.default")) {
            List<String> completions = new ArrayList<>();
            if (args.length == 1) {
                String partialCommand = args[0];
                List<String> commands = new ArrayList<>(Arrays.asList(COMMANDS));
                StringUtil.copyPartialMatches(partialCommand, commands, completions);
            }if (args.length == 2){
                if(args[0].equals("locate")){
                    String partialCommand = args[1];
                    List<String> commands = new ArrayList<>();
                    for(Player p : Bukkit.getOnlinePlayers()){
                        commands.add(p.getName());
                    }
                    Global.SavedLocs.keySet().forEach(keyStr -> {
                        commands.add(keyStr.toString());
                    });
                    StringUtil.copyPartialMatches(partialCommand, commands, completions);
                }

            }

            Collections.sort(completions);

            return completions;
        }

        return null;
    }
}
