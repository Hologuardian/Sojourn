package holo.sojourn.world.aracoria.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MapGenEssenceSpike extends WorldGenerator
{
    /**
     * The Block ID that the generator is allowed to replace while generating the terrain.
     */
    private int replaceID;
    private int replaceID1;

    public MapGenEssenceSpike(int par1, int par2)
    {
        this.replaceID = par1;
        this.replaceID1 = par2;
    }

    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        int tempID = world.getBlockId(x, y, z);
        if ((tempID == this.replaceID || tempID == this.replaceID1) || world.isAirBlock(x, y, z))
        {
            int l = rand.nextInt(7) + 16;
            float i1 = 1F;
            float j1;
            float k1;
            float l1;
            float xS = 0;
            float zS = 0;
            float[] dir = {rand.nextFloat() - 0.5F, rand.nextFloat() - 0.5F};
            
            dir[0] += Math.signum(dir[0]) * 0.2F;
            dir[1] += Math.signum(dir[1]) * 0.2F;
            
            for (j1 = y; j1 < y + l && j1 < 256; ++j1)
            {
                xS += dir[0];
                zS += dir[1];
                i1 = y + l - j1 < 4 ? 0.0F : i1;
                
                for (k1 = x - i1 + xS; k1 <= x + i1 + xS; ++k1)
                {
                    for (l1 = z - i1 + zS; l1 <= z + i1 + zS; ++l1)
                    {
                        float i2 = k1 - x - xS;
                        float j2 = l1 - z - zS;

                        if (i2 * i2 + j2 * j2 <= i1 * i1 + rand.nextFloat())
                        {
                            world.setBlock((int)k1, (int)j1, (int)l1, Block.glowStone.blockID, 0, 2);
                        }
                    }
                }
            }
            return true;
        }
        else
        {
            return false;
        }
    }
}
