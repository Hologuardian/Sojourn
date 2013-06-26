package holo.sojourn.config.aquatan;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class AquatanItemConfig
{
    public static int aquatanDimensionTravelerID;
    
    public static void initConfig(FMLPreInitializationEvent event, Configuration config)
    {
        aquatanDimensionTravelerID = config.get("Aquatan Items", "Aquatan Dimension Traveler", 6100).getInt();
    }
}
