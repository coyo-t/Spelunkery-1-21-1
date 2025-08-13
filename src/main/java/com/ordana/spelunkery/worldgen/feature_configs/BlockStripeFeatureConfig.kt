package com.ordana.spelunkery.worldgen.feature_configs

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import com.ordana.spelunkery.worldgen.feature_configs.util.StoneEntry
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistryCodecs
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration

class BlockStripeFeatureConfig(
	@JvmField val firstTarget: HolderSet<Block>,
	@JvmField val firstTargetPlacer: MutableList<StoneEntry>,
	@JvmField val useSecondTarget: Boolean,
	@JvmField val secondTarget: HolderSet<Block>?,
	@JvmField val secondTargetPlacer: MutableList<StoneEntry>?,
	@JvmField val useBiomeFilter: Boolean,
	@JvmField val biomes: HolderSet<Biome>?,
	@JvmField val blankPatchChance: Float,
	@JvmField val useHeightFilter: Boolean,
	@JvmField val surfaceOffset: Int,
	@JvmField val bottomOffset: Int
) : FeatureConfiguration
{
	companion object
	{
		@JvmField
		val CODEC = RecordCodecBuilder.create { instance ->
				instance.group(
					RegistryCodecs
						.homogeneousList(Registries.BLOCK)
						.fieldOf("first_target")
						.forGetter(BlockStripeFeatureConfig::firstTarget),
					Codec
						.list(StoneEntry.CODEC)
						.fieldOf("first_target_placer")
						.forGetter(BlockStripeFeatureConfig::firstTargetPlacer),
					Codec
						.BOOL
						.fieldOf("use_second_target")
						.orElse(false)
						.forGetter(BlockStripeFeatureConfig::useSecondTarget),
					RegistryCodecs
						.homogeneousList(Registries.BLOCK)
						.fieldOf("second_target")
						.orElse(null)
						.forGetter(BlockStripeFeatureConfig::secondTarget),
					Codec
						.list(StoneEntry.CODEC)
						.fieldOf("second_target_placer")
						.orElse(null)
						.forGetter(BlockStripeFeatureConfig::secondTargetPlacer),
					Codec
						.BOOL
						.fieldOf("use_biome_filter")
						.orElse(false)
						.forGetter(BlockStripeFeatureConfig::useBiomeFilter),
					RegistryCodecs
						.homogeneousList(Registries.BIOME)
						.fieldOf("biomes")
						.orElse(null)
						.forGetter(BlockStripeFeatureConfig::biomes),
					Codec
						.floatRange(0.0f, 1.0f)
						.fieldOf("blank_patch_chance")
						.orElse(0.0f)
						.forGetter(BlockStripeFeatureConfig::blankPatchChance),
					Codec
						.BOOL
						.fieldOf("use_height_filter")
						.orElse(false)
						.forGetter(BlockStripeFeatureConfig::useHeightFilter),
					Codec
						.intRange(0, 64)
						.fieldOf("surface_offset")
						.orElse(0)
						.forGetter(BlockStripeFeatureConfig::surfaceOffset),
					Codec.intRange(0, 64)
						.fieldOf("bottom_offset")
						.orElse(0)
						.forGetter(BlockStripeFeatureConfig::bottomOffset)
				)
				.apply(instance, ::BlockStripeFeatureConfig)
			}
	}
}
