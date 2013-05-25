package holo.sojourn.network.packet;

import holo.sojourn.essence.EssenceBar;
import holo.sojourn.group.Group;
import holo.sojourn.group.GroupManager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler
{

    public void onPacketData(INetworkManager var1, Packet250CustomPayload packet, Player player)
    {
        if (packet.channel.equals("Essence")) 
        {
            handleEssence(packet);
        }
        else if (packet.channel.equals("Group"));
        {
            handleGroup(packet);
        }
    }

    private void handleEssence(Packet250CustomPayload packet) {
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));

        float[] essenceValues = new float[5];
        float[] essenceDefaults = new float[5];
        float essenceLevel = 0F;
        String username = "";

        try 
        {
            username = inputStream.readUTF();
            for(int i = 0; i < 5; i++)
                essenceValues[i] = inputStream.readFloat();
            for(int i = 0; i < 5; i++)
                essenceDefaults[i] = inputStream.readFloat();
            essenceLevel = inputStream.readFloat();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return;
        }

        EssenceBar.bars().ratios.put(username, essenceValues);
        EssenceBar.bars().defaultRatios.put(username, essenceDefaults);
        EssenceBar.bars().levels.put(username, essenceLevel);
    }

    private void handleGroup(Packet250CustomPayload packet) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(packet.data));
        Group group = null;
        ArrayList<String> names = new ArrayList<String>();
        
        try 
        {
            int i = dis.read();
            if (i > 20 || i == 0)
                return;
            
            System.out.println("NUMBER OF PLAYERNAMES IN PACKET " + i);
            
            for (int j = i; j > 0; j--)
            {
                names.add(dis.readUTF());
            }
            System.out.println(names.toString());
            group = GroupManager.groups().createGroupFromList(names);
            GroupManager.groups().registerGroup(group);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return;
        }
    }

    public static void sendGroupPacket(EntityPlayer player)
    {
        Group group = GroupManager.groups().getGroupFromPlayer(player);
        if (group == null)
            return;
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream dos = new DataOutputStream(bos);
        try 
        {
            dos.writeInt(group.getList().size());
            
            for (String name : group.getList())
            {
                dos.writeUTF(name);
            }
            System.out.println(group.getList().toString());
            Packet250CustomPayload packet = new Packet250CustomPayload();
            packet.channel = "Group";
            packet.data = bos.toByteArray();
            packet.length = bos.size();
            
            if (FMLCommonHandler.instance().getSide() == Side.CLIENT)
            {
                PacketDispatcher.sendPacketToServer(packet);
            }
            else if (FMLCommonHandler.instance().getSide() == Side.SERVER)
            {
                PacketDispatcher.sendPacketToPlayer(packet, (Player)player);
            }
            
            dos.close();
            bos.close();
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
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
}