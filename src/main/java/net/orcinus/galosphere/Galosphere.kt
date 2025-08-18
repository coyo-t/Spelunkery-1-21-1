package net.orcinus.galosphere

import dissonance.mixin.LootTableAccessor
import dissonance.util.extension.contains
import dissonance.util.extension.decremented
import dissonance.util.extension.get
import dissonance.util.extension.isa
import net.minecraft.ChatFormatting
import net.minecraft.core.BlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.core.dispenser.ProjectileDispenseBehavior
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.EntityTypeTags
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.SpawnPlacementTypes
import net.minecraft.world.entity.animal.horse.Horse
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.ProjectileWeaponItem
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.level.block.DispenserBlock
import net.minecraft.world.level.block.ShulkerBoxBlock
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.storage.ServerLevelData
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.fml.config.ModConfig
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.event.AddReloadListenerEvent
import net.neoforged.neoforge.event.LootTableLoadEvent
import net.neoforged.neoforge.event.TagsUpdatedEvent
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
import net.neoforged.neoforge.event.entity.EntityTeleportEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent
import net.neoforged.neoforge.event.entity.living.LivingGetProjectileEvent
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import net.neoforged.neoforge.event.level.BlockEvent
import net.neoforged.neoforge.event.tick.LevelTickEvent
import net.neoforged.neoforge.event.tick.PlayerTickEvent
import net.neoforged.neoforge.network.PacketDistributor
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import net.orcinus.galosphere.api.SpectreBoundSpyglass
import net.orcinus.galosphere.blocks.WarpedAnchorBlock
import net.orcinus.galosphere.config.GalosphereConfig
import net.orcinus.galosphere.crafting.LumiereComposterDispenseItemBehavior
import net.orcinus.galosphere.crafting.LumiereReformingManager
import net.orcinus.galosphere.crafting.MonstrometerDispenseItemBehavior
import net.orcinus.galosphere.crafting.WarpedAnchorDispenseItemBehavior
import net.orcinus.galosphere.entities.*
import net.orcinus.galosphere.init.*
import net.orcinus.galosphere.items.SterlingArmorItem
import net.orcinus.galosphere.network.*
import net.orcinus.galosphere.util.PreservedShulkerBox
import org.apache.logging.log4j.LogManager
import java.util.*
import kotlin.math.pow

