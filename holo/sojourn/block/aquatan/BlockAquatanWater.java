package holo.sojourn.block.aquatan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAquatanWater extends BlockStationary
{

    public BlockAquatanWater(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.disableStats();
        this.setCreativeTab(CreativeTabs.tabRedstone);
        System.out.println("Making Water" + (byte) this.blockID);
    }
    
}
