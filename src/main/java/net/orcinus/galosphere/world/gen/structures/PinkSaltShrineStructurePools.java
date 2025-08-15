package net.orcinus.galosphere.world.gen.structures;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.data.worldgen.ProcessorLists;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;
import net.orcinus.galosphere.Galosphere;

import java.util.List;

public class PinkSaltShrineStructurePools {
    public static final ResourceKey<StructureTemplatePool> START = createKey("pink_salt_shrine/starts");

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        HolderGetter<StructureTemplatePool> holderGetter = context.lookup(Registries.TEMPLATE_POOL);
        HolderGetter<StructureProcessorList> holderGetter1 = context.lookup(Registries.PROCESSOR_LIST);
        context.register(
                START,
                new StructureTemplatePool(
                        holderGetter.getOrThrow(Pools.EMPTY),
                        List.of(
                                Pair.of(StructurePoolElement.single("galosphere:pink_salt_shrine/temple_top", holderGetter1.getOrThrow(ProcessorLists.EMPTY)), 1)
                        ),
                        StructureTemplatePool.Projection.RIGID
                )
        );
    }

    public static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, Galosphere.id(name));
    }
}
