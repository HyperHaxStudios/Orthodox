package com.hyperhaxstudios.orthodox.commands;

import com.hyperhaxstudios.orthodox.Orthodox;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Listener, CommandExecutor {
    private String invName = "Orthodox Menu";

    public Menu(Orthodox plugin) {
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().equals(invName)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        int slot = event.getSlot();

        event.setCancelled(true);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        Player player = (Player) sender;

        Inventory inv = Bukkit.createInventory(player, 9 * 3, invName);

        inv.setItem(11, getItem(new ItemStack(Material.DIAMOND_SWORD), "&9PVP", "&aClick to Join", "&aBattle other players!"));
        inv.setItem(13, getItem(new ItemStack(Material.BEACON), "&9Creative Plots", "&aClick to Join", "&aBuild structures in our creative plots"));
        inv.setItem(15, getItem(new ItemStack(Material.GRASS_BLOCK), "&9Skyblock", "&aClick to Join", "&aHow long can you survive?"));
        inv.setItem(15, getItem(new ItemStack(Material.GRASS_BLOCK), "&9Skyblock", "&aClick to Join", "&aHow long can you survive?"));

        player.openInventory(inv);

            return true;
    }

    private ItemStack getItem(ItemStack is, String name, String ... lore) {
        ItemMeta meta = is.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));

        List<String> lores = new ArrayList<>();
        for (String s : lore) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);

        is.setItemMeta(meta);
        return is;
    }
}
