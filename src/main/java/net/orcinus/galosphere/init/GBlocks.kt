package net.orcinus.galosphere.init

import net.minecraft.util.valueproviders.ConstantInt
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.Item
import net.minecraft.world.level.block.*
import net.minecraft.world.level.block.AmethystBlock
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.material.PushReaction
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.blocks.*
import java.util.function.Supplier

object GBlocks
{
	@JvmField
	val BLOCKS = DeferredRegister.createBlocks(Galosphere.MODID)

	@JvmField
	val MONSTROMETER = registerBlock("monstrometer") {
		MonstrometerBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).lightLevel {
				if (MonstrometerBlock.isActive(it)) 4
				else 0
			}
				.requiresCorrectToolForDrops().strength(3.0f, 6.0f).sound(GSoundEvents.MONSTROMETER)
		)
	}

	@JvmField
	val WARPED_ANCHOR = registerBlock("warped_anchor") {
		WarpedAnchorBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).lightLevel {
				it.getValue(
					WarpedAnchorBlock.WARPED_CHARGE
				) * 3
			}
				.requiresCorrectToolForDrops().strength(3.0f, 6.0f).sound(GSoundEvents.SILVER)
		)
	}

	@JvmField
	val SILVER_TILES = registerBlock("silver_tiles") {
		Block(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops()
				.sound(GSoundEvents.SILVER).strength(3.0f, 6.0f)
		)
	}

	@JvmField
	val SILVER_TILES_STAIRS = registerBlock("silver_tiles_stairs") {
		StairBlock(
			SILVER_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SILVER_TILES.get())
		)
	}

	@JvmField
	val SILVER_TILES_SLAB = registerBlock("silver_tiles_slab") {
		SlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				SILVER_TILES.get()
			)
		)
	}

	@JvmField
	val SILVER_PANEL = registerBlock("silver_panel") {
		Block(
			BlockBehaviour.Properties.ofFullCopy(
				SILVER_TILES.get()
			)
		)
	}

	@JvmField
	val SILVER_PANEL_STAIRS = registerBlock("silver_panel_stairs") {
		StairBlock(
			SILVER_PANEL.get()!!.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(SILVER_TILES.get())
		)
	}

	@JvmField
	val SILVER_PANEL_SLAB = registerBlock("silver_panel_slab") {
		SlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				SILVER_PANEL.get()
			)
		)
	}

	@JvmField
	val SILVER_LATTICE = registerBlock("silver_lattice") {
		SilverLatticeBlock(
			BlockBehaviour.Properties.ofFullCopy(
				SILVER_TILES.get()
			).sound(GSoundEvents.SILVER_LATTICE).noOcclusion()
		)
	}

	@JvmField
	val GLOW_BERRIES_SILVER_LATTICE =
		registerNoTabBlock("glow_berries_silver_lattice") {
			SilverLatticeVineBlock(
				BlockBehaviour.Properties.ofFullCopy(
					SILVER_TILES.get()
				).sound(GSoundEvents.SILVER_LATTICE).lightLevel(CaveVines.emission(14)).noOcclusion()
			)
		}

	@JvmField
	val SILVER_ORE = registerBlock("silver_ore") {
		DropExperienceBlock(
			ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(
				Blocks.IRON_ORE
			)
		)
	}

	@JvmField
	val DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore") {
		DropExperienceBlock(
			ConstantInt.of(0), BlockBehaviour.Properties.ofFullCopy(
				SILVER_ORE.get()
			).mapColor(MapColor.DEEPSLATE).strength(4.5f, 3.0f).sound(SoundType.DEEPSLATE)
		)
	}

	@JvmField
	val SILVER_BLOCK = registerBlock(
		"silver_block"
	) {
		Block(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops()
				.strength(3.0f, 6.0f).sound(GSoundEvents.SILVER)
		)
	}

	@JvmField
	val RAW_SILVER_BLOCK = registerBlock(
		"raw_silver_block"
	) {
		Block(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).requiresCorrectToolForDrops()
				.strength(5.0f, 6.0f)
		)
	}

	@JvmField
	val CHANDELIER = registerNoTabBlock("chandelier") {
		ChandelierBlock(
			BlockBehaviour.Properties.of().requiresCorrectToolForDrops().strength(3.5f).sound(
				SoundType.LANTERN
			).lightLevel { ChandelierBlock.getLightEmission(it) }.noOcclusion()
		)
	}

	@JvmField
	val ALLURITE_BLOCK = registerBlock(
		"allurite_block"
	) {
		AmethystBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).strength(1.5f)
				.sound(GSoundEvents.ALLURITE).requiresCorrectToolForDrops()
		)
	}

	@JvmField
	val LUMIERE_BLOCK = registerBlock(
		"lumiere_block"
	) {
		AmethystBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).strength(1.5f).sound(GSoundEvents.LUMIERE)
				.requiresCorrectToolForDrops()
		)
	}

	@JvmField
	val CHARGED_LUMIERE_BLOCK = registerBlock("charged_lumiere_block") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				LUMIERE_BLOCK.get()
			).requiresCorrectToolForDrops()
		)
	}

	@JvmField
	val ALLURITE_CLUSTER = registerBlock("allurite_cluster") {
		PollinatedClusterBlock(
			GParticleTypes.ALLURITE_RAIN,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noOcclusion().randomTicks()
				.sound(GSoundEvents.ALLURITE_CLUSTER).strength(1.5f).lightLevel { 7 }
		)
	}

	@JvmField
	val LUMIERE_CLUSTER = registerBlock("lumiere_cluster") {
		PollinatedClusterBlock(
			GParticleTypes.LUMIERE_RAIN,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noOcclusion().randomTicks()
				.sound(GSoundEvents.LUMIERE_CLUSTER).strength(1.5f).lightLevel { 7 }
		)
	}

	@JvmField
	val GLINTED_ALLURITE_CLUSTER = registerBlock("glinted_allurite_cluster") {
		GlintedClusterBlock(
			GParticleTypes.ALLURITE_RAIN,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noOcclusion().randomTicks()
				.sound(GSoundEvents.ALLURITE_CLUSTER).strength(1.5f).lightLevel { 7 }
		)
	}

	@JvmField
	val GLINTED_LUMIERE_CLUSTER = registerBlock("glinted_lumiere_cluster") {
		GlintedClusterBlock(
			GParticleTypes.LUMIERE_RAIN,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noOcclusion().randomTicks()
				.sound(GSoundEvents.LUMIERE_CLUSTER).strength(1.5f).lightLevel { 7 }
		)
	}

	@JvmField
	val GLINTED_AMETHYST_CLUSTER = registerBlock("glinted_amethyst_cluster") {
		GlintedClusterBlock(
			GParticleTypes.AMETHYST_RAIN,
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).noOcclusion().randomTicks()
				.sound(GSoundEvents.LUMIERE_CLUSTER).strength(1.5f).lightLevel { 7 }
		)
	}

	@JvmField
	val AMETHYST_STAIRS = registerBlock("amethyst_stairs") {
		CrystalStairsBlock(
			Blocks.AMETHYST_BLOCK.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(
				Blocks.AMETHYST_BLOCK
			)
		)
	}

	@JvmField
	val AMETHYST_SLAB = registerBlock("amethyst_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.AMETHYST_BLOCK
			)
		)
	}

	@JvmField
	val ALLURITE_STAIRS = registerBlock("allurite_stairs") {
		CrystalStairsBlock(
			ALLURITE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ALLURITE_BLOCK.get())
		)
	}

	@JvmField
	val ALLURITE_SLAB = registerBlock("allurite_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ALLURITE_BLOCK.get()
			)
		)
	}

	@JvmField
	val LUMIERE_STAIRS = registerBlock("lumiere_stairs") {
		CrystalStairsBlock(
			LUMIERE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LUMIERE_BLOCK.get())
		)
	}

	@JvmField
	val LUMIERE_SLAB = registerBlock("lumiere_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				LUMIERE_BLOCK.get()
			)
		)
	}

	@JvmField
	val SMOOTH_AMETHYST = registerBlock("smooth_amethyst") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.AMETHYST_BLOCK
			)
		)
	}

	@JvmField
	val SMOOTH_AMETHYST_STAIRS = registerBlock("smooth_amethyst_stairs") {
		CrystalStairsBlock(
			SMOOTH_AMETHYST.get()!!.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
		)
	}

	@JvmField
	val SMOOTH_AMETHYST_SLAB = registerBlock("smooth_amethyst_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				SMOOTH_AMETHYST.get()
			)
		)
	}

	@JvmField
	val SMOOTH_ALLURITE = registerBlock("smooth_allurite") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ALLURITE_BLOCK.get()
			)
		)
	}

	@JvmField
	val SMOOTH_ALLURITE_STAIRS = registerBlock("smooth_allurite_stairs") {
		CrystalStairsBlock(
			SMOOTH_ALLURITE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
		)
	}

	@JvmField
	val SMOOTH_ALLURITE_SLAB = registerBlock("smooth_allurite_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				SMOOTH_ALLURITE.get()
			)
		)
	}

	@JvmField
	val SMOOTH_LUMIERE = registerBlock("smooth_lumiere") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				LUMIERE_BLOCK.get()
			)
		)
	}

	@JvmField
	val SMOOTH_LUMIERE_STAIRS = registerBlock("smooth_lumiere_stairs") {
		CrystalStairsBlock(
			SMOOTH_LUMIERE.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
		)
	}

	@JvmField
	val SMOOTH_LUMIERE_SLAB = registerBlock("smooth_lumiere_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				SMOOTH_LUMIERE.get()
			)
		)
	}

	@JvmField
	val AMETHYST_BRICKS = registerBlock("amethyst_bricks") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.AMETHYST_BLOCK
			)
		)
	}

	@JvmField
	val AMETHYST_BRICK_STAIRS = registerBlock("amethyst_brick_stairs") {
		CrystalStairsBlock(
			AMETHYST_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
		)
	}

	@JvmField
	val AMETHYST_BRICK_SLAB = registerBlock("amethyst_brick_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.AMETHYST_BLOCK
			)
		)
	}

	@JvmField
	val ALLURITE_BRICKS = registerBlock("allurite_bricks") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ALLURITE_BLOCK.get()
			)
		)
	}

	@JvmField
	val ALLURITE_BRICK_STAIRS = registerBlock("allurite_brick_stairs") {
		CrystalStairsBlock(
			ALLURITE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
		)
	}

	@JvmField
	val ALLURITE_BRICK_SLAB = registerBlock("allurite_brick_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ALLURITE_BRICKS.get()
			)
		)
	}

	@JvmField
	val LUMIERE_BRICKS = registerBlock("lumiere_bricks") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				LUMIERE_BLOCK.get()
			)
		)
	}

	@JvmField
	val LUMIERE_BRICK_STAIRS = registerBlock("lumiere_brick_stairs") {
		CrystalStairsBlock(
			LUMIERE_BRICKS.get()!!.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
		)
	}

	@JvmField
	val LUMIERE_BRICK_SLAB = registerBlock("lumiere_brick_slab") {
		CrystalSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				LUMIERE_BRICKS.get()
			)
		)
	}

	@JvmField
	val CHISELED_AMETHYST = registerBlock("chiseled_amethyst") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.AMETHYST_BLOCK
			)
		)
	}

	@JvmField
	val CHISELED_ALLURITE = registerBlock("chiseled_allurite") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ALLURITE_BLOCK.get()
			)
		)
	}

	@JvmField
	val CHISELED_LUMIERE = registerBlock("chiseled_lumiere") {
		AmethystBlock(
			BlockBehaviour.Properties.ofFullCopy(
				LUMIERE_BLOCK.get()
			)
		)
	}

	@JvmField
	val AMETHYST_LAMP = registerBlock("amethyst_lamp") {
		Block(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).lightLevel { 15 }.strength(0.3f)
				.sound(SoundType.AMETHYST)
		)
	}

	@JvmField
	val ALLURITE_LAMP = registerBlock("allurite_lamp") {
		Block(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).lightLevel { 15 }.strength(0.3f).sound(GSoundEvents.ALLURITE)
		)
	}

	@JvmField
	val LUMIERE_LAMP = registerBlock("lumiere_lamp") {
		Block(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_YELLOW).lightLevel { 15 }.strength(0.3f)
				.sound(GSoundEvents.LUMIERE)
		)
	}

	@JvmField
	val LICHEN_MOSS = registerBlock("lichen_moss") {
		LichenMossBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).strength(0.1f).sound(GSoundEvents.LICHEN_MOSS)
				.lightLevel { if (it.getValue(LichenMossBlock.LIT)) 12 else 0 }
		)
	}

	@JvmField
	val LICHEN_ROOTS = registerBlock(
		"lichen_roots"
	) {
		LichenRootsBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).replaceable().noCollission().instabreak()
				.offsetType(BlockBehaviour.OffsetType.XZ).sound(GSoundEvents.LICHEN_ROOTS)
		)
	}

	@JvmField
	val BOWL_LICHEN = registerBlock("bowl_lichen") {
		LichenMushroomBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instabreak().noCollission().sound(
				SoundType.FUNGUS
			)
		)
	}

	@JvmField
	val LICHEN_SHELF = registerBlock("lichen_shelf") {
		BaseCoralWallFanBlock(
			BlockBehaviour.Properties.ofFullCopy(
				Blocks.BRAIN_CORAL_FAN
			).noCollission().sound(GSoundEvents.LICHEN_SHELF)
		)
	}

	@JvmField
	val LICHEN_CORDYCEPS = registerNoTabBlock("lichen_cordyceps") {
		CordycepsBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN)
				.lightLevel { if (it.getValue(CordycepsBlock.BULB)) 8 else 0 }
				.noCollission().sound(
					SoundType.ROOTS
				)
		)
	}

	@JvmField
	val LICHEN_CORDYCEPS_PLANT = registerNoTabBlock("lichen_cordyceps_plant") {
		CordycepsPlantBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_CYAN).instabreak().noCollission().sound(
				SoundType.ROOTS
			)
		)
	}

	@JvmField
	val GLOW_INK_CLUMPS = registerBlock(
		"glow_ink_clumps"
	) {
		GlowInkClumpsBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_BLUE).noCollission().strength(0.2f)
				.sound(GSoundEvents.GLOW_INK_CLUMPS).lightLevel(GlowInkClumpsBlock.emission(15, 8))
		)
	}

	@JvmField
	val PINK_SALT = registerBlock(
		"pink_salt"
	) {
		PinkSaltBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_RED).sound(GSoundEvents.PINK_SALT)
				.requiresCorrectToolForDrops().strength(0.75f)
		)
	}

	@JvmField
	val ROSE_PINK_SALT = registerBlock(
		"rose_pink_salt"
	) {
		PinkSaltBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_ORANGE).sound(GSoundEvents.PINK_SALT)
				.requiresCorrectToolForDrops().strength(0.75f)
		)
	}

	@JvmField
	val PASTEL_PINK_SALT = registerBlock(
		"pastel_pink_salt"
	) {
		PinkSaltBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_WHITE).sound(GSoundEvents.PINK_SALT)
				.requiresCorrectToolForDrops().strength(0.75f)
		)
	}

	@JvmField
	val PINK_SALT_STAIRS = registerBlock("pink_salt_stairs") {
		PinkSaltStairsBlock(
			PINK_SALT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(PINK_SALT.get())
		)
	}

	@JvmField
	val ROSE_PINK_SALT_STAIRS = registerBlock("rose_pink_salt_stairs") {
		PinkSaltStairsBlock(
			ROSE_PINK_SALT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ROSE_PINK_SALT.get())
		)
	}

	@JvmField
	val PASTEL_PINK_SALT_STAIRS = registerBlock("pastel_pink_salt_stairs") {
		PinkSaltStairsBlock(
			PASTEL_PINK_SALT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(PASTEL_PINK_SALT.get())
		)
	}

	@JvmField
	val PINK_SALT_SLAB = registerBlock("pink_salt_slab") {
		PinkSaltSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val ROSE_PINK_SALT_SLAB = registerBlock("rose_pink_salt_slab") {
		PinkSaltSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PASTEL_PINK_SALT_SLAB = registerBlock("pastel_pink_salt_slab") {
		PinkSaltSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PASTEL_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PINK_SALT_WALL = registerBlock("pink_salt_wall") {
		PinkSaltWallBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val ROSE_PINK_SALT_WALL = registerBlock("rose_pink_salt_wall") {
		PinkSaltWallBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PASTEL_PINK_SALT_WALL = registerBlock("pastel_pink_salt_wall") {
		PinkSaltWallBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PASTEL_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val POLISHED_PINK_SALT = registerBlock("polished_pink_salt") {
		PinkSaltBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val POLISHED_ROSE_PINK_SALT = registerBlock("polished_rose_pink_salt") {
		PinkSaltBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val POLISHED_PASTEL_PINK_SALT = registerBlock("polished_pastel_pink_salt") {
		PinkSaltBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PASTEL_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val POLISHED_PINK_SALT_STAIRS = registerBlock("polished_pink_salt_stairs") {
		PinkSaltStairsBlock(
			PINK_SALT.get()!!.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(PINK_SALT.get())
		)
	}

	@JvmField
	val POLISHED_ROSE_PINK_SALT_STAIRS =
		registerBlock("polished_rose_pink_salt_stairs") {
			PinkSaltStairsBlock(
				ROSE_PINK_SALT.get()!!.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ROSE_PINK_SALT.get())
			)
		}

	@JvmField
	val POLISHED_PASTEL_PINK_SALT_STAIRS =
		registerBlock("polished_pastel_pink_salt_stairs") {
			PinkSaltStairsBlock(
				PASTEL_PINK_SALT.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(PASTEL_PINK_SALT.get())
			)
		}

	@JvmField
	val POLISHED_PINK_SALT_SLAB = registerBlock("polished_pink_salt_slab") {
		PinkSaltSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val POLISHED_ROSE_PINK_SALT_SLAB = registerBlock("polished_rose_pink_salt_slab") {
		PinkSaltSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val POLISHED_PASTEL_PINK_SALT_SLAB =
		registerBlock("polished_pastel_pink_salt_slab") {
			PinkSaltSlabBlock(
				BlockBehaviour.Properties.ofFullCopy(
					PASTEL_PINK_SALT.get()
				)
			)
		}

	@JvmField
	val POLISHED_PINK_SALT_WALL = registerBlock("polished_pink_salt_wall") {
		PinkSaltWallBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val POLISHED_ROSE_PINK_SALT_WALL = registerBlock("polished_rose_pink_salt_wall") {
		PinkSaltWallBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val POLISHED_PASTEL_PINK_SALT_WALL =
		registerBlock("polished_pastel_pink_salt_wall") {
			PinkSaltWallBlock(
				BlockBehaviour.Properties.ofFullCopy(
					PASTEL_PINK_SALT.get()
				)
			)
		}

	@JvmField
	val PINK_SALT_BRICKS = registerBlock("pink_salt_bricks") {
		PinkSaltBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val ROSE_PINK_SALT_BRICKS = registerBlock("rose_pink_salt_bricks") {
		PinkSaltBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PASTEL_PINK_SALT_BRICKS = registerBlock("pastel_pink_salt_bricks") {
		PinkSaltBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PASTEL_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PINK_SALT_BRICK_STAIRS = registerBlock("pink_salt_brick_stairs") {
		PinkSaltStairsBlock(
			PINK_SALT.get()!!.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(PINK_SALT.get())
		)
	}

	@JvmField
	val ROSE_PINK_SALT_BRICK_STAIRS = registerBlock("rose_pink_salt_brick_stairs") {
		PinkSaltStairsBlock(
			ROSE_PINK_SALT.get()!!.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(ROSE_PINK_SALT.get())
		)
	}

	@JvmField
	val PASTEL_PINK_SALT_BRICK_STAIRS =
		registerBlock("pastel_pink_salt_brick_stairs") {
			PinkSaltStairsBlock(
				PASTEL_PINK_SALT.get()!!.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(PASTEL_PINK_SALT.get())
			)
		}

	@JvmField
	val PINK_SALT_BRICK_SLAB = registerBlock("pink_salt_brick_slab") {
		PinkSaltSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val ROSE_PINK_SALT_BRICK_SLAB = registerBlock("rose_pink_salt_brick_slab") {
		PinkSaltSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PASTEL_PINK_SALT_BRICK_SLAB = registerBlock("pastel_pink_salt_brick_slab") {
		PinkSaltSlabBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PASTEL_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PINK_SALT_BRICK_WALL = registerBlock("pink_salt_brick_wall") {
		PinkSaltWallBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val ROSE_PINK_SALT_BRICK_WALL = registerBlock("rose_pink_salt_brick_wall") {
		PinkSaltWallBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PASTEL_PINK_SALT_BRICK_WALL = registerBlock("pastel_pink_salt_brick_wall") {
		PinkSaltWallBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PASTEL_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val CHISELED_PINK_SALT = registerBlock("chiseled_pink_salt") {
		RotatableChiseledBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PINK_SALT.get()
			)
		)
	}

	@JvmField
	val CHISELED_ROSE_PINK_SALT = registerBlock("chiseled_rose_pink_salt") {
		RotatableChiseledBlock(
			BlockBehaviour.Properties.ofFullCopy(
				ROSE_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val CHISELED_PASTEL_PINK_SALT = registerBlock("chiseled_pastel_pink_salt") {
		RotatableChiseledBlock(
			BlockBehaviour.Properties.ofFullCopy(
				PASTEL_PINK_SALT.get()
			)
		)
	}

	@JvmField
	val PINK_SALT_LAMP = registerBlock("pink_salt_lamp") {
		PinkSaltLampBlock(
			BlockBehaviour.Properties.of().noOcclusion().lightLevel { 11 }
				.sound(GSoundEvents.PINK_SALT_LAMP).requiresCorrectToolForDrops()
				.strength(3.5f).pushReaction(PushReaction.DESTROY)
		)
	}

	@JvmField
	val PINK_SALT_STRAW = registerBlock(
		"pink_salt_straw"
	) {
		PinkSaltStrawBlock(
			BlockBehaviour.Properties.of().offsetType(BlockBehaviour.OffsetType.XZ).randomTicks().dynamicShape()
				.sound(GSoundEvents.PINK_SALT).requiresCorrectToolForDrops().strength(0.75f)
		)
	}

	@JvmField
	val PINK_SALT_CLUSTER = registerBlock("pink_salt_cluster") {
		PinkSaltClusterBlock(
			BlockBehaviour.Properties.of()
				.lightLevel { 6 }
				.sound(GSoundEvents.PINK_SALT_CLUSTER)
				.requiresCorrectToolForDrops()
				.strength(1.0f)
		)
	}

	@JvmField
	val PINK_SALT_CHAMBER = registerBlock(
		"pink_salt_chamber"
	) {
		PinkSaltChamberBlock(
			BlockBehaviour.Properties.of().strength(3.0f, 6.0f).sound(GSoundEvents.PINK_SALT)
				.requiresCorrectToolForDrops()
		)
	}

	@JvmField
	val CURED_MEMBRANE_BLOCK = registerBlock(
		"cured_membrane_block"
	) {
		Block(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_LIGHT_GRAY).sound(GSoundEvents.CURED_MEMBRANE)
		)
	}

	@JvmField
	val STRANDED_MEMBRANE_BLOCK = registerBlock(
		"stranded_membrane_block"
	) {
		StrandedMembraneBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_CYAN).noOcclusion()
				.sound(GSoundEvents.STRANDED_MEMBRANE)
				.isSuffocating { state, world, pos -> false }
				.isViewBlocking { state, world, pos -> false }
		)
	}

	@JvmField
	val GILDED_BEADS = registerBlock("gilded_beads") {
		GildedBeadsBlock(
			BlockBehaviour.Properties.of().mapColor(MapColor.GOLD).noCollission().sound(GSoundEvents.GILDED_BEADS)
		)
	}

	@JvmField
	val SILVER_BALANCE = registerBlock("silver_balance") {
		SilverBalanceBlock(
			BlockBehaviour.Properties.of().mapColor(DyeColor.CYAN).strength(3.0f, 6.0f).noOcclusion()
				.sound(GSoundEvents.SILVER)
		)
	}

	@JvmField
	val POTTED_BOWL_LICHEN = registerNoTabBlock("potted_bowl_lichen", {
		FlowerPotBlock(
			BOWL_LICHEN.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()
		)
	})
	@JvmField
	val POTTED_LICHEN_ROOTS = registerNoTabBlock("potted_lichen_roots", {
		FlowerPotBlock(
			LICHEN_ROOTS.get(), BlockBehaviour.Properties.of().instabreak().noOcclusion()
		)
	})

	fun <B : Block> registerBlock(name: String, supplier: Supplier<out B>): Supplier<B>
	{
		val block = BLOCKS.register(name, supplier)
		GItems.ITEMS.register(name) { it -> BlockItem(block.get(), Item.Properties()) }
		return block
	}

	fun <B : Block> registerNoTabBlock(name: String, supplier: Supplier<out B>): Supplier<B>
	{
		return BLOCKS.register(name, supplier)
	}
}
