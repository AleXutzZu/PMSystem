package me.alexutzzu.pmsystem.menu;

import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.alexutzzu.pmsystem.util.Storage;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SettingsProvider implements InventoryProvider {
    private final FileConfiguration configuration;
    private final Storage storage;
    // private final ItemStack fillerPane;
    // private final ItemStack soundSetting;
    // private final ItemStack pmSetting;
    public SettingsProvider(FileConfiguration configuration, Storage storage) {
        this.configuration = configuration;
        this.storage = storage;



    }

    @Override
    public void init(Player player, InventoryContents contents) {
       // contents.fillBorders(ClickableItem.empty();

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }


    //TODO
    private void soundSettings(Player player){

    }
    private void pmSettings(Player player){

    }
}
