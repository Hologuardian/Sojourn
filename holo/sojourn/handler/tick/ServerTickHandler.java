package holo.sojourn.handler.tick;

import holo.sojourn.essence.EssenceBar;
import holo.sojourn.group.GroupManager;

import java.util.EnumSet;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler
{
    @Override
    public void tickStart(EnumSet var1, Object ... var2) {}

    @Override
    public void tickEnd(EnumSet var1, Object ... var2)
    {
        if (var1.equals(ticks()))
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
}