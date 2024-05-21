package net.nc.neocatlib.client.gui.overlays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.nc.neocatlib.NeoCatLib;
import net.nc.neocatlib.util.NeoCatLibUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class TextMsgShowData
{
    public String msg;
    public UUID id;
    public boolean arrived = false;
    public boolean leaving = false;
    public int ticksAfterArriving = 0;
    public int arrivingTicks = 0;
    public int xOffset = 0;
    public int yOffset = 0;
    public int coolestX;
    public int coolestY;
    public boolean shouldDestroy = false;
    public void ChangeText(String newMsg)
    {
        this.msg = newMsg;
    }

    public TextMsgShowData(String msg)
    {
        this.msg = msg;
        this.id = UUID.randomUUID();
        this.arrived = false;
        this.xOffset = -100;
        int width  = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.coolestX = (width / 11) - 35;
        int currentI = NeoCatLib.currentTexter.msgs.toArray().length+1;
        this.coolestY = (height / 11) - 20 + (currentI * 10);
    }
}

public class TexterUI implements IGuiOverlay {
    public Minecraft mc;
    public Font font;
    public int ticks;
    public int seconds;
    public int additionPerDraw = 1;

    public List<TextMsgShowData> msgs = new ArrayList<TextMsgShowData>();

    public void writeSomething(String msg)
    {
        String m = msg;
        msgs.add(new TextMsgShowData(m));
    }

    @Override
    public void render(ForgeGui forgeGui, GuiGraphics guiGraphics, float v, int i, int i1) {
        if(mc == null)
            mc = forgeGui.getMinecraft();
        if(font == null)
            font = forgeGui.getFont();

        int width  = mc.getWindow().getGuiScaledWidth();
        int height = mc.getWindow().getGuiScaledHeight();
        ticks+=1;
        if(ticks == 40)
        {
            seconds +=1;
            ticks = 0;
        }

        int currentI = -1;
        for (TextMsgShowData msg : msgs) {
            currentI += 1;

            int x = msg.coolestX;

            x += msg.xOffset;

            int y = msg.coolestY;

            y += msg.yOffset;

            if (!msg.arrived) {
                if (msg.xOffset == 0) {
                    msg.arrived = true;
                } else {
                    msg.xOffset += additionPerDraw;
                    msg.arrivingTicks += 1;
                }
            }
            else
            {
                msg.ticksAfterArriving+=1;
                if(!msg.leaving) {
                    if(msg.ticksAfterArriving >= 120)
                    {
                        msg.leaving = true;
                    }
                }
                else
                {
                    msg.yOffset -= additionPerDraw;
                    if((msg.yOffset <= -100 - (currentI * 10)) || (msg.shouldDestroy))
                    {
                        msgs.remove(msg);
                    }
                }
            }

            int alpha = 0;

            if(!msg.arrived)
            {
                alpha += (msg.arrivingTicks/3) * additionPerDraw * 10;
            }
            else
            {
                if(msg.leaving)
                {
                    alpha = 255 - (((msg.ticksAfterArriving - 120)/3) * additionPerDraw * 15);
                }
                else
                {
                    alpha = 255;
                }
            }

            if(alpha > 255) {alpha = 255;}
            if(msg.arrived)
            {
                if(alpha < 0)
                {
                    alpha = 0;
                }
                if(alpha == 0)
                {
                    msg.shouldDestroy = true;
                }
            }
            else
            {
                if(alpha <= 0) {alpha = 5;}
            }

            int colour = NeoCatLibUtils.RGBToDecimal(255,255,255,alpha);

            guiGraphics.drawString(mc.font, msg.msg, x, y, colour);
        }
    }
}

