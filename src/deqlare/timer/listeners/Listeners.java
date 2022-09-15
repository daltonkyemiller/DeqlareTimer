package deqlare.timer.listeners;

import deqlare.timer.DeqlareTimer;
import deqlare.timer.Global;
import deqlare.timer.models.DeqlarePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners  implements Listener {
    private DeqlareTimer plugin;
    public Listeners(DeqlareTimer plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        Player p = e.getEntity();
        DeqlarePlayer dp = Global.getPlayer(p.getUniqueId());
        dp.lastDeathLocation = p.getLocation();
        dp.deathCount++;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e){
        Player p = e.getPlayer();
        String blockName = e.getBlock().getType().name();
        DeqlarePlayer dp = Global.getPlayer(p.getUniqueId());
        dp.blocksBroken.putIfAbsent(blockName, 0);
        dp.blocksBroken.put(blockName, (int) dp.blocksBroken.get(blockName) + 1);

        dp.totalBlocksBroken++;
    }

    @EventHandler
    public void onKillEntity(EntityDeathEvent e){
        LivingEntity entity = e.getEntity();
        Player killer = entity.getKiller();
        if(killer instanceof Player){
            DeqlarePlayer dp = Global.getPlayer(killer.getUniqueId());
            if(entity instanceof Animals){
                dp.totalAnimalsKilled++;
            }else if(entity instanceof Monster){
                dp.totalMonstersKilled++;
            }
            String entityType = entity.getType().toString();

            dp.killedEntities.putIfAbsent(entityType, 0);
            dp.killedEntities.put(entityType, (int) dp.killedEntities.get(entityType) + 1);
        }
    }



    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        plugin.worker.RegisterDeqlarePlayer(p);
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e){
        Player p = e.getPlayer();
        DeqlarePlayer dp = Global.getPlayer(p.getUniqueId());
        plugin.playerData.UpdatePlayer(dp);
        plugin.playerData.save();

    }

}
