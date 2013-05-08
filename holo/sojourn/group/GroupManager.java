package holo.sojourn.group;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;

public class GroupManager
{
    private static final GroupManager manager = new GroupManager();
    public static ArrayList<Group>groupList = new ArrayList();
    
    public static final GroupManager groups()
    {
        return manager;
    }
    
    public void registerGroup(Group group)
    {
        groupList.add(group);
    }
    
    public void disbandList(Group group)
    {
        groupList.remove(group);
    }
    
    public Group getGroupFromPlayer(EntityPlayer player)
    {
        for(Group group : groupList)
        {
            if (group != null)
                if (group.hasPlayer(player))
                    return group;
        }
        
        return null;
    }
}
