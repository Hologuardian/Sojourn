package holo.sojourn.glyph;


import java.util.Random;

public class GlyphMap
{
    public int[][] map;
    public Random rand = new Random();
    
    public GlyphMap()
    {
        map = new int[2][2];
        generateNewMap(rand);
    }

    public GlyphMap(String seed)
    {
        map = new int[2][2];
        rand.setSeed(seed.hashCode());
        generateNewMap(rand);
    }
    
    public void generateNewMap(Random rand)
    {
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                map[i][j] = rand.nextInt(64);
    }
    
    public boolean sameGlyph(GlyphMap otherGlyph)
    {
        return map.equals(otherGlyph.map);
    }
}