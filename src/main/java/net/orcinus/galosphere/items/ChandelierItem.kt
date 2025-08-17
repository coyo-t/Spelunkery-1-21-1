package net.orcinus.galosphere.items

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.orcinus.galosphere.blocks.ChandelierBlock

class ChandelierItem(block: Block, properties: Properties) : BlockItem(block, properties)
{
	override fun placeBlock(blockPlaceContext: BlockPlaceContext, blockState: BlockState): Boolean
	{
		val blockPos: BlockPos
		val level = blockPlaceContext.level
		val blockState2 = if (level.isWaterAt(
				blockPlaceContext.clickedPos
					.relative(blockState.getValue(ChandelierBlock.VERTICAL_DIRECTION)).also { blockPos = it })
		) Blocks.WATER.defaultBlockState()
		else Blocks.AIR.defaultBlockState()
		level.setBlock(blockPos, blockState2, 0b11011)
		return super.placeBlock(blockPlaceContext, blockState)
	}
}
