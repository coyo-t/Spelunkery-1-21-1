package net.orcinus.galosphere.world.gen.processors.pink_salt_shrine

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo
import net.orcinus.galosphere.init.GStructureProcessorTypes
import java.util.function.Supplier

class NoWaterloggedProcessor : StructureProcessor()
{
	override fun processBlock(
		levelReader: LevelReader,
		blockPos: BlockPos,
		blockPos2: BlockPos,
		structureBlockInfo: StructureBlockInfo,
		structureBlockInfo2: StructureBlockInfo,
		structurePlaceSettings: StructurePlaceSettings
	): StructureBlockInfo?
	{
		if (structureBlockInfo2.state()
				.hasProperty(BlockStateProperties.WATERLOGGED) && structureBlockInfo2.state()
				.getValue(BlockStateProperties.WATERLOGGED)
		)
		{
			return StructureBlockInfo(
				structureBlockInfo2.pos(),
				structureBlockInfo2.state().setValue(BlockStateProperties.WATERLOGGED, false),
				structureBlockInfo2.nbt()
			)
		}
		return super.processBlock(
			levelReader,
			blockPos,
			blockPos2,
			structureBlockInfo,
			structureBlockInfo2,
			structurePlaceSettings
		)
	}

	override fun getType() = GStructureProcessorTypes.NO_WATERLOGGED.get()

	companion object
	{
		val CODEC = MapCodec.unit(::NoWaterloggedProcessor)
	}
}