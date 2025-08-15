package net.orcinus.galosphere.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.orcinus.galosphere.init.GBlocks;
import net.orcinus.galosphere.init.GBuiltinLootTables;
import net.orcinus.galosphere.init.GEnchantmentTags;

import java.util.function.BiConsumer;

public record GChestLootTables(HolderLookup.Provider registries) implements LootTableSubProvider {

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> biConsumer) {
        HolderLookup.RegistryLookup<Enchantment> enchantment = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        biConsumer.accept(GBuiltinLootTables.PINK_SALT_SHRINE_CHEST, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(5.0F, 10.0F))
                        .add(LootItem.lootTableItem(GBlocks.PINK_SALT_CHAMBER.get().asItem())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                                .setWeight(2)
                        ).add(LootItem.lootTableItem(Items.NAME_TAG)
                                .setWeight(2)
                        ).add(LootItem.lootTableItem(Items.LEATHER)
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 4.0F)))
                        ).add(LootItem.lootTableItem(Items.PHANTOM_MEMBRANE)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 8.0F)))
                                .setWeight(2))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(3.0F, 6.0F)))
                                .setWeight(2))
                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                .setWeight(1))
                        .add(LootItem.lootTableItem(Items.BOOK)
                                .setWeight(1)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1.0F)))
                                .apply(EnchantRandomlyFunction.randomEnchantment()
                                        .withOneOf(enchantment.getOrThrow(GEnchantmentTags.PINK_SALT_SHRINE_LOOT))
                                ))
                )
        );
        biConsumer.accept(GBuiltinLootTables.PINK_SALT_SHRINE_LIBRARY_CHEST, LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(UniformGenerator.between(3.0F, 5.0F))
                        .add(LootItem.lootTableItem(Items.BOOK)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                                .setWeight(3))
                        .add(LootItem.lootTableItem(Items.PAPER)
                                .setWeight(5)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                        ).add(LootItem.lootTableItem(Items.BOOK)
                                .setWeight(1)
                                .apply(new EnchantRandomlyFunction.Builder()
                                        .withOneOf(enchantment.getOrThrow(GEnchantmentTags.PINK_SALT_SHRINE_LOOT))
                                ))
                ));
    }

}
