package com.ordana.spelunkery;

import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.material.FlowingFluid;
import net.neoforged.fml.common.EventBusSubscriber;
import org.jetbrains.annotations.Contract;

import java.util.function.Supplier;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Spelunkery.MOD_ID)
public class SpelunkeryPlatform
{
	
	@Contract
	public static void addFeatureToBiome (GenerationStep.Decoration step, TagKey<Biome> tagKey, ResourceKey<PlacedFeature> feature)
	{
		throw new AssertionError();
	}
	
	
	@Contract
	public static void addCarverToBiome (GenerationStep.Carving step, TagKey<Biome> tagKey, ResourceKey<ConfiguredWorldCarver<?>> feature)
	{
		throw new AssertionError();
	}
	
	public static LiquidBlock doPortalFluid (Supplier<FlowingFluid> flowingFluid, BlockBehaviour.Properties properties)
	{
		throw new AssertionError();
	}
	
	public static LiquidBlock doSpringWater (Supplier<FlowingFluid> flowingFluid, BlockBehaviour.Properties properties)
	{
		throw new AssertionError();
	}
}
