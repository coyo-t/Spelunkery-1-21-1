package net.orcinus.galosphere.config

import net.neoforged.neoforge.common.ModConfigSpec

//@Mod(modid = Galosphere.MODID)
object GalosphereConfig
{
	@JvmField
	var COMMON: ModConfigSpec
	@JvmField
	var SPECTRE_FLARE_ANCIENT_CITY_LOOT: ModConfigSpec.BooleanValue
	@JvmField
	var SILVER_UPGRADE_TEMPLATES_LOOT: ModConfigSpec.BooleanValue

	init
	{
		COMMON = ModConfigSpec.Builder().run {
			SPECTRE_FLARE_ANCIENT_CITY_LOOT = run {
				comment("Adds spectre flares to ancient city loot")
				define("spectreFlareAncientCityLoot", true)
			}
			SILVER_UPGRADE_TEMPLATES_LOOT = run {
				comment("Adds Silver Upgrade Template to Abandoned Mineshafts or Pillager Outposts loot")
				define("silverUpgradeTemplatesLoot", true)
			}
			build()
		}
	}
}
