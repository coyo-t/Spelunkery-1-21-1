package com.ordana.spelunkery.datamalarky

import dissonance.util.LuaCoyote
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.FileToIdConverter
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.Resource
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import party.iroiro.luajava.lua54.Lua54Consts.LUA_MULTRET
import party.iroiro.luajava.value.LuaTableValue
import java.awt.Dimension
import java.awt.Point
import java.awt.Rectangle
import java.nio.ByteBuffer
import java.nio.ByteOrder

object SlotDecoManager
{

	private lateinit var rm: ResourceManager

	private val thingz = mutableListOf<Thingzor>()
	private val thingByName = mutableMapOf<Item, Thingzor>()

	@JvmStatic
	fun getThing (p: ItemStack): Thingzor?
	{
		return thingByName[p.item]
	}


	class Thingzor(
		val at: Point,
		val baseSize: Dimension,
		val currentValueSource: DataComponentType<*>,
		val maxValueSource: DataComponentType<*>,
		val applies: List<ResourceLocation>,
		val subImages: List<ResourceLocation>,
	)
	{

		fun getSubImage (fac:Double): ResourceLocation
		{
			return subImages[(fac * (subImages.size - 1)).toInt()]
		}
	}

	private fun LuaCoyote.fuckinRun (r: Resource): Int
	{
		val pev = top
		return r.open().use { f ->
			val srcBytes = f.readAllBytes()
			val data = ByteBuffer.allocateDirect(srcBytes.size).order(ByteOrder.nativeOrder())
			load(data.put(srcBytes).flip(), "MACHINE WITNESS")
			pCall(0, LUA_MULTRET)
			toAbsoluteIndex(pev-top)
		}
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
					val tell = L.top
					try
					{
						L.fuckinRun(resource)
						val maybeRes = L.get()
						if (maybeRes is LuaTableValue)
						{
							val maybeTargetPoint = maybeRes["at"]
							val targetPoint = Point()
							if (maybeTargetPoint is LuaTableValue)
							{
								targetPoint.setLocation(
									maybeTargetPoint[1].toInteger().toInt(),
									maybeTargetPoint[2].toInteger().toInt(),
								)
							}
							val appliesTo = (maybeRes["applies_to"] as? LuaTableValue)?.let {
								buildList {
									val sz = it.length()
									for (i in 1..sz)
									{
										ResourceLocation.tryParse(it[i].toString())?.apply {
											add(this)
										}
									}
								}
							}

							val vSrcName = ResourceLocation.tryParse(maybeRes["value_source"].toString())
							val vMaxName = ResourceLocation.tryParse(maybeRes["value_max"].toString())
							val vSrc = BuiltInRegistries.DATA_COMPONENT_TYPE.get(vSrcName)
							val vMax = BuiltInRegistries.DATA_COMPONENT_TYPE.get(vMaxName)

							val points = (maybeRes["sub_images"] as? LuaTableValue)?.let { mb ->
								buildList {
									val sz = mb.length()
									for (i in 1..sz)
									{
										ResourceLocation.tryParse(mb[i].toString())?.let {
											add(it)
										}
									}
								}
							}
							val bs = (maybeRes["base_size"] as? LuaTableValue)?.let {
								Dimension(it[1].toInteger().toInt(), it[2].toInteger().toInt())
							}

							val newThing = Thingzor(
								at = targetPoint,
								baseSize = bs!!,
								currentValueSource = vSrc!!,
								maxValueSource = vMax!!,
								applies = appliesTo!!,
								subImages = points!!
							)
							thingz += newThing
							for (name in newThing.applies)
							{
								val maybeItem = BuiltInRegistries.ITEM[name]
								if (maybeItem !== Items.AIR)
								{
									thingByName[maybeItem] = newThing
								}
							}
						}
					}
					catch (e: Throwable)
					{
						System.err.println("FUCK \"$location\"\n$e")
						throw e
					}
					finally
					{
						L.top = tell
					}
				}
			}
		}

//		rm.listResources("spelunkery:textures/slot/bar/honey_level") { true }.forEach {
//			print("MACHINE WITNESS: $it")
//		}
	}

}