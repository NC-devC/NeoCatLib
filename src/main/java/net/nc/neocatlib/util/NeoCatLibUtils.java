package net.nc.neocatlib.util;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.nc.neocatlib.NeoCatLib;
import net.nc.neocatlib.network.CSendGuiMsgPacket;
import net.nc.neocatlib.network.PacketHandler;

public class NeoCatLibUtils {

    // Utils for chat

    // Send typical message
    public static void sendMessageToPlayer(String msg, Player plr)
    {
        plr.sendSystemMessage(Component.literal(msg));
    }

    // Send localizable message
    public static void sendTranslatedMessageToPlayer(String key, Player plr)
    {
        plr.sendSystemMessage(Component.translatable(key));
    }

    // GUI utils

    // Returns decimal colour.
    // If you need to use alpha channel then you can use this function.
    public static int RGBToDecimal(int r, int g, int b, int a)
    {
        return (a << 24) + (r << 16) + (g << 8) + b;
    }

    // If you don't need to use alpha channel you can simply use this function.
    public static int RGBToDecimal(int r, int g, int b)
    {
        int a = 255;
        return (a << 24) + (r << 16) + (g << 8) + b;
    }

    // Creates basic message that appears and disappears (ClientSide)
    public static void createGUIMessage(String msg)
    {
        NeoCatLib.currentTexter.writeSomething(msg);
    }

    // Sends a basic message that appears and disappears (ServerSide)
    public static void sendGUIMessage(String msg, ServerPlayer plr)
    {
        PacketHandler.sendToPlr(new CSendGuiMsgPacket(msg), plr);
    }

}
