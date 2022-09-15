package deqlare.timer;

import deqlare.timer.listeners.Listeners;
import deqlare.timer.files.PlayerData;
import deqlare.timer.placeholders.Placeholders;
import me.jasperjh.animatedscoreboard.AnimatedScoreboard;
import me.jasperjh.animatedscoreboard.AnimatedScoreboardAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class DeqlareTimer extends JavaPlugin {
    private static DeqlareTimer instance = null;
    public Worker worker;
    public AnimatedScoreboardAPI api;
    public PlayerData playerData;



    @Override
    public void onEnable() {
        instance = this;
        api = AnimatedScoreboard.loadAPI(instance);
        if(!this.getDataFolder().exists()){
            this.getDataFolder().mkdirs();
        }
        this.playerData = new PlayerData(this);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new Placeholders(this).register();
        }

        worker = new Worker(instance);

        for(Player p : Bukkit.getOnlinePlayers()){
            worker.RegisterDeqlarePlayer(p);
        }
        playerData.SetGlobals();

        this.getCommand("deqlaretimer").setExecutor(new CommandHandler(this));
        this.getCommand("deqlaretimer").setTabCompleter(new DeqlareTabCompleter());
        Bukkit.getPluginManager().registerEvents(new Listeners(instance), this);
    }

    @Override
    public void onDisable() {
        worker.StopTimer();
        playerData.UpdatePlayerData();
        playerData.save();
    }
}
