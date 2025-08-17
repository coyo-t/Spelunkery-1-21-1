package net.orcinus.galosphere.init

import com.mojang.serialization.Codec
import net.minecraft.core.BlockPos
import net.minecraft.core.registries.Registries
import net.minecraft.util.Unit
import net.minecraft.world.entity.ai.memory.MemoryModuleType
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import java.util.*
import java.util.function.Supplier

object GMemoryModuleTypes
{
	@JvmField
	val MEMORY_MODULE_TYPES =
		DeferredRegister.create<MemoryModuleType<*>>(Registries.MEMORY_MODULE_TYPE, Galosphere.MODID)

	@JvmField
	val NEAREST_POLLINATED_CLUSTER =
		MEMORY_MODULE_TYPES.register("nearest_pollinated_cluster") { rs -> MemoryModuleType<BlockPos>(Optional.empty()) }

	@JvmField
	val POLLINATED_COOLDOWN =
		MEMORY_MODULE_TYPES.register("pollinated_cooldown") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val CAN_BURY =
		MEMORY_MODULE_TYPES.register("can_bury") { rs -> MemoryModuleType<Boolean>(Optional.empty()) }

	@JvmField
	val NEAREST_LICHEN_MOSS =
		MEMORY_MODULE_TYPES.register("nearest_lichen_moss") { rs -> MemoryModuleType<BlockPos>(Optional.empty()) }

	@JvmField
	val IS_ROARING =
		MEMORY_MODULE_TYPES.register("roaring") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val IS_SMASHING =
		MEMORY_MODULE_TYPES.register("smashing") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val IS_IMPALING =
		MEMORY_MODULE_TYPES.register("impaling") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val IS_SUMMONING =
		MEMORY_MODULE_TYPES.register("summoning") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val IS_SHAKING =
		MEMORY_MODULE_TYPES.register("shaking") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val SUMMONING_COOLDOWN =
		MEMORY_MODULE_TYPES.register("summoning_cooldown") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val IMPALING_COOLDOWN =
		MEMORY_MODULE_TYPES.register("impaling_cooldown") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val SMASHING_COOLDOWN =
		MEMORY_MODULE_TYPES.register("smashing_cooldown") { rs -> MemoryModuleType(Optional.of(Codec.unit(Unit.INSTANCE))) }

	@JvmField
	val IMPALING_COUNT =
		MEMORY_MODULE_TYPES.register("impaling_count") { rs -> MemoryModuleType(Optional.of(Codec.INT)) }

	@JvmField
	val SUMMON_COUNT =
		MEMORY_MODULE_TYPES.register("summon_count") { rs -> MemoryModuleType(Optional.of(Codec.INT)) }

	@JvmField
	val HURT_COUNT =
		MEMORY_MODULE_TYPES.register("hurt_count") { rs -> MemoryModuleType(Optional.of(Codec.INT)) }

	@JvmField
	val RAMPAGE_TICKS =
		MEMORY_MODULE_TYPES.register("rampage_ticks") { rs -> MemoryModuleType(Optional.of(Codec.INT)) }
}