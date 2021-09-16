package me.alexutzzu.pmsystem.commands.message;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class Message extends Messenger implements CommandExecutor {

    public Message(Chat chat, FileConfiguration configuration) {
        this.chat = chat;
        this.configuration = configuration;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(parseText(configuration.getString("messages.error_not_player")));
            return true;
        }
        if (args.length == 0) {
            sender.sendMessage(parseText(configuration.getString("messages.error_no_player_specified")));
            return true;
        }
        Player receiver = Bukkit.getPlayer(args[0]);
        if (receiver == null) {
            sender.sendMessage(parseText(configuration.getString("messages.error_player_not_found")));
            return true;
        }
        if (args.length == 1) {
            sender.sendMessage(parseText(configuration.getString("messages.error_no_message")));
            return true;
        }
        StringBuilder message = new StringBuilder();
        for (int i = 1; i < args.length; i++) {
            message.append(args[i]);
            if (i < args.length - 1) message.append(" ");
        }

        sendMessage((Player) sender, receiver, message.toString());
        return true;
    }

}
