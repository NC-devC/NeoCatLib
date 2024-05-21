package net.nc.neocatlib.server;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nc.neocatlib.NeoCatLib;
import net.nc.neocatlib.util.NeoCatLibUtils;

public class ServerEventHandler {

    @Mod.EventBusSubscriber(modid = NeoCatLib.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
    public class ForgeServerEventHandler
    {

        @SubscribeEvent
        public static void onChatted(ServerChatEvent event)
        {
            NeoCatLibUtils.sendMessageToPlayer("Hello!", event.getPlayer());
            NeoCatLibUtils.sendGUIMessage("Hey", event.getPlayer());
        }

    }

    @Mod.EventBusSubscriber(modid = NeoCatLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public class ModServerEventHandler
    {

    }

}
