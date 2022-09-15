package deqlare.timer.files;

import deqlare.timer.DeqlareTimer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class AbstractFile {
    protected DeqlareTimer plugin;
    private File file;
    public FileConfiguration fileConfig;

    public AbstractFile(DeqlareTimer plugin, String fileName){
        this.plugin = plugin;
        this.file = new File(plugin.getDataFolder(), fileName);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        this.fileConfig = YamlConfiguration.loadConfiguration(file);
    }

    public void save(){
        try{
            fileConfig.save(file);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
