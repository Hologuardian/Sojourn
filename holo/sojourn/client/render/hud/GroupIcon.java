package holo.sojourn.client.render.hud;

import holo.sojourn.client.render.glyph.PlayerGlyphIcon;
import holo.sojourn.essence.EssenceBar;
import holo.sojourn.group.Group;
import holo.sojourn.util.Textures;

import java.util.Iterator;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.ForgeHooks;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GroupIcon extends Gui
{
    public final Minecraft mc = Minecraft.getMinecraft();
    private int updateCounter;
    private Random rand = new Random();


    @SideOnly(Side.CLIENT)
    public void renderIcon(Group group)
    {
        int i = 0;
        for (String name : group.getList())
        {
        	AbstractClientPlayer p = null;
            p = (AbstractClientPlayer) mc.theWorld.getPlayerEntityByName(name);
            
            drawPlayerOnGui(p, 20, 50 + 50 * i, 20, 0, 0);
            drawPlayerHealthAndArmor(p, 60, 85 + 103 * i, 4.35F);
            new EssenceBarIcon().renderBar(p, 194, 116 + 142 * i, 0.35F, EssenceBar.bars().getScaledEssences(p));
            new PlayerGlyphIcon(p.username).drawGlyph(160, 50 + 248 * i, 0.2F, 0x901A9E);
            Iterator iterator = null;
            if (group.potionMap.containsKey(p.username))
            {
                iterator  = group.potionMap.get(p.username).iterator();
            }

//            mc.renderEngine.bindTexture("/gui/inventory.png");
            mc.getTextureManager().bindTexture(Textures.playerInventory);
            int j = 0;      

//            System.out.println(p.username + " " + i + " " + j + " " + p.getActivePotionEffects().toString());
            while (iterator != null && iterator.hasNext())
            {
                int id = (int) iterator.next();
                drawStatusIcon(60 + j * 18, 50 + 100 * i, 0.5F, 0 + Potion.potionTypes[id].getStatusIconIndex() % 8 * 18, 198 + Potion.potionTypes[id].getStatusIconIndex() / 8 * 18);
                j++;
//                System.out.println(p.username + " " + i + " " + j);
            }
            i++;
        }
        ++this.updateCounter;
    }

    @SideOnly(Side.CLIENT)
    public void drawStatusIcon(int x, int y, float scale, int u, int v)
    {
        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        this.drawTexturedModalRect(x + 2, y + 2, u, v, 18, 18);
        GL11.glPopMatrix();
    }

    @SideOnly(Side.CLIENT)
    public void drawPlayerOnGui(AbstractClientPlayer thePlayer, int par1, int par2, int par3, float par4, float par5)
    {
        GL11.glEnable(GL11.GL_COLOR_MATERIAL);
        GL11.glPushMatrix();
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        mc.fontRenderer.drawStringWithShadow(thePlayer.username, par1 - 10, par2 - 40, 255 << 16 | 255 << 8 | 255);
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        GL11.glTranslatef(par1, par2, 50.0F);
        GL11.glScalef((-par3), par3, par3);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f2 = thePlayer.renderYawOffset;
        float f3 = thePlayer.rotationYaw;
        float f4 = thePlayer.rotationPitch;
        float f5 = thePlayer.rotationYawHead;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-((float)Math.atan(par5 / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
        thePlayer.renderYawOffset = -20;
        thePlayer.rotationYaw = 0;
        thePlayer.rotationPitch = 0;
        thePlayer.rotationYawHead = -20;
        GL11.glTranslatef(0.0F, thePlayer.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        RenderManager.instance.playerViewX = 90F;
        RenderManager.instance.renderEntityWithPosYaw(thePlayer, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        thePlayer.renderYawOffset = f2;
        thePlayer.rotationYaw = f3;
        thePlayer.rotationPitch = f4;
        thePlayer.rotationYawHead = f5;
        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    @SideOnly(Side.CLIENT)
    public void drawPlayerHealthAndArmor(EntityPlayer player, int xOffset, int yOffset, float targetSize)
    {        
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glPushMatrix();
        GL11.glScalef(targetSize / 9F, targetSize / 9F, targetSize / 9F);

        boolean flag1;
        float i1;
        float j1;
        int i2;
        int k2;
        byte b0;
        int i3;
        int j3;
        int k3;

        this.mc.getTextureManager().bindTexture(Gui.icons);
        flag1 = player.hurtResistantTime / 3 % 2 == 1;

        if (player.hurtResistantTime < 10)
        {
            flag1 = false;
        }

        i1 = player.getHealth();
        j1 = player.prevHealth;
        this.rand.setSeed(this.updateCounter * 312871);

        int j4;
        int k4;
        int l4;
        i2 = xOffset;

        k3 = yOffset;
        k2 = ForgeHooks.getTotalArmorValue(player);
        i3 = -1;

        for (j4 = 0; j4 < 10; ++j4)
        {
            if (player.isPotionActive(Potion.regeneration))
            {
                i3 = this.updateCounter % 150 / 5;
            }

            j3 = 16;

            if (player.isPotionActive(Potion.poison))
            {
                j3 += 36;
            }
            else if (player.isPotionActive(Potion.wither))
            {
                j3 += 72;
            }

            l4 = i2 + j4 * 8;
            k4 = k3;

            if (i1 <= 4)
            {
                k4 = k3 + this.rand.nextInt(2);
            }

            if (j4 == i3)
            {
                k4 -= 2;
            }

            b0 = 0;

            if (flag1)
            {
                b0 = 1;
            }

            byte b1 = 0;

            if (this.mc.theWorld.getWorldInfo().isHardcoreModeEnabled())
            {
                b1 = 5;
            }

            if (k2 > 0)
            {

                if (j4 * 2 + 1 < k2)
                {
                    this.drawTexturedModalRect(l4, k3 + 9, 34, 9, 9, 9);
                }

                if (j4 * 2 + 1 == k2)
                {
                    this.drawTexturedModalRect(l4, k3 + 9, 25, 9, 9, 9);
                }

                if (j4 * 2 + 1 > k2)
                {
                    this.drawTexturedModalRect(l4, k3 + 9, 16, 9, 9, 9);
                }
            }

            this.drawTexturedModalRect(l4, k4, 16 + b0 * 9, 9 * b1, 9, 9);

            if (flag1)
            {
                if (j4 * 2 + 1 < j1)
                {
                    this.drawTexturedModalRect(l4, k4, j3 + 60, 9 * b1, 9, 9);
                }

                if (j4 * 2 + 1 == j1)
                {
                    this.drawTexturedModalRect(l4, k4, j3 + 63, 9 * b1, 9, 9);
                }
            }

            if (j4 * 2 + 1 < i1)
            {
                this.drawTexturedModalRect(l4, k4, j3 + 36, 9 * b1, 9, 9);
            }

            if (j4 * 2 + 1 == i1)
            {
                this.drawTexturedModalRect(l4, k4, j3 + 45, 9 * b1, 9, 9);
            }
        }
        GL11.glPopMatrix();
    }
}
