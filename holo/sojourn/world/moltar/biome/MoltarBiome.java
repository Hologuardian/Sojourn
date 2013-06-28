package holo.sojourn.world.moltar.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class MoltarBiome extends BiomeGenBase
{
    public MoltarBiome(int par1)
    {
        super(par1);
        this.setBiomeName("Moltar");
        this.minHeight = 2.8F;
        this.maxHeight = 2.8F;
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 0;
        this.theBiomeDecorator.flowersPerChunk = 0;
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.theBiomeDecorator = new MoltarBiomeDecorator(this);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 0xFF000000 + 0xFF6338;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 0xFF000000 + 0xFF6338;
    }
}
