package holo.sojourn.glyph;


public class GlyphManager
{
    private static GlyphManager glyphManager = new GlyphManager();

    public static GlyphManager glyphs()
    {
        return glyphManager ;
    }

    public GlyphMap getRandomGlyph()
    {
        GlyphMap glyph = new GlyphMap();
        return glyph;
    }
    
    public GlyphMap getPlayerGlyph(String username)
    {
        return new GlyphMap(username);
    }
}
