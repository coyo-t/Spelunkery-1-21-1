package net.orcinus.galosphere;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.orcinus.galosphere.config.GalosphereConfig;
import net.orcinus.galosphere.events.MiscEvents;
import net.orcinus.galosphere.events.MobEvents;
import net.orcinus.galosphere.init.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Galosphere.MODID)
public class Galosphere
{
	
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "galosphere";
	
	public Galosphere ()
	{
		var modEventBus = ModLoadingContext.get().getActiveContainer().getEventBus();
		var eventBus = NeoForge.EVENT_BUS;
		modEventBus.addListener(this::commonSetup);
		
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, GalosphereConfig.COMMON);
		
		GAttributes.ATTRIBTUES.register(modEventBus);
		GBlocks.BLOCKS.register(modEventBus);
		GBlockEntityTypes.BLOCK_ENTITIES.register(modEventBus);
		GCreativeModeTabs.CREATIVE_MODE_TABS.register(modEventBus);
		GCriteriaTriggers.CRITERION_TRIGGERS.register(modEventBus);
		GDataComponents.DATA_COMPONENT_TYPES.register(modEventBus);
		GEnchantmentEffectComponents.DATA_COMPONENTS.register(modEventBus);
		GEntityTypes.ENTITY_TYPES.register(modEventBus);
		GFeatures.FEATURES.register(modEventBus);
		GItems.ITEMS.register(modEventBus);
		GLootModifiers.LOOT_MODIFIERS.register(modEventBus);
		GMemoryModuleTypes.MEMORY_MODULE_TYPES.register(modEventBus);
		GMobEffects.MOB_EFFECTS.register(modEventBus);
		GPotions.POTIONS.register(modEventBus);
		GParticleTypes.PARTICLES.register(modEventBus);
		GRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);
		GStructureProcessorTypes.STRUCTURE_PROCESSOR_TYPES.register(modEventBus);
		GSensorTypes.SENSOR_TYPES.register(modEventBus);
		GSoundEvents.SOUND_EVENTS.register(modEventBus);
		
		eventBus.register(this);
		eventBus.register(new MobEvents());
		eventBus.register(new MiscEvents());
		
	}
	
	private void commonSetup (final FMLCommonSetupEvent event)
	{
		event.enqueueWork(() -> {
			GPlacedFeatures.init();
			GVanillaIntegration.init();
			GNetworkHandler.init();
		});
	}
	
	public static ResourceLocation id (String path)
	{
		return ResourceLocation.fromNamespaceAndPath(Galosphere.MODID, path);
	}
	
}
