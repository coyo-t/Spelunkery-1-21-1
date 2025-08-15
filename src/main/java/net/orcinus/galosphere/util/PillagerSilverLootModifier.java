package net.orcinus.galosphere.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.orcinus.galosphere.init.GItems;
import org.jetbrains.annotations.NotNull;

public class PillagerSilverLootModifier extends LootModifier {
    public static final MapCodec<PillagerSilverLootModifier> CODEC = RecordCodecBuilder.mapCodec(instance -> codecStart(instance).and(instance.group(
            Codec.INT.fieldOf("min").forGetter(m -> m.min),
            Codec.INT.fieldOf("max").forGetter(m -> m.max)
    )).apply(instance, PillagerSilverLootModifier::new));

    private final int min;
    private final int max;

    public PillagerSilverLootModifier(LootItemCondition[] conditionsIn, int min, int max) {
        super(conditionsIn);
        this.min = min;
        this.max = max;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext lootContext) {
        int lootingModifier = lootContext.getLootingModifier();
        int count = UniformInt.of(this.min, this.max).sample(lootContext.getRandom()) + lootingModifier;
        objectArrayList.add(new ItemStack(GItems.SILVER_NUGGET.get(), count));
        return objectArrayList;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
