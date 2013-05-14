package holo.sojourn.essence;

import holo.sojourn.client.render.hud.EssenceBarIcon;

import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import cpw.mods.fml.common.IPlayerTracker;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EssenceBar implements IPlayerTracker
{
    public static final EssenceBar manager = new EssenceBar();
    
    public HashMap<String, float[]> ratios = new HashMap<String, float[]>();
    public HashMap<String, float[]> defaultRatios = new HashMap<String, float[]>();
    public HashMap<String, Float> levels = new HashMap<String, Float>();
    public HashMap<String, EssenceBarIcon> icons = new HashMap<String, EssenceBarIcon>();
    private NBTTagCompound data;

    public static final EssenceBar bars()
    {
        return manager;
    }
    
    public void addPlayer(EntityPlayer player)
    {
        ratios.put(player.username, new float[]{0.2F, 0.2F, 0.2F, 0.2F, 0.2F});
        defaultRatios.put(player.username, new float[]{0.2F, 0.2F, 0.2F, 0.2F, 0.2F});
        levels.put(player.username, 50F);
        icons.put(player.username, new EssenceBarIcon(player));
    }
    
    public void addPlayer(EntityPlayer player, float[] essences, float[] defaultEssences, float level)
    {
        ratios.put(player.username, essences);
        defaultRatios.put(player.username, defaultEssences);
        levels.put(player.username, level);
        icons.put(player.username, new EssenceBarIcon(player));
    }

    public float getEssenceAmount (EntityPlayer player, int essence)
    {
        return ratios.get(player.username)[essence] * levels.get(player.username);
    }

    public float getScaledEssenceAmount (EntityPlayer player, int essence)
    {
        return ratios.get(player.username)[essence] * 105;
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
                addScaledEssence(player, i, amount / (94 * levels.get(player.username)));
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

    public void updateBar(EntityPlayer player, int delta)
    {
        if (hasPlayer(player))
        {
            addPlayer(player);
        }
        float[] essences = ratios.get(player.username);
        
        for (int j = 0; j < delta; j++)
        {
            for (int i = 0; i < 5; i++)
            {
                if (ratios.get(player.username)[i] <= defaultRatios.get(player.username)[i] - 0.001)
                    essences[i] += 0.001;
                else if (ratios.get(player.username)[i] >= defaultRatios.get(player.username)[i] + 0.001)
                    essences[i] -= 0.001;
                else
                    essences[i] = defaultRatios.get(player.username)[i];
            }
        }
        ratios.put(player.username, essences);
    }
    
    public float[] getScaledEssences(EntityPlayer player)
    {
        float[] essences = new float[5];
        
        for (int i = 0; i < 5; i++)
        {
            essences[i] = getScaledEssenceAmount(player, i);
        }
        
        return essences;
    }
    
    @SideOnly(Side.CLIENT)
    public void renderBar(EntityPlayer player)
    {
        icons.get(player.username).renderIcon(player);
    }
    
    public boolean hasPlayer(EntityPlayer player)
    {
        return ratios.containsKey(player.username) && defaultRatios.containsKey(player.username) && levels.containsKey(player.username);
    }
    
    public void saveToPlayerNBT(EntityPlayer player)
    {
        data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
        
        NBTTagList tagList = new NBTTagList();
        
        if (hasPlayer(player))
        {
            addPlayer(player);
        }
        
        float[] essences = ratios.get(player.username);
        float[] defaultEssences = defaultRatios.get(player.username);
        float level = levels.get(player.username);

        NBTTagCompound nbttagcompound;
        
        for (int i = 0; i < 5; ++i)
        {
            nbttagcompound = new NBTTagCompound();
            nbttagcompound.setFloat("essence", i);
            nbttagcompound.setFloat("ratio", essences[i]);
            nbttagcompound.setFloat("defaultRatio", defaultEssences[i]);
            tagList.appendTag(nbttagcompound);
        }
        
        nbttagcompound = new NBTTagCompound();
        nbttagcompound.setFloat("level", level);
        tagList.appendTag(nbttagcompound);
        
        data.setTag("EssenceBar", tagList);
    }
    
    public void readFromPlayerNBT(EntityPlayer player)
    {
        data = player.getEntityData().getCompoundTag(player.PERSISTED_NBT_TAG);
        player.getEntityData().setCompoundTag(player.PERSISTED_NBT_TAG, data);
        int i = 0;
        
        NBTTagList tagList = data.getTagList("EssenceBar");
        float[] essences = new float[5];
        float[] defaultEssences = new float[5];
        float level = 50F;
        
        
        for (int j = 0; j < tagList.tagCount() - 1; ++j)
        {
            NBTTagCompound nbttagcompound = (NBTTagCompound)tagList.tagAt(j);
            essences[j] = nbttagcompound.getFloat("ratio");
            defaultEssences[j] = nbttagcompound.getFloat("defaultRatio");
            i++;
        }
        
        NBTTagCompound nbttagcompound = (NBTTagCompound)tagList.tagAt(i);
        level = nbttagcompound.getFloat("level");
        
        addPlayer(player, essences, defaultEssences, level);
    }
    
    @Override
    public void onPlayerLogin(EntityPlayer player)
    {
        readFromPlayerNBT(player);
    }

    @Override
    public void onPlayerLogout(EntityPlayer player)
    {
        saveToPlayerNBT(player);
    }

    @Override
    public void onPlayerChangedDimension(EntityPlayer player)
    {
    }

    @Override
    public void onPlayerRespawn(EntityPlayer player)
    {
    }
}