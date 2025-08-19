package net.orcinus.galosphere.entities

import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import net.minecraft.world.phys.EntityHitResult
import net.orcinus.galosphere.init.GBlocks
import net.orcinus.galosphere.init.GEntityTypes
import net.orcinus.galosphere.init.GSoundEvents

class PinkSaltShard : AbstractArrow
{
	private val maxTicks = 600
	private var ticks = 0

	constructor(entityType: EntityType<out AbstractArrow>, level: Level) : super(entityType, level)

	constructor(livingEntity: LivingEntity, level: Level) : super(GEntityTypes.PINK_SALT_SHARD.get(), level)
	{
		this.setOwner(livingEntity)
		this.baseDamage = 4.0
	}

	override fun tick()
	{
		if (this.ticks++ > this.maxTicks)
		{
			this.level().broadcastEntityEvent(this, 3.toByte())
			this.discard()
		}
		super.tick()
	}

	override fun onHitBlock(blockHitResult: BlockHitResult)
	{
		super.onHitBlock(blockHitResult)
		this.level().broadcastEntityEvent(this, 3.toByte())
		this.discard()
	}

	override fun onHitEntity(entityHitResult: EntityHitResult)
	{
		val entity = entityHitResult.entity
		val flag = entity === this.owner || this.owner != null && this.owner!!.isAlliedTo(entity)
		if (!flag)
		{
			super.onHitEntity(entityHitResult)
		}
	}

	override fun getDefaultHitGroundSoundEvent() = GSoundEvents.PINK_SALT_SHARD_LAND.get()

	override fun getPickupItem() = ItemStack.EMPTY

	override fun getDefaultPickupItem() = ItemStack.EMPTY

	override fun handleEntityEvent(b: Byte)
	{
		if (b.toInt() == 3)
		{
			val particleOptions = BlockParticleOption(ParticleTypes.BLOCK, GBlocks.PINK_SALT.get().defaultBlockState())
			for (i in 0..7)
			{
				this.level().addParticle(particleOptions, this.x, this.y + 1.0, this.z, 0.0, 0.0, 0.0)
			}
		}
	}
}