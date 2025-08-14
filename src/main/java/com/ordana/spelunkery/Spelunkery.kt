package com.ordana.spelunkery

import com.ordana.spelunkery.datamalarky.SlotDecoManager.initialize
import com.ordana.spelunkery.items.magnetic_compass.MagneticCompassItemPropertyFunction
import com.ordana.spelunkery.reg.*
import com.ordana.spelunkery.reg.GameRulez.init
import com.ordana.spelunkery.reg.ModItems.inititiititititialliziaation
import com.ordana.spelunkery.utils.isItem
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper
import net.minecraft.advancements.CriteriaTriggers
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.core.BlockPos
import net.minecraft.core.GlobalPos
import net.minecraft.core.component.DataComponents
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundSource
import net.minecraft.util.ParticleUtils
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.ItemInteractionResult
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.ItemUtils
import net.minecraft.world.item.Items
import net.minecraft.world.item.component.LodestoneTracker
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.CryingObsidianBlock
import net.minecraft.world.level.block.RespawnAnchorBlock
import net.minecraft.world.level.material.Fluid
import net.neoforged.api.distmarker.Dist
import net.neoforged.bus.api.IEventBus
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.ModContainer
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.entity.player.UseItemOnBlockEvent
import net.neoforged.neoforge.registries.DeferredRegister
import org.apache.logging.log4j.LogManager
import java.util.*
import java.util.function.Supplier

@Mod(Spelunkery.MOD_ID)
class Spelunkery(ev: IEventBus, container: ModContainer)
{
	init
	{
		ev.addListener<FMLCommonSetupEvent> { commonInit(it) }
		ev.addListener<FMLClientSetupEvent> { clientSetup(it) }

		NeoForge.EVENT_BUS.addListener<UseItemOnBlockEvent> {
			obsidianDraining(it)
		}
		with (DeferredRegister.create(Registries.SOUND_EVENT, MOD_ID))
		{
			ModSoundEvents.init(this)
			register(ev)
		}


		ModFluids.THINGS.register(ev)
		ModBlocks.BLOCKS.register(ev)
		inititiititititialliziaation()
		ModItems.ITEMS.register(ev)
		ModWorldgenFeatures.FEATS.register(ev)
		//		container.registerConfig(ModConfig.Type.COMMON, ModConfig.Type.);
	}


	fun clientSetup(ev: FMLClientSetupEvent)
	{
		ev.enqueueWork {
			try
			{
				val game = Minecraft.getInstance()
				initialize(game.textureManager, game.resourceManager)
			}
			catch (e: Throwable)
			{
				println("MACHINE WITNESS: no game?")
				throw e
			}
		}
	}

	fun commonInit(ev: FMLCommonSetupEvent?)
	{
		if (initiated)
		{
			return
		}

		init()

		//		RegHelper.addLootTableInjects(ModLootInjects::onLootInject);
		initiated = true
	}

	@EventBusSubscriber(modid = MOD_ID, value = [Dist.CLIENT], bus = EventBusSubscriber.Bus.MOD)
	object Client
	{
		@SubscribeEvent
		@JvmStatic
		fun onClientSetup(ev: FMLClientSetupEvent)
		{
			ClientHelper.addClientSetup {
				fun regs (vararg thigns: Pair<Supplier<*>, RenderType>) {
					thigns.forEach {
						when (val maybe = it.first.get())
						{
							is Fluid -> ClientHelper.registerFluidRenderType(maybe, it.second)
							is Block -> ClientHelper.registerRenderType(maybe, it.second)
						}
					}
				}

				regs(
					ModFluids.FLOWING_PORTAL_FLUID  to RenderType.translucent(),
					ModFluids.STILL_PORTAL_FLUID    to RenderType.translucent(),
					ModFluids.FLOWING_SPRING_WATER  to RenderType.translucent(),
					ModFluids.STILL_SPRING_WATER    to RenderType.translucent(),
					ModBlocks.POLISHED_QUARTZ_BLOCK to RenderType.translucent(),

					ModBlocks.PORTAL_FLUID       to RenderType.translucent(),
					ModBlocks.SPRING_WATER       to RenderType.translucent(),
					ModBlocks.TANGLE_ROOTS       to RenderType.cutout(),
					ModBlocks.TANGLE_ROOTS_PLANT to RenderType.cutout(),
					ModBlocks.SPOROPHYTE         to RenderType.cutout(),
					ModBlocks.TALL_SPOROPHYTE    to RenderType.cutout(),

					ModBlocks.CONK_FUNGUS           to RenderType.cutout(),
					ModBlocks.INKCAP_MUSHROOM       to RenderType.cutout(),
					ModBlocks.WHITE_INKCAP_MUSHROOM to RenderType.cutout(),
					ModBlocks.PHOSPHOR_FUNGUS       to RenderType.cutout(),
					ModBlocks.MUSHGLOOM             to RenderType.cutout(),
					ModBlocks.MILLY_BUBCAP          to RenderType.cutout(),
					ModBlocks.PORTABELLA            to RenderType.cutout(),
					ModBlocks.CRIMINI               to RenderType.cutout(),
					ModBlocks.BUTTON_MUSHROOM       to RenderType.cutout(),

					ModBlocks.POTTED_INKCAP_MUSHROOM       to RenderType.cutout(),
					ModBlocks.POTTED_WHITE_INKCAP_MUSHROOM to RenderType.cutout(),
					ModBlocks.POTTED_PHOSPHOR_FUNGUS       to RenderType.cutout(),
					ModBlocks.POTTED_MUSHGLOOM             to RenderType.cutout(),
					ModBlocks.POTTED_MILLY_BUBCAP          to RenderType.cutout(),
					ModBlocks.POTTED_PORTABELLA            to RenderType.cutout(),
					ModBlocks.POTTED_CRIMINI               to RenderType.cutout(),
					ModBlocks.POTTED_BUTTON_MUSHROOM       to RenderType.cutout(),
					ModBlocks.POTTED_SPOROPHYTE            to RenderType.cutout(),
					ModBlocks.PHOSPHOR_FUNGUS_BLOCK        to RenderType.translucent(),

				)

				ItemProperties.register(
					ModItems.MAGNETIC_COMPASS.get(),
					res("angle"),
					MagneticCompassItemPropertyFunction { clientLevel, itemStack, entity ->
						if (clientLevel.dimensionType().natural())
							GlobalPos.of(clientLevel.dimension(), BlockPos(0, 0, -10000000))
						else
							null
					}
				)
			}
		}
	}

