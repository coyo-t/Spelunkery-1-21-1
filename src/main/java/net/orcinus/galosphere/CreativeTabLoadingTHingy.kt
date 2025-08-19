package net.orcinus.galosphere

import dissonance.util.LuaCoyote
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.registries.DeferredRegister
import net.orcinus.galosphere.Galosphere.Companion.LOGGER
import net.orcinus.galosphere.Galosphere.Companion.MODID
import net.orcinus.galosphere.Galosphere.Companion.getInternalResource
import party.iroiro.luajava.value.LuaTableValue
import java.io.InputStreamReader
import java.util.function.Supplier

object CreativeTabLoadingTHingy
{
	val L = LuaCoyote()
	init
	{
		L.openLibraries()
	}

	fun handleResourceLocation (n:String): ResourceLocation
	{
		val nmod = n.replace(' ', '_')
		if (nmod.startsWith(':'))
		{
			return ResourceLocation.fromNamespaceAndPath(MODID, nmod.substring(1))
		}
		return ResourceLocation.parse(nmod)
	}

	private val NAMETABLE = mutableMapOf<String, Supplier<ItemStack>>()

	fun tryGetItemThing (n:String)
		= NAMETABLE.getOrPut(n) {
			Supplier { BuiltInRegistries.ITEM.get(handleResourceLocation(n)).defaultInstance }
		}

	fun LuaCoyote.uhh (srcName: String): DeferredRegister<CreativeModeTab>
	{
		val newReg = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID)
		try
		{
			NAMETABLE.clear()
			val maybeSource = InputStreamReader(getInternalResource(srcName)).use {
				it.readText()
			}
			run(maybeSource)
			val result = get()
			if (result !is LuaTableValue)
			{
				throw RuntimeException("load creative tab lua expecting a table, got ${result.type()}")
			}
			val tabName = result["name"].toString()
			val maybeItems = result["items"]
			if (maybeItems !is LuaTableValue)
			{
				throw RuntimeException("load creative tab - items supposed to be table, got ${maybeItems.type()}")
			}
			val builder = CreativeModeTab.builder().apply {
				icon(tryGetItemThing(result["iconic_item"].toString()))
				title(Component.translatable(result["title"].toString()))
			}
			val itemz = mutableListOf<Supplier<ItemStack>>()
			val itemCount = maybeItems.length()
			for (i in 1..itemCount)
			{
				val maybeThing = maybeItems[i]
				if (maybeThing is LuaTableValue)
				{
					TODO("advanced entries!!!!")
				}

				itemz += tryGetItemThing(maybeItems[i].toString())
			}
			builder.displayItems { idp, outs ->
				outs.acceptAll(itemz.map { it.get() })
			}
			newReg.register(tabName, builder::build)
		}
		catch (e: Throwable)
		{
			LOGGER.error("load error for lua '$srcName'", e)
		}
		finally
		{
			top = 0
		}
		return newReg
	}
}