package deqlare.timer;

import deqlare.timer.models.DeqlarePlayer;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.awt.*;

public class Worker {
    public int stopwatchSeconds;
    public DeqlareTimer plugin;
    public int secondsTaskId;
    public ScoreboardManager m;
    public Scoreboard b;
    public Objective o;
    public Score time;

    public Worker(DeqlareTimer plugin){
        this.stopwatchSeconds = 0;
        this.plugin = plugin;
        this.m = Bukkit.getScoreboardManager();
        this.b = m.getNewScoreboard();
    }

    public void StartTimer(){
        if(plugin.playerData.fileConfig.contains("stopwatchSeconds")){
            stopwatchSeconds = (int) plugin.playerData.fileConfig.get("stopwatchSeconds");
        }else{
            plugin.playerData.fileConfig.set("stopwatchSeconds", stopwatchSeconds);
            plugin.playerData.save();
        }

        secondsTaskId = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            stopwatchSeconds++;
        }, 1L, 20L);
    }

    public String FormatTime(int seconds){
        int newSeconds = seconds%60;
        int hours = (seconds / 3600);
        int minutes = (seconds / 60)%60;

        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", newSeconds);
    }
    public void StopTimer(){
        plugin.playerData.fileConfig.set("stopwatchSeconds", stopwatchSeconds);
        plugin.playerData.save();
        Bukkit.getScheduler().cancelTask(secondsTaskId);
    }
    public void RestartTimer(){
        StopTimer();
        plugin.playerData.fileConfig.set("stopwatchSeconds", 0);
        plugin.playerData.save();
        StartTimer();
    }
    public void ResetStats(){
        for(DeqlarePlayer dp : Global.DeqlarePlayers){
            plugin.playerData.ResetPlayer(dp);
        }
    }

    public void RegisterDeqlarePlayer(Player p){
        DeqlarePlayer dp = plugin.playerData.getPlayer(p.getName());
        if(dp == null){
            dp = new DeqlarePlayer();
            dp.AssignBukkitPlayer(p);
            plugin.playerData.UpdatePlayer(dp);
        }
        Global.DeqlarePlayers.add(dp);
    }



}
