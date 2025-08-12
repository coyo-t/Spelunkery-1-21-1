package com.ordana.spelunkery.reg;

import com.ordana.spelunkery.Spelunkery;
import com.ordana.spelunkery.blocks.entity.FallingLayerEntity;
import com.ordana.spelunkery.entities.DustBunnyEntity;
import com.ordana.spelunkery.entities.ThrownGlowstickEntity;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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
	
	//Living Entities
	public static Supplier<EntityType<DustBunnyEntity>>
	DUST_BUNNY = regEnt(
		"dust_bunny",
		DustBunnyEntity::new,
		MobCategory.CREATURE,
		0.8f, 0.5f
	);
	
	//Tile Entities
	
	//Thrown Entities
	public static Supplier<EntityType<ThrownGlowstickEntity>>
	GLOWSTICK = regEnt(
		"glowstick",
		ThrownGlowstickEntity::new,
		MobCategory.MISC,
		0.28F, 0.98F
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
