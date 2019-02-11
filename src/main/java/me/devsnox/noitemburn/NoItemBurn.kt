package me.devsnox.noitemburn

import net.minecraft.server.v1_8_R3.Entity
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by DevSnox on 12.02.18
 * Copyright (c) 2018 DevSnox
 * GitHub: https://github.com/DevSnox
 * Web: http://devsnox.me
 * Mail: me.devsnox@gmail.com
 * Discord: DevSnox#4884 | Skype: live:chaos3729
 */
class NoItemBurn : JavaPlugin(), Listener {

    override fun onEnable() {
        this.server.pluginManager.registerEvents(this, this)
    }

    @EventHandler(priority = EventPriority.LOWEST)
    fun onDrop(event: EntitySpawnEvent) {
        if (event.entity.type == EntityType.DROPPED_ITEM) {
            val field = Entity::class.java.getDeclaredField("fireProof")
            field.isAccessible = true
            field.setBoolean((event.entity as CraftEntity).handle, true)
        }
    }
}
