package holo.sojourn.command.group;

import holo.sojourn.group.GroupManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandJoinGroup extends CommandBase
{
    public String getCommandName()
    {
        return "JoinGroup";
    }

    /**
     * Return the required permission level for this command.
     */
    public int getRequiredPermissionLevel()
    {
        return 2;
    }

    public void processCommand(ICommandSender p, String[] args)
    {
        if (GroupManager.groups().doesGroupExist(args[0]))
        {
            EntityPlayerMP player = (EntityPlayerMP)p;
            if (GroupManager.groups().getGroupFromPlayer(player) != null && p instanceof EntityPlayer)
                GroupManager.groups().getGroupFromPlayer(player).addPlayer((EntityPlayer) p);
        }
    }
}