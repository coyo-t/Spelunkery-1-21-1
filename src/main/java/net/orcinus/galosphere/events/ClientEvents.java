package net.orcinus.galosphere.events;

import com.google.common.collect.Lists;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.component.DataComponents;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.common.util.MutableHashedLinkedMap;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.init.GItems;
import net.orcinus.galosphere.items.SaltboundTabletItem;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

//@Mod(modid=Galosphere.MODID, bus=Mod.EventBusSubscriber.Bus.MOD, value=Dist.CLIENT)
public class ClientEvents
{
	private static final Function<ItemLike, ItemStack> FUNCTION = ItemStack::new;

//	@SubscribeEvent
//	public static void onClientSetup (final FMLClientSetupEvent event)
//	{
//		event.enqueueWork(() -> {
//			ItemProperties.register(Items.CROSSBOW, Galosphere.id("glow_flare"), (itemStack, clientLevel, livingEntity, i) -> {
//				ChargedProjectiles chargedProjectiles = itemStack.get(DataComponents.CHARGED_PROJECTILES);
//				return chargedProjectiles != null && chargedProjectiles.contains(GItems.GLOW_FLARE.get()) ? 1 : 0;
//			});
//			ItemProperties.register(Items.CROSSBOW, Galosphere.id("spectre_flare"), (itemStack, clientLevel, livingEntity, i) -> {
//				ChargedProjectiles chargedProjectiles = itemStack.get(DataComponents.CHARGED_PROJECTILES);
//				return chargedProjectiles != null && chargedProjectiles.contains(GItems.SPECTRE_FLARE.get()) ? 1 : 0;
//			});
//			ItemProperties.register(GItems.BAROMETER.get(), Galosphere.id("weather_level"), new ClampedItemPropertyFunction()
//			{
//				private double rotation;
//				private int ticksBeforeChange;
//
//				@Override
//				public float unclampedCall (ItemStack itemStack, @Nullable ClientLevel clientLevel, @Nullable LivingEntity livingEntity, int i)
//				{
//					Entity entity = livingEntity != null ? livingEntity : itemStack.getEntityRepresentation();
//					float[][] predicates = new float[][]{{0.15F, 0.13F, 0.21F, 0.28F, 0.36F, 0.44F, 0.52F, 0.59F, 0.70F, 0.75F, 0.82F, 0.9F}, {0.9F, 0.82F, 0.75F, 0.70F, 0.59F, 0.52F, 0.44F, 0.36F, 0.28F, 0.21F, 0.13F, 0.15F}};
//					if (entity == null)
//					{
//						return 0.0f;
//					}
//					if (clientLevel == null && entity.level() instanceof ClientLevel clientWorld)
//					{
//						clientLevel = clientWorld;
//					}
//					if (clientLevel == null)
//					{
//						return 0.0f;
//					}
//					float max;
//					float speed = 0.00525F;
//					int clearWeatherTime = Galosphere.clearWeatherTime;
//					int index = clearWeatherTime < 5 ? 0 : Math.max(0, clearWeatherTime / 1000);
//					if (clearWeatherTime < 12000)
//					{
//						max = predicates[clientLevel.isRaining() ? 1 : 0][index];
//					}
//					else
//					{
//						max = clientLevel.getLevelData().isRaining() ? 0.0F : 1.0F;
//					}
//					float rainLevel = clientLevel.getRainLevel(1.0F);
//					if ((rainLevel > 0.9F || rainLevel < 0.1F) && clearWeatherTime == 0 && this.ticksBeforeChange == 0)
//					{
//						this.ticksBeforeChange = 800;
//					}
//					if (this.ticksBeforeChange > 0)
//					{
//						this.ticksBeforeChange--;
//					}
//					if (!clientLevel.dimensionType().natural() && clientLevel.getRandom().nextFloat() < 0.1F)
//					{
//						this.rotation = Mth.positiveModulo(Math.random() - this.rotation, 1);
//					}
//					if (this.rotation < max && this.ticksBeforeChange == 0)
//					{
//						this.rotation += speed;
//					}
//					if (this.rotation > max && this.ticksBeforeChange == 0)
//					{
//						this.rotation -= speed;
//					}
//					return this.rotation >= 0.99 ? 1 : (float)this.rotation;
//				}
//
//			});
//			ItemProperties.register(GItems.SALTBOUND_TABLET.get(), Galosphere.id("using"), (stack, world, entity, i) -> entity != null && entity.getUseItem().getItem() instanceof SaltboundTabletItem ? 1 : 0);
//			ItemProperties.register(GItems.SALTBOUND_TABLET.get(), Galosphere.id("cooldown"), (stack, world, entity, i) -> entity instanceof Player player && player.getCooldowns().isOnCooldown(GItems.SALTBOUND_TABLET.get()) ? 1 : 0);
//		});
//
//	}
//
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
