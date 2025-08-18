package net.orcinus.galosphere.world.gen

import dissonance.util.noizor.FastNoise
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.world.level.block.Block
import net.orcinus.galosphere.init.GBlocks
import kotlin.math.abs

object PinkSaltUtil
{
	@JvmStatic
	fun getBlock(seed: Long, blockPos: BlockPos): Block?
	{
		return getBlock(seed, blockPos.mutable())
	}

	@JvmStatic
	fun getBlock(seed: Long, mutable: MutableBlockPos): Block?
	{
		return getBlock(
			seed.toInt(),
			mutable,
			GBlocks.PASTEL_PINK_SALT.get(),
			GBlocks.ROSE_PINK_SALT.get(),
			GBlocks.PINK_SALT.get()
		)
	}

	@JvmStatic
	fun getBlock(seed: Long, blockPos: BlockPos, pastel: Block?, rose: Block?, normal: Block?): Block?
	{
		return getBlock(seed.toInt(), blockPos.mutable(), pastel, rose, normal)
	}

	@JvmStatic
	fun getBlock(seed: Int, mutable: MutableBlockPos, pastel: Block?, rose: Block?, normal: Block?): Block?
	{
		val fastNoise = FastNoise(seed)
		fastNoise.SetNoiseType(FastNoise.NoiseType.SimplexFractal)
		fastNoise.SetFractalOctaves(1)
		fastNoise.SetFractalGain(0.3f)
		fastNoise.SetFrequency(0.07f)
		val noise2 = (abs(fastNoise.GetNoise(mutable.getX().toFloat(), mutable.getZ().toFloat()) + 1) * 3).toDouble()
		val block: Block?
		if (noise2 > 4.0)
		{
			block = pastel
		}
		else if (noise2 > 3.0)
		{
			block = rose
		}
		else
		{
			block = normal
		}
		return block
	}
}
