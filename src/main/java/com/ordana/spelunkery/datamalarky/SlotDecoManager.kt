package com.ordana.spelunkery.datamalarky

import net.minecraft.resources.FileToIdConverter
import net.minecraft.server.packs.resources.ResourceManager

object SlotDecoManager
{

	private lateinit var rm: ResourceManager


	fun initialize (rm: ResourceManager)
	{
		this.rm = rm

		val basePath = "models/item_slot"
		with (FileToIdConverter(basePath, ".lua"))
		{
			listMatchingResources(rm).forEach { rl, r ->
				val thing = fileToId(rl)
				println("<MACHINE WITNESS> \"$thing\" \"$r\"")
			}
		}

//		rm.listResources("spelunkery:textures/slot/bar/honey_level") { true }.forEach {
//			print("MACHINE WITNESS: $it")
//		}
	}

}