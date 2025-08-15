package net.orcinus.galosphere.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.orcinus.galosphere.Galosphere;

public class GEnchantmentTags {

    public static final TagKey<Enchantment> PINK_SALT_SHRINE_LOOT = create("pink_salt_shrine_loot");

    private static TagKey<Enchantment> create(String name) {
        return TagKey.create(Registries.ENCHANTMENT, Galosphere.id(name));
    }

}
