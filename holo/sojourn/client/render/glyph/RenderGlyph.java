package holo.sojourn.client.render.glyph;

import holo.sojourn.glyph.GlyphManager;
import holo.sojourn.glyph.GlyphMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;

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
    
    public void drawGlyph(int x, int y, float scale)
    {
        GlyphMap glyph = this.getGlyph();
        GL11.glEnable(GL11.GL_BLEND);

        GL11.glPushMatrix();
        GL11.glScalef(scale, scale, scale);
        GL11.glColor3f(1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture("/res/texture/glyphs.png");
        for (int i = 0; i < 2; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                this.drawTexturedModalRect(x + i * 32, y + j * 32, (glyph.map[i][j] % 8) * 32, (int) Math.floor(glyph.map[i][j] / 8) * 32, 32, 32);
            }
        }
        
        GL11.glPopMatrix();
    }
}
