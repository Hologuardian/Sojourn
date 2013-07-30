package holo.sojourn.world.moltar;

import holo.sojourn.helper.SojournDimensionRegistry;
import holo.sojourn.world.base.BaseChunkManager;
import holo.sojourn.world.base.BaseWorldType;
import net.minecraft.util.MathHelper;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MoltarWorldProvider extends WorldProvider
{
    public BaseWorldType type;
    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
        this.hasNoSky = true;
        
        type = new BaseWorldType(0, "Moltar");
        type.removeAllBiomes();
        
        type.addNewBiome(SojournDimensionRegistry.moltarValleyBiome);
        type.addNewBiome(SojournDimensionRegistry.moltarBiome);
        
        type.addBiomeTransition(SojournDimensionRegistry.moltarValleyBiome, SojournDimensionRegistry.moltarBiome, SojournDimensionRegistry.moltarValleyBiome);
        
        type.setWaterSnowHeight(152, 257);
        type.setBiomeSize(1);
        
        this.worldChunkMgr = new BaseChunkManager(this.worldObj.getSeed(), type);
    }

    /**
     * Calculates the angle of sun and moon in the sky relative to a specified time (usually worldTime)
     */
    public float calculateCelestialAngle(long par1, float par3)
    {
        return -0.5F;
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Returns a double value representing the Y value relative to the top of the map at which void fog is at its
     * maximum. The default factor of 0.03125 relative to 256, for example, means the void fog will be at its maximum at
     * (256*0.03125), or 8.
     */
    public double getVoidFogYFactor()
    {
        return 0.03125;
    }

    /**
     * Returns a new chunk provider which generates chunks for this world
     */
    public IChunkProvider createChunkGenerator()
    {
        return new MoltarChunkProvider(this.worldObj, this.worldObj.getSeed(), false, type);
    }

    /**
     * Returns 'true' if in the "main surface world", but 'false' if in the Nether or End dimensions.
     */
    public boolean isSurfaceWorld()
    {
        return false;
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
        return "Moltar";
    }
}
