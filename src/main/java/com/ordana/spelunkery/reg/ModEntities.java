package com.ordana.spelunkery.reg;

import com.ordana.spelunkery.Spelunkery;
import com.ordana.spelunkery.blocks.entity.*;
import com.ordana.spelunkery.entities.*;
import net.mehvahdjukaar.moonlight.api.misc.RegSupplier;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ModEntities
{
	public static void init ()
	{
	}
	
	private static @NotNull <T extends Entity> Supplier<EntityType<T>>
	regEnt (String resName, EntityType.EntityFactory<T> factory, MobCategory category, float wide, float tall)
	{
		return RegHelper.registerEntityType(
			Spelunkery.res(resName),
			EntityType.Builder
				.of(factory, category)
				.sized(wide, tall)
				.clientTrackingRange(10)
				.setShouldReceiveVelocityUpdates(true)
				.updateInterval(20)
		);
	}
	
	private static <T extends BlockEntity> RegSupplier<BlockEntityType<T>>
	regBloc (String name, PlatHelper.BlockEntitySupplier<T> blockEntitySupplier, Supplier<Block> bloc)
	{
		return RegHelper.registerBlockEntityType(
				  Spelunkery.res(name),
				  () -> PlatHelper.newBlockEntityType(blockEntitySupplier, bloc.get())
		);
	}
	
	//Living Entities
	public static Supplier<EntityType<DustBunnyEntity>>
	DUST_BUNNY = regEnt(
		"dust_bunny",
		DustBunnyEntity::new,
		MobCategory.CREATURE,
		0.8f, 0.5f
	);
	
	//Tile Entities
	public static final Supplier<BlockEntityType<CarvedNephriteBlockEntity>>
	NEPHRITE_TILE = regBloc("carved_nephrite", CarvedNephriteBlockEntity::new, ModBlocks.CARVED_NEPHRITE);

	
	public static final Supplier<BlockEntityType<NephriteFountainEntity>>
	NEPHRITE_FOUNTAIN = regBloc(
		"nephrite_fountain",
		NephriteFountainEntity::new,
		ModBlocks.NEPHRITE_FOUNTAIN
	);
	public static final Supplier<BlockEntityType<MagnetiteBlockEntity>>
	MAGNETITE = regBloc(
		"raw_magnetite_block",
		MagnetiteBlockEntity::new,
		ModBlocks.RAW_MAGNETITE_BLOCK
	);
	public static final Supplier<BlockEntityType<BuddingAmethystBlockEntity>>
	BUDDING_AMETHYST = regBloc(
		"budding_amethyst",
		BuddingAmethystBlockEntity::new,
		() -> Blocks.BUDDING_AMETHYST
	);
	public static final Supplier<BlockEntityType<SluiceBlockEntity>>
	WOODEN_SLUICE = regBloc(
		"wooden_sluice",
		SluiceBlockEntity::new,
		ModBlocks.WOODEN_SLUICE
	);
	
	public static final Supplier<BlockEntityType<SluiceBlockEntity>>
	STONE_SLUICE = regBloc(
		"stone_sluice",
		SluiceBlockEntity::new,
		ModBlocks.STONE_SLUICE
	);
	
	//Thrown Entities
	public static Supplier<EntityType<ThrownGlowstickEntity>>
	GLOWSTICK = regEnt(
		"glowstick",
		ThrownGlowstickEntity::new,
		MobCategory.MISC,
		0.28F, 0.98F
	);
	
	public static Supplier<EntityType<ThrownMineomiteEntity>>
	MINEOMITE = regEnt(
		"mineomite",
		ThrownMineomiteEntity::new,
		MobCategory.MISC,
		0.28F, 0.98F
	);
	public static Supplier<EntityType<ThrownPebbleEntity>>
	PEBBLE = regEnt(
		"pebble",
		ThrownPebbleEntity::new,
		MobCategory.MISC,
		0.7F, 0.7F
	);
	public static Supplier<EntityType<PickOnAStickEntity>>
	PICK = regEnt(
		"pick",
		PickOnAStickEntity::new,
		MobCategory.MISC,
		0.6F, 0.6F
	);
	public static Supplier<EntityType<ThrownEggplantEntity>>
	EGGPLANT = regEnt(
		"eggplant",
		ThrownEggplantEntity::new,
		MobCategory.MISC,
		0.7F, 0.7F
	);
	
	//Other Entities
	public static Supplier<EntityType<FallingLayerEntity>>
	FALLING_LAYER = regEnt(
		"falling_layer",
		FallingLayerEntity::new,
		MobCategory.MISC,
		0.98F, 0.98F
	);
	
}
