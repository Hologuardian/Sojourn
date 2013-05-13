package holo.sojourn.client.render.hud;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class EssenceBarIcon
{

    public final Minecraft mc = Minecraft.getMinecraft();
    private int updateCounter;
    private Random rand = new Random();
    
    public EssenceBarIcon(EntityPlayer player)
    {
    }
    
    public void renderIcon(EntityPlayer player)
    {
        if (mc.currentScreen == null || !mc.currentScreen.doesGuiPauseGame())
        {
            
        }
        ++this.updateCounter;
    }
}
