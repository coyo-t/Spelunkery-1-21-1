package net.orcinus.galosphere.blocks

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.context.BlockPlaceContext
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.IntegerProperty
import net.minecraft.world.level.block.state.properties.RotationSegment
import net.minecraft.world.level.material.Fluids
import net.minecraft.world.phys.shapes.CollisionContext
import net.minecraft.world.phys.shapes.VoxelShape
import net.orcinus.galosphere.blocks.blockentities.GildedBeadsBlockEntity
import java.util.function.Function

class GildedBeadsBlock(properties: Properties) : BaseEntityBlock(properties), SimpleWaterloggedBlock
{
	init
	{
		this.registerDefaultState(
			this.stateDefinition
			.any()
			.setValue(BOTTOM, true)
			.setValue(ROTATION, 0)
			.setValue(WATERLOGGED, false)
		)
	}

	override fun codec() = CODEC

	public override fun updateShape(
		blockState: BlockState,
		direction: Direction,
		blockState2: BlockState,
		levelAccessor: LevelAccessor,
		blockPos: BlockPos,
		blockPos2: BlockPos
	): BlockState
	{
		if (blockState.getValue(WATERLOGGED))
		{
			levelAccessor.scheduleTick(blockPos, Fluids.WATER, Fluids.WATER.getTickDelay(levelAccessor))
		}
		var finalState = blockState.setValue(BOTTOM, !levelAccessor.getBlockState(blockPos.below()).`is`(this))
		if (direction == Direction.UP && blockState2.`is`(this))
		{
			finalState = finalState.setValue(ROTATION, blockState2.getValue(ROTATION))
		}
		return finalState
	}

	public override fun canSurvive(blockState: BlockState, levelReader: LevelReader, blockPos: BlockPos): Boolean
	{
		val state = levelReader.getBlockState(blockPos.above())
		return state.isFaceSturdy(levelReader, blockPos.above(), Direction.DOWN) || state.`is`(this)
	}

	public override fun getShape(
		blockState: BlockState,
		blockGetter: BlockGetter,
		blockPos: BlockPos,
		collisionContext: CollisionContext
	): VoxelShape
	{
		return SHAPE
	}

	override fun getStateForPlacement(blockPlaceContext: BlockPlaceContext): BlockState?
	{
		val level = blockPlaceContext.getLevel()
		val fluidState = level.getFluidState(blockPlaceContext.getClickedPos())
		return this.defaultBlockState()
			.setValue(ROTATION, RotationSegment.convertToSegment(blockPlaceContext.getRotation()))
			.setValue(
				WATERLOGGED, fluidState.type === Fluids.WATER
			)
	}

	override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity?
	{
		return GildedBeadsBlockEntity(blockPos, blockState)
	}

	public override fun rotate(blockState: BlockState, rotation: Rotation): BlockState
	{
		return blockState.setValue(ROTATION, rotation.rotate(blockState.getValue(ROTATION), 16))
	}

	public override fun mirror(blockState: BlockState, mirror: Mirror): BlockState
	{
		return blockState.setValue(ROTATION, mirror.mirror(blockState.getValue(ROTATION), 16))
	}

	override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>)
	{
		builder.add(ROTATION, WATERLOGGED, BOTTOM)
	}

	companion object
	{
		val CODEC = simpleCodec(::GildedBeadsBlock)

		@JvmField
		val ROTATION = BlockStateProperties.ROTATION_16
		val WATERLOGGED = BlockStateProperties.WATERLOGGED
		val BOTTOM = BlockStateProperties.BOTTOM
		protected val SHAPE = box(4.0, 0.0, 4.0, 12.0, 16.0, 12.0)
	}
}
