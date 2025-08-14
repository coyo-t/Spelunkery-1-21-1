package com.ordana.spelunkery.datamalarky

import com.ordana.spelunkery.Spelunkery
import com.ordana.spelunkery.utils.LuaCoyote
import net.minecraft.resources.FileToIdConverter
import net.minecraft.server.packs.resources.ResourceManager
import org.apache.logging.log4j.Level
import org.lwjgl.BufferUtils
import java.nio.ByteBuffer
import java.nio.ByteOrder

object SlotDecoManager
{

	private lateinit var rm: ResourceManager


	fun initialize (rm: ResourceManager)
	{
		this.rm = rm

		val basePath = "models/item_slot"
		with (FileToIdConverter(basePath, ".lua"))
		{
			LuaCoyote().use { L ->
				L.openLibraries()
				listMatchingResources(rm).forEach { location, resource ->
					val thing = fileToId(location)
					println("<MACHINE WITNESS> \"$thing\" \"$resource\"")

					try
					{
						resource.open().use { f ->
							val srcBytes = f.readAllBytes()
							val data = ByteBuffer.allocateDirect(srcBytes.size).order(ByteOrder.nativeOrder())
							L.run(data.put(srcBytes).flip(), "wow")
							val maybeRes = L.get()
							println("$maybeRes")
						}
					}
					catch (e: Throwable)
					{
						System.err.println("FUCK \"$location\"\n$e")
						throw e
					}

				}
			}
		}

//		rm.listResources("spelunkery:textures/slot/bar/honey_level") { true }.forEach {
//			print("MACHINE WITNESS: $it")
//		}
	}

}