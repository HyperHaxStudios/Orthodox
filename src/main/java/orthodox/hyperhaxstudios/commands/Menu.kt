package orthodox.hyperhaxstudios.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import orthodox.hyperhaxstudios.Orthodox

class Menu(plugin: Orthodox?) : Listener, CommandExecutor {
    private val invName: String? = "Server Menu"

    init {
        if (plugin != null) {
            Bukkit.getPluginManager().registerEvents(this, plugin)
        }
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent?) {
        if (event != null) {
            if (event.view.title != invName) {
                return
            }
        }
        val player = event?.whoClicked as Player
        val slot = event?.slot
        event?.isCancelled = true
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage("Only players can use this command.")
            return true
        }
        val player = sender as Player?
        val inv = invName?.let { Bukkit.createInventory(player, 9 * 3, it) }
        inv?.setItem(11, getItem(ItemStack(Material.DIAMOND_SWORD), "&9PVP", "&aClick to Join", "&aBattle it out in our arena"))
        inv?.setItem(13, getItem(ItemStack(Material.GRASS_BLOCK), "&9Skyblock", "&aClick to Join", "&aHow long can you survive?"))
        inv?.setItem(15, getItem(ItemStack(Material.BEACON), "&9Back to Lobby", "&aClick to go back to Lobby"))
        inv?.setItem(27, getItem(ItemStack(Material.BARRIER), "&9Coming Soon..."))
        if (inv != null) {
            player?.openInventory(inv)
        }
        return true
    }

    private fun getItem(item: ItemStack?, name: String?, vararg lore: String?): ItemStack? {
        val meta = item?.itemMeta
        meta?.setDisplayName(name?.let { ChatColor.translateAlternateColorCodes('&', it) })
        val lores: MutableList<String?> = ArrayList()
        for (s in lore) {
            lores.add(s?.let { ChatColor.translateAlternateColorCodes('&', it) })
        }
        if (meta != null) {
            meta.lore = lores
        }
        if (item != null) {
            item.itemMeta = meta
        }
        return item
    }
}