package holo.sojourn.group;

import holo.sojourn.client.render.hud.GroupIcon;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

public class Group
{
    private ArrayList<EntityPlayer> playerList;
    
    public Group(EntityPlayer host)
    {
        playerList = new ArrayList(7);
        playerList.add(host);
    }
    
    public boolean addPlayer(EntityPlayer player)
    {
        if (playerList.size() < 7)
        {
            playerList.add(player);
            return true;
        }
        else
            return false;
    }
    
    public int getSize()
    {
        int ret = 0;
        for (EntityPlayer player : playerList)
        {
            if (player != null)
                ret++;
        }
        return ret;
    }
    
    public ArrayList<EntityPlayer> getList()
    {
        return playerList;
    }
    
    public boolean hasPlayer(EntityPlayer player)
    {
        return playerList.contains(player);
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
    
    public String getHostName()
    {
        return playerList.get(0).username;
    }
    
    public void summon(EntityPlayer summoner)
    {
        for (EntityPlayer player : playerList)
        {
            if (player != null && player != summoner)
                player.setPosition(summoner.posX, summoner.posY, summoner.posZ);
        }
    }
    
    public void renderIcons()
    {
        GroupIcon icon = new GroupIcon();
        for (EntityPlayer player : playerList)
        {
            if (player != null)
            {
                icon.renderIcon(this, player);
            }
        }
    }
}