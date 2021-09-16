package me.alexutzzu.pmsystem.commands.message;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class Reply extends Messenger implements CommandExecutor  {
    public Reply(Chat chat, FileConfiguration configuration) {
        this.chat = chat;
        this.configuration = configuration;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(parseText(configuration.getString("messages.error_not_player")));
            return true;
        }
        if (Messenger.correspondence.get(sender) == null) {
            sender.sendMessage(parseText(configuration.getString("messages.error_no_one_to_reply_to")));
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage(parseText(configuration.getString("messages.error_no_message")));
            return true;
        }

        Player receiver = Messenger.correspondence.get(sender);
        if (receiver == null) {
            sender.sendMessage(parseText(configuration.getString("messages.error_player_not_found")));
            return true;
        }
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            message.append(args[i]);
            if (i < args.length - 1) message.append(" ");
        }
        sendMessage((Player) sender, receiver, message.toString());
        return true;
    }
}
