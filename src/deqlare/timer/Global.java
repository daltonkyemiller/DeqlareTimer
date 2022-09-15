package deqlare.timer;

import deqlare.timer.models.DeqlarePlayer;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.UUID;

public class Global {
    private DeqlareTimer plugin;
    public static ArrayList<DeqlarePlayer> DeqlarePlayers = new ArrayList<>();
    public static JSONObject SavedLocs = new JSONObject();

    public static DeqlarePlayer getPlayer(UUID uuid){
        DeqlarePlayer p = null;
        for(DeqlarePlayer player : DeqlarePlayers){
            if(player.uuid.equals(uuid)){
                p = player;
            }
        }
        return p;
    }
}
