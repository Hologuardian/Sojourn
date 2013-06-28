package holo.sojourn.world.demorus.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class DemorusBiome extends BiomeGenBase
{
    public DemorusBiome(int par1)
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
}
