package net.lizzy.hexnbt.casting.patterns

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import ram.talia.moreiotas.api.casting.iota.StringIota
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.EntityIota
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag

class listkeys : ConstMediaAction {
    override val argc = 1
    override fun execute(args: List<Iota>, env: CastingEnvironment) : List<Iota>{
        if (args.elementAt(0).type == EntityIota.TYPE) {
            val target = args.getEntity(0)
            var nbt = CompoundTag()
            target.saveWithoutId(nbt)
            var keys = nbt.allKeys.toList()
            var sKeys = mutableListOf<StringIota>()

            keys.forEachIndexed { i, key -> sKeys.add(StringIota.make(key.toString())) }

            return sKeys.asActionResult
        }
        else {
            val pos = args.getVec3(0)
            val target = env.world.getBlockEntity(BlockPos.containing(pos)) ?: return null.asActionResult
            var nbt = target.saveWithoutMetadata()
            var keys = nbt.allKeys.toList()
            var sKeys = mutableListOf<StringIota>()

            keys.forEachIndexed { i, key -> sKeys.add(StringIota.make(key.toString())) }

            return sKeys.asActionResult
        }
    }
}