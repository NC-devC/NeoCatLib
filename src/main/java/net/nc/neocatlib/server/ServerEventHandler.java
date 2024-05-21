package net.nc.neocatlib.server;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.nc.neocatlib.NeoCatLib;

public class ServerEventHandler {

    @Mod.EventBusSubscriber(modid = NeoCatLib.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.DEDICATED_SERVER)
    public class ForgeServerEventHandler
    {
        /*
        Usage example.
        @SubscribeEvent
        public static void onChatted(ServerChatEvent event)
        {
            NeoCatLibUtils.sendMessageToPlayer("Hello!", event.getPlayer());
            NeoCatLibUtils.sendGUIMessage("Hey", event.getPlayer());
        }
        */
    }

    @Mod.EventBusSubscriber(modid = NeoCatLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
    public class ModServerEventHandler
    {

    }

}
