package holo.sojourn.world.frigidpeaks;

import holo.sojourn.helper.SojournDimensionRegistry;
import holo.utils.world.BaseChunkManager;
import holo.utils.world.BaseChunkProvider;
import holo.utils.world.BaseWorldType;
import net.minecraft.block.Block;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FrigidPeaksWorldProvider extends WorldProvider
{
    public BaseWorldType type;
    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
        type = new BaseWorldType(0, "Frigid Peaks");
        for (BiomeGenBase biome : type.base12Biomes)
        {
            type.removeBiome(biome);
        }
        type.addNewBiome(SojournDimensionRegistry.frigidPeaksBiome);
        
        type.setWaterSnowHeight(127, 256);
        type.setBiomeSize(4);
        type.setFillBlock(Block.blockSnow.blockID);
        type.setRiverBiome(BiomeGenBase.river);
        type.caveGen = null;
        type.ravineGen = null;
        this.worldChunkMgr = new BaseChunkManager(this.worldObj.getSeed(), type);

        this.hasNoSky = true;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Returns array with sunrise/sunset colors
     */
    public float[] calcSunriseSunsetColors(float par1, float par2)
    {
        return new float[]{0, 0, 0, 0};
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long par1, float par2)
    {
    	int j = (int)(par1 % 24000L);
        float f1 = ((float)j + par2) / 24000.0F - 0.25F;

        if (f1 < 0.0F)
        {
            ++f1;
        }

        if (f1 > 1.0F)
        {
            --f1;
        }

        float f2 = f1;
        f1 = 1.0F - (float)((Math.cos((double)f1 * Math.PI) + 1.0D) / 2.0D);
        f1 = ((f2 + (f1 - f2) / 3.0F) / 2.5F) + 0.3F;
        return f1;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * the y level at which clouds are rendered.
     */
    public float getCloudHeight()
    {
        return 192.0F;
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
        return "Frigid Peaks";
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
