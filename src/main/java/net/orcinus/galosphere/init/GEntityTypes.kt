package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.entities.*
import java.util.function.Supplier

object GEntityTypes
{
	@JvmField
	val ENTITY_TYPES =
		DeferredRegister.create(Registries.ENTITY_TYPE, Galosphere.MODID)

	@JvmField
	val SPARKLE = ENTITY_TYPES.register(
		"sparkle",
		Supplier {
			EntityType.Builder.of({ type, world ->
				Sparkle(
					type,
					world
				)
			}, MobCategory.UNDERGROUND_WATER_CREATURE).sized(1.0f, 0.55f).clientTrackingRange(10)
				.build(Galosphere.id("sparkle").toString())
		})
	@JvmField
	val SPECTRE = ENTITY_TYPES.register(
		"spectre",
		Supplier {
			EntityType.Builder.of({ entityType, level ->
				Spectre(
					entityType,
					level
				)
			}, MobCategory.AMBIENT).sized(0.5f, 0.5f).clientTrackingRange(8).updateInterval(2)
				.build(Galosphere.id("spectre").toString())
		})
	@JvmField
	val GLOW_FLARE = ENTITY_TYPES.register(
		"glow_flare",
		Supplier {
			EntityType.Builder.of({ type, world ->
				GlowFlare(
					type,
					world
				)
			}, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(10)
				.build(Galosphere.id("glow_flare").toString())
		})
	@JvmField
	val SPECTRE_FLARE = ENTITY_TYPES.register(
		"spectre_flare",
		Supplier {
			EntityType.Builder.of({ type, world ->
				SpectreFlare(
					type,
					world
				)
			}, MobCategory.MISC).sized(0.25f, 0.25f).clientTrackingRange(4).updateInterval(10)
				.build(Galosphere.id("spectre_flare").toString())
		})
	@JvmField
	val SPECTERPILLAR = ENTITY_TYPES.register(
		"specterpillar",
		Supplier {
			EntityType.Builder.of({ entityType, level ->
				Specterpillar(
					entityType,
					level
				)
			}, MobCategory.CREATURE).sized(0.4f, 0.3f).clientTrackingRange(8).updateInterval(2)
				.build(Galosphere.id("specterpillar").toString())
		})
	@JvmField
	val SPECTATOR_VISION = ENTITY_TYPES.register(
		"spectator_vision",
		Supplier {
			EntityType.Builder.of({ entityType, level ->
				SpectatorVision(
					entityType,
					level
				)
			}, MobCategory.MISC).sized(0.5f, 0.5f).clientTrackingRange(4).updateInterval(5)
				.build(Galosphere.id("spectator_vision").toString())
		})
	@JvmField
	val BERSERKER = ENTITY_TYPES.register(
		"berserker",
		Supplier {
			EntityType.Builder.of({ entityType, level ->
				Berserker(
					entityType,
					level
				)
			}, MobCategory.MONSTER).sized(1.4f, 2.2f).clientTrackingRange(16).fireImmune()
				.build(Galosphere.id("berserker").toString())
		})
	@JvmField
	val PRESERVED_CORPSE = ENTITY_TYPES.register(
		"preserved_corpse",
		Supplier {
			EntityType.Builder.of({ entityType, level ->
				PreservedCorpse(
					entityType,
					level
				)
			}, MobCategory.MONSTER).sized(0.6f, 1.95f).clientTrackingRange(8).build(Galosphere.id("preserved").toString())
		})
	@JvmField
	val PINK_SALT_PILLAR = ENTITY_TYPES.register(
		"pink_salt_pillar",
		Supplier {
			EntityType.Builder.of({ entityType, level ->
				PinkSaltPillar(
					entityType,
					level
				)
			}, MobCategory.MISC).sized(0.7f, 1.5f).clientTrackingRange(16).fireImmune()
				.build(Galosphere.id("pink_salt_pillar").toString())
		})
	@JvmField
	val PINK_SALT_SHARD = ENTITY_TYPES.register(
		"pink_salt_shard",
		Supplier {
			EntityType.Builder.of({ entityType, level ->
				PinkSaltShard(
					entityType,
					level
				)
			}, MobCategory.MISC).sized(0.5f, 0.5f).clientTrackingRange(4).updateInterval(20)
				.build(Galosphere.id("pink_salt_shard").toString())
		})
}
