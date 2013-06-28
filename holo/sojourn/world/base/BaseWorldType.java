package holo.sojourn.world.base;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseWorldType extends WorldType
{
    public Map<BiomeGenBase, BiomeGenBase> hills;
    public Map<BiomeGenBase, Map<BiomeGenBase, BiomeGenBase>> subBiomes;
    public BiomeGenBase river;
    
    public int waterHeight;
    public int snowHeight;
    public int biomeSize;
    
    public BaseWorldType(int par1, String par2Str, int par3)
    {
        super(par1, par2Str, par3);
        this.hills = new HashMap<BiomeGenBase, BiomeGenBase>();
        this.subBiomes = new HashMap<BiomeGenBase, Map<BiomeGenBase, BiomeGenBase>>();
        this.river = null;
        this.waterHeight = 121;
        this.snowHeight = 205;
        this.biomeSize = 4;
    }
    
    public BaseWorldType(int par1, String par2Str)
    {
        super(par1, par2Str);
        this.hills = new HashMap<BiomeGenBase, BiomeGenBase>();
        this.subBiomes = new HashMap<BiomeGenBase, Map<BiomeGenBase, BiomeGenBase>>();
        this.river = null;
        this.waterHeight = 121;
        this.snowHeight = 205;
        this.biomeSize = 4;
    }
    
    public BaseWorldType(int par1, String par2Str, Map<BiomeGenBase, BiomeGenBase> hillBiomes, Map<BiomeGenBase, Map<BiomeGenBase, BiomeGenBase>> subB, BiomeGenBase rivers)
    {
        super(par1, par2Str);
        this.hills = hillBiomes;
        this.subBiomes = subB;
        this.river = rivers;
        this.waterHeight = 121;
        this.snowHeight = 205;
        this.biomeSize = 4;
    }
    
    public void setWaterSnowHeight(int water, int snow)
    {
        this.waterHeight = water;
        this.snowHeight = snow;
    }
    
    public void setBiomeSize(int size)
    {
        this.biomeSize = size;
    }
    
    public void addBiomeTransition(BiomeGenBase biome1, BiomeGenBase biome2, BiomeGenBase transition)
    {
        Map<BiomeGenBase, BiomeGenBase> biomes;
        if (!this.subBiomes.containsKey(biome1))
        {
            biomes = new HashMap<BiomeGenBase, BiomeGenBase>();
        }
        else
        {
            biomes = this.subBiomes.get(biome1);
        }
        
        if (!biomes.containsValue(transition))
            biomes.put(biome2, transition);
        
        this.subBiomes.put(biome1, biomes);
    }

    public int getMinimumSpawnHeight(World world)
    {
        return this.waterHeight;
    }

    public double getHorizon(World world)
    {
        return this.waterHeight;
    }
}
