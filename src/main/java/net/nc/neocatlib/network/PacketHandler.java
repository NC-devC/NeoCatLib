package net.nc.neocatlib.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;
import net.nc.neocatlib.NeoCatLib;

public class PacketHandler {

    public static final SimpleChannel INSTANCE = ChannelBuilder.named(
            new ResourceLocation(NeoCatLib.MODID, "main"))
            .serverAcceptedVersions(((status, version) -> true))
            .clientAcceptedVersions(((status, version) -> true))
            .networkProtocolVersion(1)
            .simpleChannel();

    public static void register()
    {
        INSTANCE.messageBuilder(CSendGuiMsgPacket.class, NetworkDirection.PLAY_TO_CLIENT)
                .encoder(CSendGuiMsgPacket::encode)
                .decoder(CSendGuiMsgPacket::new)
                .consumerMainThread(CSendGuiMsgPacket::handle)
                .add();
    }

    public static void sendToPlr(Object msg, ServerPlayer plr)
    {
        NeoCatLib.LOGGER.info("Sending packet to player!");
        INSTANCE.send(msg, PacketDistributor.PLAYER.with(plr));
    }

}
