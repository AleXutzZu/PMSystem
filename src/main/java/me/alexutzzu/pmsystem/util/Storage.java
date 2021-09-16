package me.alexutzzu.pmsystem.util;

import me.alexutzzu.pmsystem.PMSystem;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Storage {
    private final File storageFile;
    private final FileConfiguration storage;

    public Storage(PMSystem pmSystem){
        this.storageFile = new File(pmSystem.getDataFolder(), "storage.yml");
        if (!this.storageFile.exists()){
            try {
                this.storageFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("An error occurred while creating the storage file, plugin will shut down", e);
            }
        }
        this.storage = YamlConfiguration.loadConfiguration(this.storageFile);
    }
    public void saveStorage(){
        try{
            this.storage.save(this.storageFile);
        }catch (IOException e){
            throw new RuntimeException("An error occurred while saving the storage file, plugin will shut down", e);
        }
    }

    public File getStorageFile() {
        return storageFile;
    }

    public FileConfiguration getStorageConfiguration() {
        return storage;
    }
}
