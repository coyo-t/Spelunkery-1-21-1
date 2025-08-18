package net.orcinus.galosphere.crafting

import com.google.common.collect.Maps
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener
import net.minecraft.util.GsonHelper
import net.minecraft.util.profiling.ProfilerFiller
import net.minecraft.world.level.block.Block
import net.orcinus.galosphere.Galosphere
import net.orcinus.galosphere.Galosphere.Companion.id
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets

class LumiereReformingManager : SimpleJsonResourceReloadListener(GSON_INSTANCE, "loot_tables/gameplay")
{
	override fun apply(
		pObject: MutableMap<ResourceLocation, JsonElement>,
		resourceManagerIn: ResourceManager,
		pProfiler: ProfilerFiller
	)
	{
		val resourceLocation = id("loot_tables/gameplay/lumiere_reforming_table.json")
		try
		{
			for (iResource in resourceManagerIn.getResourceStack(resourceLocation))
			{
				try
				{
					BufferedReader(InputStreamReader(iResource.open(), StandardCharsets.UTF_8)).use { reader ->
						val jsonObject = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject::class.java)
						if (jsonObject != null)
						{
							val entryList = jsonObject.get("entries").getAsJsonArray()
							for (entry in entryList)
							{
								reformingTable.put(
									BuiltInRegistries.BLOCK.get(
										ResourceLocation.parse(
											entry.getAsJsonObject()["accepted_block"].asString
										)
									),

									BuiltInRegistries.BLOCK.get(
										ResourceLocation.parse(
											entry.getAsJsonObject()["returned_block"].asString
										)
									)
								)
							}
						}
					}
				}
				catch (exception: RuntimeException)
				{
					Galosphere.LOGGER.error(
						"Couldn't read lumiere reforming table list {} in data pack {}",
						resourceLocation,
						iResource.sourcePackId(),
						exception
					)
				}
				catch (exception: IOException)
				{
					Galosphere.LOGGER.error(
						"Couldn't read lumiere reforming table list {} in data pack {}",
						resourceLocation,
						iResource.sourcePackId(),
						exception
					)
				}
			}
		}
		catch (exception: NoSuchElementException)
		{
			Galosphere.LOGGER.error("Couldn't read lumiere reforming table from {}", resourceLocation, exception)
		}
	}

	companion object
	{
		private val GSON_INSTANCE = GsonBuilder().create()
		val reformingTable = mutableMapOf<Block, Block>()
	}
}
