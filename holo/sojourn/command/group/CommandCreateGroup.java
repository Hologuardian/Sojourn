package holo.sojourn.command.group;

import java.util.List;

import holo.sojourn.group.Group;
import holo.sojourn.group.GroupManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

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

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
        return par1ICommandSender.translateString("CreateGroup", new Object[0]);
    }

    public void processCommand(ICommandSender p, String[] args)
    {
        EntityPlayerMP player = (EntityPlayerMP)p;
        if (GroupManager.groups().getGroupFromPlayer(player.username) == null)
            GroupManager.groups().registerGroup(new Group(player.username));
    }

    /**
     * Adds the strings available in this command to the given list of tab completion options.
     */
    public List addTabCompletionOptions(ICommandSender par1ICommandSender, String[] par2ArrayOfStr)
    {
        return par2ArrayOfStr.length == 1 ? getListOfStringsMatchingLastWord(par2ArrayOfStr, this.getPlayers()) : null;
    }

    protected String[] getPlayers()
    {
        return MinecraftServer.getServer().getAllUsernames();
    }
}