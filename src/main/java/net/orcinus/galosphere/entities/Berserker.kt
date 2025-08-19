package net.orcinus.galosphere.entities

import com.mojang.serialization.Dynamic
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.util.Unit
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.Difficulty
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffectUtil
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.Brain
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.memory.MemoryModuleType
import net.minecraft.world.entity.ai.sensing.SensorType
import net.minecraft.world.entity.animal.AbstractGolem
import net.minecraft.world.entity.animal.IronGolem
import net.minecraft.world.entity.animal.Turtle
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.npc.AbstractVillager
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import net.orcinus.galosphere.entities.ai.BerserkerAi
import net.orcinus.galosphere.init.*
import java.util.function.Consumer
import java.util.function.Predicate
import kotlin.math.max
import kotlin.math.min

class Berserker(entityType: EntityType<out Monster>, level: Level) : Monster(entityType, level)
{
	private val selectedEffects = listOf(
		GMobEffects.BLOCK_BANE,
		MobEffects.DIG_SLOWDOWN,
	)
//	private val selectedEffects = Util.make(
//		Lists.newArrayList<Holder<MobEffect?>?>(),
//		Consumer { list: ArrayList<Holder<MobEffect?>?> ->
//			list.add(GMobEffects.BLOCK_BANE)
//			list.add(MobEffects.DIG_SLOWDOWN)
//		})
	var roarAnimationState = AnimationState()
	var attackAnimationState = AnimationState()
	var punchAnimationState = AnimationState()
	var impalingAnimationState = AnimationState()
	var summoningAnimationState = AnimationState()

	init
	{
		this.lookControl = BerserkerLookControl(this)
	}

	override fun isInvulnerableTo(damageSource: DamageSource): Boolean
	{
		val maybePlayer = damageSource.entity
		if (maybePlayer is Player && !maybePlayer.abilities.instabuild && this.stationaryTicks > 0)
		{
			return true
		}
		return super.isInvulnerableTo(damageSource)
	}

	override fun maxUpStep(): Float
	{
		return 1.0f
	}

	override fun defineSynchedData(builder: SynchedEntityData.Builder)
	{
		super.defineSynchedData(builder)
		builder.define(PHASE, Phase.IDLING.name)
		builder.define(STATIONARY_TICKS, 0)
		builder.define(SHEDDING, false)
	}

	override fun readAdditionalSaveData(compoundTag: CompoundTag)
	{
		super.readAdditionalSaveData(compoundTag)
		val phase = compoundTag.getString("Phase")
		if (!phase.isEmpty())
		{
			this.phase = Phase.valueOf(phase)
		}
		this.stationaryTicks = compoundTag.getInt("StationaryTicks")
		this.isShedding = compoundTag.getBoolean("Shedding")
	}

	override fun addAdditionalSaveData(compoundTag: CompoundTag)
	{
		super.addAdditionalSaveData(compoundTag)
		compoundTag.putString("Phase", this.phase.name)
		compoundTag.putInt("StationaryTicks", this.stationaryTicks)
		compoundTag.putBoolean("Shedding", this.isShedding)
	}

	fun shouldAttack() = this.phase == Phase.IDLING && !this.isStationary

	val stage: Int
		get()
		{
			val health = this.health / this.maxHealth
			return when
			{
				isStationary ->
					3
				health > 0.66f ->
					0
				health <= 0.66f && health > 0.33f ->
					1
				else ->
					2
			}
		}

	var isShedding: Boolean
		get() = this.entityData.get(SHEDDING)
		set(shedding)
		{
			this.entityData.set(SHEDDING, shedding)
		}

	var stationaryTicks: Int
		get() = this.entityData.get(STATIONARY_TICKS)
		set(stationaryTicks)
		{
			this.entityData.set(
				STATIONARY_TICKS,
				stationaryTicks
			)
		}

	override fun handleEntityEvent(b: Byte)
	{
		when
		{
			b.toInt() == 4 ->
			{
				this.attackAnimationState.start(this.tickCount)
			}
			b.toInt() == 5 ->
			{
				this.punchAnimationState.start(this.tickCount)
			}
			b.toInt() == 6 ->
			{
				this.impalingAnimationState.start(this.tickCount)
			}
			b.toInt() == 7 ->
			{
				this.summoningAnimationState.start(this.tickCount)
			}
			b.toInt() == 32 ->
			{
				val blockPos = this.getOnPos()
				this.level().addParticle(
					GParticleTypes.IMPACT.get(),
					blockPos.getX() + 0.5,
					blockPos.getY() + 1.15,
					blockPos.getZ() + 0.5,
					0.0,
					0.0,
					0.0
				)
			}
			else ->
			{
				super.handleEntityEvent(b)
			}
		}
	}

