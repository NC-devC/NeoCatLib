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
    public void ChangeText(String newMsg)
    {
        this.msg = newMsg;
    }

    public TextMsgShowData(String msg)
    {
        this.msg = msg;
        this.id = UUID.randomUUID();
        this.arrived = false;
        this.xOffset = -105;
        int width  = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int height = Minecraft.getInstance().getWindow().getGuiScaledHeight();
        this.coolestX = (width / 11) - 35;
        int niceY = (10 * NeoCatLib.currentTexter.msgs.toArray().length) + 10;
        this.coolestY = (height / 11) - 20 + (niceY);
    }
}

public class TexterUI implements IGuiOverlay {
    public Minecraft mc;
    public Font font;
    public int ticks;
    public int seconds;
    public int additionPerDraw = 1;
    private int canAppearAtY = 0;

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

            int alpha = 0;

            if (!msg.arrived) {
                if (msg.xOffset == 0) {
                    msg.arrived = true;
                } else {
                    alpha += (msg.arrivingTicks/3) * additionPerDraw * 10;
                    if(alpha > 255) {alpha = 255;}
                    if(alpha < 0) {alpha = 0;}

                    msg.xOffset += additionPerDraw;
                    msg.arrivingTicks += 1;
                }
            }
            else
            {
                if(!msg.leaving) {
                    msg.ticksAfterArriving += 1;
                    if (msg.ticksAfterArriving >= 120) {
                        msg.ticksAfterArriving = 120;
                        msg.leaving = true;
                    }
                }
                else
                {
                    msg.ticksAfterArriving+=1;
                    msg.yOffset -= additionPerDraw;
                    alpha = 255 - (((msg.ticksAfterArriving - 120)/3) * additionPerDraw * 15);
                    if(alpha > 255) {alpha = 255;}
                    if(alpha < 0) {alpha = 0;}
                    if(alpha <= 0)
                    {
                        msgs.remove(msg);
                    }
                }
            }

            int colour = NeoCatLibUtils.RGBToDecimal(255,255,255,alpha);

            guiGraphics.drawString(mc.font, msg.msg, x, y, colour);
        }
    }
}

