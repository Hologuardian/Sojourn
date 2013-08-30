package holo.sojourn.effect;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;

public abstract class BasePotion extends Potion
{
	public boolean instant = false;
	
    public BasePotion(int id, boolean badEffect, int liquidColor)
    {
        super(id, badEffect, liquidColor);
    }

    /**
     * Returns true if the potion has an instant effect instead of a continuous one (eg Harming)
     */
    public boolean isInstant()
    {
        return instant;
    }

    /**
     * checks if Potion effect is ready to be applied this tick.
     */
    public boolean isReady(int par1, int par2)
    {
        return shouldActivate(par1, par2);
    }
    
    public abstract boolean shouldActivate(int ID, int ticks);
}