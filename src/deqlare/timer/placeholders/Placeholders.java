package deqlare.timer.placeholders;

import deqlare.timer.DeqlareTimer;
import deqlare.timer.Global;
import deqlare.timer.Worker;
import deqlare.timer.models.DeqlarePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;

import java.time.Duration;

public class Placeholders extends PlaceholderExpansion {
    private DeqlareTimer plugin;

    public Placeholders(DeqlareTimer plugin){
        plugin = plugin;
    }

    @Override
    public String getIdentifier() {
        return "deqlare";
    }

    @Override
    public String getAuthor() {
        return "Dalton Miller";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    @Override
    public boolean canRegister() {
        return (plugin = (DeqlareTimer) Bukkit.getPluginManager().getPlugin(getRequiredPlugin())) != null;
    }

    @Override
    public String getRequiredPlugin(){
        return "DeqlareTimer";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        if(p==null){
            return "";
        }
        DeqlarePlayer dp = Global.getPlayer(p.getUniqueId());
        switch (identifier){
            case "time":
                return plugin.worker.FormatTime(dp.getBukkitPlayer().getStatistic(Statistic.PLAY_ONE_MINUTE) / 20);
            case "player_name":
                return p.getName();
            case "player_deathCount":
                return String.valueOf(dp.getBukkitPlayer().getStatistic(Statistic.DEATHS));
            case "player_totalBlocksBroken":
                return Integer.toString(dp.totalBlocksBroken);
            case "player_location":
                return Integer.toString(p.getLocation().getBlockX()) + ", " + Integer.toString(p.getLocation().getBlockY()) + ", " + Integer.toString(p.getLocation().getBlockZ());
            case "closest_player_name":
                String closestPlayerName = "";
                if(dp.getClosestPlayer() != null){
                    closestPlayerName = dp.getClosestPlayer().getPlayerName();
                }
                return closestPlayerName;
            case "closest_player_location":
                String closetPlayerCoords = "";
                if(dp.getClosestPlayer() != null){
                    closetPlayerCoords = Integer.toString(dp.getClosestPlayer().getBukkitPlayer().getLocation().getBlockX() - p.getLocation().getBlockX()) + ", " + Integer.toString(dp.getClosestPlayer().getBukkitPlayer().getLocation().getBlockY() - p.getLocation().getBlockY()) + ", " + Integer.toString(dp.getClosestPlayer().getBukkitPlayer().getLocation().getBlockZ() - p.getLocation().getBlockZ());
                }
                return closetPlayerCoords;
            case "loc_name":
                return dp.compassTargetName;

            case "loc":
                return Integer.toString(dp.compassTarget.getBlockX() - p.getLocation().getBlockX()) + ", " + Integer.toString(dp.compassTarget.getBlockY() - p.getLocation().getBlockY()) + ", " + Integer.toString(dp.compassTarget.getBlockZ() - p.getLocation().getBlockZ());

        }
        return null;
    }
}
