package holo.sojourn.client.render.glyph;

import holo.sojourn.glyph.GlyphManager;
import holo.sojourn.glyph.GlyphMap;
import holo.sojourn.util.Textures;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderGlyph extends Gui
{
    public GlyphMap glyph;
    public static final Minecraft mc = Minecraft.getMinecraft();
    
    public RenderGlyph()
    {
        this.glyph = GlyphManager.glyphs().getRandomGlyph();
    }
    
    public GlyphMap getGlyph()
    {
        return this.glyph;
    }
    
    public void drawGlyph(int x, int y, float scale, int color)
    {
        GlyphMap glyph = this.getGlyph();

        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        GL11.glColor3f(((float)(color >> 16 & 255) / 255.0F), (float)(color >> 8 & 255) / 255.0F, (float)(color & 255) / 255.0F);
        mc.getTextureManager().bindTexture(Textures.glyphTex);
        for (int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                this.drawTexturedModalRect(x + i * 32, y + j * 32, (glyph.map[i][j] % 8) * 32, (int) Math.floor(glyph.map[i][j] / 8) * 32, 32, 32);
            }
        }

        GL11.glColor3f(1F, 1F, 1F);
        GL11.glPopMatrix();
    }
}
