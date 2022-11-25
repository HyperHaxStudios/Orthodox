package orthodox.hyperhaxstudios.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerRespawnEvent
import orthodox.hyperhaxstudios.Orthodox
import java.util.logging.Level

class SpawnUtil(plugin: Orthodox?) : Listener {
    private val config: ConfigUtil?
    private var spawn: Location? = null

    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
        config = ConfigUtil(plugin, "spawn.yml")
        val worldName = config.getConfig().getString("world")
        val x = config.config.getDouble("x")
        val y = config.config.getDouble("y")
        val z = config.config.getDouble("z")
        val yaw = config.config.getDouble("yaw").toFloat()
        val pitch = config.config.getDouble("pitch").toFloat()
        if (worldName != null) {
            val world = Bukkit.getWorld(worldName)
            if (world == null) {
                Bukkit.getLogger().log(Level.SEVERE, "The world \"$worldName\" does not exist")
                return
            }
            spawn = Location(world, x, y, z, yaw, pitch)
        }
    }

    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent?) {
        if (spawn != null) {
            event.setRespawnLocation(spawn)
        }
    }

    fun teleport(player: Player?) {
        if (spawn == null) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThe spawn is not set"))
            return
        }
        player.teleport(spawn)
    }

    fun set(spawn: Location?) {
        this.spawn = spawn
        val worldName = spawn.getWorld().getName()
        val x = spawn.getX()
        val y = spawn.getY()
        val z = spawn.getZ()
        val yaw = spawn.getYaw()
        val pitch = spawn.getPitch()
        config.getConfig()["world"] = worldName
        config.getConfig()["x"] = x
        config.getConfig()["y"] = y
        config.getConfig()["z"] = z
        config.getConfig()["yaw"] = yaw
        config.getConfig()["pitch"] = pitch
        config.save()
    }
}