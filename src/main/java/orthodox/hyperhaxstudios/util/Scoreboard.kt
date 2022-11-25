package orthodox.hyperhaxstudios.util

import fr.mrmicky.fastboard.FastBoard
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import orthodox.hyperhaxstudios.Orthodox
import java.util.*

class Scoreboard(plugin: Orthodox?) : Listener {
    private val boards: MutableMap<UUID?, FastBoard?>? = HashMap()

    init {
        if (plugin != null) {
            Bukkit.getPluginManager().registerEvents(this, plugin)
        }
        for (board in boards?.values!!) {
            if (board != null) {
                updateBoard(board,
                        "",
                        "&aOnline:",
                        "&c" + Bukkit.getOnlinePlayers().size,
                        "",
                        "&aY-Coord:",
                        "&c" + board.player.location.blockY)
            }
        }
        plugin?.server?.scheduler?.scheduleSyncRepeatingTask(plugin, Runnable {}, 0L, 10L)
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent?) {
        val player = event?.player
        val board = FastBoard(player)
        val title = "&c&lWelcome!"
        board.updateTitle(ChatColor.translateAlternateColorCodes('&', title))
        if (player != null) {
            boards?.set(player.uniqueId, board)
        }
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent?) {
        val player = event?.player
        val board = boards?.remove(player?.uniqueId)
        board?.delete()
    }

    private fun updateBoard(board: FastBoard?, vararg lines: String?) {
        for (a in lines.indices) {
            lines[a] = lines[a]?.let { ChatColor.translateAlternateColorCodes('&', it) } as Nothing
        }
        board?.updateLines(*lines)
    }
}