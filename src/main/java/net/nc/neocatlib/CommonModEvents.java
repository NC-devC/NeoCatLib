package net.nc.neocatlib;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.nc.neocatlib.network.PacketHandler;

@Mod.EventBusSubscriber(modid = NeoCatLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonModEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event)
    {
        event.enqueueWork(() -> {
            PacketHandler.register();
        });
    }

}
