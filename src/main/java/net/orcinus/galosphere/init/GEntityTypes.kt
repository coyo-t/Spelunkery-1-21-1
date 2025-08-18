package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.entities.*
import java.util.function.Supplier

object GEntityTypes
{
	@JvmField
	val ENTITY_TYPES = DeferredRegister.create(Registries.ENTITY_TYPE, Galosphere.MODID)

	private infix fun <T: Entity> MobCategory.buildAs (factory: EntityType.EntityFactory<T>)
		= EntityType.Builder.of(factory, this)

	@JvmField
	val BERSERKER = ENTITY_TYPES.register("berserker") { rl ->
		(MobCategory.MONSTER buildAs ::Berserker).run {
			sized(1.4f, 2.2f)
			clientTrackingRange(16)
			fireImmune()
			build(rl.toString())
		}
	}

	@JvmField
	val PRESERVED_CORPSE = ENTITY_TYPES.register("preserved_corpse") { rl ->
		(MobCategory.MONSTER buildAs ::PreservedCorpse).run {
			sized(0.6f, 1.95f)
			clientTrackingRange(8)
			build(rl.toString())
		}
	}

	@JvmField
	val PINK_SALT_PILLAR = ENTITY_TYPES.register("pink_salt_pillar") { rl ->
		(MobCategory.MISC buildAs ::PinkSaltPillar).run {
			sized(0.7f, 1.5f)
			clientTrackingRange(16)
			fireImmune()
			build(rl.toString())
		}
	}

	@JvmField
	val PINK_SALT_SHARD = ENTITY_TYPES.register("pink_salt_shard") { rl ->
		(MobCategory.MISC buildAs ::PinkSaltShard).run {
			sized(0.5f, 0.5f)
			clientTrackingRange(4)
			updateInterval(20)
			build(rl.toString())
		}
	}
}
