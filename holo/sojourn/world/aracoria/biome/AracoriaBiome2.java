package holo.sojourn.world.aracoria.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class AracoriaBiome2 extends BiomeGenBase
{
    public AracoriaBiome2(int par1)
    {
        super(par1);
        this.setBiomeName("Aracoria2");
        this.minHeight = 4.0F;
        this.maxHeight = -1.5F;
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 0;
        this.theBiomeDecorator.flowersPerChunk = 0;
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.setEnableSnow();
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
    }
}
