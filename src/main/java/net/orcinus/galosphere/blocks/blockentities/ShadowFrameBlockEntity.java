package net.orcinus.galosphere.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.orcinus.galosphere.blocks.ShadowFrameBlock;
import net.orcinus.galosphere.init.GBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class ShadowFrameBlockEntity extends BlockEntity {
    private boolean isWaxed;
    private BlockState copiedState = Blocks.AIR.defaultBlockState();

    public ShadowFrameBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(GBlockEntityTypes.SHADOW_FRAME.get(), blockPos, blockState);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.loadAdditional(tag, provider);
        HolderLookup<Block> holderGetter = this.level != null ? this.level.holderLookup(Registries.BLOCK) : BuiltInRegistries.BLOCK.asLookup();
        this.copiedState = NbtUtils.readBlockState(holderGetter, tag.getCompound("CopiedState"));
        this.isWaxed = tag.getBoolean("is_waxed");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider provider) {
        super.saveAdditional(tag, provider);
        tag.put("CopiedState", NbtUtils.writeBlockState(this.copiedState));
        tag.putBoolean("is_waxed", this.isWaxed);
    }

    public BlockState getCopiedState() {
        return copiedState;
    }

    public void setCopiedState(BlockState copiedState) {
        this.copiedState = copiedState;
    }

    public void interact(BlockState copiedState, Level level, BlockPos blockPos, BlockState blockState) {
        this.setCopiedState(copiedState);
        int light = 0;
        if (copiedState.getLightEmission() > 0) {
            light = Math.min(15, copiedState.getLightEmission());
        }
        level.setBlock(blockPos, blockState.setValue(ShadowFrameBlock.FILLED, true).setValue(ShadowFrameBlock.LEVEL, light), 2);
    }

    public boolean isWaxed() {
        return isWaxed;
    }

    public void setWaxed(boolean waxed) {
        isWaxed = waxed;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider provider) {
        return this.saveWithoutMetadata(provider);
    }
}