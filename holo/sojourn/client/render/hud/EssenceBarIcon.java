package holo.sojourn.client.render.hud;

import holo.sojourn.essence.EssenceBar;
import holo.sojourn.util.Textures;
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
    
    public void renderIcon(EntityPlayer player)
    {
        if (EssenceBar.bars().hasPlayer(player) && mc.currentScreen == null)
        {
            ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
            int k = scaledresolution.getScaledWidth();
            int l = scaledresolution.getScaledHeight();
            renderBar(player, k - 2, l, 1F, EssenceBar.bars().getScaledEssences(player));
        }
    }
    
    public void renderIcon(EntityPlayer player, int x, int y, float scale)
    {
        if (EssenceBar.bars().hasPlayer(player))
        {
            renderBar(player, x, y, scale, EssenceBar.bars().getScaledEssences(player));
        }
    }
    
    public void renderBar(EntityPlayer player, int posX, int posY, float scale, float[] essences)
    {

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        mc.getTextureManager().bindTexture(Textures.essenceBar);
        
        int x = posX;
        int x1 = 0;
        int y = posY - 22;
        int h = 22;
        int u = 256;

        this.drawTexturedModalRect(x, y, 0, 0, 3, h);
        for (int i = 4; i >= 0; i--)
        {
            float val = essences[i];
            x1 = (int) (val);
            x -= x1;
            u -= x1;
            this.drawTexturedModalRect(x, y, u, h * (4 - i), x1, h);
        }
        x -= 3;
        this.drawTexturedModalRect(x, y, 3, 0, 3, h);
        
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);
    }
}
