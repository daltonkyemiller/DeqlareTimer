package deqlare.timer.files;

import deqlare.timer.DeqlareTimer;
import deqlare.timer.Global;
import deqlare.timer.files.AbstractFile;
import deqlare.timer.models.DeqlarePlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.UUID;

public class PlayerData extends AbstractFile {
    public PlayerData(DeqlareTimer plugin){
        super(plugin, "playerdata.yml");
    }


    public void UpdatePlayer(DeqlarePlayer dp){
        fileConfig.set(dp.getPlayerName(), "");
        fileConfig.set(dp.getPlayerName()+".uuid", dp.getUuid().toString());
        fileConfig.set(dp.getPlayerName()+".deathCount", dp.getDeathCount());
        fileConfig.set(dp.getPlayerName()+".totalBlocksBroken", dp.getTotalBlocksBroken());
        fileConfig.set(dp.getPlayerName()+".totalAnimalsKilled", dp.getTotalAnimalsKilled());
        fileConfig.set(dp.getPlayerName()+".totalMonstersKilled", dp.getTotalMonstersKilled());

        dp.blocksBroken.keySet().forEach(keyStr -> {
            Object keyvalue = dp.blocksBroken.get(keyStr);
            fileConfig.set(dp.getPlayerName()+".blocksBroken."+keyStr, (int) keyvalue);
        });
        dp.killedEntities.keySet().forEach(keyStr -> {
            Object keyvalue = dp.killedEntities.get(keyStr);
            fileConfig.set(dp.getPlayerName()+".killedEntities."+keyStr, (int) keyvalue);
        });

//        dp.killedBy.keySet().forEach(keyStr -> {
//            Object keyvalue = dp.killedBy.get(keyStr);
//            fileConfig.set(dp.getPlayerName()+".killedBy."+keyStr, (int) keyvalue);
//        });

        save();
    }

    public void ResetPlayer(DeqlarePlayer dp){
        UUID uuid = dp.getUuid();
        dp.reset();
        dp.AssignBukkitPlayer(Bukkit.getPlayer(uuid));
        UpdatePlayer(dp);
    }

    public void SetGlobals(){
        if(!fileConfig.contains("savedCoords")){
            fileConfig.set("savedCoords", "");
        }else{
            for(String key : fileConfig.getConfigurationSection("savedCoords").getKeys(false)){
                Object value = fileConfig.get("savedCoords."+key);
                Global.SavedLocs.put(key, value);
            }
        }

    }

    public DeqlarePlayer getPlayer(String name){
        DeqlarePlayer dp = null;
        if(fileConfig.contains(name)){
            dp = new DeqlarePlayer();
            Player p = Bukkit.getPlayer(UUID.fromString(String.valueOf(fileConfig.get(name+".uuid"))));
            dp.AssignBukkitPlayer(p);
            dp.totalBlocksBroken = (int) fileConfig.get(name+".totalBlocksBroken");
            dp.deathCount = (int) fileConfig.get(name+".deathCount");
            dp.totalMonstersKilled = (int) fileConfig.get(name+".totalMonstersKilled");
            dp.totalAnimalsKilled = (int) fileConfig.get(name+".totalAnimalsKilled");

            if(fileConfig.contains(name+".blocksBroken")){
                for(String key : fileConfig.getConfigurationSection(name+".blocksBroken").getKeys(false)){
                    Object value = fileConfig.get(name+".blocksBroken."+key);
                    dp.blocksBroken.put(key, value);
                }
            }
            if(fileConfig.contains(name+".killedEntities")){
                for(String key : fileConfig.getConfigurationSection(name+".killedEntities").getKeys(false)){
                    Object value = fileConfig.get(name+".killedEntities."+key);
                    dp.killedEntities.put(key, value);
                }
            }


        }
        return dp;
    }

    public void UpdatePlayerData(){
        for(DeqlarePlayer dp : Global.DeqlarePlayers){
            UpdatePlayer(dp);
        }
    }
}
