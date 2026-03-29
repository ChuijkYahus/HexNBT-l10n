package net.lizzy.hexnbt.casting.patterns

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import ram.talia.moreiotas.api.asActionResult as stringActionResult
import at.petrak.hexcasting.api.casting.asActionResult
import at.petrak.hexcasting.api.casting.getVec3
import at.petrak.hexcasting.api.casting.iota.EntityIota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadCaster
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import ram.talia.moreiotas.api.getString

class readnbt : ConstMediaAction {
    override val argc = 2
    override fun execute(args: List<Iota>, env: CastingEnvironment) : List<Iota>{
        if (!env.isEnlightened) { throw MishapBadCaster() }

        if (args.elementAt(0).type == EntityIota.TYPE) {
            val target = args.getEntity(0)

            env.assertEntityInRange(target)

            val key = args.getString(1)
            var nbt = CompoundTag()
            target.saveWithoutId(nbt)
            val value = nbt.get(key) ?: return null.asActionResult

            return value.toString().stringActionResult
        }
        else {
            val pos = args.getVec3(0)

            env.assertVecInRange(pos)

            val key = args.getString(1)
            val target = env.world.getBlockEntity(BlockPos.containing(pos)) ?: return null.asActionResult
            var nbt = target.saveWithoutMetadata()
            val value = nbt.get(key) ?: return null.asActionResult

            return value.toString().stringActionResult
        }
    }
}