class Galosphere(ev: IEventBus, modContainer: ModContainer)
{
	init
	{
		ev.addListener(::commonSetup)

		modContainer.registerConfig(ModConfig.Type.COMMON, GalosphereConfig.COMMON)

		GBlocks.BLOCKS.register(ev)
		GBlockEntityTypes.BLOCK_ENTITIES.register(ev)
		GCreativeModeTabs.CREATIVE_MODE_TABS.register(ev)
		GCriteriaTriggers.CRITERION_TRIGGERS.register(ev)
		GDataComponents.DATA_COMPONENT_TYPES.register(ev)
		GEnchantmentEffectComponents.DATA_COMPONENTS.register(ev)
		GEntityTypes.ENTITY_TYPES.register(ev)
		GFeatures.FEATURES.register(ev)
		GItems.ITEMS.register(ev)
		GMemoryModuleTypes.MEMORY_MODULE_TYPES.register(ev)
		GMobEffects.MOB_EFFECTS.register(ev)
		GPotions.POTIONS.register(ev)
		GParticleTypes.PARTICLES.register(ev)
		GRecipeSerializers.RECIPE_SERIALIZERS.register(ev)
		GStructureProcessorTypes.STRUCTURE_PROCESSOR_TYPES.register(ev)
		GSensorTypes.SENSOR_TYPES.register(ev)
		GSoundEvents.SOUND_EVENTS.register(ev)

		//#region Entity Eventz
		ev.addListener<RegisterSpawnPlacementsEvent> { event ->
			event.register(
				GEntityTypes.SPARKLE.get(),
				SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Sparkle::checkSparkleSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.OR,
			)
			event.register(
				GEntityTypes.SPECTRE.get(),
				SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				Mob::checkMobSpawnRules,
				RegisterSpawnPlacementsEvent.Operation.OR,
			)
		}

		ev.addListener<EntityAttributeCreationEvent> { event ->
			with (event)
			{
				put(GEntityTypes.SPARKLE.get(), Sparkle.createAttributes().build())
				put(GEntityTypes.SPECTRE.get(), Spectre.createAttributes().build())
				put(GEntityTypes.SPECTERPILLAR.get(), Specterpillar.createAttributes().build())
				put(GEntityTypes.SPECTATOR_VISION.get(), SpectatorVision.createAttributes().build())
				put(GEntityTypes.BERSERKER.get(), Berserker.createAttributes().build())
				put(GEntityTypes.PRESERVED.get(), Preserved.createAttributes().build())
			}
		}

		ev.addListener<ItemExpireEvent> { event ->
			val entity = event.entity
			val level = entity.level()
			if (!level.isClientSide)
			{
				val blockState = level.getBlockState(BlockPos.containing(entity.eyePosition))
				if (blockState isa GBlocks.STRANDED_MEMBRANE_BLOCK)
				{
					event.addExtraLife(6000)
				}
			}
		}

		ev.addListener<EntityTeleportEvent.EnderPearl> { event ->
			val pearl = event.pearlEntity
			val poses = mutableListOf<BlockPos>()
			val player = event.player
			val world = player.level()
			val pearlPos = pearl.blockPosition()
			val radius = 16
			for (x in -radius..radius)
			{
				for (z in -radius..radius)
				{
					for (y in -radius..radius)
					{
						val blockPos = BlockPos.containing(pearl.x + x, pearl.y + y, pearl.z + z)
						val blockState = world.getBlockState(blockPos)
						if (blockState isa GBlocks.WARPED_ANCHOR && blockState[WarpedAnchorBlock.WARPED_CHARGE] > 0)
						{
							poses.add(blockPos)
						}
					}
				}
			}
			if (!poses.isEmpty())
			{
				poses.sortWith(Comparator.comparingDouble(pearlPos::distSqr))
				for (blockPos in poses)
				{
					event.setCanceled(true)
					GCriteriaTriggers.WARPED_TELEPORT.get().trigger(player)
					val pearlLevel = pearl.level()
					pearlLevel.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos)
					pearlLevel.playSound(null, blockPos, SoundEvents.RESPAWN_ANCHOR_SET_SPAWN, SoundSource.BLOCKS, 1.0f, 1.0f)
					player.teleportTo(blockPos.x + 0.5, blockPos.y + 0.5, blockPos.z + 0.5)
					player.resetFallDistance()
					pearlLevel.setBlock(
						blockPos,
						pearlLevel.getBlockState(blockPos).decremented(WarpedAnchorBlock.WARPED_CHARGE),
						2
					)
					pearl.discard()
					break
				}
			}
		}

		ev.addListener<ItemTooltipEvent> { event ->
			if (GDataComponents.PRESERVED in event.itemStack)
			{
				event.toolTip.add(Component.translatable("item.galosphere.preserved").withStyle(ChatFormatting.DARK_PURPLE))
			}
		}

		ev.addListener<PlayerEvent.Clone> { event ->
			(event.entity as? ServerPlayer)?.let { player ->
				event
				.original
				.getInventory()
				.items
				.stream()
				.filter { GDataComponents.PRESERVED in it }
				.forEach { player.getInventory().add(it) }
			}
		}

		ev.addListener<BlockEvent.EntityPlaceEvent> { event ->
			(event.entity as? Player)?.run {
				val level = level()
				val blockEntity = level.getBlockEntity(event.pos)
				if (hasEffect(GMobEffects.BLOCK_BANE) && !abilities.instabuild)
				{
					hurt(level.damageSources().magic(), 3.0f)
					cooldowns.addCooldown(getItemInHand(usedItemHand).item, 100)
				}
				if (blockEntity is ShulkerBoxBlockEntity && GDataComponents.PRESERVED in mainHandItem)
				{
					(blockEntity as PreservedShulkerBox).setPreserved(true)
				}
			}
		}

