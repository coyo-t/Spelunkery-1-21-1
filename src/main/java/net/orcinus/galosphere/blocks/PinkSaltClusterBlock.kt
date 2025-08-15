package net.orcinus.galosphere.blocks

import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape

class PinkSaltClusterBlock(properties: Properties) : PinkSaltLampBlock(properties)
{
	private val northAabb = box(3.0, 3.0, 3.0, 13.0, 13.0, 16.0)
	private val southAabb = box(3.0, 3.0, 0.0, 13.0, 13.0, 13.0)
	private val eastAabb = box(0.0, 3.0, 3.0, 13.0, 13.0, 13.0)
	private val westAabb = box(3.0, 3.0, 3.0, 16.0, 13.0, 13.0)
	private val upAabb = box(3.0, 0.0, 3.0, 13.0, 13.0, 13.0)
	private val downAabb = box(3.0, 3.0, 3.0, 13.0, 16.0, 13.0)

	override fun getShape(
		blockState: BlockState,
		blockGetter: BlockGetter,
		blockPos: BlockPos,
		collisionContext: CollisionContext
	): VoxelShape
	{
		return when (blockState.getValue(FACING))
		{
			Direction.NORTH -> this.northAabb
			Direction.SOUTH -> this.southAabb
			Direction.EAST -> this.eastAabb
			Direction.WEST -> this.westAabb
			Direction.DOWN -> this.downAabb
			else -> this.upAabb
		}
	}
}
