package holo.sojourn.world.moltar.biome;

import holo.sojourn.world.moltar.features.MapGenDeadTree;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class MoltarBiomeDecorator extends BiomeDecorator
{
    private WorldGenerator deadTrees;
    
    public MoltarBiomeDecorator(BiomeGenBase par1BiomeGenBase)
    {
        super(par1BiomeGenBase);
        deadTrees = new MapGenDeadTree(false);
    }
    
    /**
     * The method that does the work of actually decorating chunks
     */
    protected void decorate()
    {
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
        
        int i;
        int j;
        int k;
        
        if (this.randomGenerator.nextInt(5) == 0)
        {
            i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            j = this.currentWorld.getTopSolidOrLiquidBlock(i, k);
            this.deadTrees.generate(currentWorld, randomGenerator, i, j, k);
        }
        
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
}
