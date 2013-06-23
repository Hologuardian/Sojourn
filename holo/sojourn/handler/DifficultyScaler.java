package holo.sojourn.handler;

import holo.sojourn.item.base.EssenceItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class DifficultyScaler
{
    public Random rand = new Random();
    public HashMap<Integer, ArrayList<Item>> weaponMap = new HashMap<Integer, ArrayList<Item>>();
    
    public DifficultyScaler()
    {
        this.addWeaponToList(1, Item.swordWood);
        this.addWeaponToList(3, Item.swordGold);
        this.addWeaponToList(5, Item.swordStone);
        this.addWeaponToList(7, Item.swordIron);
        this.addWeaponToList(9, Item.swordDiamond);
    }
    
    public void equipLivingEntity(EntityLiving entity, int difficulty)
    {
        int enchantLevel = 0;
        int potionValue = 0;
        if (difficulty > 6)
            enchantLevel = 7 * (difficulty - 7);
        else 
            enchantLevel = 0;
        
        for(int i = 1; i < 5; i++)
        {
            Item item = entity.getArmorItemForSlot(i, Math.min(4, difficulty / 3));
            if (item == null)
                continue;
            ItemStack stack = new ItemStack(item);
            
            this.enchantItem(stack, enchantLevel);
            entity.setCurrentItemOrArmor(i, stack);
        }
        
        if (!(entity instanceof IRangedAttackMob))
        {
            ArrayList<Item> meleeWeapons = null;
            int weapon = difficulty;
            
            while (meleeWeapons == null)
            {
                if (weapon < 0)
                    break;
                
                meleeWeapons = this.getWeaponsForValue(weapon);
                weapon--;
            }
            
            if (meleeWeapons != null)
            {
                ItemStack stack = new ItemStack(meleeWeapons.get(this.rand.nextInt(meleeWeapons.size())));
                this.enchantItem(stack, enchantLevel);
                entity.setCurrentItemOrArmor(0, stack);
            }
        }
        
        //TODO Potions
        if (potionValue > 0)
        {
            entity.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 999999, 2));
        }
    }
    
    public void addWeaponToList(int slot, Item item)
    {
        if (item == null)
            return;
        
        if (this.weaponMap.containsKey(slot))
        {
            ArrayList<Item> list = this.weaponMap.get(slot);
            list.add(item);
            this.weaponMap.put(slot, list);
        }
        else
        {
            ArrayList<Item> list = new ArrayList<Item>();
            list.add(item);
            this.weaponMap.put(slot, list);
        }
    }
    
    public ArrayList<Item> getWeaponsForValue(int value)
    {
        if (weaponMap.containsKey(value))
            return weaponMap.get(value);
        else
            return null;
    }
    
    public ItemStack enchantItem(ItemStack stack, int enchantLevel)
    {
        if (enchantLevel > 0)
        {
            int enchantPower = EnchantmentHelper.calcItemStackEnchantability(this.rand, 1, enchantLevel, stack);
            List list = EnchantmentHelper.buildEnchantmentList(this.rand, stack, enchantPower);
            for (int k = 0; k < list.size(); ++k)
            {
                EnchantmentData enchantmentdata = (EnchantmentData)list.get(k);

                stack.addEnchantment(enchantmentdata.enchantmentobj, enchantmentdata.enchantmentLevel);
            }
        }
        return stack;
    }
    
    public ArrayList<EntityItem> alterDrops(int difficulty, ArrayList<EntityItem> drops, int specialDrops)
    {
        //TODO Change armor drop rarity.
        System.out.println(specialDrops);
        ArrayList<EntityItem> newDrops = new ArrayList<EntityItem>();
        for(EntityItem i : drops)
        {
            EntityItem item = i;
            if (item.getEntityItem().getItem() instanceof ItemSword ||
                    item.getEntityItem().getItem() instanceof ItemBow ||
                    item.getEntityItem().getItem() instanceof ItemArmor ||
                    item.getEntityItem().getItem() instanceof EssenceItem
                    )
            {
                if (this.rand.nextInt(150) == 0)
                {
                    ItemStack stack = item.getEntityItem();
                    stack.setItemDamage(0);
                    item.setEntityItemStack(stack);
                }
            }
            else
            {
                ItemStack stack = item.getEntityItem();
                stack.stackSize *= difficulty / 3;
                item.setEntityItemStack(stack);
            }
            newDrops.add(item);
            
        }
        
        return newDrops;
    }
}
