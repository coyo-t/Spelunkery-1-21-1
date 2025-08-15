package net.orcinus.galosphere.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GrowingPlantBodyBlock;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.orcinus.galosphere.init.GBlocks;

public class CordycepsPlantBlock extends GrowingPlantBodyBlock {
    public static final MapCodec<CordycepsPlantBlock> CODEC = CordycepsPlantBlock.simpleCodec(CordycepsPlantBlock::new);
    public static final VoxelShape SHAPE = Block.box(4.0, 0.0, 4.0, 13.0, 16.0, 13.0);

    public CordycepsPlantBlock(Properties properties) {
        super(properties, Direction.UP, SHAPE, false);
    }

    @Override
    protected GrowingPlantHeadBlock getHeadBlock() {
        return (GrowingPlantHeadBlock) GBlocks.LICHEN_CORDYCEPS.get();
    }

    @Override
    protected MapCodec<? extends GrowingPlantBodyBlock> codec() {
        return CODEC;
    }
}
