package com.ordana.spelunkery.events;

import net.mehvahdjukaar.moonlight.api.platform.network.Message;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;

public class ClientBoundSendKnockbackPacket implements Message
{
	
	public final int id;
	public final double knockbackX;
	public final double knockbackY;
	public final double knockbackZ;
	
	public ClientBoundSendKnockbackPacket (Vec3 knockback, int id)
	{
		this.id = id;
		this.knockbackX = knockback.x;
		this.knockbackY = knockback.y;
		this.knockbackZ = knockback.z;
	}
	
	public ClientBoundSendKnockbackPacket (FriendlyByteBuf buf)
	{
		this.id = buf.readInt();
		this.knockbackX = buf.readDouble();
		this.knockbackY = buf.readDouble();
		this.knockbackZ = buf.readDouble();
	}
	
	@Override
	public void write (RegistryFriendlyByteBuf buf)
	{
		buf.writeInt(this.id);
		buf.writeDouble(this.knockbackX);
		buf.writeDouble(this.knockbackY);
		buf.writeDouble(this.knockbackZ);
		
	}
	
	@Override
	public void handle (Context context)
	{
		// client world
		ClientReceivers.handleSendBombKnockbackPacket(this);
	}
	
	@Override
	public Type<? extends CustomPacketPayload> type ()
	{
		return null;
	}
}