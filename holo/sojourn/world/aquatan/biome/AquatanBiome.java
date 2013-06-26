package holo.sojourn.world.aquatan.biome;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class AquatanBiome extends BiomeGenBase
{
    public AquatanBiome(int par1)
    {
        super(par1);
        this.setBiomeName("Aquatan");
        this.minHeight = -2.5F;
        this.maxHeight = 3.0F;
        this.theBiomeDecorator.treesPerChunk = 0;
        this.theBiomeDecorator.grassPerChunk = 0;
        this.theBiomeDecorator.flowersPerChunk = 0;
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.theBiomeDecorator = new AquatanBiomeDecorator(this);
    }
}
