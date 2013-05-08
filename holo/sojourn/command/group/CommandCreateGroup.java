package holo.sojourn.command.group;

import holo.sojourn.group.Group;
import holo.sojourn.group.GroupManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;

public class CommandCreateGroup extends CommandBase
{
    public String getCommandName()
    {
        return "CreateGroup";
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
        EntityPlayerMP player = (EntityPlayerMP)p;
        if (GroupManager.groups().getGroupFromPlayer(player) == null)
            GroupManager.groups().registerGroup(new Group(player));
    }
}