	var phase: Phase
		get() = Phase.valueOf(this.entityData.get<String>(PHASE))
		set(phase)
		{
			when (phase)
			{
				Phase.IDLING ->
					this.pose = Pose.STANDING
				Phase.SMASH ->
					this.level().broadcastEntityEvent(this, 4.toByte())
				Phase.UNDERMINE ->
					this.level().broadcastEntityEvent(this, 6.toByte())
				Phase.SUMMONING ->
					this.level().broadcastEntityEvent(this, 7.toByte())
			}
			this.entityData.set(PHASE, phase.name)
		}

	override fun aiStep()
	{
		super.aiStep()
		val range = 0.75
		val threshold = range - 0.6
		val increment = 0.2
		if (!this.level().isClientSide)
		{
			val count = 250
			val stationary = this.isStationary
			val shedding = this.isShedding
			if (this.health < this.maxHealth && this.tickCount % count == 0)
			{
				this.heal(10.0f)
			}
			if (stationary)
			{
				val brain1 = getBrain()
				brain1
				.getMemories()
				.keys
				.stream()
				.filter { it == MemoryModuleType.WALK_TARGET || it == MemoryModuleType.LOOK_TARGET }
				.forEach { brain1.eraseMemory(it) }

				val list = this.level()
				.getEntitiesOfClass(Player::class.java, this.boundingBox.inflate(3.0))
				.stream()
				.filter { !it.isCreative && it.isAlive }
				.toList()

				val player = list.stream().findAny()
				if (!shedding)
				{
					player.ifPresent(this::setTarget)
				}
				else
				{
					list
					.stream()
					.filter(ServerPlayer::class.java::isInstance)
					.map(ServerPlayer::class.java::cast)
					.forEach { CriteriaTriggers.SUMMONED_ENTITY.trigger(it, this) }
					if (this.stationaryTicks == 32)
					{
						brain1.setMemory(GMemoryModuleTypes.IS_SHAKING.get(), Unit.INSTANCE)
					}
					this.stationaryTicks = this.stationaryTicks - 1
					this.addParticles(range, increment, threshold)
				}
			}
			else
			{
				if (shedding)
				{
					this.isShedding = false
					this.setPersistenceRequired()
				}
			}
		}
	}

	private fun addParticles(range: Double, increment: Double, threshold: Double)
	{
		if (this.tickCount % 20 != 0) return
		var y = 0.0
		while (y <= 1.95)
		{
			var x = -range
			while (x <= range)
			{
				var z = -range
				while (z <= range)
				{
					if (x >= -threshold && x <= threshold || z >= -threshold && z <= threshold)
					{
						z += increment
						continue
					}
					(this.level() as ServerLevel).sendParticles(
						GParticleTypes.PINK_SALT_FALLING_DUST.get(),
						this.getX() + x,
						this.getY() + y,
						this.getZ() + z,
						1,
						0.0,
						0.0,
						0.0,
						0.0
					)
					z += increment
				}
				x += increment
			}
			y += 0.35
		}
	}

	val isStationary: Boolean
		get() = this.stationaryTicks > 0

	private fun setTarget(player: Player?)
	{
		val brain = this.getBrain()
		brain.setMemory(MemoryModuleType.ATTACK_TARGET, player)
		this.isShedding = true
	}

