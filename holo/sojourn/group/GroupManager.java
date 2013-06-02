package holo.sojourn.group;

import holo.sojourn.network.packet.PacketHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IPlayerTracker;

public class GroupManager implements IPlayerTracker
{
    private static final GroupManager manager = new GroupManager();
    public static HashMap<String, Group>groupList = new HashMap<String, Group>();
    
    public static final GroupManager groups()
    {
        return manager;
    }
    
    public void registerGroup(Group group)
    {
        groupList.put(group.getHostName(), group);
    }
    
    public Group createGroupFromList(List<String> players)
    {
        if (players.size() == 0)
            return null;
        Group group = new Group(players.get(0));
        Iterator<String> iterator = players.iterator();
        while (iterator.hasNext())
        {
            String name = iterator.next();
            if (!group.hasPlayer(name))
                group.addPlayer(name);
        }
        return group;
    }
    
    public void disbandList(Group group)
    {
        groupList.remove(group.getHostName());
    }
    
    public Group getGroupFromPlayer(String player)
    {
        if (groupList.containsKey(player))
            return groupList.get(player);
        
        return null;
    }

    public void update(String name)
    {
        if (FMLCommonHandler.instance().getSide().isClient())
        {
            if (Minecraft.getMinecraft().theWorld.getPlayerEntityByName(name) != null)
                PacketHandler.sendGroupPacket(Minecraft.getMinecraft().theWorld.getPlayerEntityByName(name));
        }
        else if (FMLCommonHandler.instance().getSide().isServer())
        {
            if (MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(name) != null)
                PacketHandler.sendGroupPacket(MinecraftServer.getServer().getConfigurationManager().getPlayerForUsername(name));
        }
    }

    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        Group group = this.getGroupFromPlayer(player.username);
        if (group != null)
            group.removePlayer(player.username);
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        Group group = this.getGroupFromPlayer(player.username);
        if (group != null)
            group.summon(player);
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player){}
    
    public boolean doesGroupExist(String username)
    {
        return groupList.containsKey(username);
    }
}
