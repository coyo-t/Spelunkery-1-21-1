package net.orcinus.galosphere.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.orcinus.galosphere.client.gui.CombustionTableMenu;
import org.jetbrains.annotations.Nullable;

public class CombustionTableBlock extends Block {
    private static final Component CONTAINER_TITLE = Component.translatable("container.galosphere.combustion_table");

    public CombustionTableBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockState, Level level, BlockPos blockPos, Player player, BlockHitResult blockHitResult) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        player.openMenu(blockState.getMenuProvider(level, blockPos));
        player.awardStat(Stats.INTERACT_WITH_CARTOGRAPHY_TABLE);
        return InteractionResult.CONSUME;
    }

    @Override
    @Nullable
    public MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new CombustionTableMenu(id, inventory, ContainerLevelAccess.create(world, pos)), CONTAINER_TITLE);
    }

}
