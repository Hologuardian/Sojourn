package holo.sojourn.helper.aracoria;

import holo.sojourn.config.aracoria.AracoriaItemConfig;
import holo.sojourn.item.aracoria.AracoriaDimensionTraveler;
import net.minecraft.item.Item;

public class AracoriaItems
{
    public static Item aracoriaDimensionTeleporter;
    
    public static void initItems()
    {
        aracoriaDimensionTeleporter = new AracoriaDimensionTraveler(AracoriaItemConfig.aracoriaDimensionTravelerID, "DimensionTraveler");
    }
}
