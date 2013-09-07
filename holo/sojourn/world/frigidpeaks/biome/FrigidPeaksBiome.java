package holo.sojourn.world.frigidpeaks.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class FrigidPeaksBiome extends BiomeGenBase
{
    public FrigidPeaksBiome(int par1)
    {
        super(par1);
        this.setBiomeName("Frigid Peaks");
        this.setMinMaxHeight(0.5F, 2.2F);
        this.topBlock = (byte) Block.blockSnow.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.setTemperatureRainfall(0.05F, 1F);
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
    }
}