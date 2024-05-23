package net.nc.neocatlib;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.server.command.ConfigCommand;
import net.nc.neocatlib.network.PacketHandler;
import net.nc.neocatlib.server.commands.SendGUIMsgCommand;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = NeoCatLib.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public class CommonModEvents {

        @SubscribeEvent
        public static void commonSetup(FMLCommonSetupEvent event) {
            event.enqueueWork(() -> {
                PacketHandler.register();
            });
        }

    }

    @Mod.EventBusSubscriber(modid = NeoCatLib.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public class ForgeModEvents
    {

        @SubscribeEvent
        public static void commandRegister(RegisterCommandsEvent event)
        {
            new SendGUIMsgCommand(event.getDispatcher());

            ConfigCommand.register(event.getDispatcher());
        }

    }

}
