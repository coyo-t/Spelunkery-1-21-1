package net.orcinus.galosphere.client.particles

import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import net.minecraft.client.Camera
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.util.Mth
import net.minecraft.world.phys.Vec3
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn
import net.orcinus.galosphere.blocks.MonstrometerBlock
import org.joml.Vector3f
import kotlin.math.max

@OnlyIn(Dist.CLIENT)
class IndicatorParticle(
	world: ClientLevel,
	x: Double,
	y: Double,
	z: Double,
	pQuadSizeMulitiplier: Double,
	sprites: SpriteSet
) : TextureSheetParticle(world, x, y, z)
{
	private val sprites: SpriteSet

	init
	{
		alpha = 1f
		quadSize = 0f
		lifetime = 48

		rCol = this.color.x()
		gCol = this.color.y()
		bCol = this.color.z()

		setSpriteFromAge(sprites.also { this.sprites = it })
	}

	private val color
		get() = if (MonstrometerBlock.isUnsafe(level, BlockPos.containing(x, y, z)))
			UNSAFE_COLOR
		else
			SAFE_COLOR

	override fun tick()
	{
		xo = x
		yo = y
		zo = z

		quadSize = Mth.lerp(0.06f, quadSize, 0.5f)

		rCol = Mth.lerp(0.25f, rCol, this.color.x())
		gCol = Mth.lerp(0.25f, gCol, this.color.y())
		bCol = Mth.lerp(0.25f, bCol, this.color.z())

		if (age++ >= lifetime)
		{
			remove()
		}
		else
		{
			if (age > (lifetime / 2))
			{
				alpha -= 0.04f
			}
		}

		setSpriteFromAge(sprites)
	}

	override fun getRenderType(): ParticleRenderType
	{
		return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT
	}

	override fun render(consumer: VertexConsumer, camera: Camera, delta: Float)
	{
		this.renderRotatedQuad(consumer, camera, Axis.XP.rotation(Mth.PI / 2), delta)
		this.renderRotatedQuad(consumer, camera, Axis.XN.rotation(Mth.PI / 2), delta)
	}

	override fun getLightColor(tint: Float): Int
	{
		return max(50, super.getLightColor(tint))
	}

	@OnlyIn(Dist.CLIENT)
	class Provider(private val sprites: SpriteSet) : ParticleProvider<SimpleParticleType>
	{
		override fun createParticle(
			pType: SimpleParticleType,
			pLevel: ClientLevel,
			pX: Double,
			pY: Double,
			pZ: Double,
			speed: Double,
			pYSpeed: Double,
			pZSpeed: Double
		): Particle
		{
			return IndicatorParticle(pLevel, pX, pY, pZ, speed, sprites)
		}
	}

	companion object
	{
		private val SAFE_COLOR = Vec3.fromRGB24(0xFFB219).toVector3f()
		private val UNSAFE_COLOR = Vec3.fromRGB24(0x93B9FF).toVector3f()
	}
}