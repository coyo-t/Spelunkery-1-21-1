package net.orcinus.galosphere.events;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.ServerLevelData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.tick.LevelTickEvent;
import net.orcinus.galosphere.init.GNetworkHandler;
import net.orcinus.galosphere.network.BarometerPacket;

public class MiscEvents
{
	@SubscribeEvent
	public void onWorldTick (LevelTickEvent.Post event)
	{
		if (event.getLevel() instanceof ServerLevel serverLevel)
		{
			serverLevel.getPlayers(serverPlayer -> true).forEach(serverPlayer -> {
				var levelData = (ServerLevelData)serverLevel.getLevelData();
				int i = levelData.getClearWeatherTime() > 0 ? levelData.getClearWeatherTime() : levelData.getRainTime();
				GNetworkHandler.INSTANCE.send(new BarometerPacket(i), PacketDistributor.PLAYER.with(serverPlayer));
			});
		}
	}

	
}
