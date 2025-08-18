package net.orcinus.galosphere.init

import com.google.common.collect.Lists
import net.minecraft.core.HolderGetter
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BiomeDefaultFeatures
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.sounds.Music
import net.minecraft.sounds.Musics
import net.minecraft.util.Mth
import net.minecraft.world.level.biome.*
import net.minecraft.world.level.biome.Biome.BiomeBuilder
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.orcinus.galosphere.Galosphere.Companion.id

object GBiomes
{
	val BIOMES = Lists.newLinkedList<ResourceKey<Biome>>()

	@JvmField val CRYSTAL_CANYONS = register("crystal_canyons")
	@JvmField val LICHEN_CAVES = register("lichen_caves")
	@JvmField val PINK_SALT_CAVES = register("pink_salt_caves")

	fun init()
	{
	}

	fun bootstrap(bootstrapContext: BootstrapContext<Biome>)
	{
		val holderGetter = bootstrapContext.lookup(Registries.PLACED_FEATURE)
		val holderGetter2 = bootstrapContext.lookup(Registries.CONFIGURED_CARVER)
		bootstrapContext.register(CRYSTAL_CANYONS, crystalCanyons(holderGetter, holderGetter2))
		bootstrapContext.register(LICHEN_CAVES, lichenCaves(holderGetter, holderGetter2))
		bootstrapContext.register(PINK_SALT_CAVES, pinkSaltCaves(holderGetter, holderGetter2))
	}

	fun pinkSaltCaves(
		holderGetter: HolderGetter<PlacedFeature>,
		holderGetter2: HolderGetter<ConfiguredWorldCarver<*>>
	): Biome
	{
		val mobBuilder = MobSpawnSettings.Builder()
		BiomeDefaultFeatures.commonSpawns(mobBuilder)
		val biomeBuilder = BiomeGenerationSettings.Builder(holderGetter, holderGetter2)
		BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder)
		BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder)
		BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder)
		BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder)
		BiomeDefaultFeatures.addDefaultSprings(biomeBuilder)
		BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder)
		BiomeDefaultFeatures.addPlainGrass(biomeBuilder)
		BiomeDefaultFeatures.addDefaultOres(biomeBuilder)
		val music = Musics.createGameMusic(GSoundEvents.MUSIC_PINK_SALT_CAVES)
		return biome(true, 0.5f, 0.5f, mobBuilder, biomeBuilder, music)
	}

	fun lichenCaves(
		holderGetter: HolderGetter<PlacedFeature>,
		holderGetter2: HolderGetter<ConfiguredWorldCarver<*>>
	): Biome
	{
		val mobBuilder = MobSpawnSettings.Builder()
		BiomeDefaultFeatures.commonSpawns(mobBuilder)
		val biomeBuilder = BiomeGenerationSettings.Builder(holderGetter, holderGetter2)
		BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder)
		BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder)
		BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder)
		BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder)
		BiomeDefaultFeatures.addDefaultSprings(biomeBuilder)
		BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder)
		BiomeDefaultFeatures.addPlainGrass(biomeBuilder)
		BiomeDefaultFeatures.addDefaultOres(biomeBuilder)
		val music = Musics.createGameMusic(GSoundEvents.MUSIC_LICHEN_CAVES)
		return biome(true, 0.5f, 0.5f, mobBuilder, biomeBuilder, music)
	}

	fun crystalCanyons(
		holderGetter: HolderGetter<PlacedFeature>,
		holderGetter2: HolderGetter<ConfiguredWorldCarver<*>>
	): Biome
	{
		val mobBuilder = MobSpawnSettings.Builder()
		BiomeDefaultFeatures.commonSpawns(mobBuilder)
		val biomeBuilder = BiomeGenerationSettings.Builder(holderGetter, holderGetter2)
		BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder)
		BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder)
		BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder)
		BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder)
		BiomeDefaultFeatures.addDefaultSprings(biomeBuilder)
		BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder)
		BiomeDefaultFeatures.addPlainGrass(biomeBuilder)
		BiomeDefaultFeatures.addDefaultOres(biomeBuilder)
		val music = Musics.createGameMusic(GSoundEvents.MUSIC_CRYSTAL_CANYONS)
		return biome(true, 0.5f, 0.5f, mobBuilder, biomeBuilder, music)
	}

	private fun biome(
		bl: Boolean,
		f: Float,
		g: Float,
		msb: MobSpawnSettings.Builder,
		bgb: BiomeGenerationSettings.Builder,
		music: Music
	): Biome
	{
		return biome(bl, f, g, 0x3f76e4, 0x050533, null, null, msb, bgb, music)
	}

	private fun biome(
		bl: Boolean,
		f: Float,
		g: Float,
		i: Int,
		j: Int,
		gcol: Int?,
		fcol: Int?,
		msb: MobSpawnSettings.Builder,
		bgb: BiomeGenerationSettings.Builder,
		music: Music
	): Biome
	{
		return BiomeBuilder().run {
			hasPrecipitation(bl)
			temperature(f)
			downfall(g)
			specialEffects(BiomeSpecialEffects.Builder().run {
				waterColor(i)
				waterFogColor(j)
				fogColor(0xc0d8ff)
				skyColor(calculateSkyColor(f))
				ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
				backgroundMusic(music)
				if (gcol != null)
					grassColorOverride(gcol)
				if (fcol != null)
					foliageColorOverride(fcol)
				build()
			})
			mobSpawnSettings(msb.build())
			generationSettings(bgb.build())
			build()
		}
	}

	internal fun calculateSkyColor(t: Float): Int
	{
		val k = Mth.clamp(t / 3f, -1f, +1f)
		return Mth.hsvToRgb(0.62222224f - k * 0.05f, 0.5f + k * 0.1f, 1.0f)
	}

	private fun register(name: String)
		= ResourceKey.create(Registries.BIOME, id(name)).also { BIOMES.add(it) }
}