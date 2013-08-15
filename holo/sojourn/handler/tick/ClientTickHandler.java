package holo.sojourn.handler.tick;

import holo.sojourn.client.render.hud.EssenceBarIcon;
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
    public void tickStart(EnumSet tickType, Object ... args) 
    {
        onTickInGame();
    }

    @Override
    public void tickEnd(EnumSet tickType, Object ... args)
    {
        if (tickType.equals(EnumSet.of(TickType.RENDER)))
        {
            onRenderTick();
        }
    }

    public void onRenderTick()
    {
        EntityPlayer player = mc.thePlayer;
        if (player != null)
        {
            Group playerGroup = GroupManager.groups().getGroupFromPlayer(player.username);
            if (playerGroup != null)
            {
//                System.out.println(player.username);
                playerGroup.renderIcons();
            }
            new EssenceBarIcon().renderIcon(player);
        }
    }

    @Override
    public EnumSet ticks()
    {
        return EnumSet.of(TickType.CLIENT, TickType.RENDER);
    }

    @Override
    public String getLabel()
    {
        return "Sojourn Client Tick Handler";
    }

    public void onTickInGame()
    {
    }
}