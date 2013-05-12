package holo.sojourn.essence;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

public class EssenceBar
{
    public HashMap<String, float[]> ratios = new HashMap<String, float[]>();
    public HashMap<String, float[]> defaultRatios = new HashMap<String, float[]>();
    public HashMap<String, Float> levels = new HashMap<String, Float>();

    public void addPlayer(EntityPlayer player)
    {
        ratios.put(player.username, new float[]{0.2F, 0.2F, 0.2F, 0.2F, 0.2F});
        defaultRatios.put(player.username, new float[]{0.2F, 0.2F, 0.2F, 0.2F, 0.2F});
        levels.put(player.username, 50F);
    }

    public float getEssenceAmount (EntityPlayer player, int essence)
    {
        return ratios.get(player)[essence] * levels.get(player);
    }

    public void addScaledEssence(EntityPlayer player, int essence, float amount)
    {
        float[] essences = ratios.get(player.username);
        essences[essence] += amount / levels.get(player.username);
        ratios.put(player.username, essences);
    }

    public void reduceEssenceAmount(EntityPlayer player, int essence, float amount)
    {
        float[] essences = ratios.get(player.username);
        essences[essence] -= amount / levels.get(player.username);
        for (int i = 0; i < 5; ++i)
            if (i != essence)
                addScaledEssence(player, i, amount / 4);
        ratios.put(player.username, essences);
    }

    public boolean consumeEssence(EntityPlayer player, int essence, float amount)
    {
        if (getEssenceAmount(player, essence) >= amount)
        {
            return true;
        }
        return false;
    }

    public void updateBar(EntityPlayer player)
    {
        float[] essences = ratios.get(player.username);
        for (int i = 0; i < 5; i++)
        {
            if (ratios.get(player)[i] <= defaultRatios.get(player)[i] - 0.001)
                essences[i] += 0.001;
            else if (ratios.get(player)[i] >= defaultRatios.get(player)[i] + 0.001)
                essences[i] -= 0.001;
        }
        ratios.put(player.username, essences);
    }
}