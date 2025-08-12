package com.ordana.spelunkery.reg

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.blocks.*
import com.ordana.spelunkery.blocks.fungi.*
import com.ordana.spelunkery.blocks.nephrite.RawNephriteBlock
import com.ordana.spelunkery.blocks.rock_salt.*
import net.minecraft.core.BlockPos
import net.minecraft.util.ColorRGBA
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.util.valueproviders.UniformInt
import net.minecraft.world.entity.EntityType
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.material.MapColor
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier


private typealias PropCB = BlockBehaviour.Properties.()->Unit

object ModBlocks
{
	@JvmField
	val BLOCKS = DeferredRegister.createBlocks(Spelunkery.MOD_ID)

	val SHOULD_ITEMITITIZE = mutableListOf<Supplier<out Block>>()
	val ITEMZORNAMEZOR = mutableListOf<String>()

	//#region theze whorez want my orez

	@JvmField
	val SMOOTH_BASALT_DIAMOND_ORE = regWithItem("smooth_basalt_diamond_ore") {
		DropExperienceBlock(
			UniformInt.of(3, 7),
			BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_ORE)
				.requiresCorrectToolForDrops()
				.strength(3f, 3f)
				.sound(SoundType.BASALT)
		)
	}

	@JvmField
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

	@JvmField
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
	val redstoneFamily = createREDSTONERockFamily("redstone_ore", Blocks.REDSTONE_ORE) {
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
	@JvmField
	val ROUGH_CINNABAR_BLOCK = regWithItem("rough_cinnabar_block") {
		RoughCinnabarBlock(
			roughGemBloc(MapColor.COLOR_RED).apply {
				// FIXME
				lightLevel(createLightLevelFromLitBlockState(9))
				randomTicks()
			}
		)
	}
	@JvmField
	val ROUGH_LAZURITE_BLOCK = regWithItem("rough_lazurite_block") {
		Block(roughGemBloc(MapColor.LAPIS))
	}
	@JvmField
	val ROUGH_EMERALD_BLOCK = regWithItem("rough_emerald_block") {
		Block(roughGemBloc(MapColor.EMERALD))
	}
	@JvmField
	val ROUGH_DIAMOND_BLOCK = regWithItem("rough_diamond_block") {
		Block(roughGemBloc(MapColor.DIAMOND))
	}
	@JvmField
	val ROUGH_QUARTZ_BLOCK = regWithItem("rough_quartz_block") {
		RotatedPillarBlock(roughGemBloc(MapColor.QUARTZ))
	}
	@JvmField
	val CINNABAR_BLOCK = regWithItem("cinnabar_block") {
		PoweredBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_BLOCK).mapColor(MapColor.COLOR_RED)
				.requiresCorrectToolForDrops().strength(5.0f, 6.0f).sound(SoundType.METAL)
				.isRedstoneConductor(::never)
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
	@JvmField
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
	@JvmField
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
	@JvmField
	val SULFUR_GEYSER = regWithItem("sulfur_geyser") {
		SulfuricVentBlock(
			propertiesFrom(Blocks.STONE) {
				mapColor(MapColor.SAND)
			}
		)
	}

	//nephrite
	@JvmField
	val RAW_NEPHRITE = regWithItem("raw_nephrite") {
		RawNephriteBlock(
			propertiesFrom(Blocks.STONE) {
				mapColor(MapColor.EMERALD)
				requiresCorrectToolForDrops()
				strength(3f, 2f)
			}
		)
	}
	@JvmField
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

	@JvmField
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
	@JvmField
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
	@JvmField
	val SALTPETER = regWithItem("saltpeter") {
		FallingLayerBlock(
			BlockBehaviour.Properties.of().noCollission().instabreak().sound(
				SoundType.SAND
			).mapColor(MapColor.TERRACOTTA_WHITE)
		)
	}

	//plants
	@JvmField
	val TANGLE_ROOTS = regWithItem("tangle_roots") {
		TangleRootsHeadBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES).mapColor(MapColor.COLOR_BROWN).randomTicks()
				.noCollission().instabreak().sound(
					SoundType.WEEPING_VINES
				)
		)
	}
	@JvmField
	val TANGLE_ROOTS_PLANT = regBlock("tangle_roots_plant") {
		TangleRootsBodyBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.WEEPING_VINES_PLANT).mapColor(MapColor.COLOR_BROWN).randomTicks()
				.noCollission().instabreak().sound(
					SoundType.WEEPING_VINES
				)
		)
	}
	@JvmField
	val TANGLE_ROOTS_BLOCK = regWithItem("tangle_roots_block") {
		TangleRootsBlockBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.PODZOL).strength(3f, 0.5f).randomTicks().sound(
				SoundType.MANGROVE_ROOTS
			).ignitedByLava()
		)
	}
	@JvmField
	val SPOROPHYTE = regWithItem("sporophyte") {
		SporophyteBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.GRASS_BLOCK
			).noCollission().instabreak().sound(SoundType.MOSS).offsetType(BlockBehaviour.OffsetType.XZ)
		)
	}
	@JvmField
	val TALL_SPOROPHYTE = regWithItem("tall_sporophyte") {
		DoublePlantBlock(
			BlockBehaviour.Properties.ofFullCopy(Blocks.TALL_GRASS).noCollission().instabreak().sound(SoundType.MOSS)
				.offsetType(BlockBehaviour.OffsetType.XZ)
		)
	}


	//fungi
	@JvmField
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
	@JvmField
	val INKCAP_MUSHROOM = regWithItem("inkcap_mushroom") {
		GrowableMushroomBlock(
			propertiesFrom(PORTABELLA.get()) {
				hasPostProcess(::always)
			}
		)
	}
	@JvmField
	val WHITE_INKCAP_MUSHROOM = regWithItem("white_inkcap_mushroom") {
		GrowableMushroomBlock(
			propertiesFrom(PORTABELLA.get()) {
				hasPostProcess(::always)
			}
		)
	}
	@JvmField
	val PHOSPHOR_FUNGUS = regWithItem("phosphor_fungus") {
		FloorAndSidesMushroomBlock(
			propertiesFrom(CONK_FUNGUS.get()) {
				alwaysLuminescent(3)
			}
		)
	}
	@JvmField
	val MUSHGLOOM = regWithItem("mushgloom") {
		FloorAndSidesMushroomBlock(
			propertiesFrom(CONK_FUNGUS.get()) {
				alwaysLuminescent(1)
			}
		)
	}
	@JvmField
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
	@JvmField
	val POTTED_PORTABELLA = regBlock("potted_portabella") {
		fp(PORTABELLA, Blocks.POTTED_POPPY) {
			instabreak()
			noOcclusion()
		}
	}
	@JvmField
	val POTTED_CRIMINI = regBlock("potted_crimini") {
		fp(CRIMINI, POTTED_PORTABELLA.get())
	}
	@JvmField
	val POTTED_BUTTON_MUSHROOM = regBlock("potted_button_mushroom") {
		fp(BUTTON_MUSHROOM, POTTED_PORTABELLA.get())
	}
	@JvmField
	val POTTED_INKCAP_MUSHROOM = regBlock("potted_inkcap_mushroom") {
		fp(INKCAP_MUSHROOM, POTTED_PORTABELLA.get())
	}
	@JvmField
	val POTTED_WHITE_INKCAP_MUSHROOM = regBlock("potted_white_inkcap_mushroom") {
		fp(WHITE_INKCAP_MUSHROOM, POTTED_PORTABELLA.get())
	}
	@JvmField
	val POTTED_PHOSPHOR_FUNGUS = regBlock("potted_phosphor_fungus") {
		fp(PHOSPHOR_FUNGUS, POTTED_PORTABELLA.get()) {
			alwaysLuminescent(3)
		}
	}
	@JvmField
	val POTTED_MUSHGLOOM = regBlock("potted_mushgloom") {
		fp(MUSHGLOOM, POTTED_PORTABELLA.get()) {
			alwaysLuminescent(1)
		}
	}
	@JvmField
	val POTTED_MILLY_BUBCAP = regBlock("potted_milly_bubcap") {
		fp(MILLY_BUBCAP, POTTED_PORTABELLA.get())
	}
	@JvmField
	val POTTED_SPOROPHYTE = regBlock("potted_sporophyte") {
		fp(SPOROPHYTE, POTTED_PORTABELLA.get())
	}
	@JvmField
	val CONK_FUNGUS_BLOCK = regWithItem("conk_fungus_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.DIRT)
				strength(0.2f)
				sound(SoundType.STEM)
			}
		)
	}
	@JvmField
	val PORTABELLA_BLOCK = regWithItem("portabella_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.DIRT)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}
	@JvmField
	val INKCAP_MUSHROOM_BLOCK = regWithItem("inkcap_mushroom_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.TERRACOTTA_BLACK)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}
	@JvmField
	val WHITE_INKCAP_MUSHROOM_BLOCK = regWithItem("white_inkcap_mushroom_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.SAND)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}
	@JvmField
	val MILLY_BUBCAP_BLOCK = regWithItem("milly_bubcap_block") {
		HugeMushroomBlock(
			propertiesFrom(Blocks.RED_MUSHROOM_BLOCK) {
				mapColor(MapColor.TERRACOTTA_BROWN)
				strength(0.2f)
				sound(SoundType.WOOD)
			}
		)
	}
	@JvmField
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
	@JvmField
	val PHOSPHOR_SHROOMLIGHT = regWithItem("phosphor_shroomlight") {
		Block(
			propertiesFrom(Blocks.SHROOMLIGHT) {
				mapColor(MapColor.COLOR_CYAN)
				sound(SoundType.SHROOMLIGHT)
				alwaysLuminescent(8)
			}
		)
	}
	@JvmField
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
	@JvmField
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
	// FIXME how 2 fluidz???
	@JvmField
	val PORTAL_FLUID = regBlock("portal_fluid") {
//		PortalFluidBlock(
//			ModFluids.STILL_PORTAL_FLUID,
		Block(
			propertiesFrom(Blocks.WATER) {
				noCollission()
				strength(100f)
				noLootTable()
				lightLevel { 5 }
			}
		)
	}
	@JvmField
	val SPRING_WATER = regBlock("spring_water") {
//		SpringWaterBlock(
//			ModFluids.STILL_SPRING_WATER,
		Block(
			propertiesFrom(Blocks.WATER) {
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

	private fun FUCKINGROCKS (base: Block, p: PropCB) = propertiesFrom(base) {
		requiresCorrectToolForDrops()
		strength(3f, 3f)
		p(this)
	}

	private fun <T:Block> rockFamilyBullshit (name:String, base: Block, p: PropCB, mkBloc:(BlockBehaviour.Properties)->T): Supplier<T>
	{
		val r = regWithItem("granite_${name}") {
			mkBloc(FUCKINGROCKS(base, p))
		}
		regWithItem("andesite_${name}") {
			mkBloc(FUCKINGROCKS(base, p))
		}
		regWithItem("diorite_${name}") {
			mkBloc(FUCKINGROCKS(base, p))
		}
		regWithItem("tuff_${name}") {
			mkBloc(FUCKINGROCKS(base) { sound(SoundType.TUFF); p() })
		}
		return r
	}

	private fun createRockFamily (name: String, base: Block, xp: IntProvider)
		= rockFamilyBullshit(name, base, {}) { DropExperienceBlock(xp, it) }

	private fun createRockFamily (name: String, base: Block, p: PropCB )
		= rockFamilyBullshit(name, base, p) { Block(it) }

	private fun createREDSTONERockFamily (name: String, base: Block, p: PropCB )
		= rockFamilyBullshit(name, base, p) { RedStoneOreBlock(it) }


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

	private fun createLightLevelFromLitBlockState(lightValue: Int)
		= { blockState: BlockState? -> if (blockState?.getValue(BlockStateProperties.LIT) == true) lightValue else 0 }

	private fun <T : Block> regBlock(name: String, block: Supplier<T>)
		= BLOCKS.register<T>(name, block)

	private fun <T : Block> regWithItem(name: String, blockFactory: Supplier<T>): Supplier<T>
	{
		return regBlock(name, blockFactory).apply {
			SHOULD_ITEMITITIZE += this
			ITEMZORNAMEZOR += name
			// FIXME
//			RegHelper.registerItem(Spelunkery.res(name)) { BlockItem(this.get(), Item.Properties()) }
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
