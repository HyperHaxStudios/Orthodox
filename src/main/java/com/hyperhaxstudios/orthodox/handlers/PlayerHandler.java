package com.hyperhaxstudios.orthodox.handlers;

import com.hyperhaxstudios.orthodox.Orthodox;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PlayerHandler implements Listener {
    public PlayerHandler(Orthodox plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        ItemStack is = new ItemStack(Material.GRASS, 10);
        Inventory inv = player.getInventory();

        inv.setItem(8, is);
    }
}
