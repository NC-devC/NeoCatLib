package net.nc.neocatlib;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nc.neocatlib.client.gui.overlays.TexterUI;
import org.slf4j.Logger;

@Mod(NeoCatLib.MODID)
public class NeoCatLib
{
    public static final String MODID = "neocatlib";
    public static final Logger LOGGER = LogUtils.getLogger();
    @OnlyIn(Dist.CLIENT) public static TexterUI currentTexter;

    public NeoCatLib()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
    }
}
