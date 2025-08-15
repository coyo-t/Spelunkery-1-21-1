package net.orcinus.galosphere.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.init.GDataComponents;
import net.orcinus.galosphere.init.GItems;
import net.orcinus.galosphere.init.GRecipeSerializers;

public class PreservedTransformRecipe implements SmithingRecipe {
    private final ResourceLocation ID = Galosphere.id("preserved_transform_recipe");

    public PreservedTransformRecipe() {
    }

    @Override
    public boolean isTemplateIngredient(ItemStack itemStack) {
        return itemStack.is(GItems.PRESERVED_TEMPLATE.get());
    }

    @Override
    public boolean isBaseIngredient(ItemStack itemStack) {
        boolean preserved = itemStack.has(GDataComponents.PRESERVED.get());
        if (preserved) {
            return false;
        }
        boolean blockItem = itemStack.getItem() instanceof BlockItem dummy && dummy.getBlock() instanceof ShulkerBoxBlock;
        return blockItem || itemStack.getCount() == 1;
    }

    @Override
    public boolean isAdditionIngredient(ItemStack itemStack) {
        return itemStack.is(GItems.PINK_SALT_SHARD.get());
    }

    @Override
    public boolean matches(SmithingRecipeInput recipeInput, Level level) {
        boolean templateIngredient = this.isTemplateIngredient(recipeInput.getItem(0));
        boolean baseIngredient = this.isBaseIngredient(recipeInput.getItem(1));
        boolean additionIngredient = this.isAdditionIngredient(recipeInput.getItem(2));
        return templateIngredient && baseIngredient && additionIngredient;
    }

    @Override
    public ItemStack assemble(SmithingRecipeInput recipeInput, HolderLookup.Provider provider) {
        ItemStack stack = recipeInput.getItem(1).copy();
        stack.set(GDataComponents.PRESERVED.get(), true);
        return stack;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return new ItemStack(Items.IRON_INGOT);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return GRecipeSerializers.PRESERVED_TRANSFORM.get();
    }

    @Override
    public boolean isIncomplete() {
        return false;
    }

    public static class Serializer implements RecipeSerializer<PreservedTransformRecipe> {
        private static final MapCodec<PreservedTransformRecipe> CODEC = MapCodec.unit(PreservedTransformRecipe::new);
        public static final StreamCodec<RegistryFriendlyByteBuf, PreservedTransformRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        private static PreservedTransformRecipe fromNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf) {
            return new PreservedTransformRecipe();
        }

        private static void toNetwork(RegistryFriendlyByteBuf registryFriendlyByteBuf, PreservedTransformRecipe smithingTransformRecipe) {
        }

        @Override
        public MapCodec<PreservedTransformRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PreservedTransformRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }

}
