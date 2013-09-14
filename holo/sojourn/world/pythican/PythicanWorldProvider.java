package holo.sojourn.world.pythican;

import holo.sojourn.helper.SojournDimensionRegistry;
import holo.sojourn.world.pythican.feature.MapGenMassiveCanopyTree;
import holo.utils.world.BaseChunkManager;
import holo.utils.world.BaseChunkProvider;
import holo.utils.world.BaseWorldType;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PythicanWorldProvider extends WorldProvider
{
    public BaseWorldType type;
    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
        type = new BaseWorldType(0, "Pythican Jungle");
        for (BiomeGenBase biome : type.base12Biomes)
        {
            type.removeBiome(biome);
        }
        type.addNewBiome(SojournDimensionRegistry.pythicanBiome);
        
        type.setWaterSnowHeight(34, 256);
        type.setBiomeSize(4);
        type.setFillBlock(Block.dirt.blockID);
        
        type.caveGen = null;
        type.ravineGen = null;
        
        type.addMapGenFeature(new MapGenMassiveCanopyTree(2, 3, 165, 9, 120, 32));
        
        this.worldChunkMgr = new BaseChunkManager(this.worldObj.getSeed(), type);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Return Vec3D with biome specific fog color
     */
    public Vec3 getFogColor(float par1, float par2)
    {
        float f2 = MathHelper.cos(par1 * (float)Math.PI * 2.0F) * 2.0F - 0.5F;

        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float f3 = 0.0F;
        float f4 = 0.05F;
        float f5 = 0.0F;
        f3 *= f2 * 0.0F + 0.01F;
        f4 *= f2 * 0.2F + 0.01F;
        f5 *= f2 * 0.0F + 0.01F;
        return this.worldObj.getWorldVec3Pool().getVecFromPool((double)f3, (double)f4, (double)f5);
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * the y level at which clouds are rendered.
     */
    public float getCloudHeight()
    {
        return 255.0F;
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator()
    {
        return new BaseChunkProvider(this.worldObj, this.worldObj.getSeed(), false, type);
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return true;
    }

    /**
     * Will check if the x, z position specified is alright to be set as the map spawn point
     */
    public boolean canCoordinateBeSpawn(int par1, int par2)
    {
        return false;
    }

    /**
     * True if the player can respawn in this dimension (true = overworld, false = nether).
     */
    public boolean canRespawnHere()
    {
        return false;
    }

    /**
     * Returns the dimension's name, e.g. "The End", "Nether", or "Overworld".
     */
    public String getDimensionName()
    {
        return "Pythican Jungle";
    } 
    
    /**
     * Returns the sub-folder of the world folder that this WorldProvider saves to.
     * EXA: DIM1, DIM-1
     * @return The sub-folder name to save this world's chunks to.
     */
    public String getSaveFolder()
    {
        return getDimensionName();
    }
}
