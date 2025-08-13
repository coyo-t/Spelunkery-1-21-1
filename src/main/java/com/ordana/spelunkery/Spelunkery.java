package com.ordana.spelunkery;

import com.ordana.spelunkery.items.magnetic_compass.MagneticCompassItemPropertyFunction;
import com.ordana.spelunkery.reg.*;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CryingObsidianBlock;
import net.minecraft.world.level.block.RespawnAnchorBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

import static net.minecraft.core.component.DataComponents.LODESTONE_TRACKER;

@Mod(Spelunkery.MOD_ID)
public class Spelunkery
{
	public static final String MOD_ID = "spelunkery";
	
	public static final Logger LOGGER = LogManager.getLogger();
	private static boolean initiated = false;
	
	private static void obsidianDraining (UseItemOnBlockEvent ev)
	{
		final var item = ev.getItemStack();
		if (item.is(Items.GLASS_BOTTLE))
		{
			final var level = ev.getLevel();
			final var co = ev.getPos();
			final var blocState = level.getBlockState(co);
			final var bloc = blocState.getBlock();
			final var player = ev.getPlayer();
			final var gr = level.getGameRules();
			// TODO: should be data recipe thing
			if (gr.getBoolean(GameRulez.CRYING_OBSIDIAN_JUICABLE))
			{
				if (bloc instanceof CryingObsidianBlock)
				{
					level.playSound(player, co, ModSoundEvents.PORTAL_FLUID_BOTTLE_FILL, SoundSource.BLOCKS);
					ParticleUtils.spawnParticlesOnBlockFaces(level, co, ParticleTypes.FALLING_OBSIDIAN_TEAR, UniformInt.of(3, 5));
					if (player instanceof ServerPlayer serverPlayer)
					{
						var itemStack2 = ItemUtils.createFilledResult(item, player, ModItems.PORTAL_FLUID_BOTTLE.get().getDefaultInstance());
						player.setItemInHand(ev.getHand(), itemStack2);
						level.setBlockAndUpdate(co, Blocks.OBSIDIAN.defaultBlockState());
						CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, co, item);
					}
					ev.cancelWithResult(ItemInteractionResult.sidedSuccess(level.isClientSide));
					return;
				}
			}
			if (level.getGameRules().getBoolean(GameRulez.RESPAWN_ANCHOR_JUICABLE))
			{
				if (bloc instanceof RespawnAnchorBlock ra && blocState.getValue(RespawnAnchorBlock.CHARGE) > 0)
				{
					level.playSound(player, co, ModSoundEvents.PORTAL_FLUID_BOTTLE_FILL, SoundSource.BLOCKS);
					ParticleUtils.spawnParticlesOnBlockFaces(level, co, ParticleTypes.FALLING_OBSIDIAN_TEAR, UniformInt.of(3, 5));
					if (player instanceof ServerPlayer serverPlayer)
					{
						var itemStack2 = new ItemStack(ModItems.PORTAL_FLUID_BOTTLE.get());
						itemStack2.set(LODESTONE_TRACKER, new LodestoneTracker(Optional.of(GlobalPos.of(level.dimension(), co)), false));
	
						if (!player.getInventory().add(itemStack2))
						{
							player.drop(itemStack2, false);
						}
						item.shrink(1);
						level.setBlockAndUpdate(co, blocState.setValue(RespawnAnchorBlock.CHARGE, blocState.getValue(RespawnAnchorBlock.CHARGE) - 1));
						CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, co, item);
					}
					ev.cancelWithResult(ItemInteractionResult.sidedSuccess(level.isClientSide));
				}
			}
		}
	}
	
	public Spelunkery (IEventBus ev, ModContainer container)
	{
		ev.addListener(this::commonInit);
		NeoForge.EVENT_BUS.addListener(Spelunkery::obsidianDraining);
		final var se = DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID);
		ModSoundEvents.init(se);
		se.register(ev);
		
		ModFluids.THINGS.register(ev);
		ModBlocks.BLOCKS.register(ev);
		ModItems.inititiititititialliziaation();
		ModItems.ITEMS.register(ev);
		ModWorldgenFeatures.FEATS.register(ev);
//		container.registerConfig(ModConfig.Type.COMMON, ModConfig.Type.);
	}
	
	
	public static ResourceLocation res (String name)
	{
		return ResourceLocation.fromNamespaceAndPath(MOD_ID, name);
	}
	
	
	public void commonInit (FMLCommonSetupEvent ev)
	{
		if (initiated)
		{
			return;
		}
		
		GameRulez.init();

//		RegHelper.addLootTableInjects(ModLootInjects::onLootInject);
		initiated = true;
	}
	
	@EventBusSubscriber(modid = Spelunkery.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
	public static class Client
	{
		@SubscribeEvent
		static void onClientSetup (FMLClientSetupEvent ev)
		{
			ClientHelper.addClientSetup(() -> {
//				ClientHelper.registerFluidRenderType(ModFluids.FLOWING_PORTAL_FLUID.get(), RenderType.translucent());
//				ClientHelper.registerFluidRenderType(ModFluids.STILL_PORTAL_FLUID.get(), RenderType.translucent());
//				ClientHelper.registerFluidRenderType(ModFluids.FLOWING_SPRING_WATER.get(), RenderType.translucent());
//				ClientHelper.registerFluidRenderType(ModFluids.STILL_SPRING_WATER.get(), RenderType.translucent());
				
				ClientHelper.registerRenderType(ModBlocks.POLISHED_QUARTZ_BLOCK.get(), RenderType.translucent());
				
				ClientHelper.registerRenderType(ModBlocks.PORTAL_FLUID.get(), RenderType.translucent());
				ClientHelper.registerRenderType(ModBlocks.SPRING_WATER.get(), RenderType.translucent());
				ClientHelper.registerRenderType(ModBlocks.TANGLE_ROOTS.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.TANGLE_ROOTS_PLANT.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.SPOROPHYTE.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.TALL_SPOROPHYTE.get(), RenderType.cutout());
				
				ClientHelper.registerRenderType(ModBlocks.CONK_FUNGUS.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.INKCAP_MUSHROOM.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.WHITE_INKCAP_MUSHROOM.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.PHOSPHOR_FUNGUS.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.MUSHGLOOM.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.MILLY_BUBCAP.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.PORTABELLA.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.CRIMINI.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.BUTTON_MUSHROOM.get(), RenderType.cutout());
				
				ClientHelper.registerRenderType(ModBlocks.POTTED_INKCAP_MUSHROOM.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.POTTED_WHITE_INKCAP_MUSHROOM.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.POTTED_PHOSPHOR_FUNGUS.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.POTTED_MUSHGLOOM.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.POTTED_MILLY_BUBCAP.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.POTTED_PORTABELLA.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.POTTED_CRIMINI.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.POTTED_BUTTON_MUSHROOM.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.POTTED_SPOROPHYTE.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.PHOSPHOR_FUNGUS_BLOCK.get(), RenderType.translucent());
				
				ItemProperties.register(
					ModItems.MAGNETIC_COMPASS.get(),
					Spelunkery.res("angle"),
					new MagneticCompassItemPropertyFunction(
						(clientLevel, itemStack, entity)
							-> clientLevel.dimensionType().natural() ? GlobalPos.of(clientLevel.dimension(), new BlockPos(0, 0, -10000000)) : null
					)
				);
			});
		}
	}
}