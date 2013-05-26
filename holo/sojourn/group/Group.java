package holo.sojourn.group;

import holo.sojourn.client.render.hud.GroupIcon;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;

public class Group
{
    private ArrayList<String> playerList;
    public final MinecraftServer server = MinecraftServer.getServer();

    public Group(String host)
    {
        playerList = new ArrayList(7);
        addPlayer(host);
    }

    public boolean addPlayer(String player)
    {
        if (playerList.size() < 7)
            playerList.add(player);
        return false;
    }

    public boolean removePlayer(String player)
    {
        return playerList.remove(player);
    }

    public int getSize()
    {
        int ret = 0;
        for (String player : playerList)
        {
            if (!player.equals(""))
                ret++;
        }
        return ret;
    }

    public ArrayList<String> getList()
    {
        return playerList;
    }

    public boolean hasPlayer(String player)
    {
        return playerList.contains(player);
    }

    public void transportToHost()
    {
        EntityPlayer host = server.getConfigurationManager().getPlayerForUsername(playerList.get(0));
        for (String name : playerList.subList(1, 7))
        {
            EntityPlayer player = server.getConfigurationManager().getPlayerForUsername(name);
            if (player != null)
                player.setPosition(host.posX, host.posY, host.posZ);
        }
    }

    public String getHostName()
    {
        return playerList.get(0);
    }

    public void summon(EntityPlayer summoner)
    {
        for (String name : playerList)
        {
            EntityPlayer player = server.getConfigurationManager().getPlayerForUsername(name);
            if (player != null && player != summoner)
                player.setPosition(summoner.posX, summoner.posY, summoner.posZ);
        }
    }

    public void renderIcons()
    {
        GroupIcon icon = new GroupIcon();
        for (String name : playerList)
        {
            if (!name.equals(""))
            {
                icon.renderIcon(this);
            }
        }
    }
}