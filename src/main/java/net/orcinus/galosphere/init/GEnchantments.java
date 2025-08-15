package net.orcinus.galosphere.init;

import com.google.common.collect.Maps;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.orcinus.galosphere.Galosphere;

import java.util.Map;

public class GEnchantments {
    public static final Map<ResourceLocation, ResourceKey<Enchantment>> ENCHANTMENTS = Maps.newLinkedHashMap();

    public static final ResourceKey<Enchantment> ENFEEBLE = key("enfeeble");
    public static final ResourceKey<Enchantment> SUSTAIN = key("sustain");
    public static final ResourceKey<Enchantment> RUPTURE = key("rupture");

    private static ResourceKey<Enchantment> key(String string) {
        ResourceLocation id = Galosphere.id(string);
        ResourceKey<Enchantment> key = ResourceKey.create(Registries.ENCHANTMENT, id);
        ENCHANTMENTS.put(id, key);
        return key;
    }

    public static void bootstrap(BootstrapContext<Enchantment> bootstrapContext) {
        HolderGetter<Item> itemHolderGetter = bootstrapContext.lookup(Registries.ITEM);

        register(bootstrapContext, ENFEEBLE, Enchantment.enchantment(
                Enchantment.definition(itemHolderGetter.getOrThrow(GItemTags.SALTBOUND_TABLET_ENCHANTABLE),
                        1,
                        1,
                        Enchantment.dynamicCost(25, 25),
                        Enchantment.dynamicCost(75, 75),
                        8,
                        EquipmentSlotGroup.MAINHAND)
        ).withEffect(
                GEnchantmentEffectComponents.SALTBOUND_TABLET_DECELERATION.get(),
                new AddValue(LevelBasedValue.perLevel(1.0F))
        ));

        register(bootstrapContext, SUSTAIN, Enchantment.enchantment(
                Enchantment.definition(itemHolderGetter.getOrThrow(GItemTags.SALTBOUND_TABLET_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.dynamicCost(25, 25),
                        Enchantment.dynamicCost(75, 75), 8,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(
                GEnchantmentEffectComponents.SALTBOUND_TABLET_SUSTAIN.get(),
                new AddValue(LevelBasedValue.perLevel(1.0F))
        ));

        register(bootstrapContext, RUPTURE, Enchantment.enchantment(
                Enchantment.definition(itemHolderGetter.getOrThrow(GItemTags.SALTBOUND_TABLET_ENCHANTABLE),
                        1,
                        3,
                        Enchantment.dynamicCost(25, 25),
                        Enchantment.dynamicCost(75, 75),
                        8,
                        EquipmentSlotGroup.MAINHAND
                )
        ).withEffect(
                GEnchantmentEffectComponents.SALTBOUND_TABLET_RUPTURE.get(),
                new AddValue(LevelBasedValue.perLevel(1.0F))));
    }

    private static void register(BootstrapContext<Enchantment> bootstrapContext, ResourceKey<Enchantment> resourceKey, Enchantment.Builder builder) {
        bootstrapContext.register(resourceKey, builder.build(resourceKey.location()));
    }

}