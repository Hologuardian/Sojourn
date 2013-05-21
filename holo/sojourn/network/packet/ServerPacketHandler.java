package holo.sojourn.network.packet;

import holo.sojourn.essence.EssenceBar;
import holo.sojourn.group.Group;
import holo.sojourn.group.GroupManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class ServerPacketHandler implements IPacketHandler
{
    public void onPacketData(INetworkManager par1, Packet250CustomPayload par2, Player par3)
    {
        new DataInputStream(new ByteArrayInputStream(par2.data));
    }

    public static void sendBarsPacket(EntityPlayer player)
    {
        float[] essenceValues = EssenceBar.bars().ratios.get(player.username);
        float[] essenceDefaults = EssenceBar.bars().defaultRatios.get(player.username);
        float essenceLevel = EssenceBar.bars().levels.get(player.username);

        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try 
        {
            outputStream.writeUTF(player.username);
            for(int i = 0; i < 5; i++)
                outputStream.writeFloat(essenceValues[i]);
            for(int i = 0; i < 5; i++)
                outputStream.writeFloat(essenceDefaults[i]);
            outputStream.writeFloat(essenceLevel);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "Essence";
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        PacketDispatcher.sendPacketToPlayer(packet, (Player)player);
    }

    public static void sendGroupPacket(EntityPlayer player)
    {
        Group group = GroupManager.groups().getGroupFromPlayer(player);
        if (group == null)
            return;
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try 
        {

            outputStream.write(group.getSize());
            for (EntityPlayer p : group.getList())
                if (p != null)
                    outputStream.writeUTF(p.username);
        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }

        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "Group";
        packet.data = bos.toByteArray();
        packet.length = bos.size();

        PacketDispatcher.sendPacketToPlayer(packet, (Player)player);
    }
}