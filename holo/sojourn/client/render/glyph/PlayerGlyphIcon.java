package holo.sojourn.client.render.glyph;

import holo.sojourn.glyph.GlyphManager;
import holo.sojourn.glyph.GlyphMap;


public class PlayerGlyphIcon extends RenderGlyph
{
    public GlyphMap glyph;
    int tickTimer = 0;

    public PlayerGlyphIcon(String username)
    {
        this.glyph = GlyphManager.glyphs().getPlayerGlyph(username);
    }
    
    public GlyphMap getGlyph()
    {
        return this.glyph;
    }
}
