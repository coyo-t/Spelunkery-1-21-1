package net.orcinus.galosphere.entities.ai.sensors;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.sensing.NearestLivingEntitySensor;
import net.orcinus.galosphere.entities.PreservedCorpse;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;

public class PreservedEntitySensor extends NearestLivingEntitySensor<PreservedCorpse> {

    @Override
    public Set<MemoryModuleType<?>> requires() {
        return ImmutableSet.copyOf(Iterables.concat(super.requires(), List.of(MemoryModuleType.NEAREST_ATTACKABLE)));
    }

    @Override
    protected void doTick(ServerLevel serverLevel, PreservedCorpse preservedCorpse) {
        super.doTick(serverLevel, preservedCorpse);
        getClosest(preservedCorpse, livingEntity -> livingEntity.getType() == EntityType.PLAYER)
                .or(() -> getClosest(preservedCorpse, livingEntity -> livingEntity.getType() != EntityType.PLAYER))
                .ifPresentOrElse(livingEntity -> preservedCorpse.getBrain().setMemory(MemoryModuleType.NEAREST_ATTACKABLE, livingEntity), () -> preservedCorpse.getBrain().eraseMemory(MemoryModuleType.NEAREST_ATTACKABLE));
    }

    private static Optional<LivingEntity> getClosest(PreservedCorpse preservedCorpse, Predicate<LivingEntity> predicate) {
        return preservedCorpse.getBrain().getMemory(MemoryModuleType.NEAREST_LIVING_ENTITIES).stream().flatMap(Collection::stream).filter(preservedCorpse::canTargetEntity).filter(predicate).findFirst();
    }

    @Override
    protected int radiusXZ() {
        return 24;
    }

    @Override
    protected int radiusY() {
        return 2;
    }
}