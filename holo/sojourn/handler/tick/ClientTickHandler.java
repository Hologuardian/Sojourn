package holo.sojourn.handler.tick;

import holo.sojourn.group.Group;
import holo.sojourn.group.GroupManager;

import java.util.EnumSet;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler
{
    public static final Minecraft mc = Minecraft.getMinecraft();
    
    @Override
    public void tickStart(EnumSet var1, Object ... var2) {}

    @Override
    public void tickEnd(EnumSet var1, Object ... var2)
    {
        onTickInGame();
    }

    @Override
    public EnumSet ticks()
    {
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel()
    {
        return "Sojourn Client Tick Handler";
    }

    public void onTickInGame()
    {
        EntityPlayer player = mc.thePlayer;
        if (player != null)
        {
            player.sendChatToPlayer("TICK");
            Group playerGroup = GroupManager.groups().getGroupFromPlayer(player);
            
            if (playerGroup != null)
            {
                playerGroup.renderIcons();
            }
        }
    }
}