package holo.sojourn.helper;

import holo.sojourn.config.DimensionConfig;
import holo.sojourn.world.aquatan.AquatanWorldProvider;
import holo.sojourn.world.aquatan.biome.AquatanBiome;
import holo.sojourn.world.aracoria.AracoriaWorldProvider;
import holo.sojourn.world.aracoria.biome.AracoriaBiome1;
import holo.sojourn.world.aracoria.biome.AracoriaBiome2;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class SojournDimensionRegistry
{
    public static BiomeGenBase aquatanBiome;
    public static BiomeGenBase aracoriaBiome1;
    public static BiomeGenBase aracoriaBiome2;
    
    public static void initWorlds()
    {
        aquatanBiome = new AquatanBiome(DimensionConfig.aquatanBiomeID);
        aracoriaBiome1 = new AracoriaBiome1(DimensionConfig.aracoriaBiomeID);
        aracoriaBiome2 = new AracoriaBiome2(DimensionConfig.aracoriaBiomeID - 1);
        
        DimensionManager.registerProviderType(DimensionConfig.aracoriaDimensionID, AracoriaWorldProvider.class, DimensionConfig.keepLoadedAracoria);
        DimensionManager.registerDimension(DimensionConfig.aracoriaDimensionID, DimensionConfig.aracoriaDimensionID);

        DimensionManager.registerProviderType(DimensionConfig.aquatanDimensionID, AquatanWorldProvider.class, DimensionConfig.keepLoadedAquatan);
        DimensionManager.registerDimension(DimensionConfig.aquatanDimensionID, DimensionConfig.aquatanDimensionID);
    }
}
