package orthodox.hyperhaxstudios.handlers

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import orthodox.hyperhaxstudios.Orthodox

class TorchHandler(plugin: Orthodox?) : Listener {
    init {
        if (plugin != null) {
            Bukkit.getPluginManager().registerEvents(this, plugin)
        }
    }

    @EventHandler
    fun onTorchPlace(event: BlockPlaceEvent?) {
        if (event != null) {
            if (event.block.type != Material.TORCH) {
                return
            }
        }
        if (event != null) {
            if (!event.player.hasPermission("orthodox.hyperhaxstudios.torch.diamond")) {
                return
            }
        }
        if (event != null) {
            event.block.type = Material.DIAMOND_BLOCK
        }
    }
}