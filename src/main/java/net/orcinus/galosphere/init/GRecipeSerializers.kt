package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.item.crafting.RecipeSerializer
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.crafting.PreservedTransformRecipe
import java.util.function.Supplier

object GRecipeSerializers
{
	@JvmField
	val RECIPE_SERIALIZERS =
		DeferredRegister.create(Registries.RECIPE_SERIALIZER, Galosphere.MODID)

	@JvmField
	val PRESERVED_TRANSFORM = RECIPE_SERIALIZERS.register("preserved_transform_recipe") { rs ->
		PreservedTransformRecipe.Serializer()
	}
}
