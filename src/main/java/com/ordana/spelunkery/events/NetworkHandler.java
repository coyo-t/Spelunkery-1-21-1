package com.ordana.spelunkery.events;

import net.mehvahdjukaar.moonlight.api.platform.network.Message;
import net.mehvahdjukaar.moonlight.api.platform.network.NetworkHelper;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class NetworkHandler
{
//	public static final ChannelHandler CHANNEL = ChannelHandler.createChannel(Spelunkery.res("network"));
	
	public static void registerMessages ()
	{
		// FIXME
		NetworkHelper.addNetworkRegistration(it -> {
//				it.registerClientBound(new CustomPacketPayload.TypeAndCodec(ClientBoundSendKnockbackPacket.class, Message::makeType));
				
			},
			0
		);
//		CHANNEL.register(NetworkDir.PLAY_TO_CLIENT,
//				  ClientBoundSendKnockbackPacket.class, ClientBoundSendKnockbackPacket::new);
//
//		CHANNEL.register(NetworkDir.PLAY_TO_CLIENT,
//				  ClientBoundParticlePacket.class, ClientBoundParticlePacket::new);
	}
	
}