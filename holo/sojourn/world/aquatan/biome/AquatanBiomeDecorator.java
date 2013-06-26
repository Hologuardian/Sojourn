package holo.sojourn.world.aquatan.biome;

import holo.sojourn.helper.aquatan.AquatanBlocks;
import holo.sojourn.world.aquatan.features.kelpforest.WorldGenUnderwaterForest;
import holo.sojourn.world.aracoria.features.GlowingSpikes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class AquatanBiomeDecorator extends BiomeDecorator
{
    public WorldGenerator kelpForest;

    public AquatanBiomeDecorator(BiomeGenBase par1BiomeGenBase)
    {
        super(par1BiomeGenBase);
        this.kelpForest = new WorldGenUnderwaterForest(false);
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
        
        for (int n = 0; n < 5; n++)
        {
            i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            j = 32;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            
            while(currentWorld.getBlockId(i, j, k) != AquatanBlocks.aquatanWater.blockID && currentWorld.getBlockId(i, j, k) != AquatanBlocks.aquatanFlowingWater.blockID)
            {
                j++;
            }
            
            this.kelpForest.generate(currentWorld, randomGenerator, i, j, k);
        }
        
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
}
