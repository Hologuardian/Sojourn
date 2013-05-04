package holo.sojourn.handler.tick;

import java.util.EnumSet;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class ClientTickHandler implements ITickHandler
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
        return EnumSet.of(TickType.CLIENT);
    }

    @Override
    public String getLabel()
    {
        return "Falling Earth Client Tick Handler";
    }

    public void onTickInGame()
    {
    }
}