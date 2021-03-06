package holo.sojourn.group;

import holo.sojourn.network.packet.PacketHandler;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
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
        for(Group group : groupList.values())
        {
            if (group.hasPlayer(player))
            {
                return group;
            }
        }
        
        return null;
    }

    public void update()
    {
        for (Group group : this.groupList.values())
        {
            for(String name : group.getList())
            {
//                System.out.println("UPDATING");
                PacketHandler.sendGroupPacket(name, group.getList());
            }
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
