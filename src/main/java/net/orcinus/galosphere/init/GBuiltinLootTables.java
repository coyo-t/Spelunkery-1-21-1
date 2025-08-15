package net.orcinus.galosphere.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;
import net.orcinus.galosphere.Galosphere;

public class GBuiltinLootTables {
    public static final ResourceKey<LootTable> PINK_SALT_SHRINE_CHEST = register("chests/pink_salt_shrine");
    public static final ResourceKey<LootTable> PINK_SALT_SHRINE_LIBRARY_CHEST = register("chests/pink_salt_shrine_library");

    private static ResourceKey<LootTable> register(String name) {
        return ResourceKey.create(Registries.LOOT_TABLE, Galosphere.id(name));
    }
}
