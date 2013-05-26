package holo.sojourn.event;

import holo.sojourn.group.GroupManager;

import java.util.List;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class DifficultyScaler
{
    @ForgeSubscribe
    public void onEntitySpawnEvent(LivingSpawnEvent e)
    {
        World world = e.world;
        EntityLiving ent = e.entityLiving;
        int difficulty = 0;
        List<EntityPlayerMP> playerList = world.playerEntities;
        
        for (EntityPlayerMP player : playerList)
            if (player != null)
                if (GroupManager.groups().getGroupFromPlayer(player.username) != null)
                    if (GroupManager.groups().getGroupFromPlayer(player.username).getSize() >= difficulty && ent.getDistanceToEntity(player) <= 128)
                        difficulty = GroupManager.groups().getGroupFromPlayer(player.username).getSize();
        
        
    }
}
