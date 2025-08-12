package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.blocks.*
import com.ordana.spelunkery.blocks.fungi.*
import com.ordana.spelunkery.blocks.nephrite.RawNephriteBlock
import com.ordana.spelunkery.blocks.rock_salt.*
import com.ordana.spelunkery.reg.ModBlocks.always
import net.mehvahdjukaar.moonlight.api.platform.RegHelper
import net.minecraft.core.BlockPos
import net.minecraft.util.ColorRGBA
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.entity.EntityType
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockBehaviour.StateArgumentPredicate
import net.minecraft.world.level.block.state.BlockBehaviour.StatePredicate
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.material.FlowingFluid
import net.minecraft.world.level.material.Fluid
import net.minecraft.world.level.material.MapColor
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier
import java.util.function.ToIntFunction

private typealias PropCB = BlockBehaviour.Properties.()->Unit

object ModBlocks
{
	val BLOCKS: DeferredRegister.Blocks = DeferredRegister.createBlocks(Spelunkery.MOD_ID)

	//#region theze whorez want my orez

	val SMOOTH_BASALT_DIAMOND_ORE = regWithItem("smooth_basalt_diamond_ore") {
		DropExperienceBlock(
			UniformInt.of(3, 7),
			BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
				.requiresCorrectToolForDrops()
				.strength(3f, 3f)
				.sound(SoundType.BASALT)
		)
	}

