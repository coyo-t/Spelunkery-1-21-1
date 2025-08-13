package com.ordana.spelunkery.datamalarky

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.worldgen.BM
import com.ordana.spelunkery.worldgen.CF
import com.ordana.spelunkery.worldgen.PF
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.PackOutput
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.registries.NeoForgeRegistries
import java.util.concurrent.CompletableFuture

class ModDataProviderz : DatapackBuiltinEntriesProvider
{

	companion object
	{
		val BUILDR = RegistrySetBuilder().apply {
			add(Registries.CONFIGURED_FEATURE, CF::paradox)
			add(Registries.PLACED_FEATURE, PF::paradox)
			add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, BM::paradox)
		}
	}

	constructor(po: PackOutput, reg: CompletableFuture<HolderLookup.Provider>):
		super(po, reg, BUILDR, setOf(Spelunkery.MOD_ID))

}