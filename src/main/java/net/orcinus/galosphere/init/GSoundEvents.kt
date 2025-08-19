package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.level.block.SoundType
import net.neoforged.neoforge.common.util.DeferredSoundType
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import java.util.function.Supplier

object GSoundEvents
{
	@JvmField
	val SOUND_EVENTS =
		DeferredRegister.create(Registries.SOUND_EVENT, Galosphere.MODID)

	@JvmField
	val MUSIC_CRYSTAL_CANYONS = register("music.biome.crystal_canyons")
	@JvmField
	val MUSIC_LICHEN_CAVES = register("music.biome.lichen_caves")
	@JvmField
	val MUSIC_PINK_SALT_CAVES = register("music.biome.pink_salt_caves")

	@JvmField
	val MONSTROMETER_CHARGE = register("block.monstrometer.charge")
	@JvmField
	val MONSTROMETER_ACTIVATE = register("block.monstrometer.activate")
	@JvmField
	val MONSTROMETER_DEACTIVATE = register("block.monstrometer.deactivate")

	@JvmField
	val PRESERVED_DEATH = register("entity.preserved.death")
	@JvmField
	val PRESERVED_HURT = register("entity.preserved.hurt")
	@JvmField
	val PRESERVED_IDLE = register("entity.preserved.idle")
	@JvmField
	val PRESERVED_EMERGE = register("entity.preserved.emerge")
	@JvmField
	val PRESERVED_STEP = register("entity.preserved.step")

	@JvmField
	val PINK_SALT_PILLAR_EMERGE = register("entity.pink_salt_pillar.emerge")

	@JvmField
	val PINK_SALT_SHARD_LAND = register("entity.pink_salt_shard.land")

	@JvmField
	val SALTBOUND_TABLET_PREPARE_ATTACK = register("item.saltbound_tablet.prepare_attack")
	@JvmField
	val SALTBOUND_TABLET_CAST_ATTACK = register("item.saltbound_tablet.cast_attack")
	@JvmField
	val SALTBOUND_TABLET_COOLDOWN_OVER = register("item.saltbound_tablet.cooldown_over")

	@JvmField
	val PINK_SALT_CHAMBER_SUMMON = register("block.pink_salt_chamber.summon")
	@JvmField
	val PINK_SALT_CHAMBER_DEACTIVATE = register("block.pink_salt_chamber.deactivate")

	val ALLURITE = soundType("allurite")
	val ALLURITE_CLUSTER = soundType("allurite_cluster")
	val MONSTROMETER = soundType("monstrometer")
	val BOWL_LICHEN = soundType("bowl_lichen")
	val COMBUSTION_TABLE = soundType("combustion_table")
	val GLOW_INK_CLUMPS = soundType("glow_ink_clumps")
	val LICHEN_CORDYCEPS = soundType("lichen_cordyceps")
	val LICHEN_CORDYCEPS_BULB = soundType("lichen_cordyceps_bulb")
	val LICHEN_MOSS = soundType("lichen_moss")
	val LICHEN_ROOTS = soundType("lichen_roots")
	val LICHEN_SHELF = soundType("lichen_shelf")
	val LUMIERE = soundType("lumiere")
	val LUMIERE_CLUSTER = soundType("lumiere_cluster")
	val SILVER = soundType("silver")
	val SILVER_LATTICE = soundType("silver_lattice")
	val PINK_SALT = soundType("pink_salt")
	val PINK_SALT_CLUSTER = soundType("pink_salt_cluster")
	val PINK_SALT_LAMP = soundType("pink_salt_lamp")
	val GILDED_BEADS = soundType("gilded_beads")
	val CURED_MEMBRANE = soundType("cured_membrane")
	val STRANDED_MEMBRANE = soundType("stranded_membrane")

	private fun register(string: String) = SOUND_EVENTS.register(string) { r -> SoundEvent.createVariableRangeEvent(Galosphere.id(string)) }

	private fun block(name: String, append: String) = "block.$name.$append"

	private fun soundType(name: String) = register(name, 1f, 1f)

	private fun register(name: String, volume: Float, pitch: Float) = DeferredSoundType(
		volume, pitch,
		register(block(name, "break")),
		register(block(name, "step")),
		register(block(name, "place")),
		register(block(name, "hit")),
		register(block(name, "fall")),
	)
}
