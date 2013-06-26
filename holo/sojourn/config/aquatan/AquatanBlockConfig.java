package holo.sojourn.config.aquatan;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class AquatanBlockConfig
{
    public static int aquatanWaterID;
    
    public static void initConfig(FMLPreInitializationEvent event, Configuration config)
    {
        aquatanWaterID = config.get("Aquatan Blocks", "Aquatan Water", 200).getInt();
    }
}
