package holo.sojourn.group;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
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
    
    public void disbandList(Group group)
    {
        groupList.remove(group);
    }
    
    public Group getGroupFromPlayer(EntityPlayer player)
    {
        for(Group group : groupList.values())
        {
            if (group != null)
                if (group.hasPlayer(player))
                    return group;
        }
        
        return null;
    }

    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        Group group = this.getGroupFromPlayer(player);
        if (group != null)
            group.removePlayer(player);
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
        Group group = this.getGroupFromPlayer(player);
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
