package holo.sojourn.effect;

import java.util.ArrayList;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import cpw.mods.fml.common.IPlayerTracker;

public class EffectManager implements IPlayerTracker
{
	private static EffectManager effectManager = new EffectManager();
	public static EffectManager manager(){return effectManager;}
	
	public Table<EntityLivingBase, BaseEffect, Integer> activeEffects = HashBasedTable.create();
	
	public ArrayList<BaseEffect> effectList = new ArrayList<BaseEffect>();
	
	public void updateEffects()
	{
		for(EntityLivingBase entity : activeEffects.rowKeySet())
		{
			Map<BaseEffect, Integer> entityEffects = activeEffects.row(entity);
			for (BaseEffect effect : entityEffects.keySet())
			{
				int time = entityEffects.get(effect);
				--time;
				if (time < 0)
					activeEffects.remove(entity, effect);
				else
				{
					effect.updateEffect(entity, time);
					activeEffects.put(entity, effect, time);
				}
			}
		}
	}
	
	public void addEffect(EntityLivingBase entity, int effectID, int time)
	{
		activeEffects.put(entity, effectList.get(effectID), time);
	}
	
	
	@Override
	public void onPlayerLogin(EntityPlayer player) 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		player.writeEntityToNBT(nbt);
		if (nbt != null && nbt.hasKey("SojournEffect") && nbt.hasKey("SojournEffectTime"))
		{
			int[] effects = nbt.getIntArray("SojournEffect");
			int[] effectTimers = nbt.getIntArray("SojournEffectTime");
			
			if (effects == null || effectTimers == null || effects.length == 0 || effectTimers.length == 0)
				return;
			if(effects.length != effectTimers.length)
				System.out.println("Something went wrong loading player effects");
			else
			{
				for(int i = 0; i < effects.length; ++i)
				{
					activeEffects.put(player, effectList.get(effects[i]), effectTimers[i]);
				}
			}
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player) 
	{
		NBTTagCompound nbt = new NBTTagCompound();
		player.writeEntityToNBT(nbt);
		
		if (activeEffects.containsRow(player))
		{
			int[] effectIDs = new int[activeEffects.row(player).keySet().size()];
			int[] effectTimes = new int[activeEffects.row(player).keySet().size()];
			int i = 0;
			for (BaseEffect e : activeEffects.row(player).keySet())
			{
				effectIDs[i] = effectList.indexOf(e);
				effectTimes[i] = activeEffects.get(player, e);
				activeEffects.remove(player, e);
				++i;
			}
			nbt.setIntArray("SojournEffect", effectIDs);
			nbt.setIntArray("SojournEffectTime", effectTimes);
		}
		
		player.readEntityFromNBT(nbt);
	}
	
	@ForgeSubscribe
	public void onLivingDeathEvent(LivingDeathEvent event)
	{
		EntityLivingBase entity = event.entityLiving;
		
		if (activeEffects.containsRow(entity))
		{
			for (BaseEffect e : activeEffects.row(entity).keySet())
			{
				activeEffects.remove(entity, e);
			}
		}
		
		
	}

	
	@Override
	public void onPlayerChangedDimension(EntityPlayer player) {}
	@Override
	public void onPlayerRespawn(EntityPlayer player) {}

	public void registerEffect(BaseEffect baseEffect, int ID) 
	{
		effectList.add(ID, baseEffect);
	}
}
