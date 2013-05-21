package holo.sojourn.network.packet;

import holo.sojourn.essence.EssenceBar;
import holo.sojourn.group.Group;
import holo.sojourn.group.GroupManager;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

public class ClientPacketHandler implements IPacketHandler
{
    public void onPacketData(INetworkManager var1, Packet250CustomPayload packet, Player player)
    {
        new DataInputStream(new ByteArrayInputStream(packet.data));

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
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        Group group = null;

        try 
        {
            for(int i = 0; i < inputStream.read(); i++)
            {
                if (i == 0)
                    group = new Group(Minecraft.getMinecraft().thePlayer.worldObj.getPlayerEntityByName(inputStream.readUTF()));
                else
                    group.addPlayer(Minecraft.getMinecraft().thePlayer.worldObj.getPlayerEntityByName(inputStream.readUTF()));

            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return;
        }
        
        if (group != null)
            GroupManager.groups().registerGroup(group);
    }
}