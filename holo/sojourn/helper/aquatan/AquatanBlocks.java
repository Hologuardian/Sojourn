package holo.sojourn.helper.aquatan;

import holo.sojourn.block.aquatan.BlockAquatanFlowingWater;
import holo.sojourn.block.aquatan.BlockAquatanWater;
import holo.sojourn.config.aquatan.AquatanBlockConfig;
import net.minecraft.block.BlockFluid;
import net.minecraft.block.material.Material;

public class AquatanBlocks
{
    public static BlockFluid aquatanWater;
    public static BlockFluid aquatanFlowingWater;
    
    public static void initBlocks()
    {
        aquatanWater = (BlockFluid)(new BlockAquatanWater(AquatanBlockConfig.aquatanWaterID, Material.water)).setHardness(100.0F).setLightOpacity(1).setUnlocalizedName("aquatanWater");
        aquatanFlowingWater = (BlockFluid)(new BlockAquatanFlowingWater(AquatanBlockConfig.aquatanWaterID - 1, Material.water)).setHardness(100.0F).setLightOpacity(1).setUnlocalizedName("aquatanWater");

    }
}
