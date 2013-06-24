package holo.sojourn.helper;

import holo.sojourn.config.DimensionConfig;
import holo.sojourn.world.aracoria.AracoriaWorldProvider;
import holo.sojourn.world.aracoria.biome.AracoriaBiome;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;

public class SojournDimensionRegistry
{
    public static BiomeGenBase aracoriaBiome;
    
    public static void initWorlds()
    {
        aracoriaBiome = new AracoriaBiome(DimensionConfig.aracoriaBiomeID);
        
        DimensionManager.registerProviderType(DimensionConfig.aracoriaDimensionID, AracoriaWorldProvider.class, DimensionConfig.keepLoadedAracoria);
        DimensionManager.registerDimension(DimensionConfig.aracoriaDimensionID, DimensionConfig.aracoriaDimensionID);
    }
}
