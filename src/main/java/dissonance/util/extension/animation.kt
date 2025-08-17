package dissonance.util.extension

import net.minecraft.client.animation.AnimationDefinition

fun buildAnimation (len:Number, cb: AnimationDefinition.Builder.()->Unit): AnimationDefinition
{
	with (AnimationDefinition.Builder.withLength(len.toFloat()))
	{
		cb()
		return build()
	}
}