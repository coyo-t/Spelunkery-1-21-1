package net.orcinus.galosphere

import dissonance.mixin.LootTableAccessor
import dissonance.util.LuaCoyote
import dissonance.util.contains
import dissonance.util.decremented
import dissonance.util.get
import dissonance.util.isa
import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.client.model.HumanoidModel.createMesh
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.client.model.geom.builders.MeshDefinition
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction
import net.minecraft.client.renderer.item.ItemProperties
import net.minecraft.client.resources.sounds.SimpleSoundInstance
import net.minecraft.core.BlockPos
import net.minecraft.core.BlockPos.MutableBlockPos
import net.minecraft.core.component.DataComponents
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.EntityTypeTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.animal.horse.Horse
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potions
import net.minecraft.world.level.block.RenderShape
import net.minecraft.world.level.block.ShulkerBoxBlock
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.storage.ServerLevelData
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent
import net.neoforged.neoforge.client.event.RenderBlockScreenEffectEvent.OverlayType
import net.neoforged.neoforge.client.event.ViewportEvent.ComputeFogColor
import net.neoforged.neoforge.common.CreativeModeTabRegistry
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.event.AddReloadListenerEvent
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent
import net.neoforged.neoforge.event.LootTableLoadEvent
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent
import net.neoforged.neoforge.event.entity.EntityTeleportEvent
import net.neoforged.neoforge.event.entity.item.ItemExpireEvent
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent
import net.neoforged.neoforge.event.entity.player.PlayerEvent
import net.neoforged.neoforge.event.level.BlockEvent
import net.neoforged.neoforge.event.tick.LevelTickEvent
import net.neoforged.neoforge.network.PacketDistributor
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent
import net.neoforged.neoforge.network.handling.IPayloadContext
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.blocks.WarpedAnchorBlock
import net.orcinus.galosphere.client.model.PreservedModel
import net.orcinus.galosphere.client.particles.CrystalRainParticle
import net.orcinus.galosphere.client.particles.ImpactParticle
import net.orcinus.galosphere.client.particles.IndicatorParticle
import net.orcinus.galosphere.client.particles.providers.PinkSaltFallingDustProvider
import net.orcinus.galosphere.client.particles.providers.WarpedProvider
import net.orcinus.galosphere.client.renderer.GildedBeadsRenderer
import net.orcinus.galosphere.client.renderer.PinkSaltPillarRenderer
import net.orcinus.galosphere.client.renderer.PinkSaltShardRenderer
import net.orcinus.galosphere.client.renderer.PreservedRenderer
import net.orcinus.galosphere.entities.PreservedCorpse
import net.orcinus.galosphere.init.*
import net.orcinus.galosphere.items.SaltboundTabletItem
import net.orcinus.galosphere.items.SterlingArmorItem
import net.orcinus.galosphere.network.BarometerPacket
import net.orcinus.galosphere.network.PlayCooldownSoundPacket
import net.orcinus.galosphere.util.PreservedShulkerBox
import org.apache.logging.log4j.LogManager
import org.joml.Vector3d
import party.iroiro.luajava.value.LuaTableValue
import java.io.File
import java.io.InputStreamReader
import kotlin.math.max

//@Mod(Galosphere.MODID)
class Galosphere(ev: IEventBus)
{
	init
	{
		ev.addListener<FMLCommonSetupEvent> {
			it.enqueueWork {
				GPlacedFeatures.init()
			}
		}

		val creativeTabRegistar = CreativeTabLoadingTHingy.run {
			L.uhh("creative tab.lua")
		}


		val registars = listOf(
			GBlocks.BLOCKS,
			GBlockEntityTypes.BLOCK_ENTITIES,
//			GCreativeModeTabs.CREATIVE_MODE_TABS,
			creativeTabRegistar,
			GDataComponents.DATA_COMPONENT_TYPES,
			GEnchantmentEffectComponents.DATA_COMPONENTS,
			GEntityTypes.ENTITY_TYPES,
			GFeatures.FEATURES,
			GItems.ITEMS,
			GMemoryModuleTypes.MEMORY_MODULE_TYPES,
			GMobEffects.MOB_EFFECTS,
			GPotions.POTIONS,
			GParticleTypes.PARTICLES,
			GRecipeSerializers.RECIPE_SERIALIZERS,
			GStructureProcessorTypes.STRUCTURE_PROCESSOR_TYPES,
			GSensorTypes.SENSOR_TYPES,
			GSoundEvents.SOUND_EVENTS,
		)

		registars.forEach { it.register(ev)}

		clientEventz(ev)
		entityEventz(ev)
		miscEventz(ev)

		ev.addListener(::loadCreativeTabz)
	}

	private fun loadCreativeTabz (event: BuildCreativeModeTabContentsEvent)
	{

	}


