package holo.sojourn.command.group;

import java.util.List;

import holo.sojourn.group.GroupManager;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

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
            EntityPlayerMP player =(EntityPlayerMP)(p);
            EntityPlayerMP host = func_82359_c(p, args[0]);
            if (GroupManager.groups().getGroupFromPlayer(host) != null)
                GroupManager.groups().getGroupFromPlayer(host).addPlayer(player);
        }
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

    /**
     * Return whether the specified command parameter index is a username parameter.
     */
    public boolean isUsernameIndex(String[] par1ArrayOfStr, int par2)
    {
        return par2 == 0;
    }
}