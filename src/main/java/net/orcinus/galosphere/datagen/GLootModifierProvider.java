package net.orcinus.galosphere.datagen;

import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.util.PillagerSilverLootModifier;

import java.util.concurrent.CompletableFuture;

public class GLootModifierProvider extends GlobalLootModifierProvider {

    public GLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, Galosphere.MODID, registries);
    }

    @Override
    protected void start(HolderLookup.Provider provider) {
        add("pillager_silver_loot", new PillagerSilverLootModifier(
                new LootItemCondition[]{
                        LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(EntityType.PILLAGER)).build()
                }, 0, 2
        ));
    }
}
