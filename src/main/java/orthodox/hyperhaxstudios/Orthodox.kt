package orthodox.hyperhaxstudios

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import orthodox.hyperhaxstudios.commands.Fly
import orthodox.hyperhaxstudios.commands.Menu
import orthodox.hyperhaxstudios.handlers.PlayerHandler
import orthodox.hyperhaxstudios.handlers.TorchHandler
import orthodox.hyperhaxstudios.util.ConfigUtil
import orthodox.hyperhaxstudios.util.DelayedTask
import orthodox.hyperhaxstudios.util.Scoreboard
import orthodox.hyperhaxstudios.util.SpawnUtil

class Orthodox : JavaPlugin() {
    override fun onEnable() {
        // Plugin startup logic
        logger.info("Hello! Welcome to the Orthodox Plugin!")
        saveDefaultConfig()
        val kitItems = config.getList("kit") as MutableList<String?>?
        if (kitItems != null) {
            for (itemName in kitItems) {
                Bukkit.getLogger().info(itemName)
            }
        }
        val config = ConfigUtil(this, "test.yml")
        config.config?.set("hello", "world")
        config.save()
        val spawnUtil = SpawnUtil(this)
        getCommand("fly")?.setExecutor(Fly())
        getCommand("menu")?.setExecutor(Menu(this))
        TorchHandler(this)
        PlayerHandler(this)
        DelayedTask(this)
        Scoreboard(this)
    }

    override fun onDisable() {
        // Plugin shutdown logic
        Bukkit.getLogger().info("Goodbye! Hope to see you soon!")
    }
}