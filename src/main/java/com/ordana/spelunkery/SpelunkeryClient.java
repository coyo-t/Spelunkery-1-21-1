package com.ordana.spelunkery;

import com.ordana.spelunkery.blocks.rock_salt.RockSaltBlock;
import com.ordana.spelunkery.entities.DustBunnyModel;
import com.ordana.spelunkery.entities.DustBunnyRenderer;
import com.ordana.spelunkery.items.magnetic_compass.MagneticCompassItemPropertyFunction;
import com.ordana.spelunkery.particles.PortalFluidFlameParticle;
import com.ordana.spelunkery.particles.SulfurParticle;
import com.ordana.spelunkery.reg.*;
import net.mehvahdjukaar.moonlight.api.client.renderer.FallingBlockRendererGeneric;
import net.mehvahdjukaar.moonlight.api.misc.EventCalled;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.util.math.colors.RGBColor;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.particle.ExplodeParticle;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;

@EventBusSubscriber(modid = Spelunkery.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
@Mod(value=Spelunkery.MOD_ID, dist=Dist.CLIENT)
public class SpelunkeryClient
{
	
	public static final ModelLayerLocation DUST_BUNNY = loc("dust_bunny");
	
	public static void init ()
	{
		ClientHelper.addClientSetup(SpelunkeryClient::setup);
		ClientHelper.addModelLayerRegistration(SpelunkeryClient::registerLayers);
		ClientHelper.addEntityRenderersRegistration(SpelunkeryClient::registerEntityRenderers);
		ClientHelper.addParticleRegistration(SpelunkeryClient::registerParticles);
	}
	
	private static boolean finishedSetup = false;
	
	public static void setup ()
	{
		ClientHelper.registerFluidRenderType(ModFluids.FLOWING_PORTAL_FLUID.get(), RenderType.translucent());
		ClientHelper.registerFluidRenderType(ModFluids.PORTAL_FLUID.get(), RenderType.translucent());
		ClientHelper.registerFluidRenderType(ModFluids.FLOWING_SPRING_WATER.get(), RenderType.translucent());
		ClientHelper.registerFluidRenderType(ModFluids.SPRING_WATER.get(), RenderType.translucent());
		
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
		
		ItemProperties.register(ModItems.DEPTH_GAUGE.get(), Spelunkery.res("depth"),
				  (stack, world, entity, seed) -> entity != null ? (((float)entity.getBlockY() + 64) / 384) : 0);
		
		ItemProperties.register(ModItems.MAGNETIC_COMPASS.get(), Spelunkery.res("angle"),
				  new MagneticCompassItemPropertyFunction(((clientLevel, itemStack, entity) -> clientLevel.dimensionType().natural() ? GlobalPos.of(clientLevel.dimension(), new BlockPos(0, 0, -10000000)) : null)));
		
		finishedSetup = true;
	}
	
	private static ModelLayerLocation loc (String name)
	{
		return new ModelLayerLocation(Spelunkery.res(name), name);
	}
	
	private static void registerLayers (ClientHelper.ModelLayerEvent event)
	{
		event.register(DUST_BUNNY, DustBunnyModel::createBodyLayer);
	}
	
	public static void checkIfFailed ()
	{
		if (!finishedSetup)
		{
			throw new RuntimeException("Failed to run client setup. This is likely due to the mod integration code being outdated, crashing with other mods new versions. Terminating");
		}
	}
	
	@EventCalled
	private static void registerBlockColors (ClientHelper.BlockColorEvent event)
	{
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.ROCK_SALT_BLOCK.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.ROCK_SALT_STAIRS.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.ROCK_SALT_SLAB.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.ROCK_SALT_WALL.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.POLISHED_ROCK_SALT.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.POLISHED_ROCK_SALT_STAIRS.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.POLISHED_ROCK_SALT_SLAB.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.POLISHED_ROCK_SALT_WALL.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.ROCK_SALT_BRICKS.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.ROCK_SALT_BRICK_STAIRS.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.ROCK_SALT_BRICK_SLAB.get());
		event.register((blockState, blockAndTintGetter, blockPos, i) -> getSaltTint(event, blockState, blockAndTintGetter, blockPos, i), ModBlocks.ROCK_SALT_BRICK_WALL.get());
	}
	
	private static int getSaltTint (ClientHelper.BlockColorEvent event, BlockState state, BlockAndTintGetter level, BlockPos pos, int i)
	{
		//int original = event.getColor(state, level, pos, i);
		
		//interpolate between color and brown
		float percentage = 10f / state.getValue(RockSaltBlock.LIGHT);
		int brown = 0xf5df9d;
		return new RGBColor(0).asLAB().mixWith(new RGBColor(brown).asLAB(), percentage).asRGB().toInt();
	}
	
	private static void registerEntityRenderers (ClientHelper.EntityRendererEvent event)
	{
		event.register(ModEntities.FALLING_LAYER.get(), FallingBlockRendererGeneric::new);
		event.register(ModEntities.DUST_BUNNY.get(), DustBunnyRenderer::new);
		event.register(ModEntities.GLOWSTICK.get(), context -> new ThrownItemRenderer<>(context, 1, true));
	}
	
	private static void registerParticles (ClientHelper.ParticleEvent event)
	{
		event.register(ModParticles.SULFUR.get(), SulfurParticle.Provider::new);
		event.register(ModParticles.SULFUR_DUSTING.get(), SulfurParticle.Provider::new);
		event.register(ModParticles.PORTAL_FLAME.get(), PortalFluidFlameParticle.Provider::new);
		event.register(ModParticles.DUST_POOF.get(), ExplodeParticle.Provider::new);
	}
}