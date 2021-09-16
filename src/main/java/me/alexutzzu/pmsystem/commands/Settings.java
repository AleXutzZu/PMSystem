package me.alexutzzu.pmsystem.commands;

import me.alexutzzu.pmsystem.PMSystem;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Settings implements CommandExecutor {

    private final FileConfiguration configuration;
    private final PMSystem pmSystem;

    public Settings(FileConfiguration configuration, PMSystem pmSystem) {
        this.configuration = configuration;
        this.pmSystem = pmSystem;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                    configuration
                            .getString("messages.error_not_player")
                            .replaceAll("\\$pluginPrefix", configuration.getString("messages.prefix")))
            );
            return true;
        }
        if (args.length != 0) {
            return true;
        }

       // new SettingsProvider((Player) sender, configuration, pmSystem).openInventory((Player) sender);
        return true;
    }
}
