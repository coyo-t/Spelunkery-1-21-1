package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.worldgen.features.BlockStripeFeature
import com.ordana.spelunkery.worldgen.features.HugeConkFungusFeature
import com.ordana.spelunkery.worldgen.features.HugeForkingMushroomFeature
import com.ordana.spelunkery.worldgen.features.WallMushroomFeature
import net.minecraft.core.registries.Registries
import net.neoforged.neoforge.registries.DeferredRegister

object ModWorldgenFeatures
{
	@JvmField
	val FEATS = DeferredRegister.create(Registries.FEATURE, Spelunkery.MOD_ID)

	val HUGE_CONK_FEATURE = FEATS.register("huge_conk", ::HugeConkFungusFeature)
	val HUGE_FORKING_MUSHROOM_FEATURE = FEATS.register("huge_forking_mushroom", ::HugeForkingMushroomFeature)
	val WALL_MUSHROOM_FEATURE = FEATS.register("wall_mushroom", ::WallMushroomFeature)
	val BLOCK_STRIPE_FEATURE = FEATS.register("block_stripe", ::BlockStripeFeature)

//	private val String.pfKey get() = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res(this))
//	private val String.ccKey get() = ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res(this))

//	fun initFeaturez (context: BootstrapContext<PlacedFeature>)
//	{
//
//		fun addFeatureToBiome(
//			step: GenerationStep.Decoration,
//			tagKey: TagKey<Biome>,
//			feature: ResourceKey<PlacedFeature>
//		)
//		{
//		}
//
//		//stone generation
//		val noise_stone = "noise_stone".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_STONE_NOISE, noise_stone)
//
//		val noise_lush = "noise_lush".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_LUSH_NOISE, noise_lush)
//
//		val noise_dirt = "noise_dirt".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_DIRT_NOISE, noise_dirt)
//
//		val noise_ocean = "noise_ocean".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_OCEAN_NOISE, noise_ocean)
//
//
//		val noise_ice = "noise_ice".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_ICE_NOISE, noise_ice)
//
//		val noise_sculk = "noise_sculk".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_SCULK_NOISE, noise_sculk)
//
//		val noise_end = "noise_end".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_END_NOISE, noise_end)
//
//		val noise_iron = "noise_iron".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_STONE_NOISE, noise_iron)
//
//
//		//ores
//		val spring_water_pool = "spring_water_pool".pfKey
//		addFeatureToBiome(FLUID_SPRINGS, ModTags.HAS_SALT_NOISE, spring_water_pool)
//
//		val spring_water_spring = "spring_water_spring".pfKey
//		addFeatureToBiome(FLUID_SPRINGS, ModTags.HAS_SALT_NOISE, spring_water_spring)
//
//		val ore_aquifer = "ore_aquifer".pfKey
//		addFeatureToBiome(UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, ore_aquifer)
//
//		val diamond_ore_lava = "diamond_ore_lava".pfKey
//		addFeatureToBiome(UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, diamond_ore_lava)
//
//		val nephrite_geode = "nephrite_geode".pfKey
//		addFeatureToBiome(UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, nephrite_geode)
//
//		val magnetite_geode = "magnetite_geode".pfKey
//		addFeatureToBiome(UNDERGROUND_ORES, BiomeTags.IS_NETHER, magnetite_geode)
//
//		val quartz_geode = "quartz_geode".pfKey
//		addFeatureToBiome(UNDERGROUND_ORES, BiomeTags.IS_NETHER, quartz_geode)
//
//		//veins
//		val large_gold_vein = "large_gold_vein".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_NETHER_NOISE, large_gold_vein)
//
//		val large_coal_vein = "large_coal_vein".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_SWAMP_NOISE, large_coal_vein)
//
//		val large_emerald_vein = "large_emerald_vein".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_SALT_NOISE, large_emerald_vein)
//
//		val large_lapis_vein = "large_lapis_vein".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_DESERT_NOISE, large_lapis_vein)
//
//
//		//vegetation
//		val tangle_roots_ceiling = "tangle_roots_ceiling".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, tangle_roots_ceiling)
//
//		val conk_fungus = "conk_fungus".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, conk_fungus)
//
//		val conk_fungus_surface = "conk_fungus_surface".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, conk_fungus_surface)
//
//		val inkcap = "inkcap".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, inkcap)
//
//		val inkcap_deepslate = "inkcap_deepslate".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, inkcap_deepslate)
//
//		val portabella = "portabella".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, portabella)
//
//		val phosphor_fungus = "phosphor_fungus".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, phosphor_fungus)
//
//		val mushgloom = "mushgloom".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, mushgloom)
//
//		val rare_huge_mushroom = "rare_huge_mushroom".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, rare_huge_mushroom)
//
//		val deep_dark_fossil = "deep_dark_fossil".pfKey
//		addFeatureToBiome(VEGETAL_DECORATION, ModTags.HAS_SCULK_NOISE, deep_dark_fossil)
//
//		val portal_fluid_pool = "portal_fluid_pool".pfKey
//		addFeatureToBiome(SURFACE_STRUCTURES, ModTags.HAS_END_NOISE, portal_fluid_pool)
//
//		val portal_fluid_spring = "portal_fluid_spring".pfKey
//		addFeatureToBiome(SURFACE_STRUCTURES, ModTags.HAS_END_NOISE, portal_fluid_spring)
//
//		val obsidian_patch = "obsidian_patch".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_END_NOISE, obsidian_patch)
//
//		val sulfur_patch = "sulfur_patch".pfKey
//		addFeatureToBiome(RAW_GENERATION, ModTags.HAS_SULFUR_PATCHES, sulfur_patch)
//	}
//
//	fun initCarverz (bs: BootstrapContext<Carvers>)
//	{
//		val bio = bs.lookup(Registries.BIOME)
//		val pla = bs.lookup(Registries.CONFIGURED_CARVER)
//		fun addCarverToBiome(
//			step: Carving,
//			tagKey: TagKey<Biome>,
//			feature: ResourceKey<ConfiguredWorldCarver<*>>,
//		)
//		{
//
//			BiomeModifiers.AddCarversBiomeModifier(
//				bio.getOrThrow(tagKey),
//				HolderSet.direct(pla.getOrThrow(feature)),
//				step
//			)
//		}
//
//		addCarverToBiome(Carving.AIR, ModTags.HAS_END_NOISE, "end_cave".ccKey)
//		addCarverToBiome(Carving.AIR, ModTags.HAS_END_NOISE, "end_cave_extra".ccKey)
//		addCarverToBiome(Carving.AIR, ModTags.HAS_END_NOISE, "end_canyon".ccKey)
//		addCarverToBiome(Carving.AIR, ModTags.HAS_STONE_NOISE, "crevice".ccKey)
//	}


}
