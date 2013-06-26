package holo.sojourn.world.aracoria.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GlowingSpikes extends WorldGenerator
{
    /**
     * The Block ID that the generator is allowed to replace while generating the terrain.
     */
    private int replaceID;
    private int replaceID1;

    public GlowingSpikes(int par1, int par2)
    {
        this.replaceID = par1;
        this.replaceID1 = par2;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int tempID = par1World.getBlockId(par3, par4, par5);
        if ((tempID == this.replaceID || tempID == this.replaceID1))
        {
            int l = par2Random.nextInt(16) + 25;
            int i1 = 1;
            int j1;
            int k1;
            int l1;
            int i2;

            for (j1 = par4; j1 < par4 + l && j1 < 128; ++j1)
            {
                for (k1 = par3 - i1; k1 <= par3 + i1; ++k1)
                {
                    for (l1 = par5 - i1; l1 <= par5 + i1; ++l1)
                    {
                        i2 = k1 - par3;
                        int j2 = l1 - par5;

                        if (i2 * i2 + j2 * j2 <= i1 * i1 + par2Random.nextInt(2))
                        {
                            par1World.setBlock(k1, j1, l1, Block.glowStone.blockID, 0, 2);
                        }
                    }
                }
            }
//            System.out.println();

//            EntityEnderCrystal entityendercrystal = new EntityEnderCrystal(par1World);
//            entityendercrystal.setLocationAndAngles((double)((float)par3 + 0.5F), (double)(par4 + l), (double)((float)par5 + 0.5F), par2Random.nextFloat() * 360.0F, 0.0F);
//            par1World.spawnEntityInWorld(entityendercrystal);
//            par1World.setBlock(par3, par4 + l, par5, Block.bedrock.blockID, 0, 2);
            return true;
        }
        else
        {
            return false;
        }
    }
}
