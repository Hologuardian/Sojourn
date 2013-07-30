package holo.sojourn.locations;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatMessageComponent;

public class ControlPoint 
{
	public int dimensionID;
	public int[] location;
	public int effectID;
	public EntityLivingBase controller;
	
	public final int captureTime;
	public int captureProgress;
	
	public ControlPoint(int capTime, int eID, int[] position, int dimID)
	{
		captureTime = capTime;
		effectID = eID;
		location = position;
		dimensionID = dimID;
	}
	
	public void captureTick(EntityLivingBase entity)
	{
		if (captureTime == 0 && this.controller instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)this.controller;
			String message = "Entity " + entity.getEntityName() + " is attempting to take your control point in dimension "
					+ this.dimensionID + " at X: " + this.location[0] + " Y: " + this.location[1] + "Z: " + this.location[2] + "!";
			ChatMessageComponent messageComp = new ChatMessageComponent();
			messageComp.func_111079_a(message);
			player.sendChatToPlayer(messageComp);
		}
	}
}
