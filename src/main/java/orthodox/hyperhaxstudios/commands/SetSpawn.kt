package orthodox.hyperhaxstudios.commands

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import orthodox.hyperhaxstudios.util.SpawnUtil

abstract class SetSpawn(private val spawnUtil: SpawnUtil?) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Only players can run this command")
            return true
        }
        val player = sender as Player?
        spawnUtil?.set(player?.location)
        player?.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSet Server Spawn"))
        return true
    }
}