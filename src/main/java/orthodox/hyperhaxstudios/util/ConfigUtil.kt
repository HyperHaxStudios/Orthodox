package orthodox.hyperhaxstudios.util

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

class ConfigUtil(path: String?) {
    private val file: File?
    val config: FileConfiguration?

    constructor(plugin: Plugin?, path: String?) : this((plugin?.dataFolder?.absolutePath) + "/" + path)

    init {
        file = File(path)
        config = YamlConfiguration.loadConfiguration(file)
    }

    fun save(): Boolean {
        return try {
            if (file != null) {
                config?.save(file)
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun getFile(): File? {
        return file
    }

    @JvmName("getConfig1")
    fun getConfig(): FileConfiguration? {
        return config
    }
}