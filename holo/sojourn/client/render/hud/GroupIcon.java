package holo.sojourn.client.render.hud;

import holo.sojourn.group.Group;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;

public class GroupIcon
{
    private final RenderPlayer playerRenderer;
    
    public GroupIcon(EntityPlayer player)
    {
        playerRenderer = new RenderPlayer();
    }
    
    public void renderIcon(Group group, EntityPlayer player)
    {
        playerRenderer.renderPlayer(player, player.posX + 10, player.posY, player.posZ, 0, 0);
    }
}
