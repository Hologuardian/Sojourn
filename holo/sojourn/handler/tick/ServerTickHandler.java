package holo.sojourn.handler.tick;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ServerTickHandler implements ITickHandler
{

    @Override
    public void tickStart(EnumSet var1, Object ... var2) {}

    @Override
    public void tickEnd(EnumSet var1, Object ... var2)
    {
        onTickInGame();
    }

    @Override
    public EnumSet ticks()
    {
        return EnumSet.of(TickType.SERVER);
    }
    
    @Override
    public String getLabel()
    {
        return "Falling Earth Server Tick Handler";
    }

    public void onTickInGame()
    {
    }
}