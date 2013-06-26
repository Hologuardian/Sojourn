package holo.sojourn.config;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class DimensionConfig
{
    public static boolean keepLoadedAracoria;
    public static int aracoriaBiomeID;
    public static int aracoriaDimensionID;
    public static boolean keepLoadedAquatan;
    public static int aquatanBiomeID;
    public static int aquatanDimensionID;
    
    public static void initDimensionsConfig(FMLPreInitializationEvent event, Configuration config)
    {
        keepLoadedAracoria = config.get("Dimensions", "Keep Aracoria Loaded", true).getBoolean(true);
        aracoriaBiomeID = config.get("Dimensions", "Aracoria Biome ID", 50).getInt();
        aracoriaDimensionID = config.get("Dimensions", "Aracoria Dimension ID", 30).getInt();
        

        keepLoadedAquatan = config.get("Dimensions", "Keep Aquatan Loaded", true).getBoolean(true);
        aquatanBiomeID = config.get("Dimensions", "Aquatan Biome ID", 51).getInt();
        aquatanDimensionID = config.get("Dimensions", "Aquatan Dimension ID", 31).getInt();
    }
}
