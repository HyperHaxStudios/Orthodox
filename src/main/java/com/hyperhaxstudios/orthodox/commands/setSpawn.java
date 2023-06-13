package com.hyperhaxstudios.orthodox.commands;

import com.hyperhaxstudios.orthodox.util.SpawnUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setSpawn implements CommandExecutor {
    private SpawnUtil spawnUtil;

    public setSpawn(SpawnUtil spawnUtil) {
        this.spawnUtil = spawnUtil;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        Player player = (Player) sender;
        spawnUtil.set(player.getLocation());

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aServer Spawn has been set"));

        return true;
    }
}
