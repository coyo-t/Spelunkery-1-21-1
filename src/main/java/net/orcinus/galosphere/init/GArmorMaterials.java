package net.orcinus.galosphere.init;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.orcinus.galosphere.Galosphere;

import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class GArmorMaterials {
    public static final Holder<ArmorMaterial> STERLING = register("sterling", Util.make(new EnumMap<>(ArmorItem.Type.class), enumMap -> {
        enumMap.put(ArmorItem.Type.BOOTS, 1);
        enumMap.put(ArmorItem.Type.LEGGINGS, 4);
        enumMap.put(ArmorItem.Type.CHESTPLATE, 3);
        enumMap.put(ArmorItem.Type.HELMET, 1);
        enumMap.put(ArmorItem.Type.BODY, 3);
    }), 9, SoundEvents.ARMOR_EQUIP_CHAIN, 0.0f, 0.0f, () -> Ingredient.of(GItems.SILVER_INGOT.get()));

    private static Holder<ArmorMaterial> register(String string, EnumMap<ArmorItem.Type, Integer> enumMap, int i, Holder<SoundEvent> holder, float f, float g, Supplier<Ingredient> supplier) {
        List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(Galosphere.id(string)));
        return register(string, enumMap, i, holder, f, g, supplier, list);
    }

    private static Holder<ArmorMaterial> register(String string, EnumMap<ArmorItem.Type, Integer> enumMap, int i, Holder<SoundEvent> holder, float f, float g, Supplier<Ingredient> supplier, List<ArmorMaterial.Layer> list) {
        EnumMap<ArmorItem.Type, Integer> enumMap2 = new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            enumMap2.put(type, enumMap.get(type));
        }
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, Galosphere.id(string), new ArmorMaterial(enumMap2, i, holder, supplier, list, f, g));
    }

}