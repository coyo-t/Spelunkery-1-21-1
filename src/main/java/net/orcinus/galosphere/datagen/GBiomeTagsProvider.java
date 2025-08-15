package net.orcinus.galosphere.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.init.GBiomeTags;
import net.orcinus.galosphere.init.GBiomes;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class GBiomeTagsProvider extends BiomeTagsProvider {

    public GBiomeTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, Galosphere.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        GBiomes.BIOMES.forEach(this::addBiomeTag);

        this.tag(GBiomeTags.HAS_PINK_SALT_SHRINE).addOptional(GBiomes.PINK_SALT_CAVES.location());
    }

    public void addBiomeTag(ResourceKey<Biome> biome) {
        this.tag(BiomeTags.HAS_MINESHAFT).addOptional(biome.location());
        this.tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD).addOptional(biome.location());
        this.tag(BiomeTags.STRONGHOLD_BIASED_TO).addOptional(biome.location());
        this.tag(BiomeTags.IS_OVERWORLD).addOptional(biome.location());
        this.tag(Tags.Biomes.IS_UNDERGROUND).addOptional(biome.location());
    }

}
