package holo.sojourn.item.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

public class ItemSojourn extends Item
{
    public String textureName;
    public ItemSojourn(int par1, String name)
    {
        super(par1);
        this.textureName = name;
        this.setUnlocalizedName(name);
    }
    
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.itemIcon = par1IconRegister.registerIcon("/res/texture/item/" + this.textureName);
    }
}
