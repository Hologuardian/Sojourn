package holo.sojourn.block.aquatan;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockAquatanFlowingWater extends BlockFlowing
{

    public BlockAquatanFlowingWater(int par1, Material par2Material)
    {
        super(par1, par2Material);
        this.disableStats();
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    @SideOnly(Side.CLIENT)

    /**
     * Returns which pass should this block be rendered on. 0 for solids and 1 for alpha
     */
    public int getRenderBlockPass()
    {
        return this.blockMaterial == Material.water ? 1 : 0;
    }
}
