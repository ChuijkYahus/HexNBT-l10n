package net.lizzy.hexnbt.casting.patterns

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.utils.asList
import ram.talia.moreiotas.api.asActionResult
import net.minecraft.nbt.CompoundTag
import ram.talia.moreiotas.api.getString

class readnbt : ConstMediaAction {
    override val argc = 2
    override fun execute(args: List<Iota>, env: CastingEnvironment) : List<Iota>{
        val target = args.getEntity(0)
        val key = args.getString(1)
        var nbt = CompoundTag()
        target.saveWithoutId(nbt)
        val value = nbt.get(key)

        return value.toString().asActionResult
    }
}