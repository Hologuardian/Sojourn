package holo.sojourn.world.pythican.feature;

import holo.utils.world.feature.BaseMapGen;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureStart;

public class MapGenMassiveCanopyTree extends BaseMapGen
{
	private Map treeMap = new HashMap<Long, Integer>();
	int spacing;
	int chance = 1;
	int baseHeight;
	int baseRadius;
	int baseCanopyHeight;
	int baseCanopyRadius;

	public MapGenMassiveCanopyTree(int spread, int space, int trunkHeight, int trunkRadius, int leafRadius, int leafHeight)
	{
		super();
		chance = spread;
		if(chance < 1)
			chance = 1;
		this.spacing = space;
		this.baseHeight = trunkHeight;
		this.baseRadius = trunkRadius;
		this.baseCanopyHeight = leafHeight;
		this.baseCanopyRadius = leafRadius;
		range = baseCanopyRadius / 16 + 1;
	}

	public void generateTrunk(long seed, int centerX, int centerZ, short[] shortArray, int x, int y, int z, int radius, int height)
	{
		int cX = centerX * 16 + 8;
		int cZ = centerZ * 16 + 8;

		for(float i = x; i < x + 16; ++i)
		{
			for(float j = y; j <= y + height && y < 256; ++j)
			{
				for(float k = z; k < z + 16; ++k)
				{
					int key = hashCoord((int)(i - x), (int)j, (int)(k - z));
					float y1 = height - (j - y) - rand.nextInt(radius/2);
					if ((i - cX) * (i - cX) + (k - cZ) * (k - cZ) <= (radius * radius + y1) && key < 65536)
					{
						shortArray[key] = (short) Block.wood.blockID;
					}
				}
			}
		}
	}

	public void generateCanopy(long seed, int centerX, int centerZ, short[] shortArray, int x, int y, int z, int radius, int height)
	{
		int cX = centerX * 16 + 8;
		int cZ = centerZ * 16 + 8;
		float uDiv = (radius * radius) / (height / 2);
		float lDiv = (radius * radius) / (height / 2);
		boolean vineFlag = false;

		for(float i = x; i < x + 16; ++i)
		{
			for(float k = z; k < z + 16; ++k)
			{
				if((int)(i) % (18 + rand.nextInt(5)) == 0 && (int)(k) % (17 + rand.nextInt(6)) == 0)
					vineFlag = true;
				else
					vineFlag = false;
				
				int groundHeight = getTopSolidOrLiquidBlock((int)(i - x), (int)(k - z), shortArray);
				
				for(float j = y + height; j >= 0 && y < 256; --j)
				{
					int key = hashCoord((int)(i - x), (int)j, (int)(k - z));
					float bx = (i - cX);
					float bz = (k - cZ);
					float by = (j - y);
					if (inCanopy(bx, by, bz, radius, height, lDiv, uDiv) && key < 65536)
					{
						shortArray[key] = (short) Block.leaves.blockID;
					}
					
					if(vineFlag && by <= lowerCanopyY(bx, by, bz, radius, height, lDiv) && key < 65536 &&  j > groundHeight + 10
							&& bx * bx + bz * bz <= radius * radius - 25)
					{
						shortArray[key] = (short) Block.leaves.blockID;
					}
				}
			}
		}
	}

	public boolean inCanopy(float x, float y, float z, float r, float h, float d, float uD)
	{
		if(y <= upperCanopyY(x, y, z, r, h, uD) && y >= lowerCanopyY(x, y, z, r, h, d))
			return true;
		return false;
	}

	public float lowerCanopyY(float x, float y, float z, float r, float h, float div)
	{
		return (x * x + z * z) / div;
	}

	public float upperCanopyY(float x, float y, float z, float r, float h, float div)
	{
		return h - (x * x + z * z) / div;
	}

	@Override
	protected void recursiveGenerate(World world, int chunkX, int chunkZ, int centerX, int centerZ, short[] shortArray) 
	{
		int x = centerX * 16;
		int z = centerZ * 16;
		int y = 30;
		if(!this.treeMap.containsKey(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(chunkX, chunkZ))))
		{
			if(chunkX % (this.range + rand.nextInt(chance * 2) - chance) == 0 && chunkZ % (this.range + rand.nextInt(chance * 2) - chance) == 0)
			{
				this.treeMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(chunkX, chunkZ)), y);
				this.generateTree(world, Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(centerX, centerZ)), chunkX, chunkZ, shortArray, x, (int)this.treeMap.get(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(chunkX, chunkZ))), z);
			}
		}
		else
		{
			this.generateTree(world, Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(centerX, centerZ)), chunkX, chunkZ, shortArray, x, (int)this.treeMap.get(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(chunkX, chunkZ))), z);
		}
	}

	public void generateTree(World world, long seed, int centerX, int centerZ, short[] shortArray, int x, int y, int z)
	{
		float rDiv = (rand.nextFloat() * 0.05F + 1.0F);
		float hDiv = (rand.nextFloat() * 0.05F + 1.0F);
		
		this.generateTrunk(seed, centerX, centerZ, shortArray, x, y, z, (int)(baseRadius * rDiv), (int)(baseHeight * hDiv));
		this.generateCanopy(seed, centerX, centerZ, shortArray, x, y + (int)(baseHeight * hDiv), z, (int)(baseCanopyRadius * rDiv), (int)(baseCanopyHeight * hDiv));
	}

	@Override
	public boolean shouldGenerate(int centerX, int centerZ) 
	{
		return true;
	}
}