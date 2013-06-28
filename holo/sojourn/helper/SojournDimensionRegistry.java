package holo.sojourn.helper;

import holo.sojourn.config.DimensionConfig;
import holo.sojourn.world.aquatan.AquatanWorldProvider;
import holo.sojourn.world.aquatan.biome.AquatanBiome;
import holo.sojourn.world.aracoria.AracoriaWorldProvider;
import holo.sojourn.world.aracoria.biome.AracoriaBiome1;
import holo.sojourn.world.aracoria.biome.AracoriaBiome2;
import holo.sojourn.world.demorus.DemorusWorldProvider;
import holo.sojourn.world.demorus.biome.DemorusBiome;
import holo.sojourn.world.demorus.biome.DemorusBiome2;
import holo.sojourn.world.demorus.biome.DemorusValleyBiome;
import holo.sojourn.world.fungalmarsh.FungalMarshWorldProvider;
import holo.sojourn.world.fungalmarsh.biome.FungalMarshBiome;
import holo.sojourn.world.moltar.MoltarWorldProvider;
import holo.sojourn.world.moltar.biome.MoltarBiome;
import holo.sojourn.world.moltar.biome.MoltarValleyBiome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class SojournDimensionRegistry
{
    public static BiomeGenBase aquatanBiome;
    public static BiomeGenBase aracoriaBiome1;
    public static BiomeGenBase aracoriaBiome2;
    public static BiomeGenBase fungalMarshBiome;
    
    public static BiomeGenBase moltarBiome;
    public static BiomeGenBase moltarValleyBiome;
    
    public static BiomeGenBase demorusValleyBiome;
    public static BiomeGenBase demorusBiome;
    public static BiomeGenBase demorusBiome2;
    
    public static void initWorlds()
    {
        aquatanBiome = new AquatanBiome(DimensionConfig.aquatanBiomeID);
        aracoriaBiome1 = new AracoriaBiome1(DimensionConfig.aracoriaBiomeID);
        aracoriaBiome2 = new AracoriaBiome2(DimensionConfig.aracoriaBiomeID - 1);
        fungalMarshBiome = new FungalMarshBiome(DimensionConfig.fungalMarshBiomeID).setTemperatureRainfall(0.8F, 0.9F);
        
        moltarBiome = new MoltarBiome(DimensionConfig.moltarBiomeID);
        moltarValleyBiome = new MoltarValleyBiome(DimensionConfig.moltarValleyBiomeID);

        demorusValleyBiome = new DemorusValleyBiome(DimensionConfig.demorusValleyBiomeID);
        demorusBiome = new DemorusBiome(DimensionConfig.demorusBiomeID);
        demorusBiome2 = new DemorusBiome2(DimensionConfig.demorusBiome1ID);
        
        DimensionManager.registerProviderType(DimensionConfig.aracoriaDimensionID, AracoriaWorldProvider.class, DimensionConfig.keepLoadedAracoria);
        DimensionManager.registerDimension(DimensionConfig.aracoriaDimensionID, DimensionConfig.aracoriaDimensionID);

        DimensionManager.registerProviderType(DimensionConfig.aquatanDimensionID, AquatanWorldProvider.class, DimensionConfig.keepLoadedAquatan);
        DimensionManager.registerDimension(DimensionConfig.aquatanDimensionID, DimensionConfig.aquatanDimensionID);

        DimensionManager.registerProviderType(DimensionConfig.fungalMarshDimensionID, FungalMarshWorldProvider.class, DimensionConfig.keepLoadedFungalMarsh);
        DimensionManager.registerDimension(DimensionConfig.fungalMarshDimensionID, DimensionConfig.fungalMarshDimensionID);

        DimensionManager.registerProviderType(DimensionConfig.moltarDimensionID, MoltarWorldProvider.class, DimensionConfig.keepLoadedMoltar);
        DimensionManager.registerDimension(DimensionConfig.moltarDimensionID, DimensionConfig.moltarDimensionID);

        DimensionManager.registerProviderType(DimensionConfig.demorusDimensionID, DemorusWorldProvider.class, DimensionConfig.keepLoadedDemorus);
        DimensionManager.registerDimension(DimensionConfig.demorusDimensionID, DimensionConfig.demorusDimensionID);
    }
}
