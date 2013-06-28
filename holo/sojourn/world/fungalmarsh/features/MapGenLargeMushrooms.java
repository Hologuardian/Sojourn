package holo.sojourn.world.fungalmarsh.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class MapGenLargeMushrooms extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int i1 = random.nextInt(3) + 6;
        int j1 = random.nextInt(5) + 7;
        int l = 1;
        
//        for (int j = y; j < y + j1 && j < 256; j++)
//        {
//            if (j >= y + j1 - 2)
//            {
//                for (int i = x - i1; i < x + i1; i++)
//                {
//                    for (int k = z - i1; k < z + i1; k++)
//                    {
//                        if (!world.isAirBlock(i, j, k))
//                            return false;
//                    }
//                }
//            }
//            else
//            {
//                for (int i = x ; i < x + l; i++)
//                {
//                    for (int k = z; k < z + l; k++)
//                    {
//                        if (!world.isAirBlock(i, j, k))
//                            return false;
//                    }
//                }
//            }
//        }
        
        for (int j = y; j < y + j1 && j < 256; j++)
        {
            if (j >= y + j1 - 2)
            {
                for (int i = x - i1; i <= x + i1; i++)
                {
                    for (int k = z - i1; k <= z + i1; k++)
                    {
                        int a = i - x;
                        int b = k - z;
                        if (j == y + j1 - 1)
                        {
                            if (a * a + b * b <= i1 * i1 + 1)
                            {
                                setBlock(world, i, j, k, Block.cobblestone.blockID);
                            }
                        }
                        else
                        {
                            int c = i1 + 1;
                            float c1 = i1 - 0.5F;
                            if (a * a + b * b <= c * c + 1 && a * a + b * b >= c1 * c1 + 1)
                            {
                                setBlock(world, i, j, k, Block.cobblestone.blockID);
                            } 
                        }
                        

                    }
                }
            }
            
            for (int i = x - l; i <= x + l; i++)
            {
                for (int k = z - l; k <= z + l; k++)
                {
                    setBlock(world, i, j, k, Block.cobblestone.blockID);
                }
            }
        }
        
        return true;
    }
    
}
