package com.ordana.spelunkery.datamalarky

import com.ordana.spelunkery.utils.LuaCoyote
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import party.iroiro.luajava.value.LuaTableValue
import party.iroiro.luajava.value.LuaValue
import java.nio.ByteBuffer
import java.nio.ByteOrder

object SlotDecoManager
{

	private lateinit var rm: ResourceManager

	private val thingz = mutableMapOf<ResourceLocation, Thingzor>()

	class Thingzor
	{

	}

	fun initialize (tm: TextureManager, rm: ResourceManager)
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
							val maybeRes = L.toAbsoluteIndex(-1)
							if (L.isTable(maybeRes))
							{
								L.getField(maybeRes, "path")
								val sprPath = L.toString(-1)?.let {
									ResourceLocation.tryParse(it)
								}
								L.pop(1)
								if (sprPath == null)
								{
									println("<MACHINE WITNESS> MALFORMED RESOURCE LOCATION")
								}
								else
								{
									println("<MACHINE WITNESS> $sprPath")
								}
								L.getField(maybeRes, "points")
								val ptIndex = L.toAbsoluteIndex(-1)
								if (L.isTable(ptIndex))
								{
									val size = L.rawLength(ptIndex)
									for (i in 1..size)
									{
										L.rawGetI(ptIndex, i)
										val uhh = L.get()
										println("<MACHINE WITNESS> $i - $uhh")
									}
								}
								L.pop(1)

							}
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