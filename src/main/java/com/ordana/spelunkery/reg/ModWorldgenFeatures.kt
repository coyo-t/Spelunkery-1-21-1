package com.ordana.spelunkery.reg;

import com.ordana.spelunkery.Spelunkery;
import com.ordana.spelunkery.worldgen.feature_configs.BlockStripeFeatureConfig;
import com.ordana.spelunkery.worldgen.feature_configs.HugeConkFungusFeatureConfig;
import com.ordana.spelunkery.worldgen.feature_configs.HugeForkingMushroomFeatureConfig;
import com.ordana.spelunkery.worldgen.feature_configs.WallMushroomFeatureConfig;
import com.ordana.spelunkery.worldgen.features.BlockStripeFeature;
import com.ordana.spelunkery.worldgen.features.HugeConkFungusFeature;
import com.ordana.spelunkery.worldgen.features.HugeForkingMushroomFeature;
import com.ordana.spelunkery.worldgen.features.WallMushroomFeature;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.function.Supplier;

public class ModWorldgenFeatures
{
	
	
	//custom feature types
	public static final Supplier<Feature<HugeConkFungusFeatureConfig>> HUGE_CONK_FEATURE = RegHelper.registerFeature(
			  Spelunkery.res("huge_conk"), () ->
						 new HugeConkFungusFeature(HugeConkFungusFeatureConfig.CODEC));
	
	public static final Supplier<Feature<HugeForkingMushroomFeatureConfig>> HUGE_FORKING_MUSHROOM_FEATURE = RegHelper.registerFeature(
			  Spelunkery.res("huge_forking_mushroom"), () ->
						 new HugeForkingMushroomFeature(HugeForkingMushroomFeatureConfig.CODEC));
	
	public static final Supplier<Feature<WallMushroomFeatureConfig>> WALL_MUSHROOM_FEATURE = RegHelper.registerFeature(
			  Spelunkery.res("wall_mushroom"), () ->
						 new WallMushroomFeature(WallMushroomFeatureConfig.CODEC));
	
	
	public static final Supplier<Feature<BlockStripeFeatureConfig>> BLOCK_STRIPE_FEATURE = RegHelper.registerFeature(
			  Spelunkery.res("block_stripe"), () ->
						 new BlockStripeFeature(BlockStripeFeatureConfig.CODEC));
	
	public static void init ()
	{
		
		//carver generation
		ResourceKey<ConfiguredWorldCarver<?>> end_cave = ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res("end_cave"));
		addCarverToBiome(GenerationStep.Carving.AIR, ModTags.HAS_END_NOISE, end_cave);
		
