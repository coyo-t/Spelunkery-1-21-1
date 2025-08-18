package net.orcinus.galosphere.client.model

import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.model.geom.ModelPart
import net.minecraft.client.model.geom.PartPose
import net.minecraft.client.model.geom.builders.CubeDeformation
import net.minecraft.client.model.geom.builders.CubeListBuilder
import net.minecraft.client.model.geom.builders.LayerDefinition
import net.minecraft.world.entity.LivingEntity
import net.neoforged.api.distmarker.Dist
import net.neoforged.api.distmarker.OnlyIn

@OnlyIn(Dist.CLIENT)
class SterlingArmorModel<T : LivingEntity?>(part: ModelPart) : HumanoidModel<T?>(part)
{
	val helmet = this.head.getChild("helmet")
}
