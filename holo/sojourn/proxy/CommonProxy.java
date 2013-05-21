package holo.sojourn.proxy;

import holo.sojourn.essence.EssenceBar;
import holo.sojourn.group.GroupManager;
import holo.sojourn.handler.tick.ServerTickHandler;
import holo.sojourn.network.packet.ServerPacketHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy 
{

    public void init(FMLInitializationEvent event) {
//        entityRegistry();
          tickRegistry();
          eventRegistry();
//        SojournItems.initItems();
//        SojournBlocks.initBlocks();
//        renderRegistry();
//        SojournRecipes.initRecipes();
//        SojournNames.initNames();
    }
    
    public void tickRegistry() {
        TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER); 
        NetworkRegistry.instance().registerChannel(new ServerPacketHandler(), "Essence");
        NetworkRegistry.instance().registerChannel(new ServerPacketHandler(), "Group");
    }
    
    public void renderRegistry() {
    }
    
    public void eventRegistry() 
    {
        GameRegistry.registerPlayerTracker(GroupManager.groups());
        GameRegistry.registerPlayerTracker(EssenceBar.bars());
    }
    
    public void entityRegistry() 
    {
    }
    
    public int addArmor(String name)
    {
        return 0;
    }
}