		ResourceKey<ConfiguredWorldCarver<?>> end_cave_extra = ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res("end_cave_extra"));
		addCarverToBiome(GenerationStep.Carving.AIR, ModTags.HAS_END_NOISE, end_cave_extra);
		
		ResourceKey<ConfiguredWorldCarver<?>> end_canyon = ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res("end_canyon"));
		addCarverToBiome(GenerationStep.Carving.AIR, ModTags.HAS_END_NOISE, end_canyon);
		
		ResourceKey<ConfiguredWorldCarver<?>> crevice = ResourceKey.create(Registries.CONFIGURED_CARVER, Spelunkery.res("crevice"));
		addCarverToBiome(GenerationStep.Carving.AIR, ModTags.HAS_STONE_NOISE, crevice);
		
		ResourceKey<PlacedFeature> portal_fluid_ocean = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("portal_fluid_ocean"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, BiomeTags.IS_END, portal_fluid_ocean);
		
		//stone generation
		ResourceKey<PlacedFeature> noise_stone = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_stone"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_STONE_NOISE, noise_stone);
		
		ResourceKey<PlacedFeature> noise_lush = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_lush"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_LUSH_NOISE, noise_lush);
		
		ResourceKey<PlacedFeature> noise_dirt = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_dirt"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_DIRT_NOISE, noise_dirt);
		
		ResourceKey<PlacedFeature> noise_ocean = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_ocean"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_OCEAN_NOISE, noise_ocean);
		
		//ResourceKey<PlacedFeature> noise_desert = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_desert"));
		//addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_DESERT_NOISE, noise_desert);
		
		ResourceKey<PlacedFeature> noise_ice = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_ice"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_ICE_NOISE, noise_ice);
		
		ResourceKey<PlacedFeature> noise_sculk = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_sculk"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SCULK_NOISE, noise_sculk);
		
		ResourceKey<PlacedFeature> noise_salt = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_salt"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SALT_NOISE, noise_salt);
		
		ResourceKey<PlacedFeature> noise_end = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_end"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_END_NOISE, noise_end);
		
		ResourceKey<PlacedFeature> rock_salt = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("rock_salt"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SALT_NOISE, rock_salt);
		
		
		ResourceKey<PlacedFeature> noise_iron = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("noise_iron"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_STONE_NOISE, noise_iron);
		
		
		//ores
		ResourceKey<PlacedFeature> spring_water_pool = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("spring_water_pool"));
		addFeatureToBiome(GenerationStep.Decoration.FLUID_SPRINGS, ModTags.HAS_SALT_NOISE, spring_water_pool);
		
		ResourceKey<PlacedFeature> spring_water_spring = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("spring_water_spring"));
		addFeatureToBiome(GenerationStep.Decoration.FLUID_SPRINGS, ModTags.HAS_SALT_NOISE, spring_water_spring);
		
		ResourceKey<PlacedFeature> ore_aquifer = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("ore_aquifer"));
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, ore_aquifer);
		
		ResourceKey<PlacedFeature> diamond_ore_lava = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("diamond_ore_lava"));
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, diamond_ore_lava);
		
		ResourceKey<PlacedFeature> nephrite_geode = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("nephrite_geode"));
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_OVERWORLD, nephrite_geode);
		
		ResourceKey<PlacedFeature> magnetite_geode = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("magnetite_geode"));
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_NETHER, magnetite_geode);
		
		ResourceKey<PlacedFeature> quartz_geode = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("quartz_geode"));
		addFeatureToBiome(GenerationStep.Decoration.UNDERGROUND_ORES, BiomeTags.IS_NETHER, quartz_geode);
		
		//veins
		ResourceKey<PlacedFeature> large_gold_vein = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("large_gold_vein"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_NETHER_NOISE, large_gold_vein);
		
		ResourceKey<PlacedFeature> large_coal_vein = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("large_coal_vein"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SWAMP_NOISE, large_coal_vein);
		
		ResourceKey<PlacedFeature> large_emerald_vein = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("large_emerald_vein"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SALT_NOISE, large_emerald_vein);
		
		ResourceKey<PlacedFeature> large_lapis_vein = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("large_lapis_vein"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_DESERT_NOISE, large_lapis_vein);
		
		
		//vegetation
		ResourceKey<PlacedFeature> tangle_roots_ceiling = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("tangle_roots_ceiling"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, tangle_roots_ceiling);
		
		ResourceKey<PlacedFeature> conk_fungus = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("conk_fungus"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, conk_fungus);
		
		ResourceKey<PlacedFeature> conk_fungus_surface = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("conk_fungus_surface"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, conk_fungus_surface);
		
		ResourceKey<PlacedFeature> inkcap = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("inkcap"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, inkcap);
		
		ResourceKey<PlacedFeature> inkcap_deepslate = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("inkcap_deepslate"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, inkcap_deepslate);
		
		ResourceKey<PlacedFeature> portabella = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("portabella"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, portabella);
		
		ResourceKey<PlacedFeature> phosphor_fungus = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("phosphor_fungus"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, phosphor_fungus);
		
		ResourceKey<PlacedFeature> mushgloom = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("mushgloom"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, mushgloom);
		
		ResourceKey<PlacedFeature> rare_huge_mushroom = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("rare_huge_mushroom"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, BiomeTags.IS_OVERWORLD, rare_huge_mushroom);
		
		ResourceKey<PlacedFeature> deep_dark_fossil = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("deep_dark_fossil"));
		addFeatureToBiome(GenerationStep.Decoration.VEGETAL_DECORATION, ModTags.HAS_SCULK_NOISE, deep_dark_fossil);
		
		ResourceKey<PlacedFeature> portal_fluid_pool = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("portal_fluid_pool"));
		addFeatureToBiome(GenerationStep.Decoration.SURFACE_STRUCTURES, ModTags.HAS_END_NOISE, portal_fluid_pool);
		
		ResourceKey<PlacedFeature> portal_fluid_spring = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("portal_fluid_spring"));
		addFeatureToBiome(GenerationStep.Decoration.SURFACE_STRUCTURES, ModTags.HAS_END_NOISE, portal_fluid_spring);
		
		ResourceKey<PlacedFeature> obsidian_patch = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("obsidian_patch"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_END_NOISE, obsidian_patch);
		
		ResourceKey<PlacedFeature> sulfur_patch = ResourceKey.create(Registries.PLACED_FEATURE, Spelunkery.res("sulfur_patch"));
		addFeatureToBiome(GenerationStep.Decoration.RAW_GENERATION, ModTags.HAS_SULFUR_PATCHES, sulfur_patch);
	}
	

	public static void addFeatureToBiome (GenerationStep.Decoration step, TagKey<Biome> tagKey, ResourceKey<PlacedFeature> feature)
	{
		throw new AssertionError();
	}
	
	
	public static void addCarverToBiome (GenerationStep.Carving step, TagKey<Biome> tagKey, ResourceKey<ConfiguredWorldCarver<?>> feature)
	{
		throw new AssertionError();
	}
	
}
