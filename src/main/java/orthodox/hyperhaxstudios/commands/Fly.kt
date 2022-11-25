package orthodox.hyperhaxstudios.commands

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Fly : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Only players can run this command.")
        }
        val player = sender as Player?
        if (player != null) {
            if (player.getAllowFlight()) {
                if (player != null) {
                    player.setAllowFlight(false)
                }
                if (player != null) {
                    player.sendMessage("Flying disabled")
                }
            } else {
                if (player != null) {
                    player.setAllowFlight(true)
                }
                if (player != null) {
                    player.sendMessage("Flying enabled")
                }
            }
        }
        return true
    }
}