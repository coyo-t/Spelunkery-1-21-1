package com.ordana.spelunkery.configs;

import com.ordana.spelunkery.Spelunkery;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigBuilder;
import net.mehvahdjukaar.moonlight.api.platform.configs.ConfigType;
import net.mehvahdjukaar.moonlight.api.platform.configs.ModConfigHolder;

import java.util.function.Supplier;

public class CommonConfigs
{
	
	
	public static ModConfigHolder SERVER_SPEC;
	
	public static Supplier<Integer> ECHO_FORK_RANGE;
	public static Supplier<Integer> ECHO_DURRATION;
	public static Supplier<Integer> ECHO_COOLDOWN;

	public static Supplier<Boolean> PORTAL_FLUID_DRINKING;
	public static Supplier<Boolean> INSTANT_TELEPORTATION;
	public static Supplier<Boolean> RESPAWN_ANCHOR_PORTAL_FLUID;
	public static Supplier<Boolean> CRYING_OBSIDIAN_PORTAL_FLUID;
	public static Supplier<Boolean> PORTAL_DESTRUCTION_CRYING_OBSIDIAN;

	public static Supplier<Boolean> STONE_STRIPE_FEATURES;
	public static Supplier<Boolean> ENABLE_SPOROPHYTES;
	public static Supplier<Boolean> DARK_FOREST_PORTABELLAS;
	public static Supplier<Boolean> END_OCEAN_BUCKETABLE;
	
	
	public static void init ()
	{
		// bump class load init
	}
	
	static
	{
		final var builder = ConfigBuilder.create(Spelunkery.res("common"), ConfigType.COMMON);

		builder.push("utilities");
		ECHO_FORK_RANGE = builder.define("echo_fork_range", 16, 1, 256);
		ECHO_COOLDOWN = builder.define("echo_fork_cooldown", 600, 1, 72000);
		ECHO_DURRATION = builder.define("echo_glow_duration", 1200, 1, 72000);
		builder.pop();
		
		builder.push("worldgen");
		STONE_STRIPE_FEATURES = builder.define("stone_stripe_features", true);
		ENABLE_SPOROPHYTES = builder.define("sporophytes_in_lush_caves", true);
		DARK_FOREST_PORTABELLAS = builder.define("dark_forest_portabellas", true);
		builder.pop();
		
		SERVER_SPEC = builder.build();
		// TODO
//		SERVER_SPEC.forceLoad();
		
//		SERVER_SPEC = builder.buildAndRegister();
//		SERVER_SPEC.loadFromFile();
	}
}
