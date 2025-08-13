package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.worldgen.feature_configs.BlockStripeFeatureConfig
import com.ordana.spelunkery.worldgen.feature_configs.HugeConkFungusFeatureConfig
import com.ordana.spelunkery.worldgen.feature_configs.HugeForkingMushroomFeatureConfig
import com.ordana.spelunkery.worldgen.feature_configs.WallMushroomFeatureConfig
import com.ordana.spelunkery.worldgen.features.BlockStripeFeature
import com.ordana.spelunkery.worldgen.features.HugeConkFungusFeature
import com.ordana.spelunkery.worldgen.features.HugeForkingMushroomFeature
import com.ordana.spelunkery.worldgen.features.WallMushroomFeature
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.placement.PlacementUtils
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.GenerationStep.Carving
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.neoforged.neoforge.registries.DeferredRegister

object ModWorldgenFeatures
{
	@JvmField
	val FEATS = DeferredRegister.create(Registries.FEATURE, Spelunkery.MOD_ID)

	val HUGE_CONK_FEATURE = FEATS.register("huge_conk") { res ->
		HugeConkFungusFeature(HugeConkFungusFeatureConfig.CODEC)
	}

	val HUGE_FORKING_MUSHROOM_FEATURE = FEATS.register("huge_forking_mushroom") { res ->
		HugeForkingMushroomFeature(HugeForkingMushroomFeatureConfig.CODEC)
	}

	val WALL_MUSHROOM_FEATURE = FEATS.register("wall_mushroom") { res ->
		WallMushroomFeature(WallMushroomFeatureConfig.CODEC)
	}

	val BLOCK_STRIPE_FEATURE = FEATS.register("block_stripe") { res ->
		BlockStripeFeature(BlockStripeFeatureConfig.CODEC)
	}

	fun init()
	{
		//carver generation

		val end_cave =
			ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res("end_cave"))
		addCarverToBiome(Carving.AIR, ModTags.HAS_END_NOISE, end_cave)

		val end_cave_extra =
			ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res("end_cave_extra"))
		addCarverToBiome(Carving.AIR, ModTags.HAS_END_NOISE, end_cave_extra)

		val end_canyon =
			ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res("end_canyon"))
		addCarverToBiome(Carving.AIR, ModTags.HAS_END_NOISE, end_canyon)

		val crevice =
			ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res("crevice"))
		addCarverToBiome(Carving.AIR, ModTags.HAS_STONE_NOISE, crevice)

		//stone generation
		val noise_stone = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_stone"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_STONE_NOISE, noise_stone)

		val noise_lush = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_lush"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_LUSH_NOISE, noise_lush)

		val noise_dirt = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_dirt"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_DIRT_NOISE, noise_dirt)

		val noise_ocean = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_ocean"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_OCEAN_NOISE, noise_ocean)


		val noise_ice = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_ice"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_ICE_NOISE, noise_ice)

		val noise_sculk = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_sculk"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SCULK_NOISE, noise_sculk)

		val noise_end = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_end"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_END_NOISE, noise_end)

		val noise_iron = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_iron"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_STONE_NOISE, noise_iron)


		//ores
		val spring_water_pool =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("spring_water_pool"))
		addFeatureToBiome(GenerationStep.Decoration.FLUID_SPRINGS, ModTags.HAS_SALT_NOISE, spring_water_pool)

		val spring_water_spring =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("spring_water_spring"))
		addFeatureToBiome(GenerationStep.Decoration.FLUID_SPRINGS, ModTags.HAS_SALT_NOISE, spring_water_spring)

		val ore_aquifer = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("ore_aquifer"))
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, ore_aquifer)

		val diamond_ore_lava =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("diamond_ore_lava"))
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, diamond_ore_lava)

		val nephrite_geode =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("nephrite_geode"))
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, nephrite_geode)

		val magnetite_geode =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("magnetite_geode"))
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_NETHER, magnetite_geode)

		val quartz_geode = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("quartz_geode"))
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_NETHER, quartz_geode)


		//veins
		val large_gold_vein =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("large_gold_vein"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_NETHER_NOISE, large_gold_vein)

		val large_coal_vein =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("large_coal_vein"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SWAMP_NOISE, large_coal_vein)

		val large_emerald_vein =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("large_emerald_vein"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SALT_NOISE, large_emerald_vein)

		val large_lapis_vein =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("large_lapis_vein"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_DESERT_NOISE, large_lapis_vein)


		//vegetation
		val tangle_roots_ceiling =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("tangle_roots_ceiling"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, tangle_roots_ceiling)

		val conk_fungus = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("conk_fungus"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, conk_fungus)

		val conk_fungus_surface =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("conk_fungus_surface"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, conk_fungus_surface)

		val inkcap = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("inkcap"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, inkcap)

		val inkcap_deepslate =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("inkcap_deepslate"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, inkcap_deepslate)

		val portabella = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("portabella"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, portabella)

		val phosphor_fungus =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("phosphor_fungus"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, phosphor_fungus)

		val mushgloom = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("mushgloom"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, mushgloom)

		val rare_huge_mushroom =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("rare_huge_mushroom"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, rare_huge_mushroom)

		val deep_dark_fossil =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("deep_dark_fossil"))
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, ModTags.HAS_SCULK_NOISE, deep_dark_fossil)

		val portal_fluid_pool =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("portal_fluid_pool"))
		addFeatureToBiome(GenerationStep.Decoration.SURFACE_STRUCTURES, ModTags.HAS_END_NOISE, portal_fluid_pool)

		val portal_fluid_spring =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("portal_fluid_spring"))
		addFeatureToBiome(GenerationStep.Decoration.SURFACE_STRUCTURES, ModTags.HAS_END_NOISE, portal_fluid_spring)

		val obsidian_patch =
			ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("obsidian_patch"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_END_NOISE, obsidian_patch)

		val sulfur_patch = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("sulfur_patch"))
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SULFUR_PATCHES, sulfur_patch)
	}


	fun addFeatureToBiome(
		step: GenerationStep.Decoration,
		tagKey: TagKey<Biome>,
		feature: ResourceKey<PlacedFeature>
	)
	{
	}


	fun addCarverToBiome(
		step: Carving,
		tagKey: TagKey<Biome>,
		feature: ResourceKey<ConfiguredWorldCarver<*>>,
	)
	{
	}
}
