package holo.sojourn.group;

import java.util.ArrayList;

import cpw.mods.fml.common.IPlayerTracker;

import net.minecraft.entity.player.EntityPlayer;

public class Group implements IPlayerTracker
{
    private ArrayList<EntityPlayer> playerList;
    
    public Group(EntityPlayer host)
    {
        playerList = new ArrayList(15);
        playerList.add(host);
    }
    
    public boolean addPlayer(EntityPlayer player)
    {
        if (playerList.get(19) == null)
        {
            playerList.add(player);
            return true;
        }
        else
            return false;
    }
    
    public ArrayList<EntityPlayer> getList()
    {
        return playerList;
    }
    
    public boolean removePlayer(EntityPlayer player)
    {
        return playerList.remove(player);
    }
    
    public void transportToHost()
    {
        EntityPlayer host = playerList.get(0);
        for (EntityPlayer player : playerList.subList(1, 19))
        {
            if (player != null)
                player.setPosition(host.posX, host.posY, host.posZ);
        }
    }
    
    public void summon(EntityPlayer player)
    {
        EntityPlayer host = playerList.get(0);
        if (player != host)
            player.setPosition(host.posX, host.posY, host.posZ);
    }

    @Override
    public void onPlayerLogin(EntityPlayer player){}

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        playerList.remove(player);
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        for (EntityPlayer p : playerList)
        {
            if (p != null && p != player)
                p.travelToDimension(player.dimension);
        }
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player){}
}