	companion object
	{
		const val MOD_ID = "spelunkery"

		val LOGGER = LogManager.getLogger()
		private var initiated = false

		private fun obsidianDraining(ev: UseItemOnBlockEvent)
		{
			val item = ev.itemStack
			if (item isItem Items.GLASS_BOTTLE)
			{
				val level = ev.level
				val co = ev.pos
				val blocState = level.getBlockState(co)
				val bloc = blocState.block
				val player = ev.player
				val gr = level.gameRules
				// TODO: should be data recipe thing
				if (gr.getBoolean(GameRulez.CRYING_OBSIDIAN_JUICABLE))
				{
					if (bloc is CryingObsidianBlock)
					{
						level.playSound(player, co, ModSoundEvents.PORTAL_FLUID_BOTTLE_FILL, SoundSource.BLOCKS)
						ParticleUtils.spawnParticlesOnBlockFaces(
							level,
							co,
							ParticleTypes.FALLING_OBSIDIAN_TEAR,
							UniformInt.of(3, 5)
						)
						if (player is ServerPlayer)
						{
							val itemStack2 = ItemUtils.createFilledResult(
								item,
								player,
								ModItems.PORTAL_FLUID_BOTTLE.get().getDefaultInstance()
							)
							player.setItemInHand(ev.getHand(), itemStack2)
							level.setBlockAndUpdate(co, Blocks.OBSIDIAN.defaultBlockState())
							CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(player, co, item)
						}
						ev.cancelWithResult(ItemInteractionResult.sidedSuccess(level.isClientSide))
						return
					}
				}
				if (level.gameRules.getBoolean(GameRulez.RESPAWN_ANCHOR_JUICABLE))
				{
					if (bloc is RespawnAnchorBlock && blocState.getValue(RespawnAnchorBlock.CHARGE) > 0)
					{
						level.playSound(player, co, ModSoundEvents.PORTAL_FLUID_BOTTLE_FILL, SoundSource.BLOCKS)
						ParticleUtils.spawnParticlesOnBlockFaces(
							level,
							co,
							ParticleTypes.FALLING_OBSIDIAN_TEAR,
							UniformInt.of(3, 5)
						)
						if (player is ServerPlayer)
						{
							val itemStack2 = ItemStack(ModItems.PORTAL_FLUID_BOTTLE.get())
							itemStack2.set<LodestoneTracker?>(
								DataComponents.LODESTONE_TRACKER, LodestoneTracker(
									Optional.of(GlobalPos.of(level.dimension(), co)), false
								)
							)

							if (!player.getInventory().add(itemStack2))
							{
								player.drop(itemStack2, false)
							}
							item.shrink(1)
							level.setBlockAndUpdate(
								co,
								blocState.setValue(
									RespawnAnchorBlock.CHARGE,
									blocState.getValue(RespawnAnchorBlock.CHARGE) - 1
								)
							)
							CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(player, co, item)
						}
						ev.cancelWithResult(ItemInteractionResult.sidedSuccess(level.isClientSide))
					}
				}
			}
		}

		@JvmStatic
		fun res(name: String): ResourceLocation
		{
			return ResourceLocation.fromNamespaceAndPath(MOD_ID, name)
		}
	}
}