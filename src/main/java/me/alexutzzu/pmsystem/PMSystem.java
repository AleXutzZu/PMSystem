package me.alexutzzu.pmsystem;

import me.alexutzzu.pmsystem.commands.message.Message;
import me.alexutzzu.pmsystem.commands.message.Reply;
import me.alexutzzu.pmsystem.commands.Settings;
import me.alexutzzu.pmsystem.util.Storage;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class PMSystem extends JavaPlugin {
    static PMSystem pmSystem;
    private Storage storage;
    private Chat chat = null;
    private Permission permission = null;

    @Override
    public void onEnable() {
        pmSystem = this;
        this.getLogger().info("Starting up...");
        this.setUpConfig();
        this.setUpPermissions();
        if (this.setUpChat()) {
            this.getLogger().info("Successfully loaded chat.");
        } else {
            this.getLogger().severe("An error occurred while setting up. Plugin will be disabled.");
            this.getServer().getPluginManager().disablePlugin(this);
        }

        this.getCommand("msg").setExecutor(new Message(chat, this.getConfig()));
        this.getCommand("reply").setExecutor(new Reply(chat, this.getConfig()));
        this.getCommand("settings").setExecutor(new Settings(this.getConfig(),this));
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Plugin disabled.");
    }

    private boolean setUpChat() {
        final RegisteredServiceProvider<Chat> rsp = this.getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setUpPermissions() {
        final RegisteredServiceProvider<Permission> rsp = this.getServer().getServicesManager().getRegistration(Permission.class);
        permission = rsp.getProvider();
        return permission != null;
    }

    private void setUpConfig() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        this.storage = new Storage(this);
    }

    public Permission getPermission() {
        return permission;
    }

    public Storage getStorage() {
        return storage;
    }

    public static PMSystem getInstance() {
        return pmSystem;
    }

}
