package net.orcinus.galosphere.init;

import com.google.common.collect.Lists;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.orcinus.galosphere.Galosphere;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GBiomes {
    public static final List<ResourceKey<Biome>> BIOMES = Lists.newLinkedList();

    public static ResourceKey<Biome> CRYSTAL_CANYONS = register("crystal_canyons");
    public static ResourceKey<Biome> LICHEN_CAVES = register("lichen_caves");
    public static ResourceKey<Biome> PINK_SALT_CAVES = register("pink_salt_caves");

    public static void init() {
    }

    public static void bootstrap(BootstrapContext<Biome> bootstrapContext) {
        HolderGetter<PlacedFeature> holderGetter = bootstrapContext.lookup(Registries.PLACED_FEATURE);
        HolderGetter<ConfiguredWorldCarver<?>> holderGetter2 = bootstrapContext.lookup(Registries.CONFIGURED_CARVER);
        bootstrapContext.register(CRYSTAL_CANYONS, crystalCanyons(holderGetter, holderGetter2));
        bootstrapContext.register(LICHEN_CAVES, lichenCaves(holderGetter, holderGetter2));
        bootstrapContext.register(PINK_SALT_CAVES, pinkSaltCaves(holderGetter, holderGetter2));
    }

    public static Biome pinkSaltCaves(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter2) {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(mobBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        Music music = Musics.createGameMusic(GSoundEvents.MUSIC_PINK_SALT_CAVES.getHolder().orElseThrow());
        return biome(true, 0.5f, 0.5f, mobBuilder, biomeBuilder, music);
    }

    public static Biome lichenCaves(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter2) {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(mobBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        Music music = Musics.createGameMusic(GSoundEvents.MUSIC_LICHEN_CAVES.getHolder().orElseThrow());
        return biome(true, 0.5f, 0.5f, mobBuilder, biomeBuilder, music);
    }

    public static Biome crystalCanyons(HolderGetter<PlacedFeature> holderGetter, HolderGetter<ConfiguredWorldCarver<?>> holderGetter2) {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(mobBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(holderGetter, holderGetter2);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultSprings(biomeBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(biomeBuilder);
        BiomeDefaultFeatures.addPlainGrass(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        Music music = Musics.createGameMusic(GSoundEvents.MUSIC_CRYSTAL_CANYONS.getHolder().orElseThrow());
        return biome(true, 0.5f, 0.5f, mobBuilder, biomeBuilder, music);
    }

    private static Biome biome(boolean bl, float f, float g, MobSpawnSettings.Builder builder, BiomeGenerationSettings.Builder builder2, @Nullable Music music) {
        return biome(bl, f, g, 4159204, 329011, null, null, builder, builder2, music);
    }

    private static Biome biome(boolean bl, float f, float g, int i, int j, @Nullable Integer integer, @Nullable Integer integer2, MobSpawnSettings.Builder builder, BiomeGenerationSettings.Builder builder2, @Nullable Music music) {
        BiomeSpecialEffects.Builder builder3 = new BiomeSpecialEffects.Builder().waterColor(i).waterFogColor(j).fogColor(12638463).skyColor(calculateSkyColor(f)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).backgroundMusic(music);
        if (integer != null) {
            builder3.grassColorOverride(integer);
        }
        if (integer2 != null) {
            builder3.foliageColorOverride(integer2);
        }
        return new Biome.BiomeBuilder().hasPrecipitation(bl).temperature(f).downfall(g).specialEffects(builder3.build()).mobSpawnSettings(builder.build()).generationSettings(builder2.build()).build();
    }

    protected static int calculateSkyColor(float p_194844_) {
        float $$1 = p_194844_ / 3.0F;
        $$1 = Mth.clamp($$1, -1.0F, 1.0F);
        return Mth.hsvToRgb(0.62222224F - $$1 * 0.05F, 0.5F + $$1 * 0.1F, 1.0F);
    }

    private static ResourceKey<Biome> register(String name) {
        ResourceKey<Biome> key = ResourceKey.create(Registries.BIOME, Galosphere.id(name));
        BIOMES.add(key);
        return key;
    }

}