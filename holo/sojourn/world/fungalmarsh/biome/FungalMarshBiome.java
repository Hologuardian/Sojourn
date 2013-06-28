package holo.sojourn.world.fungalmarsh.biome;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;

public class FungalMarshBiome extends BiomeGenBase
{
    public FungalMarshBiome(int par1)
    {
        super(par1);
        this.setBiomeName("Fungal Marsh");
        this.minHeight = -0.2F;
        this.maxHeight = 0.1F;
        this.topBlock = (byte) Block.grass.blockID;
        this.fillerBlock = (byte) Block.dirt.blockID;
        this.waterColorMultiplier = 0xC3FF00;
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        this.spawnableCaveCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.theBiomeDecorator = new FungalMarshBiomeDecorator(this);
    }

    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic grass color based on the biome temperature and rainfall
     */
    public int getBiomeGrassColor()
    {
        double d0 = (double)this.getFloatTemperature();
        double d1 = (double)this.getFloatRainfall();
        return 0xFF000000 + 0x5D4E8E;
    }

    @SideOnly(Side.CLIENT)

    /**
     * Provides the basic foliage color based on the biome temperature and rainfall
     */
    public int getBiomeFoliageColor()
    {
        double d0 = (double)this.getFloatTemperature();
        double d1 = (double)this.getFloatRainfall();
        return 0xFF000000 + 0xD3BE00;
    }
}
