package deqlare.timer.models;

import deqlare.timer.DeqlareTimer;
import deqlare.timer.Global;
import deqlare.timer.files.AbstractFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.UUID;

public class DeqlarePlayer {
    public UUID uuid;
    public String playerName;
    public int deathCount;
    public int totalBlocksBroken;

    public JSONObject blocksBroken;
    public JSONObject killedBy;

    public Vector compassTarget;
    public String compassTargetName;

    public int totalAnimalsKilled;
    public int totalMonstersKilled;
    public JSONObject killedEntities;

    public Location lastDeathLocation;

    public DeqlarePlayer(){
        reset();
    }
    public void reset(){
        this.uuid = null;
        this.playerName = null;
        this.deathCount = 0;
        this.totalBlocksBroken = 0;
        this.blocksBroken = new JSONObject();
        this.killedBy = new JSONObject();

        this.totalAnimalsKilled = 0;
        this.totalMonstersKilled = 0;
        this.killedEntities = new JSONObject();

        this.compassTarget = new Vector();
        this.compassTargetName = "";
    }

    public void AssignBukkitPlayer(Player p){
        this.uuid = p.getUniqueId();
        this.playerName = p.getName();
    }

    public UUID getUuid() {
        return uuid;
    }
    public int getDeathCount() {
        return deathCount;
    }
    public int getTotalBlocksBroken() {
        return totalBlocksBroken;
    }

    public int getTotalAnimalsKilled() {
        return totalAnimalsKilled;
    }

    public int getTotalMonstersKilled() {
        return totalMonstersKilled;
    }

    public String getPlayerName() {
        return playerName;
    }
    public Player getBukkitPlayer(){return Bukkit.getPlayer(this.uuid);}

    public void Locate(String name, Vector loc){
        this.compassTarget = loc;
        this.compassTargetName = name;
    }

    public DeqlarePlayer getClosestPlayer(){
        double lastDist = Double.MAX_VALUE;
        Player playerNear = null;
        for(Player p : Bukkit.getOnlinePlayers()){
            if(getBukkitPlayer() == p)
                continue;
            double distance = getBukkitPlayer().getLocation().distance(p.getLocation());
            if (distance < lastDist) {
                lastDist = distance;
                playerNear = p;
            }
        }
        DeqlarePlayer deqlarePlayerNear = null;
        try{
             deqlarePlayerNear = Global.getPlayer(playerNear.getUniqueId());
        }catch (NullPointerException npe){

        }

        return deqlarePlayerNear;
    }
}
