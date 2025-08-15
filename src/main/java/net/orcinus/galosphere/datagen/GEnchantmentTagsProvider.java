package net.orcinus.galosphere.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EnchantmentTagsProvider;
import net.minecraft.world.item.enchantment.Enchantments;
import net.orcinus.galosphere.init.GEnchantmentTags;
import net.orcinus.galosphere.init.GEnchantments;

import java.util.concurrent.CompletableFuture;

public class GEnchantmentTagsProvider extends EnchantmentTagsProvider {

    public GEnchantmentTagsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> completableFuture) {
        super(packOutput, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(GEnchantmentTags.PINK_SALT_SHRINE_LOOT).addOptional(GEnchantments.ENFEEBLE.location()).addOptional(GEnchantments.SUSTAIN.location()).addOptional(GEnchantments.RUPTURE.location()).add(Enchantments.UNBREAKING);
    }

}
