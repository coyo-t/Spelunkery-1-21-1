package net.orcinus.galosphere.init

import com.jcraft.jorbis.Block
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters
import net.minecraft.world.item.Item
import net.minecraft.world.level.ItemLike
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import java.util.function.Supplier

object GCreativeModeTabs
{
	private operator fun <T : ItemLike> CreativeModeTab.Output.plusAssign(s: Supplier<T>) = accept(s.get())

	@JvmField
	val CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Galosphere.MODID)

	val GALOSPHERE = CREATIVE_MODE_TABS.register("galosphere") { res ->
		CreativeModeTab.builder().apply {
			icon { GItems.ICON_ITEM.get().defaultInstance }
			title(Component.translatable("itemGroup.galosphere.galosphere"))
			displayItems { idp, output ->
				output += GItems.SILVER_UPGRADE_SMITHING_TEMPLATE
				output += GItems.PRESERVED_TEMPLATE
				output += GItems.SALTBOUND_TABLET
				output += GItems.BERSERKER_SPAWN_EGG
				output += GItems.PRESERVED_SPAWN_EGG
				output += GItems.ALLURITE_SHARD
				output += GItems.LUMIERE_SHARD
				output += GItems.PINK_SALT_SHARD
				output += GItems.RAW_SILVER
				output += GItems.SILVER_INGOT
				output += GItems.SILVER_NUGGET
				output += GItems.BAROMETER
				output += GItems.STERLING_HELMET
				output += GItems.STERLING_CHESTPLATE
				output += GItems.STERLING_LEGGINGS
				output += GItems.STERLING_BOOTS
				output += GItems.STERLING_HORSE_ARMOR
				output += GItems.LICHEN_CORDYCEPS
				output += GItems.GOLDEN_LICHEN_CORDYCEPS
				output += GItems.SALTED_JERKY
				output += GItems.PRESERVED_FLESH
				output += GItems.CURED_MEMBRANE
				output += GItems.CHANDELIER
				output += GBlocks.GILDED_BEADS
				output += GBlocks.MONSTROMETER
				output += GBlocks.WARPED_ANCHOR
				output += GBlocks.SILVER_BALANCE
				output += GBlocks.SILVER_TILES
				output += GBlocks.SILVER_TILES_STAIRS
				output += GBlocks.SILVER_TILES_SLAB
				output += GBlocks.SILVER_PANEL
				output += GBlocks.SILVER_PANEL_STAIRS
				output += GBlocks.SILVER_PANEL_SLAB
				output += GBlocks.SILVER_LATTICE
				output += GBlocks.SILVER_ORE
				output += GBlocks.DEEPSLATE_SILVER_ORE
				output += GBlocks.SILVER_BLOCK
				output += GBlocks.RAW_SILVER_BLOCK
				output += GBlocks.ALLURITE_BLOCK
				output += GBlocks.LUMIERE_BLOCK
				output += GBlocks.CHARGED_LUMIERE_BLOCK
				output += GBlocks.ALLURITE_CLUSTER
				output += GBlocks.LUMIERE_CLUSTER
				output += GBlocks.GLINTED_ALLURITE_CLUSTER
				output += GBlocks.GLINTED_LUMIERE_CLUSTER
				output += GBlocks.GLINTED_AMETHYST_CLUSTER
				output += GBlocks.AMETHYST_STAIRS
				output += GBlocks.AMETHYST_SLAB
				output += GBlocks.ALLURITE_STAIRS
				output += GBlocks.ALLURITE_SLAB
				output += GBlocks.LUMIERE_STAIRS
				output += GBlocks.LUMIERE_SLAB
				output += GBlocks.SMOOTH_AMETHYST
				output += GBlocks.SMOOTH_AMETHYST_STAIRS
				output += GBlocks.SMOOTH_AMETHYST_SLAB
				output += GBlocks.SMOOTH_ALLURITE
				output += GBlocks.SMOOTH_ALLURITE_STAIRS
				output += GBlocks.SMOOTH_ALLURITE_SLAB
				output += GBlocks.SMOOTH_LUMIERE
				output += GBlocks.SMOOTH_LUMIERE_STAIRS
				output += GBlocks.SMOOTH_LUMIERE_SLAB
				output += GBlocks.AMETHYST_BRICKS
				output += GBlocks.AMETHYST_BRICK_STAIRS
				output += GBlocks.AMETHYST_BRICK_SLAB
				output += GBlocks.ALLURITE_BRICKS
				output += GBlocks.ALLURITE_BRICK_STAIRS
				output += GBlocks.ALLURITE_BRICK_SLAB
				output += GBlocks.LUMIERE_BRICKS
				output += GBlocks.LUMIERE_BRICK_STAIRS
				output += GBlocks.LUMIERE_BRICK_SLAB
				output += GBlocks.CHISELED_AMETHYST
				output += GBlocks.CHISELED_ALLURITE
				output += GBlocks.CHISELED_LUMIERE
				output += GBlocks.AMETHYST_LAMP
				output += GBlocks.ALLURITE_LAMP
				output += GBlocks.LUMIERE_LAMP
				output += GBlocks.LICHEN_MOSS
				output += GBlocks.LICHEN_ROOTS
				output += GBlocks.BOWL_LICHEN
				output += GBlocks.LICHEN_SHELF
				output += GBlocks.GLOW_INK_CLUMPS
				output += GBlocks.PINK_SALT
				output += GBlocks.ROSE_PINK_SALT
				output += GBlocks.PASTEL_PINK_SALT
				output += GBlocks.PINK_SALT_STAIRS
				output += GBlocks.ROSE_PINK_SALT_STAIRS
				output += GBlocks.PASTEL_PINK_SALT_STAIRS
				output += GBlocks.PINK_SALT_SLAB
				output += GBlocks.ROSE_PINK_SALT_SLAB
				output += GBlocks.PASTEL_PINK_SALT_SLAB
				output += GBlocks.PINK_SALT_WALL
				output += GBlocks.ROSE_PINK_SALT_WALL
				output += GBlocks.PASTEL_PINK_SALT_WALL
				output += GBlocks.POLISHED_PINK_SALT
				output += GBlocks.POLISHED_ROSE_PINK_SALT
				output += GBlocks.POLISHED_PASTEL_PINK_SALT
				output += GBlocks.POLISHED_PINK_SALT_STAIRS
				output += GBlocks.POLISHED_ROSE_PINK_SALT_STAIRS
				output += GBlocks.POLISHED_PASTEL_PINK_SALT_STAIRS
				output += GBlocks.POLISHED_PINK_SALT_SLAB
				output += GBlocks.POLISHED_ROSE_PINK_SALT_SLAB
				output += GBlocks.POLISHED_PASTEL_PINK_SALT_SLAB
				output += GBlocks.POLISHED_PINK_SALT_WALL
				output += GBlocks.POLISHED_ROSE_PINK_SALT_WALL
				output += GBlocks.POLISHED_PASTEL_PINK_SALT_WALL
				output += GBlocks.PINK_SALT_BRICKS
				output += GBlocks.ROSE_PINK_SALT_BRICKS
				output += GBlocks.PASTEL_PINK_SALT_BRICKS
				output += GBlocks.PINK_SALT_BRICK_STAIRS
				output += GBlocks.ROSE_PINK_SALT_BRICK_STAIRS
				output += GBlocks.PASTEL_PINK_SALT_BRICK_STAIRS
				output += GBlocks.PINK_SALT_BRICK_SLAB
				output += GBlocks.ROSE_PINK_SALT_BRICK_SLAB
				output += GBlocks.PASTEL_PINK_SALT_BRICK_SLAB
				output += GBlocks.PINK_SALT_BRICK_WALL
				output += GBlocks.ROSE_PINK_SALT_BRICK_WALL
				output += GBlocks.PASTEL_PINK_SALT_BRICK_WALL
				output += GBlocks.CHISELED_PINK_SALT
				output += GBlocks.CHISELED_ROSE_PINK_SALT
				output += GBlocks.CHISELED_PASTEL_PINK_SALT
				output += GBlocks.PINK_SALT_CHAMBER
				output += GBlocks.PINK_SALT_LAMP
				output += GBlocks.PINK_SALT_STRAW
				output += GBlocks.PINK_SALT_CLUSTER
				output += GBlocks.PINK_SALT_CLUSTER
				output += GBlocks.CURED_MEMBRANE_BLOCK
				output += GBlocks.STRANDED_MEMBRANE_BLOCK
			}
		}.build()
	}
}
