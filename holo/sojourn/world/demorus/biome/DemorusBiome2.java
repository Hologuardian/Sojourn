package holo.sojourn.world.demorus.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class DemorusBiome2 extends BiomeGenBase
{
    public DemorusBiome2(int par1)
    {
        super(par1);
        this.setBiomeName("Demorus Wasteland");
        this.minHeight = -0.1F;
        this.maxHeight = 0.1F;
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 0;
        this.theBiomeDecorator.flowersPerChunk = 0;
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
//        this.setEnableSnow();
//        this.setTemperatureRainfall(0.05F, 0.8F);
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();

        this.setDisableRain();
    }

    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        return 0xFF000000 + 0x7F4800;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        return 0xFF000000 + 0x7F4800;
    }
}
