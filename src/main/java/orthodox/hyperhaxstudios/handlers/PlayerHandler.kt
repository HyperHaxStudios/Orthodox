package orthodox.hyperhaxstudios.handlers

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory
import orthodox.hyperhaxstudios.Orthodox
import orthodox.hyperhaxstudios.util.DelayedTask

class PlayerHandler(plugin: Orthodox?) : Listener {
    init {
        if (plugin != null) {
            Bukkit.getPluginManager().registerEvents(this, plugin)
        }
    }

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent?, player: Player?) {
        val item = ItemStack(Material.BARRIER, 1)
        val inv: PlayerInventory? = player?.inventory
        val meta = item.itemMeta
        meta?.setDisplayName("Exit Server")
        item.itemMeta = meta
        inv?.addItem(item)
        inv?.setItem(8, item)
    }

    @EventHandler
    fun onEntityDamage(event: EntityDamageEvent?) {
        if (event != null) {
            if (!(event.entity is Player && event.cause == EntityDamageEvent.DamageCause.FALL)) {
                return
            }
        }
        val player = event?.entity as Player
        val task = DelayedTask({ player.inventory.addItem(ItemStack(Material.DIAMOND)) }, (20 * 3).toLong())
        Bukkit.getScheduler().cancelTask(task.id)
    }
}