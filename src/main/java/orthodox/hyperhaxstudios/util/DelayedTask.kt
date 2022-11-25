package orthodox.hyperhaxstudios.util

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.Plugin

class DelayedTask : Listener {
    var id = -1

    constructor(instance: Plugin?) {
        plugin = instance
    }

    @JvmOverloads
    constructor(runnable: Runnable?, delay: Long = 0) {
        if (plugin?.isEnabled == true) {
            id = runnable?.let { Bukkit.getScheduler().scheduleSyncDelayedTask(plugin!!, it, delay) }!!
        } else {
            runnable?.run()
        }
    }

    @JvmName("getId1")
    fun getId(): Int {
        return id
    }

    companion object {
        private var plugin: Plugin? = null
    }
}