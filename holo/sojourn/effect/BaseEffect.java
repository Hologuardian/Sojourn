package holo.sojourn.effect;

import net.minecraft.entity.EntityLivingBase;

public abstract class BaseEffect 
{
	public int effectID;
	
	public BaseEffect(int ID)
	{
		this.effectID = ID;
		EffectManager.manager().registerEffect(this, ID);
	}
	
	public abstract boolean shouldActivate(EntityLivingBase target, int ticksActive);
	
	public abstract void activateEffect(EntityLivingBase target, int ticksActive);
	
	public void updateEffect(EntityLivingBase target, int ticksActive)
	{
		if (this.shouldActivate(target, ticksActive))
			this.activateEffect(target, ticksActive);
	}
}
