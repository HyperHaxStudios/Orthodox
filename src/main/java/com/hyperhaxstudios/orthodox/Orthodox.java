package com.hyperhaxstudios.orthodox;

import com.hyperhaxstudios.orthodox.commands.Fly;
import com.hyperhaxstudios.orthodox.commands.Menu;
import com.hyperhaxstudios.orthodox.commands.Spawn;
import com.hyperhaxstudios.orthodox.commands.setSpawn;
import com.hyperhaxstudios.orthodox.handlers.PlayerHandler;
import com.hyperhaxstudios.orthodox.util.ConfigUtil;
import com.hyperhaxstudios.orthodox.util.SpawnUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Orthodox extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("Hello! Welcome to the Orthodox Plugin!");

        saveDefaultConfig();

        List<String> kitItems = (List<String>) getConfig().getList("kit");
        for (String itemName : kitItems) {
            Bukkit.getLogger().info(itemName);
        }

        ConfigUtil config = new ConfigUtil(this, "test.yml");
        config.getConfig().set("hello", "world");
        config.save();

        SpawnUtil spawnUtil = new SpawnUtil(this);

        getCommand("fly").setExecutor(new Fly());
        getCommand("menu").setExecutor(new Menu(this));
        getCommand("spawn").setExecutor(new Spawn(spawnUtil));
        getCommand("setspawn").setExecutor(new setSpawn(spawnUtil));

        new PlayerHandler(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Goodbye! Hope to see you soon!");
    }
}
