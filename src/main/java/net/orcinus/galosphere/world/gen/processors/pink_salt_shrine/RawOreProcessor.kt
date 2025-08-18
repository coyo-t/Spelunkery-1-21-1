package net.orcinus.galosphere.world.gen.processors.pink_salt_shrine

import com.mojang.serialization.MapCodec
import net.minecraft.core.BlockPos
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate.StructureBlockInfo
import net.orcinus.galosphere.init.GStructureProcessorTypes

class RawOreProcessor : StructureProcessor()
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
		val randomSource = structurePlaceSettings.getRandom(structureBlockInfo2.pos())
		if (structureBlockInfo2.state().`is`(Blocks.RAW_COPPER_BLOCK))
		{
			return StructureBlockInfo(
				structureBlockInfo2.pos(),
				LIST[randomSource.nextInt(LIST.size)].defaultBlockState(),
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

	override fun getType() = GStructureProcessorTypes.RAW_ORES.get()

	companion object
	{
		val CODEC = MapCodec.unit<RawOreProcessor>(::RawOreProcessor)
		private val LIST = listOf(
			Blocks.RAW_IRON_BLOCK,
			Blocks.RAW_COPPER_BLOCK,
			Blocks.RAW_GOLD_BLOCK,
		)
	}
}