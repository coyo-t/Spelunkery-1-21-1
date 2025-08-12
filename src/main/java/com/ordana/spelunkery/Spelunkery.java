package com.ordana.spelunkery;

import com.ordana.spelunkery.configs.ClientConfigs;
import com.ordana.spelunkery.configs.CommonConfigs;
import com.ordana.spelunkery.events.NetworkHandler;
import com.ordana.spelunkery.items.magnetic_compass.MagneticCompassItemPropertyFunction;
import com.ordana.spelunkery.loot_modifiers.ModLootInjects;
import com.ordana.spelunkery.particles.PortalFluidFlameParticle;
import com.ordana.spelunkery.particles.SulfurParticle;
import com.ordana.spelunkery.reg.*;
import net.mehvahdjukaar.moonlight.api.client.renderer.FallingBlockRendererGeneric;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.platform.RegHelper;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Spelunkery.MOD_ID)
public class Spelunkery
{
	public static final String MOD_ID = "spelunkery";
	
	public static final Logger LOGGER = LogManager.getLogger();
	private static boolean initiated = false;
	
	public Spelunkery (IEventBus ev, ModContainer container)
	{
		ev.addListener(this::commonInit);
		ModBlocks.BLOCKS.register(ev);
		ModItems.ITEMS.register(ev);
		ModEntities.THINGS.register(ev);
//		container.registerConfig(ModConfig.Type.COMMON, ModConfig.Type.);
	}
	
	
	public static ResourceLocation res (String name)
	{
		return ResourceLocation.tryBuild(MOD_ID, name);
	}
	
	
	public void commonInit (FMLCommonSetupEvent ev)
	{
		if (initiated)
		{
			return;
		}
		
		NetworkHandler.registerMessages();
		CommonConfigs.init();

		if (PlatHelper.getPhysicalSide().isClient())
		{
			ClientConfigs.init();
			
			ClientHelper.registerOptionalTexturePack(Spelunkery.res("better_vanilla_gems"), Component.literal("Better Vanilla Gems"), false);
			
		}
		
		ModParticles.init();
		ModSoundEvents.init();
		
		RegHelper.addLootTableInjects(ModLootInjects::onLootInject);
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
				ClientHelper.registerRenderType(ModBlocks.SALT_LAMP.get(), RenderType.cutout());
				ClientHelper.registerRenderType(ModBlocks.SALT.get(), RenderType.cutout());
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
			ClientHelper.addEntityRenderersRegistration(event -> {
				event.register(ModEntities.FALLING_LAYER.get(), FallingBlockRendererGeneric::new);
				event.register(ModEntities.GLOWSTICK.get(), context -> new ThrownItemRenderer<>(context, 1, true));
			});
			ClientHelper.addParticleRegistration(event -> {
				event.register(ModParticles.SULFUR.get(), SulfurParticle.Provider::new);
				event.register(ModParticles.SULFUR_DUSTING.get(), SulfurParticle.Provider::new);
				event.register(ModParticles.PORTAL_FLAME.get(), PortalFluidFlameParticle.Provider::new);
				event.register(ModParticles.DUST_POOF.get(), ExplodeParticle.Provider::new);
			});
		}
	}
}