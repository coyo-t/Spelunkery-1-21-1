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
	var helmet = this.head.getChild("helmet")

	companion object
	{
		@JvmStatic
		fun createBodyLayer(): LayerDefinition
		{
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

			return LayerDefinition.create(meshdefinition, 64, 64)
		}
	}
}
