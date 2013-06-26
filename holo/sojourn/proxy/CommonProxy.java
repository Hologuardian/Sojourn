package holo.sojourn.proxy;

import holo.sojourn.config.DimensionConfig;
import holo.sojourn.config.aquatan.AquatanBlockConfig;
import holo.sojourn.config.aracoria.AracoriaItemConfig;
import holo.sojourn.essence.EssenceBar;
import holo.sojourn.group.GroupManager;
import holo.sojourn.handler.MobEventHandler;
import holo.sojourn.handler.tick.ServerTickHandler;
import holo.sojourn.helper.SojournBlocks;
import holo.sojourn.helper.SojournDimensionRegistry;
import holo.sojourn.helper.SojournItems;
import holo.sojourn.network.packet.PacketHandler;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
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
          SojournItems.initItems();
          SojournBlocks.initBlocks();
//        renderRegistry();
//        SojournRecipes.initRecipes();
//        SojournNames.initNames();
          SojournDimensionRegistry.initWorlds();
    }
    
    public void tickRegistry() {
        TickRegistry.registerTickHandler(new ServerTickHandler(), Side.SERVER); 
        NetworkRegistry.instance().registerChannel(new PacketHandler(), "Essence");
        NetworkRegistry.instance().registerChannel(new PacketHandler(), "SojournGroup");
    }
    
    public void configRegistry(FMLPreInitializationEvent event) 
    {
        File file = new File(event.getModConfigurationDirectory(), "Sojourn.cfg");
        Configuration config = new Configuration(file);
        config.load();
        DimensionConfig.initDimensionsConfig(event, config);
        AracoriaItemConfig.initConfig(event, config);
        AquatanBlockConfig.initConfig(event, config);
        config.save();
    }
    
    public void renderRegistry() 
    {
        
    }
    
    public void eventRegistry() 
    {
        GameRegistry.registerPlayerTracker(GroupManager.groups());
        GameRegistry.registerPlayerTracker(EssenceBar.bars());
        MinecraftForge.EVENT_BUS.register(new MobEventHandler());
    }
    
    public void entityRegistry() 
    {
    }
    
    public int addArmor(String name)
    {
        return 0;
    }
}