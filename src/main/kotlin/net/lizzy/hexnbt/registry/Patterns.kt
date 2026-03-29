package net.lizzy.hexnbt.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.hex.HexActions
import net.minecraft.core.Registry
import net.lizzy.hexnbt.HexNBT
import net.lizzy.hexnbt.casting.patterns.listkeys
import net.lizzy.hexnbt.casting.patterns.readnbt

object Patterns {
    @JvmStatic
    fun register() {
        Registry.register<ActionRegistryEntry>(
            HexActions.REGISTRY,
            HexNBT.MOD_ID + ":" + "readnbt",
            ActionRegistryEntry(HexPattern.fromAngles("qqqqqeeeee", HexDir.SOUTH_EAST), readnbt())
        )

        Registry.register<ActionRegistryEntry>(
            HexActions.REGISTRY,
            HexNBT.MOD_ID + ":" + "listkeys",
            ActionRegistryEntry(HexPattern.fromAngles("qqqqqeeeeeadaqqq", HexDir.SOUTH_EAST), listkeys())
        )
    }
}