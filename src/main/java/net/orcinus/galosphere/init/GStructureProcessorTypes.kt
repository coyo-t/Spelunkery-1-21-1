package net.orcinus.galosphere.init

import com.mojang.serialization.MapCodec
import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessor
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorType
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.world.gen.processors.pink_salt_shrine.MainRoomProcessor
import net.orcinus.galosphere.world.gen.processors.pink_salt_shrine.NoWaterloggedProcessor
import net.orcinus.galosphere.world.gen.processors.pink_salt_shrine.RawOreProcessor
import java.util.function.Supplier

object GStructureProcessorTypes
{
	@JvmField
	val STRUCTURE_PROCESSOR_TYPES = DeferredRegister.create(Registries.STRUCTURE_PROCESSOR, Galosphere.MODID)

	@JvmField
	val PINK_SALT_MAIN_ROOM = register("pink_salt_main_room", MainRoomProcessor.CODEC)
	@JvmField
	val NO_WATERLOGGED = register("no_waterlogged", NoWaterloggedProcessor.CODEC)
	@JvmField
	val RAW_ORES = register("raw_ores", RawOreProcessor.CODEC)

	fun <P : StructureProcessor> register(string: String, codec: MapCodec<P>)
		= STRUCTURE_PROCESSOR_TYPES.register(string) { r -> StructureProcessorType { codec } }
}