	private fun clientEventz(ev: IEventBus)
	{
		ev.addListener<FMLClientSetupEvent> { event ->
			event.enqueueWork {
				ItemProperties.register(GItems.BAROMETER.get(), id("weather_level"), object : ClampedItemPropertyFunction
				{
					private var rotation = 0.0
					private var ticksBeforeChange = 0

					override fun unclampedCall(
						itemStack: ItemStack,
						clientLevel: ClientLevel?,
						livingEntity: LivingEntity?,
						i: Int
					): Float
					{
						val entity = (livingEntity ?: itemStack.entityRepresentation) ?: return 0f
						var clientLevel = (clientLevel ?: (entity.level() as? ClientLevel)) ?: return 0f

						val speed = 0.00525f
						val clearWeatherTime = clearWeatherTime
						val index = if (clearWeatherTime < 5) 0 else max(0, clearWeatherTime / 1000)
						val max = if (clearWeatherTime < 12000)
						{
							val predicates = arrayOf(
								floatArrayOf(0.15f, 0.13f, 0.21f, 0.28f, 0.36f, 0.44f, 0.52f, 0.59f, 0.70f, 0.75f, 0.82f, 0.9f),
								floatArrayOf(0.9f, 0.82f, 0.75f, 0.70f, 0.59f, 0.52f, 0.44f, 0.36f, 0.28f, 0.21f, 0.13f, 0.15f)
							)
							predicates[if (clientLevel.isRaining) 1 else 0][index]
						}
						else
						{
							if (clientLevel.getLevelData().isRaining) 0.0f else 1.0f
						}
						val rainLevel = clientLevel.getRainLevel(1.0f)
						if ((rainLevel > 0.9f || rainLevel < 0.1f) && clearWeatherTime == 0 && this.ticksBeforeChange == 0)
						{
							this.ticksBeforeChange = 800
						}
						if (this.ticksBeforeChange > 0)
						{
							this.ticksBeforeChange--
						}
						if (!clientLevel.dimensionType().natural() && clientLevel.getRandom().nextFloat() < 0.1f)
						{
							this.rotation = Mth.positiveModulo(Math.random() - this.rotation, 1.0)
						}
						if (this.rotation < max && this.ticksBeforeChange == 0)
						{
							this.rotation += speed.toDouble()
						}
						if (this.rotation > max && this.ticksBeforeChange == 0)
						{
							this.rotation -= speed.toDouble()
						}
						return if (this.rotation >= 0.99) 1f else this.rotation.toFloat()
					}
				})
				ItemProperties.register(GItems.SALTBOUND_TABLET.get(), id("using")) { stack, world, entity, i ->
					if (entity != null && entity.getUseItem().item is SaltboundTabletItem) 1f else 0f
				}
				ItemProperties.register(GItems.SALTBOUND_TABLET.get(), id("cooldown")) { stack, world, entity, i ->
					if (entity is Player && entity.cooldowns.isOnCooldown(GItems.SALTBOUND_TABLET.get())) 1f else 0f
				}
			}
		}

		val evBus = NeoForge.EVENT_BUS
		evBus.addListener<ComputeFogColor> { event ->
			val camera = event.camera
			if (renderShadowPhase(camera.entity) && getViewBlockingState(camera.entity as LivingEntity) != null)
			{
				event.red = 0f
				event.green = 0f
				event.blue = 0f
			}
		}

		evBus.addListener<RenderBlockScreenEffectEvent> { event ->
			if (event.overlayType == OverlayType.BLOCK && event.player.hasEffect(GMobEffects.ASTRAL))
			{
				event.setCanceled(true)
			}
		}

		ev.addListener<RegisterParticleProvidersEvent> {
			with(it) {
				registerSpriteSet(GParticleTypes.WARPED.get(), ::WarpedProvider)
				registerSpriteSet(GParticleTypes.ALLURITE_RAIN.get(), CrystalRainParticle::Provider)
				registerSpriteSet(GParticleTypes.LUMIERE_RAIN.get(), CrystalRainParticle::Provider)
				registerSpriteSet(GParticleTypes.AMETHYST_RAIN.get(), CrystalRainParticle::Provider)
				registerSpriteSet(GParticleTypes.AURA_RINGER_INDICATOR.get(), IndicatorParticle::Provider)
				registerSpriteSet(GParticleTypes.PINK_SALT_FALLING_DUST.get(), ::PinkSaltFallingDustProvider)
				registerSpriteSet(GParticleTypes.IMPACT.get(), ImpactParticle::Provider)
			}
		}

		ev.addListener<EntityRenderersEvent.RegisterRenderers> {
			with(it)
			{
				registerEntityRenderer(GEntityTypes.PRESERVED_CORPSE.get(), ::PreservedRenderer)
				registerEntityRenderer(GEntityTypes.PINK_SALT_PILLAR.get(), ::PinkSaltPillarRenderer)
				registerEntityRenderer(GEntityTypes.PINK_SALT_SHARD.get(), ::PinkSaltShardRenderer)
				registerBlockEntityRenderer(GBlockEntityTypes.GILDED_BEADS.get(), ::GildedBeadsRenderer)
			}
		}

		ev.addListener<EntityRenderersEvent.RegisterLayerDefinitions> {
			with(it)
			{
				registerLayerDefinition(GModelLayers.STERLING_HELMET) {
					val meshdefinition = createMesh(CubeDeformation.NONE, 0.0f)
					val partdefinition = meshdefinition.root

					val head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO)
					val helmet = head.addOrReplaceChild(
						"helmet",
						CubeListBuilder.create().texOffs(0, 0)
							.addBox(-1.0f, -12.25f, -6.0f, 2.0f, 12.0f, 12.0f, CubeDeformation(0.0f))
							.texOffs(20, 16).addBox(-4.0f, -9.0f, -4.0f, 8.0f, 8.0f, 8.0f, CubeDeformation(1.0f)),
						PartPose.offset(0.0f, 0.0f, 0.0f)
					)
					return@registerLayerDefinition LayerDefinition.create(meshdefinition, 64, 64)
				}
				registerLayerDefinition(GModelLayers.GILDED_BEADS) {
					val meshdefinition = MeshDefinition()
					val partdefinition = meshdefinition.root
					partdefinition.addOrReplaceChild(
						"gilded_beads",
						CubeListBuilder.create().texOffs(0, 0)
							.addBox(-8.0f, -16.0f, 0.0f, 16.0f, 16.0f, 0.0f, CubeDeformation(0.0f)),
						PartPose.offset(0.0f, 24.0f, 0.0f)
					)
					return@registerLayerDefinition LayerDefinition.create(meshdefinition, 32, 32)
				}
				registerLayerDefinition(GModelLayers.PRESERVED) { PreservedModel.createBodyLayer() }
				registerLayerDefinition(GModelLayers.PINK_SALT_PILLAR) {
					val meshdefinition = MeshDefinition()
					val partdefinition = meshdefinition.root

					val root = partdefinition.addOrReplaceChild(
						"root",
						CubeListBuilder.create().texOffs(0, 0)
							.addBox(-5.0f, -32.0f, -5.0f, 10.0f, 32.0f, 10.0f, CubeDeformation(0.0f)),
						PartPose.offset(0.0f, 24.0f, 0.0f)
					)
					return@registerLayerDefinition LayerDefinition.create(meshdefinition, 48, 48)
				}
			}

		}
	}

	private fun miscEventz(ev: IEventBus)
	{
		ev.addListener<RegisterPayloadHandlersEvent> { event ->
			with (event.registrar("1").optional())
			{
				playToClient(
					BarometerPacket.TYPE,
					BarometerPacket.CODEC,
					::sendBarometerInfo,
				)
				playToClient(
					PlayCooldownSoundPacket.TYPE,
					PlayCooldownSoundPacket.CODEC,
					::playCooldownSound,
				)
			}
		}

		NeoForge.EVENT_BUS.addListener<AddReloadListenerEvent> { event ->
	//			event.addListener(LumiereReformingManager())
		}

		NeoForge.EVENT_BUS.addListener<LootTableLoadEvent> { event ->
			val name = event.name
			val pools = (event.table as LootTableAccessor).getPools()
			if ((name == BuiltInLootTables.PILLAGER_OUTPOST.location() || name == BuiltInLootTables.ABANDONED_MINESHAFT.location()))
			{
				pools.add(
					LootPool.lootPool().add(
						LootItem.lootTableItem(GItems.SILVER_UPGRADE_SMITHING_TEMPLATE.get()).setWeight(1)
							.apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0f, 2.0f)))
					).build()
				)
			}
		}

		NeoForge.EVENT_BUS.addListener<RegisterBrewingRecipesEvent> { event ->
			event.builder.apply {
				addMix(Potions.AWKWARD, GItems.CURED_MEMBRANE.get(), GPotions.ASTRAL)
				addMix(GPotions.ASTRAL, Items.REDSTONE, GPotions.LONG_ASTRAL)
			}
		}


		NeoForge.EVENT_BUS.addListener<LevelTickEvent.Post> { event ->
			val serverLevel = event.level
			if (serverLevel is ServerLevel)
			{
				val levelData = serverLevel.getLevelData() as ServerLevelData
				PacketDistributor.sendToAllPlayers(BarometerPacket(if (levelData.clearWeatherTime > 0) levelData.clearWeatherTime else levelData.rainTime))
			}
		}
	}

	private fun entityEventz(ev: IEventBus)
	{
		//#region Entity Eventz

		val evBus = NeoForge.EVENT_BUS
		ev.addListener<EntityAttributeCreationEvent> { event ->
			with(event)
			{
				put(GEntityTypes.PRESERVED_CORPSE.get(), PreservedCorpse.createAttributes().build())
			}
		}

		evBus.addListener<ItemExpireEvent> { event ->
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

		evBus.addListener<EntityTeleportEvent.EnderPearl> { event ->
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
					val pearlLevel = pearl.level()
					pearlLevel.gameEvent(player, GameEvent.BLOCK_CHANGE, blockPos)
					pearlLevel.playSound(
						null,
						blockPos,
						SoundEvents.RESPAWN_ANCHOR_SET_SPAWN,
						SoundSource.BLOCKS,
						1.0f,
						1.0f
					)
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

		evBus.addListener<ItemTooltipEvent> { event ->
			if (GDataComponents.PRESERVED in event.itemStack)
			{
				event.toolTip.add(Component.translatable("item.galosphere.preserved").withStyle(ChatFormatting.DARK_PURPLE))
			}
		}

		evBus.addListener<PlayerEvent.Clone> { event ->
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

		evBus.addListener<BlockEvent.EntityPlaceEvent> { event ->
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
					(blockEntity as PreservedShulkerBox).isPreserved = true
				}
			}
		}

		evBus.addListener<BlockEvent.BreakEvent> { event ->
			val world = event.level
			val pos = event.pos
			val blockEntity = world.getBlockEntity(pos)
			val player = event.player
			val state = event.state
			// TODO: may or may not work.
	//			if (blockEntity is ShulkerBoxBlockEntity && (blockEntity as PreservedShulkerBox).isPreserved())
			if (blockEntity is ShulkerBoxBlockEntity && (blockEntity as PreservedShulkerBox).isPreserved)
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

		evBus.addListener<LivingDamageEvent.Pre> { event ->
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
	}

	fun sendBarometerInfo(packet: BarometerPacket, ctx: IPayloadContext)
	{
		ctx.enqueueWork {
			clearWeatherTime = packet.weatherTicks
//			ctx.handle(packet)
		}
	}

	fun playCooldownSound(packet: PlayCooldownSoundPacket, ctx: IPayloadContext)
	{
		ctx.enqueueWork {
			val instance = Minecraft.getInstance()
			val player = instance.player
			if (player != null)
			{
				instance.soundManager
					.play(SimpleSoundInstance.forUI(GSoundEvents.SALTBOUND_TABLET_COOLDOWN_OVER.get(), 1.0f))
			}
//			ctx.handle(packet)
		}
	}

	companion object
	{
		@JvmField
		val LOGGER = LogManager.getLogger()
		const val MODID = "galosphere"

		@JvmField
		var clearWeatherTime = 0


		@JvmStatic
		fun id (path: String) = ResourceLocation.fromNamespaceAndPath(MODID, path)

		val occlusionCubeCornerPoints = buildList {
			for (i in 0..7)
			{
				val xx = ((((i) and 1) - 0.5) * 0.8)
				val yy = ((((i shr 1) and 1) - 0.5) * 0.1)
				val zz = ((((i shr 2) and 1) - 0.5) * 0.8)
				this += Vector3d(xx, yy, zz)
			}
		}

		private fun getViewBlockingState(player: LivingEntity): BlockState?
		{
			val mutableBlockPos = MutableBlockPos()
			val x = player.x
			val y = player.eyeY
			val z = player.z
			val wide = player.bbWidth
			val level = player.level()
			for (p in occlusionCubeCornerPoints)
			{
				val blockState = level.getBlockState(mutableBlockPos.set(p.x*wide+x, p.y+y, p.z*wide+z))
				if (blockState.renderShape != RenderShape.INVISIBLE && blockState.isViewBlocking(level, mutableBlockPos))
				{
					return blockState
				}
			}
//			for (i in 0..7)
//			{
//				val xx = x + ((((i) and 1) - 0.5) * wide * 0.8)
//				val yy = y + ((((i shr 1) and 1) - 0.5) * 0.1)
//				val zz = z + ((((i shr 2) and 1) - 0.5) * wide * 0.8)
//				val blockState = level.getBlockState(mutableBlockPos.set(xx, yy, zz))
//				if (blockState.renderShape != RenderShape.INVISIBLE && blockState.isViewBlocking(level, mutableBlockPos))
//				{
//					return blockState
//				}
//			}
			return null
		}

		private fun renderShadowPhase(entity: Entity): Boolean
		{
			return entity is LivingEntity && entity.hasEffect(GMobEffects.ASTRAL)
		}

		fun getInternalResource (at:String)
			= Galosphere::class.java.getResourceAsStream(at.trimStart('/')) ?: throw NoSuchFileException(File(at))

	}
}
