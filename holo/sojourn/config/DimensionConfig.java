package holo.sojourn.config;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class DimensionConfig
{
    public static boolean keepLoadedAracoria;
    public static boolean keepLoadedAquatan;
    public static boolean keepLoadedFungalMarsh;
    public static boolean keepLoadedMoltar;
    public static boolean keepLoadedDemorus;
    
    public static int aracoriaBiomeID;
    public static int aquatanBiomeID;
    public static int fungalMarshBiomeID;
    
    public static int moltarValleyBiomeID;
    public static int moltarBiomeID;

    public static int demorusValleyBiomeID;
    public static int demorusBiomeID;
    public static int demorusBiome1ID;
    
    public static int aracoriaDimensionID;
    public static int aquatanDimensionID;
    public static int fungalMarshDimensionID;
    public static int moltarDimensionID;
    public static int demorusDimensionID;
    
    public static void initDimensionsConfig(FMLPreInitializationEvent event, Configuration config)
    {
        keepLoadedAracoria = config.get("Dimensions", "Keep Aracoria Loaded", true).getBoolean(true);
        keepLoadedAquatan = config.get("Dimensions", "Keep Aquatan Loaded", true).getBoolean(true);
        keepLoadedFungalMarsh = config.get("Dimensions", "Keep Fungal Marsh Loaded", true).getBoolean(true);
        keepLoadedMoltar = config.get("Dimensions", "Keep Moltar Loaded", true).getBoolean(true);
        keepLoadedDemorus = config.get("Dimensions", "Keep Demorus Loaded", true).getBoolean(true);
        
        aracoriaBiomeID = config.get("Dimensions", "Aracoria Biome ID", 50).getInt();
        aquatanBiomeID = config.get("Dimensions", "Aquatan Biome ID", 51).getInt();
        fungalMarshBiomeID = config.get("Dimensions", "Fungal Marsh Biome ID", 52).getInt();
        
        moltarValleyBiomeID = config.get("Dimensions", "Moltar Valley Biome ID", 53).getInt();
        moltarBiomeID = config.get("Dimensions", "Moltar Biome ID", 54).getInt();

        demorusValleyBiomeID = config.get("Dimensions", "Demorus Valley Biome ID", 55).getInt();
        demorusBiomeID = config.get("Dimensions", "Demorus Biome ID", 56).getInt();
        demorusBiome1ID = config.get("Dimensions", "Demorus Biome 2 ID", 57).getInt();
        

        aracoriaDimensionID = config.get("Dimensions", "Aracoria Dimension ID", 30).getInt();
        aquatanDimensionID = config.get("Dimensions", "Aquatan Dimension ID", 31).getInt();
        fungalMarshDimensionID = config.get("Dimensions", "Fungal Marsh Dimension ID", 32).getInt();
        moltarDimensionID = config.get("Dimensions", "Moltar Dimension ID", 33).getInt();
        demorusDimensionID = config.get("Dimensions", "Demorus Dimension ID", 34).getInt();
        
    }
}
