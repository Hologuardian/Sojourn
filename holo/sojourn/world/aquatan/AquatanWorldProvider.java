package holo.sojourn.world.aquatan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import holo.sojourn.helper.SojournDimensionRegistry;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;

public class AquatanWorldProvider extends WorldProvider
{
    /**
     * creates a new world chunk manager for WorldProvider
     */
    public void registerWorldChunkManager()
    {
//        WorldType type;
//        type = new WorldType(0, "Aracoria");
//        for (BiomeGenBase biome : type.base12Biomes)
//        {
//            type.removeBiome(biome);
//        }
//        type.addNewBiome(SojournDimensionRegistry.aracoriaBiome);
//        type.addNewBiome(SojournDimensionRegistry.aracoriaBiome1);
//        type.addNewBiome(SojournDimensionRegistry.aracoriaBiome2);
//        this.worldChunkMgr = new WorldChunkManager(this.worldObj.getSeed(), type);
//        
        this.worldChunkMgr = new WorldChunkManagerHell(SojournDimensionRegistry.aquatanBiome, 1.0F, 1.0F);
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
        return new AquatanChunkProvider(this.worldObj, this.worldObj.getSeed(), false);
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
        return "Aracoria";
    }
}
