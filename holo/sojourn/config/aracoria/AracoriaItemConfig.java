package holo.sojourn.config.aracoria;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class AracoriaItemConfig
{
    public static int aracoriaDimensionTravelerID;
    
    public static void initConfig(FMLPreInitializationEvent event, Configuration config)
    {
        aracoriaDimensionTravelerID = config.get("Aracoria Items", "Aracoria Dimension Traveler", 6000).getInt();
    }
}
