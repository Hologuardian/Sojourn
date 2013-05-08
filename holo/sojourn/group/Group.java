package holo.sojourn.group;

import holo.sojourn.client.render.hud.GroupIcon;

import java.util.ArrayList;

import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.entity.player.EntityPlayer;

public class Group implements IPlayerTracker
{
    private ArrayList<EntityPlayer> playerList;
    private ArrayList<GroupIcon> icons;
    
    public Group(EntityPlayer host)
    {
        playerList = new ArrayList();
        icons = new ArrayList();
        playerList.add(host);
        icons.add(playerList.indexOf(host), new GroupIcon(host));
    }
    
    public boolean addPlayer(EntityPlayer player)
    {
        if (playerList.get(19) == null)
        {
            playerList.add(player);
            icons.add(playerList.indexOf(player), new GroupIcon(player));
            return true;
        }
        else
            return false;
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
        icons.add(playerList.indexOf(player), new GroupIcon(player));
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
    
    public void summon(EntityPlayer summoner)
    {
        for (EntityPlayer player : playerList)
        {
            if (player != null && player != summoner)
                player.setPosition(summoner.posX, summoner.posY, summoner.posZ);
        }
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
    
    @SideOnly(Side.CLIENT)
    public void renderIcons()
    {
        for (EntityPlayer player : playerList)
        {
            if (player != null)
            {
                player.sendChatToPlayer("RENDER");
                icons.get(playerList.indexOf(player)).renderIcon(this, player);
            }
        }
    }
}