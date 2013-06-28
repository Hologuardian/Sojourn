package holo.sojourn.config;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class DimensionConfig
{
    public static boolean keepLoadedAracoria;
    public static boolean keepLoadedAquatan;
    public static boolean keepLoadedFungalMarsh;
    
    public static int aracoriaBiomeID;
    public static int aquatanBiomeID;
    public static int fungalMarshBiomeID;
    
    public static int aracoriaDimensionID;
    public static int aquatanDimensionID;
    public static int fungalMarshDimensionID;
    
    public static void initDimensionsConfig(FMLPreInitializationEvent event, Configuration config)
    {
        keepLoadedAracoria = config.get("Dimensions", "Keep Aracoria Loaded", true).getBoolean(true);
        keepLoadedAquatan = config.get("Dimensions", "Keep Aquatan Loaded", true).getBoolean(true);
        keepLoadedFungalMarsh = config.get("Dimensions", "Keep Fungal Marsh Loaded", true).getBoolean(true);
        
        aracoriaBiomeID = config.get("Dimensions", "Aracoria Biome ID", 50).getInt();
        aquatanBiomeID = config.get("Dimensions", "Aquatan Biome ID", 51).getInt();
        fungalMarshBiomeID = config.get("Dimensions", "Fungal Marsh Biome ID", 52).getInt();
        

        aracoriaDimensionID = config.get("Dimensions", "Aracoria Dimension ID", 30).getInt();
        aquatanDimensionID = config.get("Dimensions", "Aquatan Dimension ID", 31).getInt();
        fungalMarshDimensionID = config.get("Dimensions", "Fungal Marsh Dimension ID", 32).getInt();
        
    }
}
