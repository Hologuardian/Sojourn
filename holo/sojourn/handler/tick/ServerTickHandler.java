package holo.sojourn.handler.tick;

import holo.sojourn.essence.EssenceBar;
import holo.sojourn.group.GroupManager;

import java.util.EnumSet;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler
{
    @Override
    public void tickStart(EnumSet tickType, Object ... args) 
    {
    	if (tickType.equals(EnumSet.of(TickType.PLAYER)))
        {
        	onPlayerTick(args);
        }
    }

    @Override
    public void tickEnd(EnumSet tickType, Object ... args)
    {
        if (tickType.equals(ticks()))
        {
            onTickInGame();
        }
    }

    @Override
    public EnumSet ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }
    
    @Override
    public String getLabel()
    {
        return "Sojourn Server Tick Handler";
    }

    public void onTickInGame()
    {
        Iterator players = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();
        while (players.hasNext())
        {
            EntityPlayerMP player = (EntityPlayerMP) players.next();
            EssenceBar.bars().updateBar(player, 2);
        }
        GroupManager.groups().update();
    }

    public void onPlayerTick(Object ... args)
    {
    	EntityPlayer player = (EntityPlayer) args[0];
//    	player.capabilities.setFlySpeed(0.05F);
    }
}