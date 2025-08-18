package net.orcinus.galosphere.events;

import com.google.common.collect.Lists;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.common.util.MutableHashedLinkedMap;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

//@Mod(modid=Galosphere.MODID, bus=Mod.EventBusSubscriber.Bus.MOD, value=Dist.CLIENT)
public class ClientEvents
{
	private static final Function<ItemLike, ItemStack> FUNCTION = ItemStack::new;

	private static void addAfter (
		MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map,
		ItemLike after,
		ItemLike... block
	)
	{
		List<ItemLike> stream = Lists.newArrayList(Arrays.stream(block).toList());
		Collections.reverse(stream);
		stream.forEach(blk -> addAfter(map, after, blk));
	}
	
	private static void addBefore (MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map, ItemLike before, ItemLike... block)
	{
		List<ItemLike> stream = Lists.newArrayList(Arrays.stream(block).toList());
		Collections.reverse(stream);
		stream.forEach(blk -> addBefore(map, before, blk));
	}
	
	private static void addAfter (MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map, ItemLike after, ItemLike block)
	{
		map.putAfter(FUNCTION.apply(after), FUNCTION.apply(block), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	}
	
	private static void addBefore (MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map, ItemLike before, ItemLike block)
	{
		map.putBefore(FUNCTION.apply(before), FUNCTION.apply(block), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	}
	
	private static void accept (MutableHashedLinkedMap<ItemStack, CreativeModeTab.TabVisibility> map, ItemLike block)
	{
		map.put(new ItemStack(block), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);
	}
	
	@SubscribeEvent
	public static void buildCreativeModeTabContents (BuildCreativeModeTabContentsEvent event)
	{
	}

	
}
