package me.alexutzzu.pmsystem.commands.message;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public abstract class Messenger {
    protected static HashMap<Player, Player> correspondence = new HashMap<>();
    protected FileConfiguration configuration;
    protected Chat chat;
    protected void sendMessage(Player sender, Player receiver, String message){
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&',
                configuration.getString("format.sender")
                        .replaceAll("\\$prefix", chat.getPlayerPrefix(receiver))
                        .replaceAll("\\$player", receiver.getName())
                        .replaceAll("\\$message", message)
        ));
        receiver.sendMessage(ChatColor.translateAlternateColorCodes('&',
                configuration.getString("format.receiver")
                        .replaceAll("\\$prefix", chat.getPlayerPrefix(sender))
                        .replaceAll("\\$player", receiver.getName())
                        .replaceAll("\\$message", receiver.getName())
        ));
        receiver.playSound(receiver.getLocation(), Sound.valueOf(configuration.getString("sound.pm_sound")), 1, 1);
        Messenger.correspondence.put(sender, receiver);
        Messenger.correspondence.put(receiver, sender);
    }

    protected String parseText(String text){
        return ChatColor.translateAlternateColorCodes('&', text.replaceAll("\\$pluginPrefix", configuration.getString("messages.prefix")));
    }
}
