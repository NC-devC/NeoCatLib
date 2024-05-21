package net.nc.neocatlib.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.nc.neocatlib.NeoCatLib;
import net.nc.neocatlib.client.gui.overlays.TexterUI;

public class ClientEventHandler {

    @Mod.EventBusSubscriber(modid = NeoCatLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ModClientEventHandler
    {

        @SubscribeEvent
        public static void onOverlayRegister(RegisterGuiOverlaysEvent event)
        {
            TexterUI myTexter = new TexterUI();
            myTexter.seconds = 0;
            NeoCatLib.currentTexter = myTexter;
            event.registerAboveAll("ncltexter", myTexter);
        }

    }

}
