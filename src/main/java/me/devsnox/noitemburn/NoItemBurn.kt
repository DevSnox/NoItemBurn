package me.devsnox.noitemburn

import org.bukkit.Material
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftEntity
import org.bukkit.entity.Entity
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerDropItemEvent
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

    private val permission = "noitemburn.use"

    override fun onEnable() {
        this.server.pluginManager.registerEvents(this, this)
    }

    @EventHandler(priority = EventPriority.LOW)
    fun onBreak(event: BlockBreakEvent) {
        if(event.player.hasPermission(this.permission)) {
            val block = event.block
            val drops = block.drops

            block.type = Material.AIR
            drops.forEach { this.fireProtection(block.world.dropItem(block.location, it)) }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    fun onDrop(event: PlayerDropItemEvent) {
        if(event.player.hasPermission(this.permission)) {
            this.fireProtection(event.itemDrop)
        }
    }

    private fun fireProtection(entity: Entity) : Entity {
        val field = Entity::class.java.getDeclaredField("fireProof")

        field.isAccessible = true
        field.setBoolean((entity as CraftEntity).handle, true)

        return entity
    }
}
