package holo.sojourn.handler;

import holo.sojourn.group.Group;
import holo.sojourn.group.GroupManager;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

public class MobEventHandler
{
    static final boolean accountForArmor = false;
    static final float armorScale = 0.2F;
    static final boolean accountForWeapons = false;
    static final float weaponScale = 0.15F;
    static final boolean balanceRanged = false;
    static final boolean accountForDifficulty = false;
    static final boolean harderHardMode = false;
    
    private DifficultyScaler scaler = new DifficultyScaler();
    @ForgeSubscribe
    public void handleLivingSpawn(LivingSpawnEvent e)
    {
        if (!(e.entityLiving instanceof EntityMob))
            return;
        int difficulty = this.getDifficultyValue(e.world, e.entityLiving);
//        if (!e.entityLiving.getEntityName().equals("Bat"))
//            System.out.println(difficulty + " " + e.entityLiving.getEntityName());
        if (difficulty > 0)
        {
            scaler.equipLivingEntity(e.entityLiving, difficulty);
        }
    }
    
    @ForgeSubscribe
    public void handleLivingDrops(LivingDropsEvent e)
    {
        int difficulty = this.getDifficultyValue(e.entityLiving.worldObj, e.entityLiving);
        if(!(e.entityLiving instanceof EntityMob) || difficulty < 1)
            return;
        
        ArrayList<EntityItem> items = new ArrayList<EntityItem>();
        for (EntityItem item : e.drops)
            items.add(item);
        e.drops.clear();
        e.drops.addAll(this.scaler.alterDrops(difficulty, items, e.specialDropValue));
        e.entityLiving.experienceValue *= difficulty;
    }
    
    public int getDifficultyValue(World world, EntityLiving entityLiving)
    {
        int difficulty = 0;
        int armorLevel = 0;
        int weaponLevel = 0;
        int groupSize = 0;
        float meleeRangedRatio = 0F;
        
        int worldScaleDifficulty = world.difficultySetting;
        
        int numPlayers = 0;
        int numMobs = 0;
        if (world == null)
            return 0;
        
        List<Entity> entities = world.loadedEntityList;
        for (Entity ent : entities)
        {
            if(ent.getDistance(entityLiving.posX, entityLiving.posY, entityLiving.posZ) > 128)
                continue;
            if (ent instanceof EntityPlayer)
            {
                armorLevel *= numPlayers;
                weaponLevel *= numPlayers;
                Group group = GroupManager.groups().getGroupFromPlayer(((EntityPlayer) ent).username);
                if (group != null)
                {
                    if (group.getSize() > groupSize)
                        groupSize = group.getSize();
                }
                int tempWepValue = 0;
                for(ItemStack item : ((EntityPlayer) ent).inventory.mainInventory)
                {
                    if (item != null && item.getDamageVsEntity(entityLiving) > tempWepValue)
                        tempWepValue = item.getDamageVsEntity(entityLiving);
                }
                armorLevel += ((EntityPlayer) ent).inventory.getTotalArmorValue();
                weaponLevel += tempWepValue;
                numPlayers++;
                armorLevel /= numPlayers;
                weaponLevel /= numPlayers;
            }
            else if (ent instanceof EntityMob)
            {
                meleeRangedRatio *= numMobs;
                
                if (ent instanceof IRangedAttackMob)
                {
                    meleeRangedRatio += 1;
                }
                else
                {
                    meleeRangedRatio -= 1;
                }
                
                meleeRangedRatio /= numMobs++;
            }
        }
        
        
        if (accountForArmor)
            difficulty += armorLevel * armorScale;
        if (accountForWeapons)
            difficulty += weaponLevel * weaponScale;
        if (accountForDifficulty)
        {
            if (!harderHardMode)
                worldScaleDifficulty /= 2;
            difficulty += worldScaleDifficulty;
        }
        difficulty += groupSize;
        return difficulty;
    }
}
