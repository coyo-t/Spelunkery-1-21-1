package net.orcinus.galosphere.world.gen.features;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class SimpleWaterloggedBlockFeature extends Feature<SimpleBlockConfiguration> {

    public SimpleWaterloggedBlockFeature(Codec<SimpleBlockConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> featurePlaceContext) {
        SimpleBlockConfiguration simpleblockconfiguration = featurePlaceContext.config();
        WorldGenLevel worldgenlevel = featurePlaceContext.level();
        BlockPos blockpos = featurePlaceContext.origin();
        BlockState blockstate = simpleblockconfiguration.toPlace().getState(featurePlaceContext.random(), blockpos);
        if (blockstate.isAir()) {
            return true;
        }
        if (blockstate.canSurvive(worldgenlevel, blockpos)) {
            if (blockstate.getBlock() instanceof DoublePlantBlock) {
                if (!worldgenlevel.isEmptyBlock(blockpos.above())) {
                    return false;
                }

                DoublePlantBlock.placeAt(worldgenlevel, blockstate, blockpos, 2);
            } else {
                if (blockstate.hasProperty(BlockStateProperties.WATERLOGGED) && worldgenlevel.isWaterAt(blockpos)) {
                    blockstate = blockstate.setValue(BlockStateProperties.WATERLOGGED, true);
                }
                worldgenlevel.setBlock(blockpos, blockstate, 2);
            }

            return true;
        } else {
            return false;
        }
    }
}
