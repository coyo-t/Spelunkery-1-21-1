package net.orcinus.galosphere.world.gen.features.config

import com.mojang.datafixers.util.Function6
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration
import net.minecraft.world.level.levelgen.placement.CaveSurface
import java.util.function.Function

@JvmRecord
data class CrystalSpikeConfig(
	@JvmField val crystal_state: BlockState,
	@JvmField val cluster_state: BlockState,
	@JvmField val glinted_cluster: BlockState,
	@JvmField val xzRadius: IntProvider,
	@JvmField val crystal_direction: CaveSurface,
	@JvmField val glinted_cluster_chance: Float
) : FeatureConfiguration
{
	companion object
	{
		val CODEC = RecordCodecBuilder.create { codec ->
			codec.group(
				BlockState.CODEC.fieldOf("crystal_state").forGetter(CrystalSpikeConfig::crystal_state),
				BlockState.CODEC.fieldOf("cluster_state").forGetter(CrystalSpikeConfig::cluster_state),
				BlockState.CODEC.fieldOf("glinted_cluster").forGetter(CrystalSpikeConfig::glinted_cluster),
				IntProvider.CODEC.fieldOf("xz_radius").forGetter(CrystalSpikeConfig::xzRadius),
				CaveSurface.CODEC.fieldOf("crystal_direction").forGetter(CrystalSpikeConfig::crystal_direction),
				Codec.floatRange(0f, 1f).fieldOf("glinted_cluster_chance").forGetter(CrystalSpikeConfig::glinted_cluster_chance)
			).apply(codec, ::CrystalSpikeConfig)
		}
	}
}