		ev.addListener<BlockEvent.BreakEvent> { event ->
			val world = event.level
			val pos = event.pos
			val blockEntity = world.getBlockEntity(pos)
			val player = event.player
			val state = event.state
			// TODO: may or may not work.
//			if (blockEntity is ShulkerBoxBlockEntity && (blockEntity as PreservedShulkerBox).isPreserved())
			if (blockEntity is ShulkerBoxBlockEntity && (blockEntity as PreservedShulkerBox).isPreserved())
			{
				val stack = ItemStack(ShulkerBoxBlock.getBlockByColor((state.block as ShulkerBoxBlock).color))
				blockEntity.saveToItem(stack, world.registryAccess())
				if (blockEntity.hasCustomName())
				{
					stack[DataComponents.CUSTOM_NAME] = blockEntity.customName
				}
				stack[GDataComponents.PRESERVED] = true
				val itementity = ItemEntity(
					player.level(),
					pos.x.toDouble() + 0.5,
					pos.y.toDouble() + 0.5,
					pos.z.toDouble() + 0.5,
					stack
				)
				itementity.setDefaultPickUpDelay()
				world.addFreshEntity(itementity)
				world.removeBlock(pos, false)
				state.block.playerWillDestroy(player.level(), pos, state, player)
				event.setCanceled(true)
			}
		}

		ev.addListener<LivingGetProjectileEvent> { event ->
			val entity = event.entity
			val weapon = event.projectileWeaponItemStack
			if (weapon isa Items.CROSSBOW)
			{
				val ammo = ProjectileWeaponItem.getHeldProjectile(entity) { true }
				if (ammo.isEmpty)
				{
					if (entity is Player)
					{
						for (i in 0..<entity.getInventory().containerSize)
						{
							val ist = entity.getInventory().getItem(i)
							if (ist isa GItems.GLOW_FLARE || ist isa GItems.SPECTRE_FLARE)
							{
								event.projectileItemStack = ist
							}
						}
					}
				}
				else
				{
					if (ammo isa GItems.GLOW_FLARE || ammo isa GItems.SPECTRE_FLARE)
					{
						event.projectileItemStack = ammo
					}
				}
			}
		}

		ev.addListener<PlayerTickEvent.Post> { event ->
			val entity = event.entity
			val useItem = entity.getUseItem()
			if (SpectreBoundSpyglass.canUseSpectreBoundedSpyglass(useItem))
			{
				if (!entity.level().isClientSide)
				{
					val spectreBound = (entity.level() as ServerLevel).getEntity(useItem.get(GDataComponents.SPECTRE_BOUND.get())!!.uuid)
					Optional.ofNullable(spectreBound)
					.filter(Spectre::class.java::isInstance)
					.map(Spectre::class.java::cast)
					.filter(Spectre::isAlive)
					.ifPresent { spectre ->
						if (spectre.manipulatorUUID !== entity.getUUID())
						{
							val MAX_DIST = (110 * 110)
							if ((entity.x - spectre.x).pow(2.0) + (entity.z - spectre.z).pow(2.0) < MAX_DIST)
							{
								spectre.setCamera(entity)
							}
						}
					}
				}
			}
		}

		ev.addListener<LivingDamageEvent.Pre> { event ->
			val entity = event.entity
			val mob = event.source.entity
			if (mob is Mob && (mob.type.`is`(EntityTypeTags.ILLAGER) || mob.type.`is`(GEntityTypeTags.STERLING_IMMUNE_ENTITY_TYPES)))
			{
				// TODO: in 1 21 horse armour might be reported in equipment slots?
				val originalAmount = event.originalDamage
				if (entity is Horse && entity.bodyArmorItem isa GItems.STERLING_HORSE_ARMOR)
				{
					event.newDamage = originalAmount - 4
				}
				var illagerReduction = 0f
				for (equipmentSlot in EquipmentSlot.entries)
				{
					val item = entity.getItemBySlot(equipmentSlot).item
					if (item is SterlingArmorItem && equipmentSlot.type == EquipmentSlot.Type.HUMANOID_ARMOR)
					{
						illagerReduction += item.getIllagerResistance(equipmentSlot)
					}
				}
				if (illagerReduction > 0)
				{
					event.newDamage = 4 * (originalAmount / illagerReduction)
				}
			}
		}
		//#endregion

