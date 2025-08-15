package net.orcinus.galosphere.entities;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public abstract class ThrowableLaunchedProjectile extends FireworkRocketEntity {
    protected static final EntityDataAccessor<Boolean> THROWN = SynchedEntityData.defineId(ThrowableLaunchedProjectile.class, EntityDataSerializers.BOOLEAN);

    public ThrowableLaunchedProjectile(EntityType<? extends FireworkRocketEntity> entityType, Level level) {
        super(entityType, level);
    }

    public ThrowableLaunchedProjectile(Level world, ItemStack stack, Entity entity, double x, double y, double z, boolean shotAtAngle) {
        super(world, stack, entity, x, y, z, shotAtAngle);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(THROWN, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
        this.setThrown(compoundTag.getBoolean("Thrown"));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
        compoundTag.putBoolean("Thrown", this.isThrown());
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
    }

    public boolean isThrown() {
        return this.entityData.get(THROWN);
    }

    public void setThrown(boolean thrown) {
        this.entityData.set(THROWN, thrown);
    }

    @Override
    public void tick() {
        if (this.isThrown()) {
            float h;
            if (!this.hasBeenShot) {
                this.gameEvent(GameEvent.PROJECTILE_SHOOT, this.getOwner());
                this.hasBeenShot = true;
            }
            if (!this.leftOwner) {
                this.leftOwner = this.checkLeftOwner();
            }
            this.baseTick();
            HitResult hitResult = ProjectileUtil.getHitResultOnMoveVector(this, this::canHitEntity);
            if (hitResult.getType() != HitResult.Type.MISS) {
                this.hitTargetOrDeflectSelf(hitResult);
            }
            this.checkInsideBlocks();
            Vec3 vec3 = this.getDeltaMovement();
            double d = this.getX() + vec3.x;
            double e = this.getY() + vec3.y;
            double f = this.getZ() + vec3.z;
            this.updateRotation();
            if (this.isInWater()) {
                for (int i = 0; i < 4; ++i) {
                    this.level().addParticle(ParticleTypes.BUBBLE, d - vec3.x * 0.25, e - vec3.y * 0.25, f - vec3.z * 0.25, vec3.x, vec3.y, vec3.z);
                }
                h = 0.8f;
            } else {
                h = 0.99f;
            }
            this.setDeltaMovement(vec3.scale(h));
            if (!this.isNoGravity()) {
                Vec3 vec32 = this.getDeltaMovement();
                this.setDeltaMovement(vec32.x, vec32.y - (double)this.getGravity(), vec32.z);
            }
            this.setPos(d, e, f);
        } else {
            super.tick();
            this.handleLaunchedProjectile();
        }
    }

    @Override
    protected double getDefaultGravity() {
        return 0.03D;
    }

    public void handleLaunchedProjectile() {
    }

    protected abstract Item getDefaultItem();

    @Override
    public ItemStack getItem() {
        return new ItemStack(this.getDefaultItem());
    }
}