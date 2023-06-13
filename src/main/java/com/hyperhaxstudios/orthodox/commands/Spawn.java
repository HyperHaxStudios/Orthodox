package com.hyperhaxstudios.orthodox.commands;

import com.hyperhaxstudios.orthodox.util.SpawnUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    private SpawnUtil spawnUtil;

    public Spawn(SpawnUtil spawnUtil) {
        this.spawnUtil = spawnUtil;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            sender.sendMessage("Only players can run this command.");
            return true;
        }

        Player player = (Player) sender;
        spawnUtil.teleport(player);

        return true;
    }
}
