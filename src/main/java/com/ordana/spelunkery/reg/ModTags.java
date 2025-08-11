package com.ordana.spelunkery.reg;

import com.ordana.spelunkery.Spelunkery;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
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
	

	private ModTags ()
	{
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
