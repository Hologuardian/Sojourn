package holo.sojourn;

import holo.sojourn.command.group.CommandCreateGroup;
import holo.sojourn.command.group.CommandJoinGroup;
import holo.sojourn.proxy.CommonProxy;
import holo.sojourn.util.Strings;
import net.minecraft.command.CommandHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.ServerStarting;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = Strings.MAIN_MOD_ID, version = Strings.VERSION, name = Strings.MAIN_MOD_ID)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class Sojourn
{   
    @Instance(Strings.MAIN_MOD_ID)
    public static Sojourn instance;
    
    @SidedProxy
    (clientSide = Strings.clientProxy, 
        serverSide = Strings.commonProxy)
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        instance = this;
        proxy.configRegistry(event);
    }
    
    @EventHandler
    public void Init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
    
    @EventHandler
    public void PostInit(FMLPostInitializationEvent event)
    {
        
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {       
        CommandHandler commandManager = (CommandHandler)event.getServer().getCommandManager();
        commandManager.registerCommand(new CommandCreateGroup());
        commandManager.registerCommand(new CommandJoinGroup());
    }
}