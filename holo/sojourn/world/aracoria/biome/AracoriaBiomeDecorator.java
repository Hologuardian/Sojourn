package holo.sojourn.world.aracoria.biome;

import holo.sojourn.world.aracoria.features.GlowingSpikes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public class AracoriaBiomeDecorator extends BiomeDecorator
{
    public WorldGenerator glowingSpikeGen;

    public AracoriaBiomeDecorator(BiomeGenBase par1BiomeGenBase)
    {
        super(par1BiomeGenBase);
        this.glowingSpikeGen = new GlowingSpikes(Block.cobblestone.blockID, Block.waterMoving.blockID);
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
        
        for (int n = 0; n < 3; n++)
        {
            i = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            j = 5;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.glowingSpikeGen.generate(currentWorld, randomGenerator, i, j, k);
//            System.out.println("LE GEN" + i + " " + j + " " + k);
        }
        
        MinecraftForge.EVENT_BUS.post(new DecorateBiomeEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
}