	override fun finalizeSpawn(
		serverLevelAccessor: ServerLevelAccessor,
		difficultyInstance: DifficultyInstance,
		mobSpawnType: MobSpawnType,
		spawnGroupData: SpawnGroupData?
	): SpawnGroupData?
	{
		if (mobSpawnType == MobSpawnType.STRUCTURE)
		{
			this.stationaryTicks = 100
		}
		return super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData)
	}

	override fun getAmbientSound(): SoundEvent?
	{
		return if (this.isStationary) null else GSoundEvents.BERSERKER_IDLE.get()
	}

	override fun getHurtSound(damageSource: DamageSource): SoundEvent
	{
		return GSoundEvents.BERSERKER_HURT.get()
	}

	override fun getDeathSound(): SoundEvent
	{
		return GSoundEvents.BERSERKER_DEATH.get()
	}

	protected val stepSound: SoundEvent
		get() = GSoundEvents.BERSERKER_STEP.get()

	override fun playStepSound(blockPos: BlockPos, blockState: BlockState)
	{
		playSound(this.stepSound, 1f, 1f)
	}

	fun canTargetEntity(entity: Entity): Boolean
	{
		if (entity !is LivingEntity)
		{
			return false
		}
		val predicate = Predicate<LivingEntity> { it.type.`is`(GEntityTypeTags.BERSERKER_INVALID_TARGETS) }
		if (entity.isInvulnerable || entity.isDeadOrDying || predicate.test(entity))
		{
			return false
		}
		val lastSource = this.getLastDamageSource()
		val e = lastSource?.entity
		if (e is LivingEntity && e === entity && !predicate.test(e))
		{
			return true
		}
		if (this.level() !== entity.level() || !EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(entity) || this.isAlliedTo(
				entity
			) || !this.level().worldBorder.isWithinBounds(entity.getBoundingBox())
		)
		{
			return false
		}
		return entity is Player || entity is AbstractVillager || entity is IronGolem || entity is Turtle
	}

	override fun updateWalkAnimation(f: Float)
	{
		val g = min(f * 10.0f, 1.0f)
		this.walkAnimation.update(g, 0.2f)
	}

	override fun onSyncedDataUpdated(entityDataAccessor: EntityDataAccessor<*>)
	{
		if (DATA_POSE == entityDataAccessor)
		{
			if (this.getPose() == Pose.EMERGING)
			{
				this.roarAnimationState.start(this.tickCount)
			}
		}
		super.onSyncedDataUpdated(entityDataAccessor)
	}

	fun shouldUseMeleeAttack(): Boolean
	{
		val brain1 = this.getBrain()
		val memory = brain1.getMemory(MemoryModuleType.ATTACK_TARGET)
		return memory.filter {
			this.isWithinMeleeAttackRange(it) && this.phase != Phase.SMASH &&
					  this.shouldAttack() &&
					  this.isInHardMode &&
					  brain1.getMemory(GMemoryModuleTypes.RAMPAGE_TICKS.get()).isPresent && brain1
				.getMemory(GMemoryModuleTypes.RAMPAGE_TICKS.get()).get() > 0
		}.isPresent
	}

	val isInHardMode: Boolean
		get() = this.level().getDifficulty() == Difficulty.HARD

	override fun doHurtTarget(entity: Entity): Boolean
	{
		if (entity is LivingEntity)
		{
			if (entity is AbstractGolem || entity is TamableAnimal)
			{
				val dist = max(1f, this.distanceTo(entity)).toDouble()
				entity.hurt(this.level().damageSources().mobAttack(this), ((entity.getMaxHealth()) / (dist / 2)).toFloat())
			}
			if (this.shouldUseMeleeAttack())
			{
				val start = this.position().add(0.0, 1.6, 0.0)
				val diff = entity.getEyePosition().subtract(start)
				val normalized = diff.normalize()
				val knockbackX = 0.25 * (1 - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE))
				val knockbackY = 1.5 * (1 - entity.getAttributeValue(Attributes.KNOCKBACK_RESISTANCE))
				entity.push(normalized.x() * knockbackY, normalized.y() * knockbackX, normalized.z() * knockbackY)
				this.level().broadcastEntityEvent(this, 5.toByte())
				this.playSound(GSoundEvents.BERSERKER_PUNCH.get(), 1f, 1f)
			}
			var flag = true
			if (entity is Player && entity.abilities.instabuild)
			{
				flag = false
			}
			if (flag)
			{
				entity.addEffect(MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100))
			}
		}
		return super.doHurtTarget(entity)
	}

	override fun canDisableShield(): Boolean
	{
		return true
	}

	override fun hurt(damageSource: DamageSource, f: Float): Boolean
	{
		if (!this.level().isClientSide && this.phase != Phase.IDLING && this.isInHardMode)
		{
			if (this.getBrain().getMemory(GMemoryModuleTypes.HURT_COUNT.get()).isEmpty())
			{
				this.getBrain().setMemory(GMemoryModuleTypes.HURT_COUNT.get(), 0)
			}
			else
			{
				val i = this.getBrain().getMemory(GMemoryModuleTypes.HURT_COUNT.get()).get() + 1
				if (i > 2)
				{
					this.getBrain().setMemory(
						GMemoryModuleTypes.RAMPAGE_TICKS.get(),
						UniformInt.of(30, 150).sample(this.getRandom())
					)
				}
				this.getBrain().setMemory(GMemoryModuleTypes.HURT_COUNT.get(), i)
			}
		}
		if (damageSource.directEntity is AbstractArrow && this.phase != Phase.IDLING)
		{
			return false
		}
		return super.hurt(damageSource, f)
	}

	override fun brainProvider() = Brain.provider<Berserker>(MEMORY_TYPES, SENSOR_TYPES)

	override fun makeBrain(dynamic: Dynamic<*>): Brain<*>
	{
		return BerserkerAi.makeBrain(this, this.brainProvider().makeBrain(dynamic))
	}

	override fun getBrain(): Brain<Berserker>
	{
		return super.getBrain() as Brain<Berserker>
	}

	override fun customServerAiStep()
	{
		this.level().profiler.push("berserkerBrain")
		this.getBrain().tick(this.level() as ServerLevel, this)
		this.level().profiler.pop()
		BerserkerAi.updateActivity(this)
		super.customServerAiStep()
		if ((this.tickCount + this.id) % 1200 == 0)
		{
			this.selectedEffects.forEach {
				val mobEffectInstance = MobEffectInstance(it, 6000, 2)
				MobEffectUtil.addEffectToPlayersAround(
					this.level() as ServerLevel,
					this,
					this.position(),
					50.0,
					mobEffectInstance,
					1200
				)
			}
		}
		if (this.getBrain().getMemory(MemoryModuleType.HURT_BY_ENTITY)
				.isEmpty && this.phase != Phase.IDLING
		)
		{
			this.phase = Phase.IDLING
		}
	}

	override fun travel(vec3: Vec3)
	{
		var vec3 = vec3
		if (this.isStationary && this.onGround())
		{
			this.setDeltaMovement(this.getDeltaMovement().multiply(0.0, 1.0, 0.0))
			vec3 = vec3.multiply(0.0, 1.0, 0.0)
		}
		super.travel(vec3)
	}

	inner class BerserkerLookControl(mob: Mob) : LookControl(mob)
	{
		override fun tick()
		{
			if (!this@Berserker.isStationary)
			{
				super.tick()
			}
		}
	}

	enum class Phase
	{
		IDLING,
		SMASH,
		UNDERMINE,
		SUMMONING
	}

	companion object
	{
		private val SENSOR_TYPES = listOf(
			SensorType.NEAREST_LIVING_ENTITIES,
			SensorType.NEAREST_PLAYERS,
			SensorType.HURT_BY,
		)
		private val MEMORY_TYPES = listOf(
			MemoryModuleType.BREED_TARGET,
			MemoryModuleType.NEAREST_LIVING_ENTITIES,
			MemoryModuleType.NEAREST_VISIBLE_LIVING_ENTITIES,
			MemoryModuleType.NEAREST_VISIBLE_PLAYER,
			MemoryModuleType.NEAREST_VISIBLE_ATTACKABLE_PLAYER,
			MemoryModuleType.LOOK_TARGET,
			MemoryModuleType.WALK_TARGET,
			MemoryModuleType.CANT_REACH_WALK_TARGET_SINCE,
			MemoryModuleType.PATH,
			MemoryModuleType.ATTACK_TARGET,
			MemoryModuleType.ATTACK_COOLING_DOWN,
			MemoryModuleType.AVOID_TARGET,
			MemoryModuleType.HURT_BY,
			MemoryModuleType.HURT_BY_ENTITY,
			MemoryModuleType.NEAREST_ATTACKABLE,
			GMemoryModuleTypes.IMPALING_COOLDOWN.get(),
			GMemoryModuleTypes.IMPALING_COUNT.get(),
			GMemoryModuleTypes.IS_SMASHING.get(),
			GMemoryModuleTypes.IS_IMPALING.get(),
			GMemoryModuleTypes.IS_SUMMONING.get(),
			GMemoryModuleTypes.SUMMONING_COOLDOWN.get(),
			GMemoryModuleTypes.SUMMON_COUNT.get(),
			GMemoryModuleTypes.SMASHING_COOLDOWN.get(),
			GMemoryModuleTypes.HURT_COUNT.get(),
			GMemoryModuleTypes.RAMPAGE_TICKS.get(),
			MemoryModuleType.ROAR_SOUND_COOLDOWN,
			MemoryModuleType.ROAR_SOUND_DELAY,
			GMemoryModuleTypes.IS_SHAKING.get()
		)
		private val PHASE =
			SynchedEntityData.defineId(Berserker::class.java, EntityDataSerializers.STRING)
		private val STATIONARY_TICKS =
			SynchedEntityData.defineId(Berserker::class.java, EntityDataSerializers.INT)
		private val SHEDDING =
			SynchedEntityData.defineId(Berserker::class.java, EntityDataSerializers.BOOLEAN)

		fun createAttributes(): AttributeSupplier.Builder
		{
			return createMonsterAttributes().apply {
				add(Attributes.MAX_HEALTH, 155.0)
				add(Attributes.MOVEMENT_SPEED, 0.3)
				add(Attributes.ATTACK_DAMAGE, 10.0)
				add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
				add(Attributes.ATTACK_KNOCKBACK, 1.5)
			}
		}
	}
}