package net.orcinus.galosphere.client.animations

import dissonance.util.Animationz
import dissonance.util.LuaCoyote
import net.minecraft.client.animation.AnimationChannel
import net.minecraft.client.animation.AnimationDefinition
import net.minecraft.client.animation.Keyframe
import net.minecraft.client.animation.KeyframeAnimations
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.Galosphere
import party.iroiro.luajava.value.LuaTableValue
import java.io.InputStreamReader

@OnlyIn(Dist.CLIENT)
object GAnims
{
	val ANIM_NON = AnimationDefinition.Builder.withLength(0f).build()

	val L = LuaCoyote().apply {
		openLibraries()
		run("""
		function KF (x, y, z, it)
			return { interpolation=it or 'linear', value={x,y,z} }
		end
		CR = 'catmull-rom'
		""")
		top = 0
	}

	fun getLocalResource (p:String)
		= GAnims::class.java.getResourceAsStream(p)

	fun <K> Map<K, AnimationDefinition>.getAnim (k:K)
		= get(k) ?: ANIM_NON

	fun loadAnims (from:String): Map<String, AnimationDefinition>
	{
		val parseResult = try
		{
//			L.run()
			val resStr = InputStreamReader(getLocalResource(from)!!).use { it.readText() }
			L.run(resStr)
			L.get() as? LuaTableValue ?: throw RuntimeException("Expected a table")
		}
		catch (e: Throwable)
		{
			Galosphere.LOGGER.error("animation script exception", e)
			return mapOf()
		}
		finally
		{
			L.top = 0
		}

		val outAnimations = mutableMapOf<String, AnimationDefinition>()
		for ((k, v) in parseResult.entries)
		{
			val animName = k.toString()
			try
			{
				val animData = v as LuaTableValue
				val animationLength = animData["duration"]?.toNumber()?.toFloat() ?: 0f.also {
					Galosphere.LOGGER.error("animation $animName problem: no animation duration??")
				}
				val bonesTable = animData["bones"] as LuaTableValue
				val newAnim = AnimationDefinition.Builder.withLength(animationLength)
				// This Is Stupid.
				animData["looping"].run {
					L.push(this)
					if (L.toBoolean(-1))
					{
						newAnim.looping()
					}
					L.pop(1)
				}
				for ((bk, bv) in bonesTable.entries)
				{
					val boneName = bk.toString()
					try
					{
						(bv["location"] as? LuaTableValue)?.let { lt ->
							val pData = buildList {
								for ((tk, av) in lt.entries)
								{
									val co = av["value"] as LuaTableValue
									this += Keyframe(
										tk.toNumber().toFloat(),
										KeyframeAnimations.posVec(
											co[1].toNumber().toFloat(),
											co[2].toNumber().toFloat(),
											co[3].toNumber().toFloat(),
										),
										Animationz.interpolationFromName(av["interpolation"].toString())
									)
								}
							}
							newAnim.addAnimation(boneName, AnimationChannel(AnimationChannel.Targets.POSITION, *pData.toTypedArray()))
						}
						(bv["rotation"] as? LuaTableValue)?.let { lt ->
							val pData = buildList {
								for ((tk, av) in lt.entries)
								{
									val co = av["value"] as LuaTableValue
									this += Keyframe(
										tk.toNumber().toFloat(),
										KeyframeAnimations.degreeVec(
											co[1].toNumber().toFloat(),
											co[2].toNumber().toFloat(),
											co[3].toNumber().toFloat(),
										),
										Animationz.interpolationFromName(av["interpolation"].toString())
									)
								}
							}
							newAnim.addAnimation(boneName, AnimationChannel(AnimationChannel.Targets.ROTATION, *pData.toTypedArray()))
						}
						(bv["scale"] as? LuaTableValue)?.let { lt ->
							val pData = buildList {
								for ((tk, av) in lt.entries)
								{
									val co = av["value"] as LuaTableValue
									this += Keyframe(
										tk.toNumber().toFloat(),
										KeyframeAnimations.scaleVec(
											co[1].toNumber(),
											co[2].toNumber(),
											co[3].toNumber(),
										),
										Animationz.interpolationFromName(av["interpolation"].toString())
									)
								}
							}
							newAnim.addAnimation(boneName, AnimationChannel(AnimationChannel.Targets.SCALE, *pData.toTypedArray()))
						}
					}
					catch (e: Throwable)
					{
						Galosphere.LOGGER.error("animation parse error for bone '$boneName'", e)
					}
				}
				outAnimations[animName] = newAnim.build()

			}
			catch (e: Throwable)
			{
				Galosphere.LOGGER.error("animation parse error for '$animName'", e)
			}
		}
		return outAnimations
	}

	object PinkSaltPillar
	{
		val BANK = loadAnims("pink salt pillar.lua")

		val PINK_SALT_PILLAR_EMERGE = BANK.getAnim("emerge")
		val PINK_SALT_PILLAR_RETRACT = BANK.getAnim("retract")
	}

	object PreservedCorpse
	{
		val BANK = loadAnims("preserved corpse.lua")

		val PRESERVED_EMERGING_FLOOR = BANK.getAnim("emerge_from_floor")
		val PRESERVED_EMERGING_CEILING = BANK.getAnim("emerge_from_ceiling")
	}

}

