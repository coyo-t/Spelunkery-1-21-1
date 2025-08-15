package net.orcinus.galosphere.init;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.orcinus.galosphere.world.gen.structures.PinkSaltShrineStructurePools;

public class GTemplatePools {

    public static void bootstrap(BootstrapContext<StructureTemplatePool> context) {
        PinkSaltShrineStructurePools.bootstrap(context);
    }

}
