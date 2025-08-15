package net.orcinus.galosphere.items;

import com.google.common.base.Suppliers;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.client.model.SterlingArmorModel;
import net.orcinus.galosphere.init.GArmorMaterials;
import net.orcinus.galosphere.init.GAttributes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class SterlingArmorItem extends ArmorItem {
    private static final ResourceLocation HELMET_TEXTURE = Galosphere.id("textures/entity/sterling_helmet.png");
    private static final ResourceLocation LEGS_TEXTURE = Galosphere.id("textures/entity/sterling_armor_2.png");
    private static final ResourceLocation TEXTURE = Galosphere.id("textures/entity/sterling_armor.png");
    private final Supplier<ItemAttributeModifiers> itemAttributeModifiersSupplier;

    public SterlingArmorItem(Type type, Properties properties) {
        super(GArmorMaterials.STERLING, type, properties);
        this.itemAttributeModifiersSupplier = Suppliers.memoize(() -> {
            ItemAttributeModifiers.Builder builder = ItemAttributeModifiers.builder();
            EquipmentSlotGroup equipmentSlotGroup = EquipmentSlotGroup.bySlot(type.getSlot());
            ResourceLocation resourceLocation = ResourceLocation.withDefaultNamespace("armor." + type.getName());
            ResourceLocation gResourceLocation = Galosphere.id("armor." + type.getName());
            builder.add(Attributes.ARMOR, new AttributeModifier(resourceLocation, this.getMaterial().value().getDefense(type), AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            builder.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(resourceLocation, this.getMaterial().value().toughness(), AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            builder.add(GAttributes.ILLAGER_RESISTANCE.getHolder().orElseThrow(), new AttributeModifier(gResourceLocation, this.getIllagerResistance(type.getSlot()), AttributeModifier.Operation.ADD_VALUE), equipmentSlotGroup);
            return builder.build();
        });
    }

    @Override
    public @Nullable ResourceLocation getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, ArmorMaterial.Layer layer, boolean inner) {
        ResourceLocation name;
        switch (slot) {
            case HEAD -> name = HELMET_TEXTURE;
            case LEGS -> name = LEGS_TEXTURE;
            default -> name = TEXTURE;
        }
        return name;
    }

    @Override
    public ItemAttributeModifiers getDefaultAttributeModifiers() {
        return this.itemAttributeModifiersSupplier.get();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                return equipmentSlot == EquipmentSlot.HEAD ? new SterlingArmorModel<>(SterlingArmorModel.createBodyLayer().bakeRoot()) : IClientItemExtensions.super.getHumanoidArmorModel(livingEntity, itemStack, equipmentSlot, original);
            }
        });
    }

    public float getIllagerResistance(EquipmentSlot slot) {
        float[] array = {3, 5, 6, 2};
        return array[slot.getIndex()];
    }

}
