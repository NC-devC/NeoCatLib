package net.nc.neocatlib.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.nc.neocatlib.NeoCatLib;
import net.nc.neocatlib.util.NeoCatLibUtils;

public class CSendGuiMsgPacket {
    private final String msg;

    public CSendGuiMsgPacket(String msg)
    {
        this.msg = msg;
    }

    public CSendGuiMsgPacket(FriendlyByteBuf friendlyByteBuf) {
        this(friendlyByteBuf.readUtf());
        NeoCatLib.LOGGER.info("Decoder");
    }

    public void encode(FriendlyByteBuf buffer)
    {
        NeoCatLib.LOGGER.info("Encoder");
        buffer.writeUtf(msg);
    }

    public void handle(CustomPayloadEvent.Context context)
    {
        if(context.isClientSide())
        {
            NeoCatLib.LOGGER.info("Clientside packet");
            NeoCatLibUtils.createGUIMessage(msg);
        }
        else {
            NeoCatLib.LOGGER.info("Failed packet");
            context.setPacketHandled(false);
        }
    }

}
