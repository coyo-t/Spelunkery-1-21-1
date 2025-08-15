package net.orcinus.galosphere.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.orcinus.galosphere.blocks.CordycepsBlock;
import net.orcinus.galosphere.init.GBlocks;

public class LichenCordycepsColumnFeature extends Feature<NoneFeatureConfiguration> {

    public LichenCordycepsColumnFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel world = context.level();
        RandomSource random = context.random();
        BlockPos blockPos = context.origin();
        boolean flag = world.getBlockState(blockPos.below()).isFaceSturdy(world, blockPos.below(), Direction.UP) && world.isStateAtPosition(blockPos, BlockBehaviour.BlockStateBase::isAir);
        if (!flag) {
            return false;
        } else {
            int length = Mth.nextInt(random, 4, 8);
            BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();
            for (int i = 0; i <= length; ++i) {
                if (world.isStateAtPosition(mutableBlockPos, BlockBehaviour.BlockStateBase::isAir)) {
                    if (i == length || !world.isStateAtPosition(mutableBlockPos.above(), BlockBehaviour.BlockStateBase::isAir)) {
                        world.setBlock(mutableBlockPos, GBlocks.LICHEN_CORDYCEPS.get().defaultBlockState().setValue(CordycepsBlock.BULB, random.nextBoolean()).setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, 17, 25)), 2);
                        break;
                    }
                    world.setBlock(mutableBlockPos, GBlocks.LICHEN_CORDYCEPS_PLANT.get().defaultBlockState(), 2);
                }

                mutableBlockPos.move(Direction.UP);
            }
            return true;
        }
    }
}
