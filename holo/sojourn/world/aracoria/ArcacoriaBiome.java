package holo.sojourn.world.aracoria;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class ArcacoriaBiome extends BiomeGenBase
{
    public ArcacoriaBiome(int par1)
    {
        super(par1);
        this.spawnableCreatureList.clear();
        this.topBlock = (byte)Block.stone.blockID;
        this.fillerBlock = (byte)Block.stone.blockID;
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 0;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 0;
    }
}
