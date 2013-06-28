package holo.sojourn.item.aracoria;

import holo.sojourn.config.DimensionConfig;
import holo.sojourn.item.base.ItemSojourn;
import holo.sojourn.teleporter.AracoriaTeleporter;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class AracoriaDimensionTraveler extends ItemSojourn
{
    
    public AracoriaDimensionTraveler(int par1, String name)
    {
        super(par1, name);
        this.setCreativeTab(CreativeTabs.tabRedstone);
    }
    
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer playerMP)
    {
        if (playerMP.ridingEntity == null && playerMP.riddenByEntity == null)
        {
            if(playerMP instanceof EntityPlayerMP)
            {
                int dimensionID = DimensionConfig.fungalMarshDimensionID;
                EntityPlayer player = (EntityPlayerMP) playerMP;
                //par5Entity.setInPortal();
                //if(par5Entity.getPortalCooldown() == 0)
                //if(Math.random() > 0.999)

                if(playerMP.timeUntilPortal == 0 && playerMP instanceof EntityPlayerMP)
                {
                    playerMP.timeUntilPortal = 100;
                    MinecraftServer minecraftserver = MinecraftServer.getServer();
                    int dimID = playerMP.dimension;
                    WorldServer worldserver = minecraftserver.worldServerForDimension(0);
                    WorldServer worldserver1 = minecraftserver.worldServerForDimension(dimensionID);
                    if(dimID == dimensionID)
                    {
                        minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) playerMP, 0, new AracoriaTeleporter(worldserver));
                        
                    } else {
                        minecraftserver.getConfigurationManager().transferPlayerToDimension((EntityPlayerMP) playerMP, dimensionID, new AracoriaTeleporter(worldserver1));
                        //par5Entity.travelToDimension(Atum.dimensionID);
                    }
                }

            }
        }
        return stack;
    }

}
