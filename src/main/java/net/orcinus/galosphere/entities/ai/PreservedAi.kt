package net.orcinus.galosphere.entities.ai

import com.google.common.collect.ImmutableMap
import com.google.common.collect.ImmutableSet
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.entity.Pose
import net.minecraft.world.entity.ai.Brain
import net.minecraft.world.entity.ai.behavior.*
import net.minecraft.world.entity.ai.memory.MemoryModuleType
import net.minecraft.world.entity.ai.memory.MemoryModuleType.IS_EMERGING
import net.minecraft.world.entity.ai.memory.MemoryStatus
import net.minecraft.world.entity.schedule.Activity
import net.orcinus.galosphere.entities.PreservedCorpse
import com.google.common.collect.ImmutableList.of as immuOf
import com.mojang.datafixers.util.Pair.of as pairOf

object PreservedAi
{
	@JvmStatic
	fun makeBrain(brain: Brain<PreservedCorpse>) = brain.apply {
		addActivity(Activity.CORE, 0, immuOf(LookAtTargetSink(45, 90), MoveToTargetSink()))
		addActivityAndRemoveMemoryWhenStopped(Activity.EMERGE, 5, immuOf(Rise(40)), IS_EMERGING)
		addActivity(
			Activity.IDLE, 10, immuOf(
				StartAttacking.create { it.getBrain().getMemory(MemoryModuleType.NEAREST_ATTACKABLE) },
				SetEntityLookTargetSometimes.create(8.0f, UniformInt.of(30, 60)),
				RunOne(
					immuOf(
						pairOf(RandomStroll.stroll(1.0f), 2),
						pairOf(SetWalkTargetFromLookTarget.create(1.0f, 3), 2),
						pairOf(DoNothing(30, 60), 1),
					)
				)
			),
		)
		addActivityAndRemoveMemoryWhenStopped(
			Activity.FIGHT, 10, immuOf(
				SetWalkTargetFromAttackTargetIfTargetOutOfReach.create(1.2f),
				MeleeAttack.create(40),
				StopAttackingIfTargetInvalid.create()
			), MemoryModuleType.ATTACK_TARGET
		)

		setCoreActivities(ImmutableSet.of(Activity.CORE))
		setDefaultActivity(Activity.IDLE)
		useDefaultActivity()
	}

	@JvmStatic
	fun updateActivity(preservedCorpse: PreservedCorpse)
	{
		preservedCorpse.getBrain()
			.setActiveActivityToFirstValid(immuOf(Activity.EMERGE, Activity.FIGHT, Activity.IDLE))
	}

	val entryCondition = ImmutableMap.of(
		IS_EMERGING,
		MemoryStatus.VALUE_PRESENT,
		MemoryModuleType.WALK_TARGET,
		MemoryStatus.VALUE_ABSENT,
		MemoryModuleType.LOOK_TARGET,
		MemoryStatus.REGISTERED
	)

	class Rise(i: Int) : Behavior<PreservedCorpse>(entryCondition, i)
	{
		override fun canStillUse(serverLevel: ServerLevel, livingEntity: PreservedCorpse, l: Long) = true

		override fun start(serverLevel: ServerLevel, livingEntity: PreservedCorpse, l: Long)
		{
			livingEntity.pose = Pose.EMERGING
		}

		override fun stop(serverLevel: ServerLevel, livingEntity: PreservedCorpse, l: Long)
		{
			if (livingEntity.hasPose(Pose.EMERGING))
			{
				livingEntity.pose = Pose.STANDING
			}
		}
	}
}