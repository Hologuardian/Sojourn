package holo.sojourn.config;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class DimensionConfig
{
    public static boolean keepLoadedAracoria;
    public static int aracoriaBiomeID;
    public static int aracoriaDimensionID;
    
    public static void initDimensionsConfig(FMLPreInitializationEvent event, Configuration config)
    {
        keepLoadedAracoria = config.get("Dimensions", "Keep Aracoria Loaded", true).getBoolean(true);
        aracoriaBiomeID = config.get("Dimensions", "Aracoria Biome ID", 50).getInt();
        aracoriaDimensionID = config.get("Dimensions", "Aracoria Dimension ID", 30).getInt();
    }
}
