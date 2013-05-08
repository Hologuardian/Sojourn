package holo.sojourn.proxy;

import holo.sojourn.handler.tick.ServerTickHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy 
{

    public void init(FMLInitializationEvent event) {
//        entityRegistry();
          tickRegistry();
//        eventRegistry();
//        SojournItems.initItems();
//        SojournBlocks.initBlocks();
//        renderRegistry();
//        SojournRecipes.initRecipes();
//        SojournNames.initNames();
        
    }
    
    public void tickRegistry() {
        TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER); 
    }
    
    public void renderRegistry() {
    }
    
    public void eventRegistry() {
    }
    
    public void entityRegistry() 
    {
    }
    
    public int addArmor(String name)
    {
        return 0;
    }
}