	val CALCITE_REDSTONE_ORE = regWithItem("calcite_redstone_ore") {
		RedStoneOreBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_ORE)
				.requiresCorrectToolForDrops()
				.strength(3f, 3f)
				.sound(SoundType.CALCITE)
				.lightLevel(createLightLevelFromLitBlockState(9))
				.randomTicks()
		)
	}

	val SANDSTONE_LAPIS_ORE = regWithItem("sandstone_lapis_ore") {
		DropExperienceBlock(
			UniformInt.of(2, 5),
			BlockBehaviour.Properties.ofFullCopy(Blocks.LAPIS_ORE)
				.requiresCorrectToolForDrops()
				.strength(2.5f, 3f)
		)
	}

	val coalFamily = createRockFamily("coal_ore", Blocks.COAL_ORE, UniformInt.of(0, 2))
	val ironFamily = createRockFamily("iron_ore", Blocks.IRON_ORE)
	val copperFamily = createRockFamily("copper_ore", Blocks.COPPER_ORE)
	val goldFamily = createRockFamily("gold_ore", Blocks.GOLD_ORE)
	val redstoneFamily = createRockFamily("redstone_ore", Blocks.REDSTONE_ORE) {
		randomTicks()
		lightLevel(createLightLevelFromLitBlockState(9))
	}
	val lapisFamily = createRockFamily("lapis_ore", Blocks.LAPIS_ORE, UniformInt.of(2, 5))
	val emeraldFamily = createRockFamily("emerald_ore", Blocks.EMERALD_ORE, UniformInt.of(3, 7))
	val diamondFamily = createRockFamily("diamond_ore", Blocks.DIAMOND_ORE, UniformInt.of(3, 7))
	val zincFamily = createRockFamily("zinc_ore", Blocks.GOLD_ORE)
	val leadFamily = createRockFamily("lead_ore", Blocks.GOLD_ORE)
	val silverFamily = createRockFamily("silver_ore", Blocks.GOLD_ORE)
	val jadeFamily = createRockFamily("jade_ore", Blocks.EMERALD_ORE, UniformInt.of(3, 7))

	//#endregion

	//#region scream

	//rough gems
	val ROUGH_CINNABAR_BLOCK = regWithItem("rough_cinnabar_block") {
		RoughCinnabarBlock(
			roughGemBloc(MapColor.COLOR_RED).apply {
				lightLevel(createLightLevelFromLitBlockState(9))
				randomTicks()
			}
		)
	}
	val ROUGH_LAZURITE_BLOCK = regWithItem("rough_lazurite_block") {
		Block(roughGemBloc(MapColor.LAPIS))
	}
	val ROUGH_EMERALD_BLOCK = regWithItem("rough_emerald_block") {
		Block(roughGemBloc(MapColor.EMERALD))
	}
	val ROUGH_DIAMOND_BLOCK = regWithItem("rough_diamond_block") {
		Block(roughGemBloc(MapColor.DIAMOND))
	}
	val ROUGH_QUARTZ_BLOCK = regWithItem("rough_quartz_block") {
		RotatedPillarBlock(roughGemBloc(MapColor.QUARTZ))
	}

	val CINNABAR_BLOCK = regWithItem("cinnabar_block") {
		PoweredBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).mapColor(MapColor.COLOR_RED)
				.requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL)
				.isRedstoneConductor(::never)
		)
	}

	//rock salt
	@JvmField
	val ROCK_SALT = regBlock("rock_salt") {
		RockSaltCrystalBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_PINK)
				.requiresCorrectToolForDrops().strength(3f, 2f).sound(SoundType.CALCITE).lightLevel(
					createLightLevelFromIlluminatedBlockState(1)
				).emissiveRendering(::ifIlluminated).noOcclusion()
		)
	}

	@JvmField
	val SALT_LAMP = regWithItem("salt_lamp") {
		SaltLampBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_PINK)
				.strength(0.5f, 2f).sound(SoundType.CALCITE).lightLevel(createLightLevelFromLitBlockState(7))
				.emissiveRendering(::ifLit)
				.noOcclusion()
		)
	}

	@JvmField
	val SALT = regBlock("salt") {
		SaltBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.REDSTONE_WIRE
			).mapColor(MapColor.TERRACOTTA_PINK).instabreak().randomTicks().noCollission().sound(
				SoundType.SAND
			)
		)
	}
	val SALT_BLOCK = regWithItem("salt_block") {
		SaltBlockBlock(
			0xdedede, BlockBehaviour.Properties.ofFullCopy(
				Blocks.SAND
			).mapColor(MapColor.TERRACOTTA_PINK).strength(0.5f).sound(SoundType.SAND)
		)
	}

	@JvmField
	val ROCK_SALT_BLOCK = regWithItem("rock_salt_block") {
		RockSaltBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).apply {
				mapColor(MapColor.TERRACOTTA_PINK)
				requiresCorrectToolForDrops()
				strength(3f, 2f)
				sound(SoundType.CALCITE)
				lightLevel(createLightLevelFromIlluminatedBlockState(1))
				emissiveRendering(::ifIlluminated)
			}
		)
	}
	val ROCK_SALT_SLAB = regWithItem("rock_salt_slab") {
		RockSaltSlab(
			BlockBehaviour.Properties.ofFullCopy(
				ROCK_SALT_BLOCK.get()
			).lightLevel(createLightLevelFromIlluminatedBlockState(1))
				.emissiveRendering(::ifIlluminated)
		)
	}
	val ROCK_SALT_STAIRS = regWithItem("rock_salt_stairs") {
		RockSaltStairs(
			ROCK_SALT_BLOCK.get().defaultBlockState(),
			BlockBehaviour.Properties.ofFullCopy(ROCK_SALT_BLOCK.get()).lightLevel(
				createLightLevelFromIlluminatedBlockState(1)
			).emissiveRendering(::ifIlluminated)
		)
	}
	val ROCK_SALT_WALL = regWithItem("rock_salt_wall") {
		RockSaltWall(
			BlockBehaviour.Properties.ofFullCopy(
				ROCK_SALT_BLOCK.get()
			).lightLevel(createLightLevelFromIlluminatedBlockState(1))
				.emissiveRendering(::ifIlluminated)
		)
	}

	@JvmField
	val POLISHED_ROCK_SALT = regWithItem("polished_rock_salt") {
		RockSaltBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.TERRACOTTA_PINK)
				.requiresCorrectToolForDrops().strength(3f, 2f).sound(SoundType.CALCITE).lightLevel(
					createLightLevelFromIlluminatedBlockState(1)
				).emissiveRendering(::ifIlluminated)
		)
	}
	val POLISHED_ROCK_SALT_SLAB = regWithItem("polished_rock_salt_slab") {
		RockSaltSlab(
			BlockBehaviour.Properties.ofFullCopy(POLISHED_ROCK_SALT.get()).lightLevel(
				createLightLevelFromIlluminatedBlockState(1)
			).emissiveRendering(::ifIlluminated)
		)
	}
	val POLISHED_ROCK_SALT_STAIRS = regWithItem("polished_rock_salt_stairs") {
		RockSaltStairs(
			POLISHED_ROCK_SALT.get().defaultBlockState(),
			BlockBehaviour.Properties.ofFullCopy(POLISHED_ROCK_SALT.get()).lightLevel(
				createLightLevelFromIlluminatedBlockState(1)
			).emissiveRendering(::ifIlluminated)
		)
	}
	val POLISHED_ROCK_SALT_WALL = regWithItem("polished_rock_salt_wall") {
		RockSaltWall(
			BlockBehaviour.Properties.ofFullCopy(POLISHED_ROCK_SALT.get()).lightLevel(
				createLightLevelFromIlluminatedBlockState(1)
			).emissiveRendering(::ifIlluminated)
		)
	}

	@JvmField
	val ROCK_SALT_BRICKS = regWithItem("rock_salt_bricks") {
		RockSaltBlock(
			propertiesFrom(Blocks.STONE) {
				mapColor(MapColor.TERRACOTTA_PINK)
				requiresCorrectToolForDrops()
				strength(3f, 2f)
				sound(SoundType.CALCITE)
				lightLevel(createLightLevelFromIlluminatedBlockState(1))
				emissiveRendering(::ifIlluminated)
			}
		)
	}
	val ROCK_SALT_BRICK_SLAB = regWithItem("rock_salt_brick_slab") {
		RockSaltSlab(
			propertiesFrom(ROCK_SALT_BRICKS.get()) {
				lightLevel(createLightLevelFromIlluminatedBlockState(1))
				emissiveRendering(::ifIlluminated)
			}
		)
	}
	val ROCK_SALT_BRICK_STAIRS = regWithItem("rock_salt_brick_stairs") {
		RockSaltStairs(
			ROCK_SALT_BRICKS.get().defaultBlockState(),
			propertiesFrom(ROCK_SALT_BRICKS.get()) {
				lightLevel(createLightLevelFromIlluminatedBlockState(1))
				emissiveRendering(::ifIlluminated)
			}
		)
	}
	val ROCK_SALT_BRICK_WALL = regWithItem("rock_salt_brick_wall") {
		RockSaltWall(
			propertiesFrom(ROCK_SALT_BRICKS.get()) {
				lightLevel(createLightLevelFromIlluminatedBlockState(1))
				emissiveRendering(::ifIlluminated)
			}
		)
	}

	@JvmField
	val POLISHED_QUARTZ_BLOCK = regWithItem("polished_quartz_block") {
		Block(
			propertiesFrom(Blocks.STONE) {
				mapColor(MapColor.QUARTZ)
				requiresCorrectToolForDrops()
				strength(3f, 2f)
				sound(SoundType.CALCITE)
			}
		)
	}

	val SALTPETER_BLOCK = regWithItem("saltpeter_block") {
		ColoredFallingBlock(
			ColorRGBA(0xdbd8d4),
			propertiesFrom(Blocks.SAND) {
				mapColor(MapColor.TERRACOTTA_WHITE)
				strength(0.5f)
				sound(SoundType.SAND)
			}
		)
	}
	val SULFUR_BLOCK = regWithItem("sulfur_block") {
		ColoredFallingBlock(
			ColorRGBA(0xe1bf89),
			propertiesFrom(Blocks.SAND) {
				mapColor(MapColor.TERRACOTTA_YELLOW)
				strength(0.5f)
				sound(SoundType.SAND)
			}
		)
	}
	val SULFUR_GEYSER = regWithItem("sulfur_geyser") {
		SulfuricVentBlock(
			propertiesFrom(Blocks.STONE) {
				mapColor(MapColor.SAND)
			}
		)
	}

	//nephrite
	val RAW_NEPHRITE = regWithItem("raw_nephrite") {
		RawNephriteBlock(
			propertiesFrom(Blocks.STONE) {
				mapColor(MapColor.EMERALD)
				requiresCorrectToolForDrops()
				strength(3f, 2f)
			}
		)
	}
	val NEPHRITE = regWithItem("nephrite") {
		Block(
			propertiesFrom(Blocks.STONE) {
				mapColor(MapColor.EMERALD)
				requiresCorrectToolForDrops()
				strength(3f, 2f)
			}
		)
	}

	@JvmField
	val COMPRESSION_BLAST_MINER = regBlock("compression_blast_miner") {
		CompressionBlastMiner(
			propertiesFrom(Blocks.OBSIDIAN) {
				sound(SoundType.NETHERITE_BLOCK)
			}
		)
	}

	@JvmField
	val RAW_MAGNETITE_BLOCK = regWithItem("raw_magnetite_block") {
		Block(
			propertiesFrom(Blocks.OBSIDIAN) {
				sound(SoundType.LODESTONE)
			}
		)
	}


	val DUST_BLOCK = regWithItem("dust_block") {
		DustBlockBlock(
			propertiez {
				noCollission()
				instabreak()
				sound(SoundType.WOOL)
				mapColor(MapColor.COLOR_GRAY)
			}
		)
	}
	val DUST = regWithItem("dust") {
		DustBlock(
			propertiez {
				noCollission()
				instabreak()
				sound(SoundType.WOOL)
				mapColor(MapColor.COLOR_GRAY)
			}
		)
	}

	@JvmField
	val BUNNY_EARS = regBlock("bunny_ears") {
		BunnyEarsUtilBlock(
			propertiez {
				noCollission()
				instabreak()
				sound(SoundType.WOOL)
				mapColor(MapColor.COLOR_GRAY)
			}
		)
	}

	@JvmField
	val TRUE_CROWN = regBlock("true_crown") {
		BunnyEarsUtilBlock(
			propertiez {
				noCollission()
				instabreak()
				sound(SoundType.WOOL)
				mapColor(MapColor.COLOR_GRAY)
			}
		)
	}

	@JvmField
	val SULFUR = regWithItem("sulfur") {
		FallingLayerBlock(
			propertiez {

				noCollission()
				instabreak()
				sound(SoundType.SAND)
				mapColor(MapColor.TERRACOTTA_YELLOW)
			}
		)
	}
	val SALTPETER = regWithItem("saltpeter") {
		FallingLayerBlock(
			BlockBehaviour.Properties.of().noCollission().instabreak().sound(
				SoundType.SAND
			).mapColor(MapColor.TERRACOTTA_WHITE)
		)
	}

	//plants
	val TANGLE_ROOTS = regWithItem("tangle_roots") {
		TangleRootsHeadBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES).mapColor(MapColor.COLOR_BROWN).randomTicks()
				.noCollission().instabreak().sound(
					SoundType.WEEPING_VINES
				)
		)
	}
	val TANGLE_ROOTS_PLANT = regBlock("tangle_roots_plant") {
		TangleRootsBodyBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES_PLANT).mapColor(MapColor.COLOR_BROWN).randomTicks()
				.noCollission().instabreak().sound(
					SoundType.WEEPING_VINES
				)
		)
	}
	val TANGLE_ROOTS_BLOCK = regWithItem("tangle_roots_block") {
		TangleRootsBlockBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(3f, 0.5f).randomTicks().sound(
				SoundType.MANGROVE_ROOTS
			).ignitedByLava()
		)
	}

	val SPOROPHYTE = regWithItem("sporophyte") {
		SporophyteBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.GRASS_BLOCK
			).noCollission().instabreak().sound(SoundType.MOSS).offsetType(BlockBehaviour.OffsetType.XZ)
		)
	}
	val TALL_SPOROPHYTE = regWithItem("tall_sporophyte") {
		DoublePlantBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).noCollission().instabreak().sound(SoundType.MOSS)
				.offsetType(BlockBehaviour.OffsetType.XZ)
		)
	}


	//fungi
	val CONK_FUNGUS = regWithItem("conk_fungus") {
		ConkFungusBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BROWN).noCollission().instabreak().sound(
				SoundType.FUNGUS
			)
		)
	}
	@JvmField
	val PORTABELLA = regBlock("portabella") {
		GrowableMushroomBlock(
			propertiesFrom(Blocks.POPPY) {
				noCollission()
				randomTicks()
				instabreak()
				sound(SoundType.FUNGUS)
				offsetType(BlockBehaviour.OffsetType.XZ)
				hasPostProcess(::always)
			}
		)
	}
	@JvmField
	val CRIMINI = regBlock("crimini") {
		ModMushroomBlock(propertiesFrom(PORTABELLA.get()))
	}
	@JvmField
	val BUTTON_MUSHROOM = regBlock("button_mushroom") {
		ModMushroomBlock(propertiesFrom(PORTABELLA.get()))
	}
	val INKCAP_MUSHROOM = regWithItem("inkcap_mushroom") {
		GrowableMushroomBlock(
			propertiesFrom(PORTABELLA.get()) {
				hasPostProcess(::always)
			}
		)
	}
	val WHITE_INKCAP_MUSHROOM = regWithItem("white_inkcap_mushroom") {
		GrowableMushroomBlock(
			propertiesFrom(PORTABELLA.get()) {
				hasPostProcess(::always)
			}
		)
	}
	val PHOSPHOR_FUNGUS = regWithItem("phosphor_fungus") {
		FloorAndSidesMushroomBlock(
			propertiesFrom(CONK_FUNGUS.get()) {
				alwaysLuminescent(3)
			}
		)
	}
	val MUSHGLOOM = regWithItem("mushgloom") {
		FloorAndSidesMushroomBlock(
			propertiesFrom(CONK_FUNGUS.get()) {
				alwaysLuminescent(1)
			}
		)
	}
	val MILLY_BUBCAP = regWithItem("milly_bubcap") {
		MillyBubcapMushroomBlock(
			propertiesFrom(Blocks.POPPY) {
				noCollission()
				instabreak()
				sound(SoundType.FUNGUS)
				offsetType(BlockBehaviour.OffsetType.XZ)
			}
		)
	}

	val POTTED_PORTABELLA = regBlock("potted_portabella") {
		fp(PORTABELLA, Blocks.POTTED_POPPY) {
			instabreak()
			noOcclusion()
		}
	}
	val POTTED_CRIMINI = regBlock("potted_crimini") {
		fp(CRIMINI, POTTED_PORTABELLA.get())
	}
	val POTTED_BUTTON_MUSHROOM = regBlock("potted_button_mushroom") {
		fp(BUTTON_MUSHROOM, POTTED_PORTABELLA.get())
	}
	val POTTED_INKCAP_MUSHROOM = regBlock("potted_inkcap_mushroom") {
		fp(INKCAP_MUSHROOM, POTTED_PORTABELLA.get())
	}
	val POTTED_WHITE_INKCAP_MUSHROOM = regBlock("potted_white_inkcap_mushroom") {
		fp(WHITE_INKCAP_MUSHROOM, POTTED_PORTABELLA.get())
	}
	val POTTED_PHOSPHOR_FUNGUS = regBlock("potted_phosphor_fungus") {
		fp(PHOSPHOR_FUNGUS, POTTED_PORTABELLA.get()) {
			alwaysLuminescent(3)
		}
	}
	val POTTED_MUSHGLOOM = regBlock("potted_mushgloom") {
		fp(MUSHGLOOM, POTTED_PORTABELLA.get()) {
			alwaysLuminescent(1)
		}
	}
	val POTTED_MILLY_BUBCAP = regBlock("potted_milly_bubcap") {
		fp(MILLY_BUBCAP, POTTED_PORTABELLA.get())
	}
	val POTTED_SPOROPHYTE = regBlock("potted_sporophyte") {
		fp(SPOROPHYTE, POTTED_PORTABELLA.get())
	}

	val CONK_FUNGUS_BLOCK = regWithItem("conk_fungus_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.DIRT)
				strength(0.2f)
				sound(SoundType.STEM)
			}
		)
	}
	val PORTABELLA_BLOCK = regWithItem("portabella_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.DIRT)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}
	val INKCAP_MUSHROOM_BLOCK = regWithItem("inkcap_mushroom_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.TERRACOTTA_BLACK)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}
	val WHITE_INKCAP_MUSHROOM_BLOCK = regWithItem("white_inkcap_mushroom_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.SAND)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}
	val MILLY_BUBCAP_BLOCK = regWithItem("milly_bubcap_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.TERRACOTTA_BROWN)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}
	val PHOSPHOR_FUNGUS_BLOCK = regWithItem("phosphor_fungus_block") {
		PhosphorFungusBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.TERRACOTTA_LIGHT_BLUE)
				strength(0.2f)
				sound(SoundType.WOOD)
				noOcclusion()
				isValidSpawn(::never)
				isRedstoneConductor(::never)
				isSuffocating(::never)
				isViewBlocking(::never)
				alwaysLuminescent(1)
			}
		)
	}
	val PHOSPHOR_SHROOMLIGHT = regWithItem("phosphor_shroomlight") {
		Block(
			propertiesFrom(Blocks.SHROOMLIGHT) {
				mapColor(MapColor.COLOR_CYAN)
				sound(SoundType.SHROOMLIGHT)
				alwaysLuminescent(8)
			}
		)
	}
	val MUSHGLOOM_BLOCK = regWithItem("mushgloom_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.TERRACOTTA_BLUE)
				strength(0.2f)
				sound(SoundType.WOOD)
				alwaysLuminescent(1)
			}
		)
	}
	val CAVE_MUSHROOM_STEM = regWithItem("cave_mushroom_stem") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.MUSHROOM_STEM) {
				mapColor(MapColor.TERRACOTTA_GRAY)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}

	@JvmField
	val GLOWSTICK = regBlock("glowstick") {
		GlowstickBlock(
			propertiesFrom(Blocks.END_ROD) {
				instabreak()
				noCollission()
				noOcclusion()
				alwaysLuminescent(14)
				sound(SoundType.CANDLE)
			}
		)
	}

	@JvmField val RED_GLOWSTICK = gs("red")
	@JvmField val ORANGE_GLOWSTICK = gs("orange")
	@JvmField val YELLOW_GLOWSTICK = gs("yellow")
	@JvmField val LIME_GLOWSTICK = gs("lime")
	@JvmField val GREEN_GLOWSTICK = gs("green")
	@JvmField val CYAN_GLOWSTICK = gs("cyan")
	@JvmField val LIGHT_BLUE_GLOWSTICK = gs("light_blue")
	@JvmField val BLUE_GLOWSTICK = gs("blue")
	@JvmField val PURPLE_GLOWSTICK = gs("purple")
	@JvmField val MAGENTA_GLOWSTICK = gs("magenta")
	@JvmField val PINK_GLOWSTICK = gs("pink")
	@JvmField val BROWN_GLOWSTICK = gs("brown")
	@JvmField val BLACK_GLOWSTICK = gs("black")
	@JvmField val WHITE_GLOWSTICK = gs("white")
	@JvmField val GRAY_GLOWSTICK = gs("gray")
	@JvmField val LIGHT_GRAY_GLOWSTICK = gs("light_gray")

	//fluids
	val PORTAL_FLUID = regBlock("portal_fluid") {
		PortalFluidBlock(
			ModFluids.PORTAL_FLUID, propertiesFrom(Blocks.WATER) {
				noCollission()
				strength(100f)
				noLootTable()
				lightLevel { 5 }
			}
		)
	}
	val SPRING_WATER = regBlock("spring_water") {
		SpringWaterBlock(
			ModFluids.SPRING_WATER, propertiesFrom(Blocks.WATER) {
				noCollission()
				strength(100f)
				noLootTable()
				lightLevel { 2 }
			}
		)
	}

	//#endregion

	//#region fucks

	private fun <T:Block> fp (thing: Supplier<T>, bh: BlockBehaviour.Properties) = FlowerPotBlock(
		{ Blocks.FLOWER_POT as FlowerPotBlock },
		thing,
		bh
	)

	private fun gs (pr:String) = regBlock("${pr}_glowstick") {
		GlowstickBlock(propertiesFrom(GLOWSTICK.get()))
	}

	private fun <T:Block> fp (thing: Supplier<T>, bhBase: Block, bhApply: PropCB)
		= fp(thing, BlockBehaviour.Properties.ofFullCopy(bhBase).apply(bhApply))

	private fun <T:Block> fp (thing: Supplier<T>, bhBase: Block)
		= fp(thing, bhBase) {}

	private fun createRockFamily (name: String, base: Block, xp: IntProvider): Supplier<DropExperienceBlock>
	{
		val r = regWithItem("granite_${name}") {
			DropExperienceBlock(xp, propertiesFrom(base) {
					requiresCorrectToolForDrops()
					strength(3f, 3f)
				}
			)
		}
		val rb = r.get()
		regWithItem("andesite_${name}") {
			DropExperienceBlock(xp, propertiesFrom(rb))
		}
		regWithItem("diorite_${name}") {
			DropExperienceBlock(xp, propertiesFrom(rb))
		}
		regWithItem("tuff_${name}") {
			DropExperienceBlock(xp, propertiesFrom(rb) { sound(SoundType.TUFF) })
		}
		return r
	}

	private fun createRockFamily (name: String, base: Block, p: PropCB ): Supplier<Block>
	{
		val r = regWithItem("granite_${name}") {
			Block(propertiesFrom(base) {
				requiresCorrectToolForDrops()
				strength(3f, 3f)
				p(this)
			})
		}
		regWithItem("andesite_${name}") {
			Block(propertiesFrom(r.get()))
		}
		regWithItem("diorite_${name}") {
			Block(propertiesFrom(r.get()))
		}
		regWithItem("tuff_${name}") {
			Block(propertiesFrom(r.get()) {
				sound(SoundType.TUFF)
			})
		}
		return r
	}

	private fun createRockFamily (name: String, base: Block): Supplier<Block>
	{
		return createRockFamily(name, base) {}
	}

	private fun always (state: BlockState, blockGetter: BlockGetter, pos: BlockPos) = true

	private fun never (state: BlockState, blockGetter: BlockGetter, pos: BlockPos) = false

	private fun never(
		blockState: BlockState?,
		blockGetter: BlockGetter?,
		blockPos: BlockPos?,
		entityType: EntityType<*>?
	) = false

	private fun ifIlluminated(state: BlockState, blockGetter: BlockGetter, pos: BlockPos)
		= state.getValue(ModBlockProperties.ILLUMINATED)

	private fun ifLit(state: BlockState, blockGetter: BlockGetter, pos: BlockPos)
		= state.getValue(BlockStateProperties.LIT)

	private fun createLightLevelFromIlluminatedBlockState(litLevel: Int)
		= { state: BlockState? -> if (state!!.getValue(ModBlockProperties.ILLUMINATED) as Boolean) litLevel else 0 }

	private fun createLightLevelFromLitBlockState(lightValue: Int)
		= { blockState: BlockState? -> if (blockState!!.getValue(BlockStateProperties.LIT) as Boolean) lightValue else 0 }

	private fun <T : Block> regBlock(name: String, block: Supplier<T>)
		= BLOCKS.register<T>(name, block)

	private fun <T : Block> regWithItem(name: String, blockFactory: Supplier<T>): Supplier<T>
	{
		return regBlock(name, blockFactory).apply {
			RegHelper.registerItem(Spelunkery.res(name)) { BlockItem(this.get(), Item.Properties()) }
		}
	}

	private fun BlockBehaviour.Properties.alwaysLuminescent (at:Int) = apply {
		emissiveRendering(::always)
		lightLevel { at }
	}
	private fun roughGemBloc (c: MapColor)
		= BlockBehaviour.Properties.ofFullCopy(Blocks.RAW_COPPER_BLOCK).apply {
			mapColor(c)
			requiresCorrectToolForDrops()
			strength(5f, 6f)
			sound(SoundType.CALCITE)
		}

	private fun propertiesFrom (bloc: Block) = BlockBehaviour.Properties.ofFullCopy(bloc)

	private fun propertiesFrom (bloc: Block, ads: PropCB)
		= propertiesFrom(bloc).apply(ads)

	private fun propertiez (it: PropCB) = BlockBehaviour.Properties.of().apply(it)
	//#endregion
}
