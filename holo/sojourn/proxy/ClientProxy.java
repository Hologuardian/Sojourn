package holo.sojourn.proxy;

import holo.sojourn.handler.tick.ClientTickHandler;
import holo.sojourn.network.packet.PacketHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    
    @Override
    public void tickRegistry() {
        super.tickRegistry();
        TickRegistry.registerTickHandler(new ClientTickHandler(), Side.CLIENT);
        NetworkRegistry.instance().registerChannel(new PacketHandler(), "Essence");
        NetworkRegistry.instance().registerChannel(new PacketHandler(), "Group");
    }
    @Override
    public void renderRegistry() 
    {
    }
    
    @Override
    public int addArmor(String name)
    {
        return RenderingRegistry.addNewArmourRendererPrefix(name);
    }
}