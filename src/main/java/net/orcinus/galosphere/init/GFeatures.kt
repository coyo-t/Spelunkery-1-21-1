package net.orcinus.galosphere.init

import net.minecraft.core.registries.Registries
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.world.gen.features.*
import net.orcinus.galosphere.world.gen.features.config.CrystalSpikeConfig
import net.orcinus.galosphere.world.gen.features.config.NoisePatchConfig
import net.orcinus.galosphere.world.gen.features.config.PinkSaltStrawPatchConfig
import java.util.function.Supplier

object GFeatures
{
	@JvmField
	val FEATURES = DeferredRegister.create(Registries.FEATURE, Galosphere.MODID)

	@JvmField
	val CRYSTAL_SPIKE = FEATURES.register("crystal_spike") { r -> CrystalSpikeFeature(CrystalSpikeConfig.CODEC) }

	@JvmField
	val LICHEN_PATCH = FEATURES.register("lichen_patch") { r -> LichenPatchFeature(VegetationPatchConfiguration.CODEC) }

	@JvmField
	val BOWL_LICHEN = FEATURES.register("bowl_lichen") { r -> LichenMushroomFeature(NoneFeatureConfiguration.CODEC) }

	@JvmField
	val LICHEN_CORDYCEPS_COLUMN = FEATURES.register("lichen_cordyceps_column") { r -> LichenCordycepsColumnFeature(NoneFeatureConfiguration.CODEC) }

	@JvmField
	val NOISE_PATCH = FEATURES.register("noise_patch") { r -> NoisePatchFeature(NoisePatchConfig.CODEC) }

	@JvmField
	val PINK_SALT_STRAW_PATCH = FEATURES.register("pink_salt_straw_patch") { r -> PinkSaltStrawPatchFeature(PinkSaltStrawPatchConfig.CODEC) }

	@JvmField
	val OASIS = FEATURES.register("oasis") { r -> OasisFeature(NoneFeatureConfiguration.CODEC) }

	@JvmField
	val BERSERKER = FEATURES.register("berserker") { r -> BerserkerFeature(NoneFeatureConfiguration.CODEC) }

	@JvmField
	val SIMPLE_WATERLOGGED_BLOCK = FEATURES.register("simple_waterlogged_block") { r -> SimpleWaterloggedBlockFeature(SimpleBlockConfiguration.CODEC) }
}
