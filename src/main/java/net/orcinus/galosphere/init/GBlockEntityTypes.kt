package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.level.block.entity.BlockEntityType
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.blocks.blockentities.*
import java.util.function.Supplier

object GBlockEntityTypes
{
	@JvmField
	val BLOCK_ENTITIES =
		DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Galosphere.MODID)

	@JvmField
	val MONSTROMETER =
		BLOCK_ENTITIES.register(
			"monstrometer",
			Supplier {
				BlockEntityType.Builder.of(BlockEntityType.BlockEntitySupplier { worldPosition, state ->
					MonstrometerBlockEntity(
						worldPosition,
						state
					)
				}, GBlocks.MONSTROMETER.get()).build(null)
			})
	@JvmField
	val GLOW_INK_CLUMPS =
		BLOCK_ENTITIES.register(
			"glow_ink_clumps",
			Supplier {
				BlockEntityType.Builder.of(BlockEntityType.BlockEntitySupplier { pos, state ->
					GlowInkClumpsBlockEntity(
						pos,
						state
					)
				}, GBlocks.GLOW_INK_CLUMPS.get()).build(null)
			})
	@JvmField
	val CORDYCEPS =
		BLOCK_ENTITIES.register(
			"cordyceps",
			Supplier {
				BlockEntityType.Builder.of(BlockEntityType.BlockEntitySupplier { blockPos, blockState ->
					CordycepsBlockEntity(
						blockPos,
						blockState
					)
				}, GBlocks.LICHEN_CORDYCEPS.get()).build(null)
			})
	@JvmField
	val PINK_SALT_CHAMBER =
		BLOCK_ENTITIES.register(
			"pink_salt_chamber",
			Supplier {
				BlockEntityType.Builder.of(BlockEntityType.BlockEntitySupplier { blockPos, blockState ->
					PinkSaltChamberBlockEntity(
						blockPos,
						blockState
					)
				}, GBlocks.PINK_SALT_CHAMBER.get()).build(null)
			})
}



