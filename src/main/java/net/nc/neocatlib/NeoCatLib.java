package net.nc.neocatlib;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.nc.neocatlib.client.gui.overlays.TexterUI;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NeoCatLib.MODID)
public class NeoCatLib
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "neocatlib";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();
    public static TexterUI currentTexter;
    public NeoCatLib()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us

        // I don't need this currently
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
