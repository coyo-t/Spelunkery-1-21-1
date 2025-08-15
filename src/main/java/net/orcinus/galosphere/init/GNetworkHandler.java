package net.orcinus.galosphere.init;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraftforge.network.Channel;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.server.ServerLifecycleHooks;
import net.orcinus.galosphere.Galosphere;
import net.orcinus.galosphere.network.BarometerPacket;
import net.orcinus.galosphere.network.ClientEventsHandler;
import net.orcinus.galosphere.network.PlayCooldownSoundPacket;
import net.orcinus.galosphere.network.SendParticlesPacket;
import net.orcinus.galosphere.network.SendPerspectivePacket;

public class GNetworkHandler {
    public static final Channel<CustomPacketPayload> INSTANCE = ChannelBuilder
            .named(Galosphere.id("network"))
            .optional()
            .payloadChannel()
            .any()
            .bidirectional()
            .add(SendParticlesPacket.TYPE, SendParticlesPacket.CODEC, ClientEventsHandler::handleSendParticles)
            .add(BarometerPacket.TYPE, BarometerPacket.CODEC, ClientEventsHandler::sendBarometerInfo)
            .add(SendPerspectivePacket.TYPE, SendPerspectivePacket.CODEC, ClientEventsHandler::sendPerspective)
            .add(PlayCooldownSoundPacket.TYPE, PlayCooldownSoundPacket.CODEC, ClientEventsHandler::playCooldownSound)
            .build();

    public GNetworkHandler() {
    }

    public static void init() {
    }

    public static void sendToAllInRangeClients(BlockPos pos, ServerLevel level, double distance, SendParticlesPacket message) {
        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();

        if (server == null) return;

        PlayerList playerList = server.getPlayerList();

        for(int i = 0; i < playerList.getPlayers().size(); ++i) {
            ServerPlayer serverplayer = playerList.getPlayers().get(i);
            if (serverplayer != null && serverplayer.level().dimension() == level.dimension()) {
                double d0 = pos.getX() - serverplayer.getX();
                double d1 = pos.getY() - serverplayer.getY();
                double d2 = pos.getZ() - serverplayer.getZ();
                if (d0 * d0 + d1 * d1 + d2 * d2 < distance * distance) {
                    INSTANCE.send(message, PacketDistributor.PLAYER.with(serverplayer));
                }
            }
        }
    }

}
