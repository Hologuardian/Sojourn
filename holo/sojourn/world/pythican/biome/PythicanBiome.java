package holo.sojourn.world.pythican.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;

public class PythicanBiome extends BiomeGenBase
{
    public PythicanBiome(int par1)
    {
        super(par1);
        this.setBiomeName("Pythican Jungle");
        this.setMinMaxHeight(-2.75F, 0.1F);
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.setTemperatureRainfall(0.93F, 1F);
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        

        
    }

    /**
     * Allocate a new BiomeDecorator for this BiomeGenBase
     */
    public BiomeDecorator createBiomeDecorator()
    {   
        return new PythicanBiomeDecorator(this);
    }
}