package net.orcinus.galosphere.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.orcinus.galosphere.Galosphere;

public class GItemTags {

    public static final TagKey<Item> SPARKLE_TEMPT_ITEMS = create("sparkle_tempt_items");
    public static final TagKey<Item> SPECTRE_TEMPT_ITEMS = create("spectre_tempt_items");
    public static final TagKey<Item> NON_SINKABLES_HORSE_ARMORS = create("non_sinkable_horse_armors");
    public static final TagKey<Item> BOMB_DURATION_MODIFIERS = create("bomb_duration_modifiers");
    public static final TagKey<Item> BOMB_EXPLOSION_MODIFIERS = create("bomb_explosion_modifiers");
    public static final TagKey<Item> BOMB_BOUNCY_MODIFIERS = create("bomb_bouncy_modifiers");
    public static final TagKey<Item> SALTBOUND_TABLET_ENCHANTABLE = create("saltbound_tablet_enchantable");

    private static TagKey<Item> create(String name) {
        return TagKey.create(Registries.ITEM, Galosphere.id(name));
    }

}
