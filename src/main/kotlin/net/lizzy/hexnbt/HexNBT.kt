package net.lizzy.hexnbt

import net.fabricmc.api.ModInitializer
import net.lizzy.hexnbt.registry.Patterns.register
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HexNBT : ModInitializer {
	override fun onInitialize() {
		register()
	}

	companion object {
		const val MOD_ID: String = "hexnbt"
		val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)
	}
}