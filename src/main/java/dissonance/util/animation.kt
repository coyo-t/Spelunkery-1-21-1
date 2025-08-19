package dissonance.util

import net.minecraft.client.animation.AnimationChannel


object Animationz
{
	val INTERPOLATION_CONSTANT_NEAR = AnimationChannel.Interpolation { outs, fac, keys, cur, next, scale ->
		outs.set(keys[cur].target()).mul(scale)
	}

	val INTERPOLATION_CONSTANT_FAR = AnimationChannel.Interpolation { outs, fac, keys, cur, next, scale ->
		outs.set(keys[next].target()).mul(scale)
	}


	fun interpolationFromName (name:String)
		= when (name.replace(' ','_').replace('-','_')) {
			"constant",
			"constant_near" -> INTERPOLATION_CONSTANT_NEAR

			"constant_far" -> INTERPOLATION_CONSTANT_FAR

			"linear" -> AnimationChannel.Interpolations.LINEAR

			"catmull",
			"catmull_rom" -> AnimationChannel.Interpolations.CATMULLROM

			else -> AnimationChannel.Interpolations.LINEAR.also {
				println("Bogus interpolation name \"$name\"! returning linear instead")
			}
		}
}