		//#region Misc Eventz

		ev.addListener<RegisterPayloadHandlersEvent> { event ->
			val registrar = event.registrar("1").optional()
			registrar.playToClient(
				SendParticlesPacket.TYPE,
				SendParticlesPacket.CODEC,
				ClientEventsHandler::handleSendParticles,
			)
			registrar.playToClient(
				BarometerPacket.TYPE,
				BarometerPacket.CODEC,
				ClientEventsHandler::sendBarometerInfo,
			)
			registrar.playToClient(
				SendPerspectivePacket.TYPE,
				SendPerspectivePacket.CODEC,
				ClientEventsHandler::sendPerspective,
			)
			registrar.playToClient(
				PlayCooldownSoundPacket.TYPE,
				PlayCooldownSoundPacket.CODEC,
				ClientEventsHandler::playCooldownSound,
			)


		}

		ev.addListener<TagsUpdatedEvent> { event ->
			DispenserBlock.registerBehavior(GBlocks.ALLURITE_BLOCK.get().asItem(), MonstrometerDispenseItemBehavior())
			DispenserBlock.registerBehavior(GBlocks.ALLURITE_BLOCK.get().asItem(), WarpedAnchorDispenseItemBehavior())
			DispenserBlock.registerBehavior(GItems.LUMIERE_SHARD.get(), LumiereComposterDispenseItemBehavior())
			DispenserBlock.registerBehavior(GItems.GLOW_FLARE.get(), ProjectileDispenseBehavior(GItems.GLOW_FLARE.get()))
		}

		ev.addListener<AddReloadListenerEvent> { event ->
			event.addListener(LumiereReformingManager())
		}

		ev.addListener<LootTableLoadEvent> { event ->
			val name = event.name
			val pools = (event.table as LootTableAccessor).getPools()
			if (name == BuiltInLootTables.ANCIENT_CITY.location() && GalosphereConfig.SPECTRE_FLARE_ANCIENT_CITY_LOOT.get())
			{
				pools.add(
					LootPool.lootPool().add(
						LootItem.lootTableItem(GItems.SPECTRE_FLARE.get()).setWeight(1)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
					).build()
				)
			}
			if ((name == BuiltInLootTables.PILLAGER_OUTPOST.location() || name == BuiltInLootTables.ABANDONED_MINESHAFT.location()) && GalosphereConfig.SILVER_UPGRADE_TEMPLATES_LOOT.get())
			{
				pools.add(
					LootPool.lootPool().add(
						LootItem.lootTableItem(GItems.SILVER_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(1)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
					).build()
				)
			}
		}

		ev.addListener<RegisterBrewingRecipesEvent> { event ->
			event.builder.apply {
				addMix(Potions.AWKWARD, GItems.CURED_MEMBRANE.get(), GPotions.ASTRAL)
				addMix(GPotions.ASTRAL, Items.REDSTONE, GPotions.LONG_ASTRAL)
			}
		}

		ev.addListener<LevelTickEvent.Post> { event ->
			val serverLevel = event.level
			if (serverLevel is ServerLevel)
			{
				val levelData = serverLevel.getLevelData() as ServerLevelData
				PacketDistributor.sendToAllPlayers(BarometerPacket(if (levelData.clearWeatherTime > 0) levelData.clearWeatherTime else levelData.rainTime))
//				serverLevel
//				.getPlayers { true }
//				.forEach { serverPlayer ->
//					PacketDistributor.sendToPlayer(
//						serverPlayer,
//						BarometerPacket(if (levelData.clearWeatherTime > 0) levelData.clearWeatherTime else levelData.rainTime)
//					)
//				}
			}
		}

		//#endregion

//		ev.register(MiscEvents())
	}

	private fun commonSetup(event: FMLCommonSetupEvent)
	{
		event.enqueueWork {
			GPlacedFeatures.init()
			GVanillaIntegration.init()
		}
	}

	companion object
	{
		@JvmField
		val LOGGER = LogManager.getLogger()
		const val MODID = "galosphere"

		@JvmStatic
		fun id (path: String) = ResourceLocation.fromNamespaceAndPath(MODID, path)
	}
}
