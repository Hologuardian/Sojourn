package holo.sojourn.client.render.hud;

import holo.sojourn.essence.EssenceBar;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EssenceBarIcon extends Gui
{

    public final Minecraft mc = Minecraft.getMinecraft();
    
    public EssenceBarIcon(EntityPlayer player)
    {
    }
    
    public void renderIcon(EntityPlayer player)
    {
        if (mc.currentScreen == null || !mc.currentScreen.doesGuiPauseGame() && EssenceBar.bars().hasPlayer(player))
        {
            ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
            int k = scaledresolution.getScaledWidth();
            int l = scaledresolution.getScaledHeight();
            renderBar(player, k - 5, l - 5, 1F, EssenceBar.bars().getScaledEssences(player));
        }
    }
    
    public void renderBar(EntityPlayer player, int posX, int posY, float scale, float[] essences)
    {
        this.mc.entityRenderer.setupOverlayRendering();
        this.mc.renderEngine.bindTexture("/res/texture/essenceBar.png");
        GL11.glEnable(GL11.GL_BLEND);
        
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        int x = posX;
        int x1;
        int y = posY - 16;
        int y1 = 16;
        for (int i = 4; i >= 0; i--)
        {
            float val = essences[i];
            x1 = (int) (val);
            this.drawTexturedModalRect(x - x1, y, 0, 16 * (4 - i) + 1, x1, y1);
            x -= x1;
        }
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);
        
    }
    
    public int essenceColor(int essence)
    {
        switch (essence)
        {
            case 0:
                return 16777216;
            case 1:
                return 16777216;
            case 2:
                return 16777216;
            case 3:
                return 16777216;
            case 4:
                return 16777216;
        }
        
        return 0;
    }
}
