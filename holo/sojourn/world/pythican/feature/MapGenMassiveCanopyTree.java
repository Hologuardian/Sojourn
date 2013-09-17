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
	private static final float villageRadius = 80;

	private static final int platformRadius = 20;

	private static int resR = 16;
	private static int marketR = 10;
	private static int industrialR = 7;
	private static int farmR = 20;

	private static int resDist = 10;
	private static int indDist = 30;
	private static int mDist = 5;
	private static int fDist = 15;

	private static int spiralWidth = 5;
	private static int walkwayWidth = 3;
	private static int villageRarity = 7;
	private static int spiralHeight = 15;
	private static int villageHeight = 20;
	private static int numPlatforms = 3;

	private Map<Long, Boolean> treeMap = new HashMap<Long, Boolean>();
	int treeNumber = 0;
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

	public void generateTrunk(long seed, int centerX, int centerZ, short[] shortArray, int x, int y, int z, int r, int h, boolean village)
	{
		int cX = centerX * 16 + 8;
		int cZ = centerZ * 16 + 8;

		for(float i = x; i < x + 16; ++i)
		{
			for(float j = y; j <= y + h && y < 256; ++j)
			{
				for(float k = z; k < z + 16; ++k)
				{
					int key = hashCoord((int)(i - x), (int)j, (int)(k - z));
					float y1 = h - (j - y) - rand.nextInt(r/2);
					float bx = (i - cX);
					float by = (j - y);
					float bz = (k - cZ);

					if (bx * bx + bz * bz <= (r * r + y1) && key < 65536)
					{
						shortArray[key] = (short) Block.wood.blockID;
					}
					else if(village && by < h - villageHeight && (bx * bx + bz * bz <= (r + spiralWidth) * (r + spiralWidth) + y1 && key < 65536 && inSpiral(bx, by, bz, spiralHeight)))
					{
						shortArray[key] = (short) Block.planks.blockID;
					}
				}
			}
		}
	}

	public boolean inSpiral(float x, float y, float z, int h)
	{
		double theta = Math.atan2(z, x);
		if(theta < 0)
			theta = Math.PI * 2 + theta;

		if(fastFloor((theta / (Math.PI * 2)) * h) == (y % h))
			return true;
		return false;
	}

	public int fastFloor(double x)
	{
		return (int) x >= 0 ? (int) x : (int)(x - 1);
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

				for(float j = y + height; j >= 0 && j < 256; --j)
				{
					int key = hashCoord((int)(i - x), (int)j, (int)(k - z));
					float bx = (i - cX);
					float by = (j - y);
					float bz = (k - cZ);
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
				boolean genVillage = false;
				if(treeNumber % villageRarity == 0)
					genVillage = true;
				++treeNumber;
				this.treeMap.put(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(chunkX, chunkZ)), genVillage);
				this.generateTree(world, Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(centerX, centerZ)), chunkX, chunkZ, shortArray, x, y, z, this.treeMap.get(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(chunkX, chunkZ))));
			}
		}
		else
		{
			this.generateTree(world, Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(centerX, centerZ)), chunkX, chunkZ, shortArray, x, y, z, this.treeMap.get(Long.valueOf(ChunkCoordIntPair.chunkXZ2Int(chunkX, chunkZ))));
		}
	}

	public void generateTree(World world, long seed, int centerX, int centerZ, short[] shortArray, int x, int y, int z, boolean village)
	{
		float rDiv = (rand.nextFloat() * 0.05F + 1.0F);
		float hDiv = (rand.nextFloat() * 0.05F + 1.0F);

		generateTrunk(seed, centerX, centerZ, shortArray, x, y, z, (int)(baseRadius * rDiv), (int)(baseHeight * hDiv), village);
		generateCanopy(seed, centerX, centerZ, shortArray, x, y + (int)(baseHeight * hDiv), z, (int)(baseCanopyRadius * rDiv), (int)(baseCanopyHeight * hDiv));
		if(village)
			genVillage(world, centerX, centerZ, shortArray, x, y + (int)(baseHeight * hDiv) - villageHeight, z, (int)(villageRadius * rDiv));
	}

	@Override
	public boolean shouldGenerate(int centerX, int centerZ) 
	{
		return true;
	}

	public void genVillage(World world, int centerX, int centerZ, short[] shortArray, int x, int y, int z, int r)
	{
		int cX = centerX * 16 + 8;
		int cZ = centerZ * 16 + 8;

		for(float i = x; i < x + 16; ++i)
		{
			for(float j = y; j <= y && y < 256; ++j)
			{
				for(float k = z; k < z + 16; ++k)
				{
					int key = hashCoord((int)(i - x), (int)j, (int)(k - z));
					float bx = (i - cX);
					float by = (j - y);
					float bz = (k - cZ);
					if(key < 65536)
					{
						if (bx * bx + bz * bz <= (platformRadius * platformRadius) && (bx * bx + bz * bz >= (9 + spiralWidth) * (9 + spiralWidth)))
						{
							shortArray[key] = (short) Block.planks.blockID; // Center platform
						}
						else if((bx - (platformRadius + resR + resDist)) * (bx - (platformRadius + resR + resDist)) + (bz) * (bz) <= (resR * resR))
						{
							shortArray[key] = (short) Block.planks.blockID; // +x residential
						}
						else if((bx + (platformRadius + industrialR + indDist)) * (bx + (platformRadius + industrialR + indDist)) + (bz) * (bz) <= (industrialR * industrialR))
						{
							shortArray[key] = (short) Block.planks.blockID; // -x industrial
						}
						else if((bz - (platformRadius + marketR + mDist)) * (bz - (platformRadius + marketR + marketR)) + (bx) * (bx) <= (marketR * marketR))
						{
							shortArray[key] = (short) Block.planks.blockID; // +z market
						}
						else if((bz + (platformRadius + farmR + fDist)) * (bz + (platformRadius + farmR + fDist)) + (bx) * (bx) <= (farmR * farmR))
						{
							shortArray[key] = (short) Block.planks.blockID; // -z farm
						}
						else 
						{
							float abx = Math.abs(bx);
							float abz = Math.abs(bz);
							if(abz <= 1)
							{
								if(abx >= platformRadius && (bx <= (platformRadius + resDist)) && (bx >= -(platformRadius + indDist)))
								{
									shortArray[key] = (short) Block.planks.blockID; // residential and industrial walkways
								}
							}
							else if(abx <= 1)
							{
								if(abz >= platformRadius && (bz <= (platformRadius + mDist)) && (bz >= -(platformRadius + fDist)))
								{
									shortArray[key] = (short) Block.planks.blockID; // market and farm walkways
								}
							}
						}
					}
				}
			}
		}
	}
}