package com.ordana.spelunkery.reg;

import com.ordana.spelunkery.Spelunkery;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public class ModTags
{
	
	public static final TagKey<Block> SALT_BLOCKS = rbt("salt_blocks");
	public static final TagKey<Block> BLAST_MINER_IMMUNE = rbt("blast_miner_immune");
	public static final TagKey<Block> SPRING_GEYSER_SOURCE = rbt("spring_geyser_source");
	public static final TagKey<Block> SPRING_GEYSER_BREAKABLE = rbt("spring_geyser_breakable");
	
	public static final TagKey<EntityType<?>> HURT_BY_SALT = ret("hurt_by_salt");
	public static final TagKey<EntityType<?>> PORTAL_FLUID_IMMUNE = ret("portal_fluid_immune");
	
	public static final TagKey<Fluid> PORTAL_FLUID = rft("portal_fluid");
	public static final TagKey<Fluid> SPRING_WATER = rft("spring_water");
	
	//biomes
	public static final TagKey<Biome> HAS_LUSH_NOISE = registerBiomeTag("has_lush_noise");
	public static final TagKey<Biome> HAS_STONE_NOISE = registerBiomeTag("has_stone_noise");
	public static final TagKey<Biome> HAS_DIRT_NOISE = registerBiomeTag("has_dirt_noise");
	public static final TagKey<Biome> HAS_OCEAN_NOISE = registerBiomeTag("has_ocean_noise");
	public static final TagKey<Biome> HAS_DESERT_NOISE = registerBiomeTag("has_desert_noise");
	public static final TagKey<Biome> HAS_SALT_NOISE = registerBiomeTag("has_salt_noise");
	public static final TagKey<Biome> HAS_ICE_NOISE = registerBiomeTag("has_ice_noise");
	public static final TagKey<Biome> HAS_SCULK_NOISE = registerBiomeTag("has_sculk_noise");
	public static final TagKey<Biome> HAS_SWAMP_NOISE = registerBiomeTag("has_mountain_noise");
	public static final TagKey<Biome> HAS_NETHER_NOISE = registerBiomeTag("has_nether_noise");
	public static final TagKey<Biome> HAS_END_NOISE = registerBiomeTag("has_end_noise");
	public static final TagKey<Biome> HAS_SULFUR_PATCHES = registerBiomeTag("has_sulfur_patches");
	
	private ModTags ()
	{
	}
	
	private static TagKey<Biome> registerBiomeTag (String id)
	{
		return TagKey.create(Registries.BIOME, Spelunkery.res(id));
	}
	
	private static TagKey<Block> rbt (String id)
	{
		return TagKey.create(Registries.BLOCK, Spelunkery.res(id));
	}
	
	private static TagKey<EntityType<?>> ret (String id)
	{
		return TagKey.create(Registries.ENTITY_TYPE, Spelunkery.res(id));
	}
	
	private static TagKey<Fluid> rft (String id)
	{
		return TagKey.create(Registries.FLUID, Spelunkery.res(id));
	}
	
}
