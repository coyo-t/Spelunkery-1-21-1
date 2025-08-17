package net.orcinus.galosphere.events;

import dissonance.mixin.LootTableAccessor;
import net.minecraft.core.BlockPos;
import net.minecraft.core.dispenser.ProjectileDispenseBehavior;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.storage.ServerLevelData;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.orcinus.galosphere.blocks.LumiereComposterBlock;
import net.orcinus.galosphere.config.GalosphereConfig;
import net.orcinus.galosphere.crafting.LumiereComposterDispenseItemBehavior;
import net.orcinus.galosphere.crafting.LumiereReformingManager;
import net.orcinus.galosphere.crafting.MonstrometerDispenseItemBehavior;
import net.orcinus.galosphere.crafting.WarpedAnchorDispenseItemBehavior;
import net.orcinus.galosphere.init.*;
import net.orcinus.galosphere.network.BarometerPacket;

import java.util.List;

public class MiscEvents
{
	
	//    @SubscribeEvent
	//    public void onParallelDispatched(ParallelDispatchEvent event) {
	//        event.enqueueWork(() -> {
	//            try {
	//                Class<?> aClass = Class.forName("terrablender.api.Region");
	//                if (aClass != null) {
	//                    try {
	//                        Class<?> clazz = Class.forName("orcinus.galosphere.compat.integration.terrablender.GalosphereRegion");
	//                        ((GalosphereRegion) clazz.getConstructor().newInstance()).init(event);
	//                    } catch (ReflectiveOperationException e) {
	//                        throw new RuntimeException(e);
	//                    }
	//                }
	//            } catch (ClassNotFoundException e) {
	//                throw new RuntimeException(e);
	//            }
	//        });
	//    }
	
	@SubscribeEvent
	public void registerBrewingRecipes (BrewingRecipeRegisterEvent event)
	{
		PotionBrewing.Builder builder = event.getBuilder();
		builder.addMix(Potions.AWKWARD, GItems.CURED_MEMBRANE.get(), GPotions.ASTRAL.getHolder().get());
		builder.addMix(GPotions.ASTRAL.getHolder().get(), Items.REDSTONE, GPotions.LONG_ASTRAL.getHolder().get());
	}
	
	@SubscribeEvent
	public void onLootTableLoad (LootTableLoadEvent event)
	{
		ResourceLocation name = event.getName();
		LootTable table = event.getTable();
		List<LootPool> pools = ((LootTableAccessor)table).getPools();
		if (name.equals(BuiltInLootTables.ANCIENT_CITY.location()) && GalosphereConfig.SPECTRE_FLARE_ANCIENT_CITY_LOOT.get())
		{
			pools.add(LootPool.lootPool().add(LootItem.lootTableItem(GItems.SPECTRE_FLARE.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))).build());
		}
		if ((name.equals(BuiltInLootTables.PILLAGER_OUTPOST.location()) || name.equals(BuiltInLootTables.ABANDONED_MINESHAFT.location())) && GalosphereConfig.SILVER_UPGRADE_TEMPLATES_LOOT.get())
		{
			pools.add(LootPool.lootPool().add(LootItem.lootTableItem(GItems.SILVER_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(1).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))).build());
		}
	}
	
	@SubscribeEvent
	public void onWorldTick (TickEvent.LevelTickEvent event)
	{
		if (event.level instanceof ServerLevel serverLevel)
		{
			serverLevel.getPlayers(serverPlayer -> true).forEach(serverPlayer -> {
				ServerLevelData levelData = (ServerLevelData)serverLevel.getLevelData();
				int rainTime = levelData.getClearWeatherTime() > 0 ? levelData.getClearWeatherTime() : levelData.getRainTime();
				int i = rainTime;
				GNetworkHandler.INSTANCE.send(new BarometerPacket(i), PacketDistributor.PLAYER.with(serverPlayer));
			});
		}
	}
	
	@SubscribeEvent
	public void onResourceLoad (AddReloadListenerEvent event)
	{
		event.addListener(new LumiereReformingManager());
	}
	
	@SubscribeEvent
	public void onRightClickBlock (PlayerInteractEvent.RightClickBlock event)
	{
		ItemStack stack = event.getItemStack();
		Player player = event.getEntity();
		InteractionHand hand = event.getHand();
		BlockPos pos = event.getPos();
		Level world = event.getLevel();
		BlockState state = world.getBlockState(pos);
		if (state.getBlock() == Blocks.COMPOSTER)
		{
			InteractionHand offHand = InteractionHand.OFF_HAND;
			if (stack.getItem() == GItems.LUMIERE_SHARD.get())
			{
				if (state.getValue(ComposterBlock.LEVEL) > 0 && state.getValue(ComposterBlock.LEVEL) < 8)
				{
					event.setCanceled(true);
					if (!player.getAbilities().instabuild)
					{
						stack.shrink(1);
					}
					world.setBlock(pos, GBlocks.LUMIERE_COMPOSTER.get().defaultBlockState().setValue(LumiereComposterBlock.LEVEL, state.getValue(ComposterBlock.LEVEL)), 2);
					world.playSound(null, pos, GSoundEvents.LUMIERE_COMPOST.get(), SoundSource.BLOCKS, 1.0F, 1.0F);
					world.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
					player.swing(hand);
				}
			}
		}
	}

	
	@SubscribeEvent
	public void onTagsUpdated (TagsUpdatedEvent event)
	{
		DispenserBlock.registerBehavior(GBlocks.ALLURITE_BLOCK.get().asItem(), new MonstrometerDispenseItemBehavior());
		DispenserBlock.registerBehavior(GBlocks.ALLURITE_BLOCK.get().asItem(), new WarpedAnchorDispenseItemBehavior());
		DispenserBlock.registerBehavior(GItems.LUMIERE_SHARD.get(), new LumiereComposterDispenseItemBehavior());
		DispenserBlock.registerBehavior(GItems.GLOW_FLARE.get(), new ProjectileDispenseBehavior(GItems.GLOW_FLARE.get()));
	}
	
}
