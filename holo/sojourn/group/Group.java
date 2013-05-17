package holo.sojourn.group;

import holo.sojourn.client.render.hud.GroupIcon;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Group
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
    
    @SideOnly(Side.CLIENT)
    public void renderIcons()
    {
        for (EntityPlayer player : playerList)
        {
            if (player != null)
            {
                GroupIcon icon = icons.get(playerList.indexOf(player));
                icon.renderIcon(this, player);
            }
        }
    }
}