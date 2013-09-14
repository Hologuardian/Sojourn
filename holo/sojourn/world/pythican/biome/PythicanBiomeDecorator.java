package holo.sojourn.world.pythican.biome;

import holo.sojourn.helper.aquatan.AquatanBlocks;
import holo.sojourn.world.aquatan.features.kelpforest.WorldGenUnderwaterForest;
import holo.sojourn.world.aracoria.features.GlowingSpikes;
import holo.sojourn.world.pythican.feature.WorldGenLargeTree;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class PythicanBiomeDecorator extends BiomeDecorator
{
    public WorldGenerator largeTree;

    public PythicanBiomeDecorator(BiomeGenBase par1BiomeGenBase)
    {
        super(par1BiomeGenBase);
        this.largeTree = new WorldGenLargeTree();
    }
    
    /**
     * The method that does the work of actually decorating chunks
     */
    protected void decorate()
    {
        super.decorate();
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        
        int i;
        int j;
        int k;
        
        if(this.randomGenerator.nextInt(8) == 0)
        {
            i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            j = 25;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            
            while(currentWorld.getBlockId(i, j, k) != 0)
            {
                j++;
            }
            
            this.largeTree.generate(currentWorld, randomGenerator, i, j, k);
        }
        
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
}
