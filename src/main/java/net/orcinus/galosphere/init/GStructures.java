package net.orcinus.galosphere.init;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.WeightedRandomList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.structures.JigsawStructure;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.world.gen.structures.PinkSaltShrineStructurePools;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GStructures {
    public static final ResourceKey<Structure> PINK_SALT_SHRINE = createKey("pink_salt_shrine");

    public static void bootstrap(BootstrapContext<Structure> bootstrapContext) {
        HolderGetter<Biome> holderGetter = bootstrapContext.lookup(Registries.BIOME);
        HolderGetter<StructureTemplatePool> holderGetter1 = bootstrapContext.lookup(Registries.TEMPLATE_POOL);
        bootstrapContext.register(
                PINK_SALT_SHRINE,
                new JigsawStructure(
                        new Structure.StructureSettings.Builder(
                                holderGetter.getOrThrow(GBiomeTags.HAS_PINK_SALT_SHRINE))
                                .generationStep(GenerationStep.Decoration.UNDERGROUND_STRUCTURES)
                                .terrainAdapation(TerrainAdjustment.BEARD_BOX)
                                .spawnOverrides(
                                        Arrays.stream(MobCategory.values())
                                                .collect(
                                                        Collectors.toMap(
                                                                category -> category,
                                                                category -> new StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType.STRUCTURE, WeightedRandomList.create())
                                                        )
                                                )
                                )
                                .build(),
                        holderGetter1.getOrThrow(PinkSaltShrineStructurePools.START),
                        Optional.empty(),
                        7,
                        UniformHeight.of(VerticalAnchor.absolute(-30), VerticalAnchor.absolute(30)),
                        false,
                        Optional.empty(),
                        80,
                        List.of(),
                        JigsawStructure.DEFAULT_DIMENSION_PADDING,
                        LiquidSettings.IGNORE_WATERLOGGING
                )
        );
    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, Galosphere.id(name));
    }

}
