package net.orcinus.galosphere.entities

import com.google.common.collect.ImmutableList
import com.mojang.serialization.Dynamic
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.DebugPackets
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AnimationState
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.Brain
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.memory.MemoryModuleType
import net.minecraft.world.entity.ai.sensing.Sensor
import net.minecraft.world.entity.ai.sensing.SensorType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.orcinus.galosphere.entities.ai.SpecterpillarAi
import net.orcinus.galosphere.init.GItemTags
import net.orcinus.galosphere.init.GMemoryModuleTypes
import net.orcinus.galosphere.init.GSensorTypes
import net.orcinus.galosphere.init.GSoundEvents

class Specterpillar(entityType: EntityType<out PathfinderMob?>, level: Level) : PathfinderMob(entityType, level)
{
	@JvmField
	val burrowAnimationState: AnimationState = AnimationState()
	var age: Int = 0

	override fun maxUpStep(): Float
	{
		return 1.0f
	}

	override fun isPushable(): Boolean
	{
		return this.pose != Pose.DIGGING && super.isPushable()
	}

	override fun onSyncedDataUpdated(entityDataAccessor: EntityDataAccessor<*>)
	{
		if (DATA_POSE == entityDataAccessor)
		{
			if (this.pose == Pose.DIGGING)
			{
				this.burrowAnimationState.start(this.tickCount)
			}
			else
			{
				this.burrowAnimationState.stop()
			}
		}
		super.onSyncedDataUpdated(entityDataAccessor)
	}

	override fun brainProvider(): Brain.Provider<Specterpillar>
	{
		return Brain.provider(MEMORY_TYPES, SENSOR_TYPES)
	}

	override fun getBrain() = super.getBrain() as Brain<Specterpillar>

	override fun makeBrain(dynamic: Dynamic<*>): Brain<*>
	{
		return SpecterpillarAi.makeBrain(this.brainProvider().makeBrain(dynamic))
	}

	override fun getMaxHeadXRot(): Int
	{
		return 1
	}

	override fun getMaxHeadYRot(): Int
	{
		return 1
	}

	override fun getHurtSound(source: DamageSource): SoundEvent?
	{
		return GSoundEvents.SPECTERPILLAR_HURT.get()
	}

	override fun getDeathSound(): SoundEvent?
	{
		return GSoundEvents.SPECTERPILLAR_DEATH.get()
	}

	override fun customServerAiStep()
	{
		this.level().profiler.push("specterpillarBrain")
		this.getBrain().tick(this.level() as ServerLevel, this)
		this.level().profiler.pop()
		this.level().profiler.push("specterpillarActivityUpdate")
		SpecterpillarAi.updateActivity(this)
		this.level().profiler.pop()
		super.customServerAiStep()
	}

	override fun mobInteract(player: Player, interactionHand: InteractionHand): InteractionResult
	{
		val stack = player.getItemInHand(interactionHand)
		val moduleType = GMemoryModuleTypes.CAN_BURY.get()
		if (!this.level().isClientSide && stack.`is`(GItemTags.SPECTRE_TEMPT_ITEMS) && !this.getBrain()
				.hasMemoryValue(moduleType)
		)
		{
			if (!player.abilities.instabuild)
			{
				stack.shrink(1)
			}
			if (this.getRandom().nextInt(5) == 0)
			{
				this.getBrain().setMemory(moduleType, true)
			}
			this.level().broadcastEntityEvent(this, 4.toByte())
			return InteractionResult.SUCCESS
		}
		return super.mobInteract(player, interactionHand)
	}

	override fun handleEntityEvent(b: Byte)
	{
		if (b.toInt() == 4)
		{
			this.level().addParticle(
				ParticleTypes.HAPPY_VILLAGER,
				this.getRandomX(1.0),
				this.getRandomY() + 0.5,
				this.getRandomZ(1.0),
				0.0,
				0.0,
				0.0
			)
		}
		else
		{
			super.handleEntityEvent(b)
		}
	}

	override fun shouldDropExperience(): Boolean
	{
		return false
	}

	override fun sendDebugPackets()
	{
		super.sendDebugPackets()
		DebugPackets.sendEntityBrain(this)
	}

	override fun addAdditionalSaveData(compoundTag: CompoundTag)
	{
		super.addAdditionalSaveData(compoundTag)
		compoundTag.putInt("Age", this.age)
	}

	override fun readAdditionalSaveData(compoundTag: CompoundTag)
	{
		super.readAdditionalSaveData(compoundTag)
		this.age = compoundTag.getInt("Age")
	}

	companion object
	{
		protected val SENSOR_TYPES = listOf(
			SensorType.NEAREST_LIVING_ENTITIES,
			SensorType.NEAREST_PLAYERS,
			SensorType.HURT_BY,
			GSensorTypes.SPECTRE_TEMPTATIONS,
			GSensorTypes.NEAREST_LICHEN_MOSS
		)
		protected val MEMORY_TYPES = listOf(
			MemoryModuleType.LOOK_TARGET,
			MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
			MemoryModuleType.WALK_TARGET,
			MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
			MemoryModuleType.PATH,
			MemoryModuleType.NEAREST_VISIBLE_ADULT,
			MemoryModuleType.TEMPTATION_COOLDOWN_TICKS,
			MemoryModuleType.IS_TEMPTED,
			MemoryModuleType.TEMPTING_PLAYER,
			MemoryModuleType.BREED_TARGET,
			MemoryModuleType.IS_PANICKING,
			GMemoryModuleTypes.CAN_BURY.get(),
			GMemoryModuleTypes.NEAREST_LICHEN_MOSS.get()
		)

		fun createAttributes(): AttributeSupplier.Builder
		{
			return createMobAttributes().add(Attributes.MAX_HEALTH, 2.0).add(Attributes.MOVEMENT_SPEED, 0.3)
		}
	}
}
