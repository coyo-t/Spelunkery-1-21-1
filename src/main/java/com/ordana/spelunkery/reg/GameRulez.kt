package com.ordana.spelunkery.reg

import net.minecraft.world.level.GameRules

object GameRulez
{
	@JvmStatic
	fun init ()
	{

	}

	@JvmField
	val BADLANDS_CHUGS_PORTALS = GameRules.register(
		"can_drink_portal_fluid",
		GameRules.Category.PLAYER,
		GameRules.BooleanValue.create(true)
	)

	@JvmField
	val INSTAPORTAL = GameRules.register(
		"portal_fluid_teleports_instantly",
		GameRules.Category.PLAYER,
		GameRules.BooleanValue.create(true)
	)

	@JvmField
	val RESPAWN_ANCHOR_JUICABLE = GameRules.register(
		"can_extract_portal_fluid_from_respawn_anchor",
		GameRules.Category.PLAYER,
		GameRules.BooleanValue.create(true)
	)

	@JvmField
	val CRYING_OBSIDIAN_JUICABLE = GameRules.register(
		"can_extract_portal_fluid_from_crying_obsidian",
		GameRules.Category.PLAYER,
		GameRules.BooleanValue.create(true)
	)

	@JvmField
	val SHATTER_TEARS = GameRules.register(
		"nether_portal_shattering_creates_crying_obsidian",
		GameRules.Category.PLAYER,
		GameRules.BooleanValue.create(true)
	)

	@JvmField
	val ECHO_TUNER_RANGE = GameRules.register(
		"echo_tuning_fork_range",
		GameRules.Category.MISC,
		GameRules.IntegerValue.create(16)
	)
	@JvmField
	val ECHO_TUNER_TIME = GameRules.register(
		"echo_tuning_fork_effect_duration",
		GameRules.Category.MISC,
		GameRules.IntegerValue.create(600)
	)
	@JvmField
	val ECHO_TUNER_COOLDOWN = GameRules.register(
		"echo_tuning_fork_cooldown",
		GameRules.Category.MISC,
		GameRules.IntegerValue.create(1200)
	)
}