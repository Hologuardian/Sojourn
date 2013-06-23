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
import java.util.HashMap;
import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class PacketHandler implements IPacketHandler
{

    public void onPacketData(INetworkManager var1, Packet250CustomPayload packet, Player player)
    {
        if (packet.channel.equals("Essence")) 
        {
            handleEssence(packet);
        }
        
        if (packet.channel.equals("SojournGroup"));
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
        HashMap<String, ArrayList<Integer>> potionMap = new HashMap<String, ArrayList<Integer>>();
        
        if (packet.channel.equals("Essence"))
            return;
        
        try 
        {
            int i = dis.readInt();
            if (i == 0)
                return;

            for (int j = i; j > 0; j--)
            {
                String name = dis.readUTF();
                names.add(name);
                potionMap.put(name, new ArrayList<Integer>());
                int n = dis.readInt();
                for (int m = n; m > 0; m--)
                {
                    Integer potionID = dis.readInt();
                    
                    ArrayList<Integer> temp = potionMap.get(name);
                    temp.add(potionID);
                    potionMap.put(name, temp);
                }
//                System.out.println();
            }

//            System.out.println(Minecraft.getMinecraft().thePlayer.username + "RECIEVING " + i + " " + names.toString());
            group = GroupManager.groups().createGroupFromList(names);
            if (group != null)
            {
                group.potionMap.putAll(potionMap);
                GroupManager.groups().registerGroup(group);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
            return;
        }
    }

    public static void sendGroupPacket(String playername, ArrayList<String> names)
    {
        ServerConfigurationManager serv = MinecraftServer.getServer().getConfigurationManager();
        EntityPlayer player = serv.getPlayerForUsername(playername);
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        try 
        {
            dos.writeInt(names.size());
            
            for (String name : names)
            {
                dos.writeUTF(name);
                dos.writeInt(serv.getPlayerForUsername(name).getActivePotionEffects().size());
                Iterator iterator = serv.getPlayerForUsername(name).getActivePotionEffects().iterator();
                while (iterator.hasNext())
                {
                    PotionEffect effect = (PotionEffect) iterator.next();
                    int dat = effect.getPotionID();
                    dos.writeInt(dat);
//                    System.out.print("WRITING " + dat + " ");
                }
//                System.out.println();
            }
//            System.out.println("SENDING " + names.size() + " " + names.toString());
            
        } 
        catch (IOException ex) 
        {
            ex.printStackTrace();
        }
        
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "SojournGroup";
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        
//        System.out.println("Sending " + names + " to: " + playername);
        PacketDispatcher.sendPacketToPlayer(packet, (Player)player);
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